package edu.nd.se2018.homework.homework4.ColumbusGame;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.Point;
import java.util.Random;
import java.util.HashSet;

/**
 * 
 * @author Alex Ayala
 * Homework 04
 * The OceanMap class handles drawing of tiles and updating the ocean grid.
 * It also initializes the random islands and pirate ships.
 *
 */

public class OceanMap
{
	int dimensions;
	int[][] oceanGrid;
	Random random = new Random();
	PirateShip[] pirateShips;
	OceanExplorer explorer;
	HashSet<Point> islands;
	HashSet<Point> pirateShipSet;
	
	// Constructor
	OceanMap(int dimensions, OceanExplorer explorer)
	{
		this.explorer = explorer;
		this.dimensions = dimensions;
		oceanGrid = new int[dimensions][dimensions];
	}
	
	// Randomly generates the locations for the islands and pirates
	public void generateIslandsAndPirates(Point shipLocation)
	{
		pirateShips = new PirateShip[2];
		islands = new HashSet<Point>();
		pirateShipSet = new HashSet<Point>();
		int islandCount = 0;
		int pirateShipCount = 0;
		
		// Add islands and make sure islands aren't put on top of each other
		// or Columbus' ship
		while(islandCount < 10)
		{
			Point p = new Point(random.nextInt(dimensions), random.nextInt(dimensions));
			if(!islands.contains(p) && !shipLocation.equals(p))
			{
				islands.add(p);
				islandCount++;
			}
			
		}
		
		// Make sure pirate ships aren't put on top of islands, other pirate ships, or
		// the Columbus' ship
		while(pirateShipCount < 2)
		{
			Point p = new Point(random.nextInt(dimensions), random.nextInt(dimensions));
			if(!pirateShipSet.contains(p) && !islands.contains(p) && !shipLocation.equals(p))
			{
				pirateShips[pirateShipCount] = new PirateShip(p, this, explorer);
				pirateShipSet.add(p);
				pirateShipCount++;
			}
			
		}
	}
	
	// Draw the ocean and islands for the map. Also update the grid to keep track of where
	// the islands, ocean, ship, and pirate ships are.
	public void drawMap(ObservableList<Node> root, int scale, Point shipLocation)
	{

		
		// Draw the appropriate ocean and island tiles. Also mark off the grid
		// for pirate ship, island, and ocean tiles
		for(int x = 0; x < dimensions; x++)
		{
			for(int y = 0; y < dimensions; y++)
			{
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				if(islands.contains(new Point(x,y)))
				{
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.GREEN);
					root.add(rect);
					oceanGrid[x][y] = 1;		
				}
				else if(pirateShipSet.contains(new Point(x,y)))
				{
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.PALETURQUOISE);
					root.add(rect);
					oceanGrid[x][y] = 2;			
				}
				else if(shipLocation.equals(new Point(x,y)))
				{
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.PALETURQUOISE);
					root.add(rect);
					oceanGrid[x][y] = 3;
				}
				else
				{
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.PALETURQUOISE);
					root.add(rect);
					oceanGrid[x][y] = 0;	
				}
			}
		}
		
	}
}
