package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.util.Observable;
import java.util.Observer;

import edu.nd.sarec.railwaycrossing.model.infrastructure.Direction;
import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;
import edu.nd.sarec.railwaycrossing.view.CarImageSelector;
import edu.nd.sarec.railwaycrossing.model.infrastructure.Road;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.Random;

/**
 * Represents Car object
 * @author jane
 * Modified by Alex Ayala
 *
 */
public class Car extends Observable implements IVehicle, Observer{
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarY = -1;  // Current Y position of car directly infront of this one
	private double leadCarX = -1;
	private int endX = 1200;
	private int endY = 1200;
	private double speed = 0.5;
	private Direction direction = Direction.SOUTH;
	private Random randGenerator = new Random();
		
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 * @param direction initial direction the car is going
	 * @param r road the car is originally on
	 */
	public Car(int x, int y, Direction direction, Road r){
		this.currentX = x;
		this.currentY = y;
		this.direction = direction;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());
		if(r != null) {
			this.endX = r.getEndX();
			this.endY = r.getEndY();
		}
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	public double getVehicleY(){
		return currentY;
	}
	
	// Updates the position of the car
	public void move(){
		boolean canMove = true; 
		
		// First case.  Car is at the front of the stopping line.
		if (gateDown && getVehicleY() < 430 && getVehicleY()> 390)
			canMove = false;
		
		// Second case. Car is too close to other car.
		if (direction == Direction.SOUTH || direction == Direction.NORTH) {
			if (leadCarY != -1  && getDistanceToLeadCar() < 100)
				canMove = false;
		} else {
			if (leadCarX != -1  && getDistanceToLeadCar() < 100)
				canMove = false;
		}
		
		// Change position based on direction
		if (canMove){
			if(direction == Direction.SOUTH) {
				currentY+=speed;
				ivCar.setY(currentY);
			} else if(direction == Direction.NORTH) {
				currentY-=speed;
				ivCar.setY(currentY);
			} else if(direction == Direction.WEST) {
				currentX-=speed;
				ivCar.setX(currentX);
			} else {
				currentX += speed;
				ivCar.setX(currentX);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown){
		this.gateDown = gateDown;
	}
	
	public boolean offScreen(){
		if (currentY > 1020)
			return true;
		else
			return false;			
	}
		
	public void reset(){
		currentY = originalY;
	}
	
	public double getDistanceToLeadCar(){
		if (direction == Direction.SOUTH || direction == Direction.NORTH) {
			return Math.abs(leadCarY-getVehicleY());
		} else {
			return Math.abs(leadCarX-getVehicleX());
		}
	}
	
	// No car is in front of this car anymore on the current road, so
	// make the car no longer depend on the leading car's position
	public void removeLeadCar(){
		if(direction == Direction.SOUTH) {
			leadCarY = -1;
		} else if(direction == Direction.NORTH) {
			leadCarY = -1;
		} else if(direction == Direction.WEST) {
			leadCarX = -1;
		} else {
			leadCarX = -1;
		}
	}
	
	// Randomly try turning at a given road. If the car is at the end of the road it's on,
	// force a turn.
	public boolean tryTurn(Road r) {
		boolean turned = false;
		int chance = randGenerator.nextInt(100);
		if (chance < 20) {
			if(direction == Direction.SOUTH || direction == Direction.NORTH) {
				if (currentY > r.getStartY()-10 && currentY < r.getStartY()-1) {
					direction = r.getDirection();
					turned = true;
					endX = r.getEndX();
					endY = -1;
				}
			} else {
				if (currentX > r.getStartX()-10 && currentX < r.getStartX()-10) {
					direction = r.getDirection();
					turned = true;
					endX = -1;
					endY = r.getEndY();
				}
			}
		}
		
		if(direction == Direction.SOUTH) {
			if (currentY > endY-25) {
				direction = r.getDirection();
				turned = true;
				endX = r.getEndX();
				endY = -1;
			}
		} else if(direction == Direction.NORTH) {
			if (currentY < endY-25) {
				direction = r.getDirection();
				turned = true;
				endX = r.getEndX();
				endY = -1;
			}
		} else if(direction == Direction.WEST) {
			if (currentX < endX-25) {
				direction = r.getDirection();
				turned = true;
				endX = -1;
				endY = r.getEndY();
			}
		} else if(direction == Direction.EAST) {
			if (currentY > endX-25) {
				direction = r.getDirection();
				turned = true;
				endX = -1;
				endY = r.getEndY();
			}
		}
		
		return turned;
	}
	
	// Use the given Car as a lead car and set leadCarY and leadCarX accordingly
	public void setLeadCarPoint(Car c) {
		if (c != null) {
			if(direction == Direction.SOUTH) {
				leadCarY = c.getVehicleY();
			} else if(direction == Direction.NORTH) {
				leadCarY = c.getVehicleY();
			} else if(direction == Direction.WEST) {
				leadCarX = c.getVehicleX();
			} else {
				leadCarX = c.getVehicleX();
			}
		} else {
			if(direction == Direction.SOUTH) {
				leadCarY = -1;
			} else if(direction == Direction.NORTH) {
				leadCarY = -1;
			} else if(direction == Direction.WEST) {
				leadCarX = -1;
			} else {
				leadCarX = -1;
			}
		}
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car){
			// Check if the leading car is off the road, and if it isn't, use its
			// Y/X position. Otherwise, there is no leading car for this car.
			if(direction == Direction.SOUTH) {
				leadCarY = (((Car)o).getVehicleY());
				if (leadCarY > endY)
					leadCarY = -1;
			} else if(direction == Direction.NORTH) {
				leadCarY = (((Car)o).getVehicleY());
				if (leadCarY < endY)
					leadCarY = -1;
			} else if(direction == Direction.WEST) {
				leadCarX = (((Car)o).getVehicleX());
				if (leadCarX < endX)
					leadCarX = -1;
			} else {
				leadCarX = (((Car)o).getVehicleX());
				if (leadCarX > endX)
					leadCarX = -1;
			}
		}
			
		if (o instanceof CrossingGate){
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand()=="STOP")			
				gateDown = true;
			else
				gateDown = false;
					
		}				
	}	
}
