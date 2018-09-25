package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import edu.nd.sarec.railwaycrossing.model.infrastructure.Direction;
import edu.nd.sarec.railwaycrossing.model.infrastructure.Road;
import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;


/**
 * Very basic car factory.  Creates the car and registers it with the crossing gate and the car in front of it.
 * @author jane
 * Modified by Alex Ayala
 *
 */
public class CarFactory {
	
	private Collection<CrossingGate> gates = null;
	private Car previousCar = null;
	private ArrayList<Car> cars = new ArrayList<Car>();
	private Direction direction = Direction.SOUTH;
	private Point location;
	private Road turnRoad = null;
	private Road currentRoad = null;
	private boolean canBuild = true;
	
	public CarFactory(){}
	
	public CarFactory(Direction direction, Point location, Collection<CrossingGate> gates, Road turnRoad, Road currentRoad, boolean canBuild) {
		this.direction = direction;
		this.location = location;
		this.gates = gates;
		this.turnRoad = turnRoad;
		this.canBuild = canBuild;
		this.currentRoad = currentRoad;
	}
	
	
	// Most code here is to create random speeds
	public Car buildCar(){
		if ((previousCar == null || location.y < previousCar.getVehicleY()-100) && canBuild){
			Car car = new Car(location.x,location.y, direction, currentRoad);	
			double speedVariable = (Math.random() * 10)/10;
			car.setSpeed((2-speedVariable)*1.5); 
			
			// All cars created by this factory must be aware of crossing gates in the road
			for(CrossingGate gate: gates){
				gate.addObserver(car);
				if(gate != null && gate.getTrafficCommand()=="STOP")
					car.setGateDownFlag(true);
			}
			
			// Each car must observe the car in front of it so it doesn't collide with it.
			if (previousCar != null) {
				// Check if previousCar still belongs to the road
				if(cars.contains(previousCar)) {
					previousCar.addObserver(car);
				}
			}
			previousCar = car;
			
			cars.add(car);
			return car;
		} else 
			return null;
	}

	// We will get a concurrency error if we try to delete cars whilst iterating through the array list
	// so we perform this in two stages.
	// 1.  Loop through the list and identify which cars are off the screen.  Add them to 'toDelete' array.
	// 2.  Iterate through toDelete and remove the cars from the original arrayList.
	// We also see if cars will turn off the current road. If so, they will be removed from the list as well
	// and be added to the road they turn onto.
	public ArrayList<Car> removeOffScreenCars() {
		// Removing cars from the array list.
		ArrayList<Car> toDelete = new ArrayList<Car>();
		ArrayList<Car> toTurn = new ArrayList<Car>();
		for(Car car: cars){
			car.move();
			if(turnRoad != null) {
				if(car.tryTurn(turnRoad)) {
					toTurn.add(car);
				}
			} else if (car.offScreen()) {
				toDelete.add(car);
			}
			
		} 
		
		// For every car that turns, update the observers on the Road it's leaving
		for (Car car: toTurn) {
			car.deleteObservers();
			int index = cars.indexOf(car);
			if (index -1 >= 0) {
				if (index + 1 < cars.size()) {
					cars.get(index-1).deleteObserver(car);
					cars.get(index-1).addObserver(cars.get(index+1));	
				}
			} else {
				if (index + 1 < cars.size()) {
					cars.get(index+1).setLeadCarPoint(null);
				}
			}
			if(turnRoad != null) {
				turnRoad.getCarFactory().insertCar(car);
			}
		}
		
		for (Car car: toTurn) {
			cars.remove(car);
		}
		
		for (Car car: toDelete)
			cars.remove(car);
		return toDelete;
	}
	
	// Inserts a car into the list of cars on the road
	public void insertCar(Car c) {
		// All cars created by this factory must be aware of crossing gates in the road
		for(CrossingGate gate: gates){
			gate.addObserver(c);
			if(gate != null && gate.getTrafficCommand()=="STOP")
				c.setGateDownFlag(true);
		}
		int index = -1;
		
		// Tries to find which car to be inserted behind
		for(Car car: cars) {
			if(direction == Direction.SOUTH) {
				if (c.getVehicleY() > car.getVehicleY()) {
					index = cars.indexOf(car);
					if (index -1 >= 0) {
						cars.get(index-1).deleteObserver(car);
						cars.get(index-1).addObserver(c);	
						
					}
					break;
				}
			} else if(direction == Direction.NORTH) {
				if (c.getVehicleY() < car.getVehicleY()) {
					index = cars.indexOf(car);
					if (index -1 >= 0) {
						cars.get(index-1).deleteObserver(car);
						cars.get(index-1).addObserver(c);	
					}
					break;
				}
			} else if(direction == Direction.EAST) {
				if (c.getVehicleX() > car.getVehicleX()) {
					index = cars.indexOf(car);
					if (index -1 >= 0) {
						cars.get(index-1).deleteObserver(car);
						cars.get(index-1).addObserver(c);	
					}
					break;
				}
			} else if(direction == Direction.WEST) {
				if (c.getVehicleX() < car.getVehicleX()) {
					index = cars.indexOf(car);
					if (index -1 >= 0) {
						cars.get(index-1).deleteObserver(car);
						cars.get(index-1).addObserver(c);	
					}
					break;
				}
			}
		}
			
		// Each car must observe the car in front of it so it doesn't collide with it.
		// If it's the only car, then no need to set observers.
		if(index < 0) {
			if(!cars.isEmpty()) {
				cars.get(cars.size()-1).addObserver(c);
			}
			cars.add(c);
		} else {
			c.addObserver(cars.get(index));
			cars.add(index, c);
		}
	}
}
