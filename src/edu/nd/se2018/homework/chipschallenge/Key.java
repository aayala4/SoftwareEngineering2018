package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Key
{
	private String color;
	private boolean acquired;
	private ImageView keyView;
	private Point location;
	
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
		keyView = new ImageView(img);
		keyView.setX(x*scale);
		keyView.setY(y*scale);
		location = new Point(x, y);
		acquired = false;
	}
	
	public ImageView getImageView()
	{
		return keyView;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public void setAcquired(boolean acq)
	{
		acquired = acq;
	}
	
	public boolean getAcquired()
	{
		return acquired;
	}
	
	public Point getLocation()
	{
		return location;
	}
}
