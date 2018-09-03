package edu.nd.se2018.homework.hwk1;

/**
 * 
 * Homework 2, Question 3
 * 
 * @author Alex Ayala
 *
 */

public class Question3 {
	
	public Question3(){}	
	
	// Given a vector of integers (e.g. {1,2,3,4,3,2,6}), some sub-sequences in the vector may be mirrored.
    public int getMirrorCount(int[] numbers){
    	
    	// Case if vector passed was empty
    	if (numbers.length == 0)
    	{
    		return 0;
    	}
    	
    	// Find two midpoints
    	int point1 = (numbers.length-1)/2;
    	int point2 = numbers.length/2;
    	
    	int longestSequenceLength = 0;
    	int currentSequenceLength;
    	
    	// Represents whether the current sequence started from the middle
    	boolean sequenceFromMiddle = true;
    	
    	// If midpoints are the same point
    	if (point1 == point2)
    	{
    		currentSequenceLength = 1;
    	}
    	// If midpoints are not the same point but equal each other
    	else if (numbers[point1] == numbers[point2])
    	{
    		currentSequenceLength = 2;
    	}
    	// If midpoints are not the same point and don't equal each other
    	else
    	{
    		currentSequenceLength = 0;
    	}
    	
		point1 -= 1;
		point2 += 1;
    	
		// Traverse vector from point1 and point2 in both directions
		// to check mirroring points in the array and find the largest
		// mirrored sequence length
    	while (point1 >= 0)
    	{

    		if (numbers[point1] == numbers[point2])
    		{
    			if (sequenceFromMiddle)
    			{
    				currentSequenceLength += 2;
    			}
    			else
    			{
    				currentSequenceLength += 1;
    			}
    		}
    		else
    		{
    			if (currentSequenceLength > longestSequenceLength)
    			{
    				longestSequenceLength = currentSequenceLength;
    			}
    			
    			currentSequenceLength = 0;
    			sequenceFromMiddle = false;
    		}
    		point1 -= 1;
    		point2 += 1;
    	}
    	
		if (currentSequenceLength > longestSequenceLength)
		{
			longestSequenceLength = currentSequenceLength;
		}
    	
		return longestSequenceLength;
	}
}
