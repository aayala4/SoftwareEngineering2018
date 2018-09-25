package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the train entity object
 * @author jane
 * Modified by Alex Ayala
 *
 */
public class Train extends Observable implements IVehicle{
	private double currentX = 0;
	private double currentY = 0;
	private double originalX = 0;
	private Image img;
	private ImageView imgView;
	private int trainLength = 35;
	private String direction = "";
	private String name = "";
	
	public Train(int x, int y, String dir, String name){
		this.currentX = x;
		this.currentY = y;
		originalX = x;
		img = new Image("images/Train.PNG",120,trainLength,false,false);
		imgView = new ImageView(img);
		imgView.setX(currentX);
		imgView.setY(currentY);
		
		// Flip the image to face east if the direction is east
		if(dir == "east") {
			imgView.setScaleX(-1);
		}
		
		this.direction = dir;
		this.name = name;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	
	public double getVehicleY(){
		return currentY;
	}
	
	public String getName() {
		return name;
	}
	
	// Train moves west if its direction is "west." East otherwise.
	public void move(){
		if (direction == "west") {
			currentX-=2;
		} else {
			currentX+=2;
		}
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}
	
	// Checks if the train is off screen
	public boolean offScreen(){
		if (direction == "west") {
			if (currentX < -200)
				return true;
			else
				return false;				
		} else {
			if (currentX > 1400)
				return true;
			else
				return false;
		}	
	}
	
	public void reset(){
		currentX = originalX;
	}

	//@Override
	public Node getImageView() {
		return imgView;
	}
}