package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class KeyWall
{
	private String color;
	private ImageView keyWallView;
	private Point location;
	private boolean opened;
	
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
		keyWallView = new ImageView(img);
		keyWallView.setX(x*scale);
		keyWallView.setY(y*scale);
		location = new Point(x, y);
		opened = false;
	}
	
	public ImageView getImageView()
	{
		return keyWallView;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public boolean containsMatchingKey(ArrayList<Key> keys)
	{
		boolean removed = false;
		Key key = null;
		for(Key k : keys)
		{
			if(k.getColor().equals(color))
			{
				key = k;
				removed = true;
				break;
			}
		}
		if(removed)
		{
			keys.remove(key);
		}
		return removed;
	}
	
	public void setOpened(boolean op)
	{
		opened = op;
	}
	
	public boolean getOpened()
	{
		return opened;
	}
}
