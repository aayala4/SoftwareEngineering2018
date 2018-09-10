package edu.nd.se2018.homework.homework3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Alex Ayala
 * Tests the Race class' methods
 *
 */

class TestCase2
{

	@Test
	void test()
	{
		Race race = new Race();
		race.enrollHorse("Horse A", 10, new EarlySprintStrategy());
		race.enrollHorse("Horse B", 10, new SlowStartStrategy());
		race.enrollHorse("Horse C", 10, new SteadyRunStrategy());
		assert(race.runRace() == "Horse B");
	}

}
