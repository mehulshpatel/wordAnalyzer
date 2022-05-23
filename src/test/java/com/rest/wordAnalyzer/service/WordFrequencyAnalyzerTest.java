package com.rest.wordAnalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.wordAnalyzer.model.WordFrequency;

public class WordFrequencyAnalyzerTest {
	
	@InjectMocks
	WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();
	
	private static final String TEST_TEXT = "The sun shines over the lake over the land";
	
	@BeforeEach
	public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    void testTextForHighestFrequency() {
		
		int count = wordFrequencyAnalyzer.calculateHighestFrequency(TEST_TEXT);
		assertEquals(3, count);
    }

	@Test
    void testTextForFrequencyOfGivenWord() {
		
		int count = wordFrequencyAnalyzer.calculateFrequencyForWord(TEST_TEXT, "over");
		assertEquals(2, count);
    }
	
	@Test
    void testTextForMostFrequentNWords() {
		
		List<WordFrequency> mostFrequentWords = wordFrequencyAnalyzer.calculateMostFrequentNWords(TEST_TEXT, 3);
		assertEquals(3, mostFrequentWords.size());
		assertEquals("lake", mostFrequentWords.get(2).getWord());
		assertEquals(1, mostFrequentWords.get(2).getFrequency());
    }

}
