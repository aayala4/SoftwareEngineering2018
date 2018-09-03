package edu.nd.se2018.homework.hwk1;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.String;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 
 * Homework 1, Question 2
 * 
 * @author Alex Ayala
 *
 */

public class Question2 {

	public Question2(){}

	// Given an input string, return the most frequently occurring word. Exclude very common words (typically
	// referred to as stop words). Stop words will be defined in a separate string that is also passed as an argument to
	// the function. If there is no single winner, return null.
	public String getMostFrequentWord(String input, String stopWords){
		
		// Convert input string to ArrayList
		ArrayList<String> inputArrayList = new ArrayList<String>(Arrays.asList(input.split(" ")));
		ArrayList<String> stopWordsArrayList = new ArrayList<String>(Arrays.asList(stopWords.split(" ")));
		
		// Used for associating words to their respective counts
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();

		// Go through every word in the input and increment the word's associated count for every occurence
		for (int i = 0; i < inputArrayList.size(); i++)
		{
			try
			{
				// Only increment a word count if word is not a stop word
				if(!stopWordsArrayList.contains(inputArrayList.get(i)))
				{
					Integer wordCount = wordCounts.get(inputArrayList.get(i));
					wordCounts.put(inputArrayList.get(i), new Integer(wordCount.intValue() + 1));
				}
			}
			// Case when it's the first time adding a word occurs in the input
			catch(NullPointerException e)
			{
				wordCounts.put(inputArrayList.get(i), 1);				
			}
		}
		
		// Find the highest word count
		Integer maxCount = Collections.max(wordCounts.values());
		String mostFrequentWord = "";
		
		// Find the word with the max value. If there's more than one, return null.
		for (String s : wordCounts.keySet())
		{
			if(wordCounts.get(s).equals(maxCount))
			{
				if (mostFrequentWord.equals(""))
				{
					mostFrequentWord = s;
				}
				else
				{
					return null;
				}
			}
		}
		
		return mostFrequentWord;
	}
}
