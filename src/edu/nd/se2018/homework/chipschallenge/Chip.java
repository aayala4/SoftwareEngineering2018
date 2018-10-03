package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Observable;
import java.util.ArrayList;

public class Chip extends Observable
{
	private Image chipImages[];
	private ImageView chipImageViews[];
	private ImageView currentImageView;
	private Point location;
	private int scale;
	private int blockingWallType = 0; // 2 for key wall, 3 for regular wall, 0 for others
	private ArrayList<Key> keys;
	
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
		keys = new ArrayList<Key>();
	}
	
	public void acquireKey(Key k)
	{
		keys.add(k);
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public ImageView getCurrentImageView()
	{
		return currentImageView;
	}
	
	public int getBlockingWallType()
	{
		return blockingWallType;
	}
		
	public void goRight(int dimensions, int[][] grid)
	{
		double x = location.getX();
		double y = location.getY();
		currentImageView = chipImageViews[2];
		currentImageView.setX(x*scale);
		currentImageView.setY(y*scale);
		x += 1;
		if(x < dimensions)
		{
			if(grid[(int)x][(int)y] == 2)
			{
				blockingWallType = 2;
			}
			else
			{
				location.setLocation(x, y);
				currentImageView.setX(x*scale);
				currentImageView.setY(y*scale);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void goLeft(int dimensions, int[][] grid)
	{
		double x = location.getX();
		double y = location.getY();
		currentImageView = chipImageViews[3];
		currentImageView.setX(x*scale);
		currentImageView.setY(y*scale);
		x -= 1;
		blockingWallType = 0;
		if(x >= 0)
		{
			if(grid[(int)x][(int)y] == 2)
			{
				blockingWallType = 2;
			}
			else
			{
				location.setLocation(x, y);
				currentImageView.setX(x*scale);
				currentImageView.setY(y*scale);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void goUp(int dimensions, int[][] grid)
	{
		double x = location.getX();
		double y = location.getY();
		currentImageView = chipImageViews[0];
		currentImageView.setX(x*scale);
		currentImageView.setY(y*scale);
		y -= 1;
		blockingWallType = 0;
		if(y >= 0)
		{
			if(grid[(int)x][(int)y] == 2)
			{
				blockingWallType = 2;
			}
			else
			{
				location.setLocation(x, y);
				currentImageView.setX(x*scale);
				currentImageView.setY(y*scale);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void goDown(int dimensions, int[][] grid)
	{
		double x = location.getX();
		double y = location.getY();
		currentImageView = chipImageViews[1];
		currentImageView.setX(x*scale);
		currentImageView.setY(y*scale);
		y += 1;
		blockingWallType = 0;
		if(y < dimensions)
		{
			if(grid[(int)x][(int)y] == 2)
			{
				blockingWallType = 2;
			}
			else
			{
				location.setLocation(x, y);
				currentImageView.setX(x*scale);
				currentImageView.setY(y*scale);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Key> getKeys()
	{
		return keys;
	}
}
