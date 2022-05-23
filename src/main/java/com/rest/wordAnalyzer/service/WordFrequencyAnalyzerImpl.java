package com.rest.wordAnalyzer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rest.wordAnalyzer.model.WordFrequency;
import com.rest.wordAnalyzer.model.WordFrequencyModel;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

	@Override
	public int calculateHighestFrequency(String text) {
		List<WordFrequency> wordFrequencies =  prepareWordFrequeciesList(text.toLowerCase());
		return wordFrequencies.isEmpty() ? 0 : wordFrequencies.get(0).getFrequency();
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
		List<WordFrequency> wordFrequencies =  prepareWordFrequeciesList(text.toLowerCase());
		if(!wordFrequencies.isEmpty()) {
			wordFrequencies = wordFrequencies.stream().filter(wordFrequency -> wordFrequency.getWord().equalsIgnoreCase(word)).collect(Collectors.toList());
		}
		return wordFrequencies.isEmpty() ? 0 : wordFrequencies.get(0).getFrequency();
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
		List<WordFrequency> wordFrequencies =  prepareWordFrequeciesList(text.toLowerCase());
		
		return n < wordFrequencies.size() ? wordFrequencies.subList(0, n) : wordFrequencies;
	}
	
	private List<WordFrequency> prepareWordFrequeciesList(String text) {
		ArrayList<WordFrequency> wordFrequencies = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(text)) {
			List<String> textlist = Arrays.asList(text.split("\\s+"));
			textlist.stream().forEach(word -> {
				WordFrequencyModel wordFrequency = new WordFrequencyModel(word, 1);
				int index = wordFrequencies.indexOf(wordFrequency);
				if(index >= 0) {
					wordFrequency.setFrequency(wordFrequencies.get(index).getFrequency() + 1);
					wordFrequencies.remove(index);
				}
				wordFrequencies.add(wordFrequency);
			});
			Collections.sort(wordFrequencies, new WordAnalyzerComparator());
		}
		return wordFrequencies;
	}

}
