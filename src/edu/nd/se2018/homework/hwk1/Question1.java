package edu.nd.se2018.homework.hwk1;
import java.util.HashSet;
import java.lang.Integer;

/**
 * 
 * Homework 2, Question 1 
 * 
 * @author Alex Ayala
 *
 */

public class Question1
{
		
	public Question1()
	{
	}
	
	// Given a vector of ints, sum all of their values;
	// however, if an int is duplicated, only include it in the sum one time. 
	public int getSumWithoutDuplicates(int[] numbers)
	{
		int sum = 0;
		
		// Holds numbers that have already been included in the sum
		HashSet<Integer> addedNumbers = new HashSet<Integer>();
		
		for(int i = 0; i < numbers.length; i++)
		{
			// Check if a number has already been added to the sum. If not,
			// add it to the sum and set of added numbers. Otherwise, skip it.
			if(!addedNumbers.contains(Integer.valueOf(numbers[i])))
			{
				addedNumbers.add(Integer.valueOf(numbers[i]));
				sum += numbers[i];
			}
		}
		return sum;	
	}
}
