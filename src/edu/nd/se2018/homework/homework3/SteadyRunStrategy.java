package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * Homework 03
 * SteadyRunStrategy which is a subclass of RaceStrategy
 * The jockey runs the horse at 80% of its maximum speed throughout the entire race.
 *
 */

public class SteadyRunStrategy extends RaceStrategy
{

	// Computes the new distance traveled
	public double calculateDistanceTraveled(double maxSpeed, double distanceTraveled)
	{
		return distanceTraveled + period * .8 * maxSpeed;
	}

}
