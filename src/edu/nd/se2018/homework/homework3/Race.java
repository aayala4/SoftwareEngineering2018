package edu.nd.se2018.homework.homework3;

import java.util.ArrayList;

/**
 * 
 * @author Alex
 * Homework 03
 * Race class which runs the race and determines the winner
 * 
 */

public class Race
{
	// Holds the participant horses
	private ArrayList<Horse> horses;
	
	// Constructor
	Race()
	{
		horses = new ArrayList<Horse>();
	}
	
	// Adds a Horse to the race
	public void enrollHorse(String horseName, double maxSpeed, RaceStrategy strategy)
	{
		strategy.setPeriod(1.0/60.0);
		horses.add(new Horse(strategy, maxSpeed, horseName));
	}
	
	// Continuously updates the distance traveled for the horses until someone wins (runs at least 10.0 miles). Returns the winner.
	public String runRace()
	{
		boolean raceFinished = false;
		double distanceRun;
		String winner = "";
		while(!raceFinished)
		{
			printRaceUpdate();
			raceFinished = true;
			for(Horse h: horses)
			{
				distanceRun = h.updateDistanceTraveled();
				if (distanceRun < 10.0)
				{
					raceFinished = false;
				}
				else
				{
					raceFinished = true;
					winner = h.getName();
					break;
				}
			}
		}
		return winner;
	}
	
	// Prints how far each horse has traveled so far
	public void printRaceUpdate()
	{
		for(Horse h: horses)
		{
			System.out.println(h.getName() + " has run " + h.getDistanceTraveled() + " miles");
		}
		System.out.print("\n");
	}
}
