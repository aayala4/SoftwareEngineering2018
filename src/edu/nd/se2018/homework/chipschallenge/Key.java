package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Alex Ayala
 * Key class. Represents key tiles and unlocks a KeyWall if they match in color.
 *
 */

public class Key extends Collectible
{
	private String color;
	
	// Constructor. Loads image based on passed color
	Key(String color, int x, int y, int scale)
	{
		this.color = color;
		Image img;
		if(color == "red")
		{
			img = new Image("images/chip/textures/redKey.png", scale, scale, true, true);
		}
		else if(color == "blue")
		{
			img = new Image("images/chip/textures/blueKey.png", scale, scale, true, true);
		}
		else if(color == "green")
		{
			img = new Image("images/chip/textures/greenKey.png", scale, scale, true, true);
		}
		else
		{
			img = new Image("images/chip/textures/yellowKey.png", scale, scale, true, true);
		}
		imageView = new ImageView(img);
		imageView.setX(x*scale);
		imageView.setY(y*scale);
		location = new Point(x, y);
		acquired = false;
	}
	
	// Return the key's color
	public String getColor()
	{
		return color;
	}

	// Display that the key is collected
	@Override
	public void showCollectedMessage()
	{
		System.out.println("Collected a " + color + " key");
	}
}
