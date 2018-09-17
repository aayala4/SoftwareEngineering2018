package edu.nd.se2018.homework.homework4.ColumbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.lang.Math;

/**
 * 
 * @author Alex Ayala
 * Homework 04
 * This PirateShip class implements Observer to listen for and chase Christopher Columbus' ship
 * around the ocean.
 *
 */

public class PirateShip implements Observer
{
	Point shipLocation;
	Point pirateLocation;
	OceanMap oceanMap;
	OceanExplorer oceanExplorer;
	
	// Constructor
	PirateShip(Point location, OceanMap map, OceanExplorer explorer)
	{
		pirateLocation = location;
		oceanMap = map;
		oceanExplorer = explorer;
	}
	
	// Runs this method when it observes that the Ship moves. Attempts moving closer and
	// has the OceanExplorer redraw the pirate ships
	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof Ship)
		{
			shipLocation = (Point) arg;
			getCloser();
			oceanExplorer.redrawPirateShips();
		}
	}
	
	// Get the location of this pirate ship
	public Point getPirateShipLocation()
	{
		return pirateLocation;
	}
	
	// Set the location for this pirate ship
	public void setPirateShipLocation(Point location)
	{
		pirateLocation = location;
	}
	
	// Move as close to the Ship as possible
	public void getCloser()
	{
		double smallestDist;
		int smallestDistDirection;
		double stayDist = Math.hypot(Math.abs(pirateLocation.x-shipLocation.x), Math.abs(pirateLocation.y-shipLocation.y));
		double eastDist = Math.hypot(Math.abs(pirateLocation.x+1-shipLocation.x), Math.abs(pirateLocation.y-shipLocation.y));
		double westDist = Math.hypot(Math.abs(pirateLocation.x-1-shipLocation.x), Math.abs(pirateLocation.y-shipLocation.y));;
		double southDist = Math.hypot(Math.abs(pirateLocation.x-shipLocation.x), Math.abs(pirateLocation.y+1-shipLocation.y));;
		double northDist = Math.hypot(Math.abs(pirateLocation.x-shipLocation.x), Math.abs(pirateLocation.y-1-shipLocation.y));;
	
		// Prevents pirate ship from going off the edges, onto islands, or other pirate
		// ships
		if(pirateLocation.x+1 < oceanMap.dimensions && pirateLocation.x+1 >= 0)
		{
			if (oceanMap.oceanGrid[pirateLocation.x+1][pirateLocation.y] != 0)
			{
				eastDist = Double.POSITIVE_INFINITY;
			}
		}
		else
		{
			eastDist = Double.POSITIVE_INFINITY;
		}
		
		if(pirateLocation.x-1 < oceanMap.dimensions && pirateLocation.x-1 >= 0)
		{
			if (oceanMap.oceanGrid[pirateLocation.x-1][pirateLocation.y] != 0)
			{
				westDist = Double.POSITIVE_INFINITY;
			}
		}
		else
		{
			westDist = Double.POSITIVE_INFINITY;
		}
		
		if(pirateLocation.y+1 < oceanMap.dimensions && pirateLocation.y+1 >= 0)
		{
			if (oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y+1] != 0)
			{
				southDist = Double.POSITIVE_INFINITY;
			}
		}
		else
		{
			southDist = Double.POSITIVE_INFINITY;
		}
		
		if (pirateLocation.y-1 < oceanMap.dimensions && pirateLocation.y-1 >= 0)
		{
			if (oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y-1] != 0)
			{
				northDist = Double.POSITIVE_INFINITY;
			}
		}
		else
		{
			northDist = Double.POSITIVE_INFINITY;
		}
		
		// Determine which direction brings the pirate ship closest to Columbus' ship
		smallestDist = stayDist;
		smallestDistDirection = 0;
		if(eastDist < smallestDist)
		{
			smallestDist = eastDist;
			smallestDistDirection = 1;
		}
		if(westDist < smallestDist)
		{
			smallestDist = westDist;
			smallestDistDirection = 2;
		}
		if(northDist < smallestDist)
		{
			smallestDist = northDist;
			smallestDistDirection = 3;
		}
		if(southDist < smallestDist)
		{
			smallestDist = southDist;
			smallestDistDirection = 4;
		}
		
		// Update the values of the pirate ship's location and the map's ocean grid
		switch(smallestDistDirection)
		{
			case 1:
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 0;
				pirateLocation.setLocation(pirateLocation.x+1, pirateLocation.y);
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 2;
				break;
			case 2:
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 0;
				pirateLocation.setLocation(pirateLocation.x-1, pirateLocation.y);
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 2;
				break;
			case 3:
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 0;
				pirateLocation.setLocation(pirateLocation.x, pirateLocation.y-1);
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 2;
				break;
			case 4:
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 0;
				pirateLocation.setLocation(pirateLocation.x, pirateLocation.y+1);
				oceanMap.oceanGrid[pirateLocation.x][pirateLocation.y] = 2;
				break;
			default:
				break;
		}
	}

}