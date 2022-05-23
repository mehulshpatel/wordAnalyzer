package com.rest.wordAnalyzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.wordAnalyzer.model.WordFrequency;
import com.rest.wordAnalyzer.service.WordFrequencyAnalyzer;

@RestController
@RequestMapping("/wordAnalyzer")
public class WordAnalyzerController {

	@Autowired
	private WordFrequencyAnalyzer wordFrequencyAnalyzer;
	
	@GetMapping("/highest")
    public ResponseEntity<Integer> calculateHighestFrequency(@RequestParam(name="text", required = true) String text) {
        return ResponseEntity.ok().body(wordFrequencyAnalyzer.calculateHighestFrequency(text));
    }
	
	@GetMapping("/highest/{word}")
    public ResponseEntity<Integer> calculateFrequencyForWord(@RequestParam(name="text", required = true) String text, @PathVariable(value = "word") String word) {
        return ResponseEntity.ok().body(wordFrequencyAnalyzer.calculateFrequencyForWord(text, word));
    }
	
	@GetMapping("/frequent/{number}")
    public ResponseEntity<List<WordFrequency>> calculateMostFrequentNWords(@RequestParam(name="text", required = true) String text, @PathVariable(value = "number") int number) {
        return ResponseEntity.ok().body(wordFrequencyAnalyzer.calculateMostFrequentNWords(text, number));
    }
}
