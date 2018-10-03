package edu.nd.se2018.homework.chipschallenge;

import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChipsChallengeMap implements Observer
{
	private int[][] grid;
	private int dimensions;
	private int scale;
	private ArrayList<Key> keys;
	private ArrayList<KeyWall> keyWalls;
	
	ChipsChallengeMap(int dimensions, int scale)
	{
		this.dimensions = dimensions;
		grid = new int[dimensions][dimensions];
		this.scale = scale;
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				grid[i][j] = 0;
			}
		}
		keys = new ArrayList<Key>();
		keyWalls = new ArrayList<KeyWall>();
	}
	
	public void generateLevelOne(ObservableList<Node> root, Chip chip)
	{
		grid[5][5] = 1;
		grid[8][8] = 1;
		grid[20][20] = 2;
		grid[15][15] = 2;
		grid[10][20] = 2;
		Key k = new Key("red", 5, 5, scale);
		root.add(k.getImageView());
		keys.add(k);
		k = new Key("blue", 8, 8, scale);
		root.add(k.getImageView());
		keys.add(k);
		KeyWall kw = new KeyWall("red", 20, 20, scale);
		root.add(kw.getImageView());
		keyWalls.add(kw);
		kw = new KeyWall("red", 15, 15, scale);
		root.add(kw.getImageView());
		keyWalls.add(kw);
		kw = new KeyWall("blue", 10, 20, scale);
		root.add(kw.getImageView());
		keyWalls.add(kw);
	}
	
	public void drawMap(ObservableList<Node> root)
	{		
		// Draw the appropriate ocean and island tiles. Also mark off the grid
		// for pirate ship, island, and ocean tiles
		for(int x = 0; x < dimensions; x++)
		{
			for(int y = 0; y < dimensions; y++)
			{
				Image tile = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
				ImageView tileView = new ImageView(tile);
				tileView.setX(x*scale);
				tileView.setY(y*scale);
				root.add(tileView);
			}
		}
	}
	
	public int[][] getGrid()
	{
		return grid;
	}
	
	public KeyWall findKeyWallAtLocation(Point p)
	{
		for(KeyWall kw : keyWalls)
		{
			if ((int)p.distance(kw.getLocation()) == 1)
			{
				return kw;
			}
		}
		return null;
	}
	
	public Key findKeyAtLocation(Point p)
	{
		for(Key kw : keys)
		{
			if (kw.getLocation().equals(p))
			{
				return kw;
			}
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Chip)
		{
			Chip c = (Chip)o;
			int x = (int)c.getLocation().getX();
			int y = (int)c.getLocation().getY();
			if(c.getBlockingWallType() == 2)
			{
				KeyWall kw = findKeyWallAtLocation(c.getLocation());
				if(kw != null)
				{
					if(kw.containsMatchingKey(c.getKeys()))
					{
						keyWalls.remove(kw);
						kw.setOpened(true);
						kw.getImageView().setVisible(false);
						grid[(int)kw.getLocation().getX()][(int)kw.getLocation().getY()] = 0;
					}
				}
			}
			else if(grid[x][y] == 1)
			{
				Key k = findKeyAtLocation(c.getLocation());
				if(k != null)
				{
					c.acquireKey(k);
					keys.remove(k);
					k.setAcquired(true);
					k.getImageView().setVisible(false);
					grid[x][y] = 0;
				}
			}
		}			
	}
}
