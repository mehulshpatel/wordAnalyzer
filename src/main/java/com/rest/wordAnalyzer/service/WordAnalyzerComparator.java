package com.rest.wordAnalyzer.service;

import java.util.Comparator;

import com.rest.wordAnalyzer.model.WordFrequency;

public class WordAnalyzerComparator implements Comparator<WordFrequency> {

	@Override
	public int compare(WordFrequency o1, WordFrequency o2) {
		if(o1.getFrequency() == o2.getFrequency()) {
			return o1.getWord().compareToIgnoreCase(o2.getWord());
		}
		return Integer.compare(o2.getFrequency(), o1.getFrequency());
	}

}
