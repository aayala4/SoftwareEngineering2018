package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

/**
 * 
 * @author Alex Ayala
 * KeyWall class. This represents a KeyWall tile. These block Chip unless he has a key of the correct color
 *
 */

public class KeyWall extends Unlockable
{
	private String color;
	
	// Constructor. Determine image based on color
	KeyWall(String color, int x, int y, int scale)
	{
		this.color = color;
		Image img;
		if(color == "red")
		{
			img = new Image("images/chip/textures/redKeyWall.png", scale, scale, true, true);
		}
		else if(color == "blue")
		{
			img = new Image("images/chip/textures/blueKeyWall.png", scale, scale, true, true);
		}
		else if(color == "green")
		{
			img = new Image("images/chip/textures/greenKeyWall.png", scale, scale, true, true);
		}
		else
		{
			img = new Image("images/chip/textures/yellowKeyWall.png", scale, scale, true, true);
		}
		imageView = new ImageView(img);
		imageView.setX(x*scale);
		imageView.setY(y*scale);
		location = new Point(x, y);
		opened = false;
	}
	
	// Return color of KeyWall
	public String getColor()
	{
		return color;
	}
	
	// Check if the collectibles list has a key of the matching color. Returns true and removes the key if so. False otherwise.
	public boolean containsMatchingKey(ArrayList<Collectible> collectibles)
	{
		boolean removed = false;
		Key key = null;
		for(Collectible c : collectibles)
		{
			if(c instanceof Key)
			{
				Key k = (Key) c;
				if(k.getColor().equals(color))
				{
					key = k;
					removed = true;
					break;
				}
			}
		}
		if(removed)
		{
			collectibles.remove(key);
			System.out.println("Removed a " + color + " key");
		}
		return removed;
	}
	
	// Return whether or not unlocking was successful (if the list passed had a matching key)
	public boolean tryUnlocking(ArrayList<Collectible> collectibles)
	{
		return containsMatchingKey(collectibles);
	}
}
