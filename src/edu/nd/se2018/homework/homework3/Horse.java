package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * Homework 03
 * Horse class which acts as a participant in a Race. How it moves depends on the RaceStrategy it's using
 *
 */

public class Horse
{
	private RaceStrategy raceStrategy;
	private double distanceTraveled;
	private double maxSpeed;
	private String name;
	
	// Constructor
	Horse(RaceStrategy raceStrategy, double maxSpeed, String name)
	{
		this.raceStrategy = raceStrategy;
		this.maxSpeed = maxSpeed;
		distanceTraveled = 0;
		this.name = name;
	}
	
	// Get the current race strategy
	public RaceStrategy getStrategy()
	{
		return raceStrategy;
	}
	
	// Set the race strategy
	public void setStrategy(RaceStrategy raceStrategy)
	{
		this.raceStrategy = raceStrategy;
	}
	
	// Get the current max speed
	public double getMaxSpeed()
	{
		return maxSpeed;
	}
	
	// Set the max speed
	public void setMaxSpeed(double maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}
	
	// Update the distance traveled by using the current race strategy
	public double updateDistanceTraveled()
	{
		distanceTraveled = raceStrategy.calculateDistanceTraveled(maxSpeed, distanceTraveled);
		return distanceTraveled;
	}
	
	// Get the current distance traveled
	public double getDistanceTraveled()
	{
		return distanceTraveled;
	}
	
	// Set the distance traveled
	public void setDistanceTraveled(double distanceTraveled)
	{
		this.distanceTraveled = distanceTraveled;
	}
	
	// Get the current horse name
	public String getName()
	{
		return name;
	}
	
	// Set the horse's name
	public void setName(String name)
	{
		this.name = name;
	}
}
