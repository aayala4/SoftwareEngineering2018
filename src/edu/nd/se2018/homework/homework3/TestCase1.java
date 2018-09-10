package edu.nd.se2018.homework.homework3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Alex Ayala
 * Tests methods for the RaceStrategy class and its sub-classes
 *
 */

class TestCase1
{

	@Test
	void test()
	{
		RaceStrategy steady = new SteadyRunStrategy();
		RaceStrategy slow = new SlowStartStrategy();
		RaceStrategy sprint = new EarlySprintStrategy();
		
		assert(steady.getPeriod() == 1);
		steady.setPeriod(2);
		assert(steady.getPeriod() == 2);
		assert(steady.calculateDistanceTraveled(2, 5) == 8.2);

		assert(slow.calculateDistanceTraveled(2, 5) == 6.5);
		assert(slow.calculateDistanceTraveled(2, 7) == 8.8);
		assert(slow.calculateDistanceTraveled(2, 9) == 11);
		
		assert(sprint.calculateDistanceTraveled(2, 1) == 3);
		assert(sprint.calculateDistanceTraveled(2, 3) == 4.5);

	}

}
