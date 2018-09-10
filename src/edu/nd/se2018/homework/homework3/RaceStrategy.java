package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * Homework 03
 * Abstract class for race strategies
 *
 */

public abstract class RaceStrategy
{
	// Period in hours
	protected double period = 1;
	
	// Sub classes implement this method to calculate the new distance traveled
	abstract public double calculateDistanceTraveled(double maxSpeed, double distanceTraveled);	

	// Get current period
	public double getPeriod()
	{
		return period;
	}
	
	// Set period
	public void setPeriod(double period)
	{
		this.period = period;
	}
}
