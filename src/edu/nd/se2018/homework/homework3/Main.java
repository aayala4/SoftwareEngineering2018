package edu.nd.se2018.homework.homework3;

/**
 * 
 * @author Alex Ayala
 * Homework 03
 * Runs a sample horse race
 *
 */

public class Main
{

	// Runs a sample race
	public static void main(String[] args)
	{
		Race race = new Race();
		race.enrollHorse("Sea Biscuit", 20, new EarlySprintStrategy());
		race.enrollHorse("Sunshine", 20, new SlowStartStrategy());
		race.enrollHorse("Mary", 20, new SteadyRunStrategy());
		race.enrollHorse("John", 15, new EarlySprintStrategy());
		race.enrollHorse("Lightning", 15, new SlowStartStrategy());
		race.enrollHorse("Thunder", 15, new SteadyRunStrategy());
		System.out.println(race.runRace() + " has won the race!");
	}

}
