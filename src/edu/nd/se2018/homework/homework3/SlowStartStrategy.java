package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * Homework 03
 * SlowStartStrategy which is a subclass of RaceStrategy
 * The jockey runs the horse at 75% of its speed for 6 miles, then 90% for 3 miles, and then 100% for 1 mile
 *
 */

public class SlowStartStrategy extends RaceStrategy
{
	
	// Computes the new distance traveled
	public double calculateDistanceTraveled(double maxSpeed, double distanceTraveled)
	{
		if (distanceTraveled < 6)
		{
			return distanceTraveled + period * maxSpeed * .75;
		}
		else if (distanceTraveled < 9)
		{
			return distanceTraveled + period * maxSpeed * .9;
		}
		else
		{
			return distanceTraveled + period * maxSpeed;
		}
	}

}
