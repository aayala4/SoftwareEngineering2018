package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Observable;
import java.util.ArrayList;

/**
 * 
 * @author Alex Ayala
 * Chip class. This is the playable character
 *
 */

public class Chip extends Observable
{
	private Image chipImages[];
	private ImageView chipImageViews[];
	private ImageView currentImageView;
	private Point location;
	private int scale;

	// This represents what blocked Chip. U for unlockable, W for regular wall, 0 for none
	private String blockingWallType = "0";

	private ArrayList<Collectible> collectibles;

	// Constructor
	Chip(int scale)
	{
		this.scale = scale;
		chipImages = new Image[4];
		chipImages[0] = new Image("images/chip/textures/chipUp.png", scale, scale, true, true);
		chipImages[1] = new Image("images/chip/textures/chipDown.png", scale, scale, true, true);
		chipImages[2] = new Image("images/chip/textures/chipRight.png", scale, scale, true, true);
		chipImages[3] = new Image("images/chip/textures/chipLeft.png", scale, scale, true, true);
		chipImageViews = new ImageView[4];
		for(int i = 0; i < 4; i++)
		{
			chipImageViews[i] = new ImageView(chipImages[i]);
		}
		currentImageView = chipImageViews[0];
		location = new Point(0,0);
		currentImageView.setX(0);
		currentImageView.setY(0);
		collectibles = new ArrayList<Collectible>();
	}

	// Try unlocking the unlockable
	public boolean tryUnlocking(Unlockable u)
	{
		if(u != null)
		{
			if (u.tryUnlocking(collectibles))
			{
				u.setOpened(true);
				u.getImageView().setVisible(false);
				return true;
			}
		}
		return false;
	}

	// Clear the collectibles lists
	public void emptyPockets()
	{
		collectibles.clear();
	}

	// Set a location and update the image view accordingly
	public void setLocation(int x, int y)
	{
		location = new Point(x, y);
		currentImageView.setX(x*scale);
		currentImageView.setY(y*scale);
	}

	// Pick up either a key or chip
	public void pickUp(Collectible c)
	{
		if(c != null)
		{
			collectibles.add(c);
			c.setAcquired(true);
			c.getImageView().setVisible(false);
			c.showCollectedMessage();
		}
	}

	// Return the current location
	public Point getLocation()
	{
		return location;
	}

	// Return the current image view
	public ImageView getCurrentImageView()
	{
		return currentImageView;
	}

	// Return the blocking wall type
	public String getBlockingWallType()
	{
		return blockingWallType;
	}

	// Move to the right. If something blocks, set blockingWallType accordingly. Notify observers of movement
	public void goRight(int dimensions, String[][] grid, ObservableList<Node> root)
	{
		if(root != null)
		{
			double x = location.getX();
			double y = location.getY();
			root.remove(currentImageView);
			currentImageView = chipImageViews[2];
			currentImageView.setX(x*scale);
			currentImageView.setY(y*scale);
			x += 1;
			blockingWallType = "0";
			if(x < dimensions)
			{
				if(grid[(int)y][(int)x].equals("W"))
				{
					blockingWallType = "W";
				}
				else if(grid[(int)y][(int)x].equals("R") || grid[(int)y][(int)x].equals("B") || grid[(int)y][(int)x].equals("G") ||
						grid[(int)y][(int)x].equals("Y") || grid[(int)y][(int)x].equals("w"))
				{
					blockingWallType = "U";
				}
				else
				{
					location.setLocation(x, y);
					currentImageView.setX(x*scale);
					currentImageView.setY(y*scale);
				}
			}
			root.add(currentImageView);
			setChanged();
			notifyObservers();
		}
	}

	// Move to the left. If something blocks, set blockingWallType accordingly. Notify observers of movement
	public void goLeft(int dimensions, String[][] grid, ObservableList<Node> root)
	{
		if(root != null)
		{
			double x = location.getX();
			double y = location.getY();
			root.remove(currentImageView);
			currentImageView = chipImageViews[3];
			currentImageView.setX(x*scale);
			currentImageView.setY(y*scale);
			x -= 1;
			blockingWallType = "0";
			if(x >= 0)
			{
				if(grid[(int)y][(int)x].equals("W"))
				{
					blockingWallType = "W";
				}
				else if(grid[(int)y][(int)x].equals("R") || grid[(int)y][(int)x].equals("B") || grid[(int)y][(int)x].equals("G") ||
						grid[(int)y][(int)x].equals("Y") || grid[(int)y][(int)x].equals("w"))
				{
					blockingWallType = "U";
				}
				else
				{
					location.setLocation(x, y);
					currentImageView.setX(x*scale);
					currentImageView.setY(y*scale);
				}
			}
			root.add(currentImageView);
			setChanged();
			notifyObservers();
		}
	}

	// Move up. If something blocks, set blockingWallType accordingly. Notify observers of movement
	public void goUp(int dimensions, String[][] grid, ObservableList<Node> root)
	{
		if(root != null)
		{
			double x = location.getX();
			double y = location.getY();
			root.remove(currentImageView);
			currentImageView = chipImageViews[0];
			currentImageView.setX(x*scale);
			currentImageView.setY(y*scale);
			y -= 1;
			blockingWallType = "0";
			if(y >= 0)
			{
				if(grid[(int)y][(int)x].equals("W"))
				{
					blockingWallType = "W";
				}
				else if(grid[(int)y][(int)x].equals("R") || grid[(int)y][(int)x].equals("B") || grid[(int)y][(int)x].equals("G") ||
						grid[(int)y][(int)x].equals("Y") || grid[(int)y][(int)x].equals("w"))
				{
					blockingWallType = "U";
				}
				else
				{
					location.setLocation(x, y);
					currentImageView.setX(x*scale);
					currentImageView.setY(y*scale);
				}
			}
			root.add(currentImageView);
			setChanged();
			notifyObservers();
		}
	}

	// Move down. If something blocks, set blockingWallType accordingly. Notify observers of movement
	public void goDown(int dimensions, String[][] grid, ObservableList<Node> root)
	{
		if(root != null)
		{
			double x = location.getX();
			double y = location.getY();
			root.remove(currentImageView);
			currentImageView = chipImageViews[1];
			currentImageView.setX(x*scale);
			currentImageView.setY(y*scale);
			y += 1;
			blockingWallType = "0";
			if(y < dimensions)
			{
				if(grid[(int)y][(int)x].equals("W"))
				{
					blockingWallType = "W";
				}
				else if(grid[(int)y][(int)x].equals("R") || grid[(int)y][(int)x].equals("B") || grid[(int)y][(int)x].equals("G") ||
						grid[(int)y][(int)x].equals("Y") || grid[(int)y][(int)x].equals("w"))
				{
					blockingWallType = "U";
				}
				else
				{
					location.setLocation(x, y);
					currentImageView.setX(x*scale);
					currentImageView.setY(y*scale);
				}
			}
			root.add(currentImageView);
			setChanged();
			notifyObservers();
		}
	}

	// Return the collectibles list
	public ArrayList<Collectible> getCollectibles()
	{
		return collectibles;
	}

}
