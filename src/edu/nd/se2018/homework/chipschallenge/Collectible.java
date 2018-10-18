package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;

import javafx.scene.image.ImageView;

/**
 * 
 * @author Alex Ayala
 * Abstract collectible class. Represents an item that can be picked up
 *
 */

public abstract class Collectible
{
	protected boolean acquired;
	protected ImageView imageView;
	protected Point location;
	
	// Return the image view
	public ImageView getImageView()
	{
		return imageView;
	}

	// Acquire the collectible
	public void setAcquired(boolean acq)
	{
		acquired = acq;
		if(acquired)
		{
			showCollectedMessage();
		}
	}
	
	// Return whether collectible is acquired
	public boolean getAcquired()
	{
		return acquired;
	}
	
	// Return current location
	public Point getLocation()
	{
		return location;
	}
	
	// Display a collected message. Must be implemented by sub class
	public abstract void showCollectedMessage();

}
