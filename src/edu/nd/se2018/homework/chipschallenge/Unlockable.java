package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.ImageView;

/**
 * 
 * @author Alex Ayala
 * Abstract Unlockable class. Represents a tile that can be unlocked
 *
 */

public abstract class Unlockable
{
	protected ImageView imageView;
	protected Point location;
	protected boolean opened;

	// Set whether the unlockable is opened
	public void setOpened(boolean op)
	{
		opened = op;
	}
	
	// Get whether the unlockable is opened
	public boolean getOpened()
	{
		return opened;
	}
	
	// Get the current location
	public Point getLocation()
	{
		return location;
	}
	
	// Get the current image view
	public ImageView getImageView()
	{
		return imageView;
	}
	
	// Try to unlock the unlockable. Must be implemented by subclass
	public abstract boolean tryUnlocking(ArrayList<Collectible> cs);
}
