package com.rest.wordAnalyzer.service;

import java.util.List;

import com.rest.wordAnalyzer.model.WordFrequency;

public interface WordFrequencyAnalyzer {
	int calculateHighestFrequency(String text);
	int calculateFrequencyForWord (String text, String word);
	List<WordFrequency> calculateMostFrequentNWords (String text, int n);
}
