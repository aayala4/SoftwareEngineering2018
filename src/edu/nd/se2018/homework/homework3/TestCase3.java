package edu.nd.se2018.homework.homework3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** 
 * 
 * @author Alex Ayala
 * Tests Horse class' methods
 *
 */

class TestCase3
{

	@Test
	void test()
	{
		RaceStrategy strategy1 = new SteadyRunStrategy();
		RaceStrategy strategy2 = new SlowStartStrategy();
		Horse horse = new Horse(strategy1, 10, "Blake");
		assert(horse.getMaxSpeed()) == 10;
		assert(horse.getDistanceTraveled() == 0);
		assert(horse.getStrategy() == strategy1);
		assert(horse.getName() == "Blake");

		horse.setStrategy(strategy2);
		horse.setMaxSpeed(20);
		horse.setDistanceTraveled(5);
		horse.setName("Ruby");
		assert(horse.getMaxSpeed() == 20);
		assert(horse.getDistanceTraveled() == 5);
		assert(horse.getStrategy() == strategy2);
		assert(horse.getName() == "Ruby");
		
		horse.updateDistanceTraveled();
		assert(horse.getDistanceTraveled() == 20);
	
	}

}
