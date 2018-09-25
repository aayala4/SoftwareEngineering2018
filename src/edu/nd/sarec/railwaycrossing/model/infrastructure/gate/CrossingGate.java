package edu.nd.sarec.railwaycrossing.model.infrastructure.gate;

import java.util.Observable;
import java.util.Observer;
import java.util.HashSet;

import edu.nd.sarec.railwaycrossing.model.vehicles.Train;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Context class for Crossing Gate
 * @author jane
 * Modified by Alex Ayala
 *
 */
public class CrossingGate extends Observable implements Observer{
	
	// Crossing Gate location and its left & right X bounds
	private int anchorX;
	private int anchorY;
	private int movingX;
	private int movingY;
	private int rightPoint;
	private int leftPoint;

	private IGateState gateClosed;
	private IGateState gateOpen;
	private IGateState gateClosing;
	private IGateState gateOpening;
	private IGateState currentGateState;
	private Line line; 
	private Pane root;
	private HashSet<String> observedTrains = new HashSet<String>();
	
	String gateName;
	
	public CrossingGate(){}
	
	public CrossingGate(int xPosition, int yPosition, String crossingGate){		
		anchorX = xPosition;
		anchorY = yPosition;
		movingX = anchorX;
		movingY = anchorY-60;
		rightPoint = anchorX+250;
		leftPoint = anchorX-250;
		
		// Gate elements
		line = new Line(anchorX, anchorY,movingX,movingY);
		line.setStroke(Color.RED);
	    line.setStrokeWidth(10);
		
		// Gate States
		gateClosed = new GateClosed(this);
		gateOpen = new GateOpen(this);
		gateOpening = new GateOpening(this);
		gateClosing = new GateClosing(this);
		currentGateState = gateOpen;
		gateName = crossingGate;
	}
	
	public Line getGateLine(){
		return line;
	}
	
	public void operateGate(){
		currentGateState.operate();
	}
	
	public void close(){
		if (movingY<anchorY){		
		    movingX+=1;
		    movingY+=1;
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		} else {
			currentGateState.gateFinishedClosing();
		}
	}
	
	public void open(){
		if (movingX>anchorX){
			movingX-=1;
			movingY-=1;		
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		}  else {
			currentGateState.gateFinishedOpening();
		}
	}
	
	// State getters and setters
	public IGateState getGateClosedState(){
		return gateClosed;
	}
	public IGateState getGateOpenState(){
		return gateOpen;
	}
	public IGateState getGateClosingState(){
		return gateClosing;
	}
	public IGateState getGateOpeningState(){
		return gateOpening;
	}
	
	public void setGateState(IGateState newState){
		currentGateState = newState;
		setChanged();
		notifyObservers();
	}
	
	public String getTrafficCommand(){
		return currentGateState.getTrafficAction();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Train){
			Train train = (Train)o;
			
			// Try adding the train to the set of observed trains
			if(observedTrains.add(train.getName())) {
				// If the train wasn't already in the list, check if it's within the bounds
				// for the gate. If so, change the gate state to have the gate opening. Other-
				// wise, remove the added train from the observed trains set
				if(train.getVehicleX() < rightPoint && train.getVehicleX() > leftPoint){
					currentGateState.approachStation();
				} else {
					observedTrains.remove(train.getName());
				}
			} else {
				// If the train was already in the set, check if it is no longer within the
				// bounds of the gate. If so, remove it from the set of observed trains and
				// check if the set is empty. If it is, change the gate state to have the
				// gate closing.
				if (train.getVehicleX() < leftPoint || train.getVehicleX() > rightPoint) {
					observedTrains.remove(train.getName());
					if(observedTrains.isEmpty()) {
						currentGateState.leaveStation();
					}
				}
			}
		}	
	}
}
