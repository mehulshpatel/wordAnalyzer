package com.rest.wordAnalyzer.model;

import java.util.Objects;

public class WordFrequencyModel implements WordFrequency {

	private String word;
	private int frequency;
	
	public WordFrequencyModel (String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	@Override
	public String getWord() {
		return word;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) { 
		this.frequency = frequency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(word, Integer.valueOf(frequency));
	}
	
	@Override
	public boolean equals(Object o) {
		if(Objects.nonNull(o) && o instanceof WordFrequencyModel) {
			return word.equalsIgnoreCase(((WordFrequencyModel) o).getWord());
		}
		return Boolean.FALSE;
	}
}
