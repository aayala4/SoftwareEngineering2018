package edu.nd.se2018.homework.chipschallenge;

import javafx.collections.ObservableList;
import java.io.*;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Alex Ayala
 * Singleton ChipsChallengeMap class. Manages loading the map and observes a Chip object to manage key/chip collection and
 * door unlocking
 *
 */

public class ChipsChallengeMap implements Observer
{
	private String[][] grid;
	private int dimensions;
	private int scale;
	private ArrayList<Collectible> collectibles;
	private ArrayList<Unlockable> unlockables;
	private String levelFile;
    private static ChipsChallengeMap singleInstance = null;
    private ObservableList<Node> root = null;
	
    // Constructor 
	private ChipsChallengeMap(int dimensions, int scale, ObservableList<Node> root)
	{
		levelFile = "level 1";
		this.dimensions = dimensions;
		grid = new String[dimensions][dimensions];
		this.scale = scale;
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				grid[i][j] = "0";
			}
		}
		collectibles = new ArrayList<Collectible>();
		unlockables = new ArrayList<Unlockable>();
		this.root = root;
	}
	
	// Gets the existing instance if there is one. Otherwise, return a new one
	// with the given dimensions, scale, and root
	public static ChipsChallengeMap getInstance(int dimensions, int scale, ObservableList<Node> root)
	{
		if(singleInstance == null)
		{
			singleInstance = new ChipsChallengeMap(dimensions, scale, root);
		}
		return singleInstance;
	}

	// Gets the existing instance if there is one. Otherwise, return a new one
	// with the given 25, 25, and null as the dimensions, scale, and root
	public static ChipsChallengeMap getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new ChipsChallengeMap(25, 25, null);
		}
		return singleInstance;
	}
	
	/*
	 * Initialize the map based on text from map file
	 * 
	 * 0: Walkable Tile
	 * r: Red Key
	 * b: Blue Key
	 * g: Green Key
	 * y: Yellow Key
	 * R: Red KeyWall
	 * B: Blue KeyWall
	 * G: Green KeyWall
	 * Y: Yellow KeyWall
	 * E: Exit Portal
	 * C: Chip
	 * c: Collectable Chip
	 * W: Wall
	 * w: Chip Wall
	 */
	public void loadLevel(Chip chip) throws IOException
	{
		root.clear();
		collectibles.clear();
		unlockables.clear();
		this.readLevel();
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				// Images for tiles without a related class
				Image tile = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
				ImageView tileView;
				Image wall = new Image("images/chip/textures/wall.png", scale, scale, true, true);
				ImageView wallView;
				Image portal = new Image("images/chip/textures/portal.png", scale, scale, true, true);
				ImageView portalView;
				Collectible c;
				Unlockable u;
				
				// Create objects for unlockables and collectibles
				// and move Chip to new starting spot
				switch(grid[i][j])
				{
					case "r":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						c = new Key("red", j, i, scale);
						root.add(c.getImageView());
						collectibles.add(c);
						break;
					case "y":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						c = new Key("yellow", j, i, scale);
						root.add(c.getImageView());
						collectibles.add(c);
						break;
					case "g":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						c = new Key("green", j, i, scale);
						root.add(c.getImageView());
						collectibles.add(c);
						break;		
					case "b":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						c = new Key("blue", j, i, scale);
						root.add(c.getImageView());
						collectibles.add(c);
						break;
					case "R":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						u = new KeyWall("red", j, i, scale);
						root.add(u.getImageView());
						unlockables.add(u);
						break;
					case "Y":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						u = new KeyWall("yellow", j, i, scale);
						root.add(u.getImageView());
						unlockables.add(u);
						break;
					case "G":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						u = new KeyWall("green", j, i, scale);
						root.add(u.getImageView());
						unlockables.add(u);
						break;
					case "B":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						u = new KeyWall("blue", j, i, scale);
						root.add(u.getImageView());
						unlockables.add(u);
						break;
					case "C":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);
						root.remove(chip.getCurrentImageView());
						chip.emptyPockets();
						chip.setLocation(j, i);
						root.add(chip.getCurrentImageView());
						grid[i][j] = "0";
						break;
					case "c":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						c = new CollectibleChip(j, i, scale);
						root.add(c.getImageView());
						collectibles.add(c);
						break;
					case "E":
						portalView = new ImageView(portal);
						portalView.setX(j*scale);
						portalView.setY(i*scale);
						root.add(portalView);	
						break;
					case "W":
						wallView = new ImageView(wall);
						wallView.setX(j*scale);
						wallView.setY(i*scale);
						root.add(wallView);		
						break;
					case "w":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);	
						u = new CollectibleChipBlocker(j, i, scale);
						root.add(u.getImageView());
						unlockables.add(u);
						break;
					case "0":
						tileView = new ImageView(tile);
						tileView.setX(j*scale);
						tileView.setY(i*scale);
						root.add(tileView);		
						break;
					default:
						break;
				}
			}
		}
		giveChipCountToChipBlockers();
	}

	// Check how many collectible chips are on the map total and let the chip blockers know
	public void giveChipCountToChipBlockers()
	{
		int chips = 0;
		for(Collectible c: collectibles)
		{
			if(c instanceof CollectibleChip)
			{
				chips++;
			}
		}
		for(Unlockable u: unlockables)
		{
			if(u instanceof CollectibleChipBlocker)
			{
				((CollectibleChipBlocker) u).setChipsNeeded(chips);
			}
		}
	}
	
	// Read text from map file into grid
	public void readLevel() throws IOException
	{
		File file;
		if (levelFile.equals("level 1"))
		{
			file = new File("src/edu/nd/se2018/homework/chipschallenge/level1.txt");
			levelFile = "level 2";
			System.out.println("Playing Level 1");
		}
		else
		{
			file = new File("src/edu/nd/se2018/homework/chipschallenge/level2.txt");
			levelFile = "level 1";
			System.out.println("Playing Level 2");
		}
		FileInputStream fi = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fi));
		 
		String line = null;
		int i = 0;
		while ((line = br.readLine()) != null)
		{
			grid[i] = line.trim().split(" ");
			i++;
		}
	 
		br.close();
	}
	
	// Return the grid
	public String[][] getGrid()
	{
		return grid;
	}
	
	// Find an unlockable one unit away from point p if there is one
	public Unlockable findUnlockableAtLocation(Point p)
	{
		for(Unlockable u : unlockables)
		{
			if ((int)p.distance(u.getLocation()) == 1)
			{
				return u;
			}
		}
		return null;
	}
	
	// Find a collectible at point p if there is one
	public Collectible findCollectibleAtLocation(Point p)
	{
		for(Collectible c : collectibles)
		{
			if (c.getLocation().equals(p))
			{
				return c;
			}
		}
		return null;
	}

	// Called when Chip moves. This manages chip/key collection, door unlocking, and reseting the level
	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Chip)
		{
			Chip c = (Chip)o;
			int x = (int)c.getLocation().getX();
			int y = (int)c.getLocation().getY();
			
			// The case where Chip was blocked by an unlockable
			// Removes the unlockable from the map
			if(c.getBlockingWallType().equals("U"))
			{
				Unlockable u = findUnlockableAtLocation(c.getLocation());
				if(u != null)
				{
					ArrayList<Collectible> chipsCollectibles = null;
 					if(u instanceof KeyWall)
					{
						chipsCollectibles = c.getKeys();
					}
					else
					{
						chipsCollectibles = c.getChips();
					}
					if(u.tryUnlocking(chipsCollectibles))
					{
						unlockables.remove(u);
						u.setOpened(true);
						u.getImageView().setVisible(false);
						grid[(int)u.getLocation().getY()][(int)u.getLocation().getX()] = "0";
					}
				}
			}
			// Case where Chip is standing on a collectible.
			// Removes the collectible from the map and adds it to Chip's collection
			else if(grid[y][x].equals("r") || grid[y][x].equals("y") || grid[y][x].equals("g") || grid[y][x].equals("b") || 
					grid[y][x].equals("c"))
			{
				Collectible collect = findCollectibleAtLocation(c.getLocation());
				if(collect != null)
				{
					c.pickUp(collect);
					collectibles.remove(collect);
					collect.setAcquired(true);
					collect.getImageView().setVisible(false);
					grid[y][x] = "0";
				}
			}
			// Case where Chip is standing on the portal. Loads the next level.
			else if(grid[y][x].equals("E"))
			{
				try
				{
					loadLevel(c);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}			
	}
}
