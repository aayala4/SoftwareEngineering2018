package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * EarlySpringStrategy which is a subclass of RaceStrategy
 * The jockey runs the horse at maximum speed for the first 2 miles and then reduces to 75%.
 *
 */

public class EarlySprintStrategy extends RaceStrategy
{

	// Computes the new distance traveled
	public double calculateDistanceTraveled(double maxSpeed, double distanceTraveled)
	{
		if (distanceTraveled < 2)
		{
			return distanceTraveled + period * maxSpeed;
		}
		else
		{
			return distanceTraveled + period * maxSpeed * .75;
		}
	}

}
