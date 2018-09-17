package edu.nd.se2018.homework.homework4.ColumbusGame;

import java.awt.Point;
import java.util.Observable;

/**
 * 
 * @author Alex Ayala
 * Homework 04
 * This Ship class extends Observable. This Ship class is used to control the
 * movement of Christopher Columbus' ship on the ocean grid. The Ship must stay on the
 * grid and cannot drive into spots occupied by islands or pirate ships.
 *
 */

public class Ship extends Observable
{
	Point location;
	OceanMap oceanMap;
	
	// Constructor
	Ship(OceanMap map)
	{
		location = new Point(0,0);
		oceanMap = map;
	}
	
	// Get location of ship
	public Point getShipLocation()
	{
		return location;
	}
	
	// Move east
	public void goEast(int dimensions)
	{
		double x = location.getX()+1;
		double y = location.getY();
		if(x < dimensions)
		{
			if(oceanMap.oceanGrid[(int)x][(int)y] == 0)
			{
				oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = 0;
				location.setLocation(x, y);
				oceanMap.oceanGrid[(int)x][(int)y] = 3;
				setChanged();
				notifyObservers(location);
			}
		}
	}
	
	// Move west
	public void goWest(int dimensions)
	{
		double x = location.getX()-1;
		double y = location.getY();
		if(x >= 0)
		{
			if(oceanMap.oceanGrid[(int)x][(int)y] == 0)
			{
				oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = 0;
				location.setLocation(x, y);
				oceanMap.oceanGrid[(int)x][(int)y] = 3;
				setChanged();
				notifyObservers(location);
			}
		}
	}
	
	// Move north
	public void goNorth(int dimensions)
	{
		double x = location.getX();
		double y = location.getY()-1;
		if(y >= 0)
		{
			if(oceanMap.oceanGrid[(int)x][(int)y] == 0)
			{
				oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = 0;
				location.setLocation(x, y);
				oceanMap.oceanGrid[(int)x][(int)y] = 3;
				setChanged();
				notifyObservers(location);
			}
		}
	}
	
	// Move south
	public void goSouth(int dimensions)
	{
		double x = location.getX();
		double y = location.getY()+1;
		if(y < dimensions)
		{
			if(oceanMap.oceanGrid[(int)x][(int)y] == 0)
			{
				oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = 0;
				location.setLocation(x, y);
				oceanMap.oceanGrid[(int)x][(int)y] = 3;
				setChanged();
				notifyObservers(location);
			}
		}
	}
}
