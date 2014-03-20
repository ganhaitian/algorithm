package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
	After years of study, scientists at Google Labs have discovered an alien language transmitted 
	from a faraway planet. The alien language is very unique in that every word consists of exactly
	 L lowercase letters. Also, there are exactly D words in this language.
	
	Once the dictionary of all the words in the alien language was built, the next breakthrough was
	to discover that the aliens have been transmitting messages to Earth for the past decade. 
	Unfortunately, these signals are weakened due to the distance between our two planets and some
	of the words may be misinterpreted. In order to help them decipher these messages, the scientists
	have asked you to devise an algorithm that will determine the number of possible interpretations
	for a given pattern.
	
	A pattern consists of exactly L tokens. Each token is either a single lowercase letter
	(the scientists are very sure that this is the letter) or a group of unique lowercase letters 
	surrounded by parenthesis ( and ). For example: (ab)d(dc) means the first letter is either a or b, 
	the second letter is definitely d and the last letter is either d or c. Therefore, the pattern 
	(ab)d(dc) can stand for either one of these 4 possibilities: add, adc, bdd, bdc.
	
	Input
	
	The first line of input contains 3 integers, L, D and N separated by a space. D lines follow, each
	containing one word of length L. These are the words that are known to exist in the alien language.
	N test cases then follow, each on its own line and each consisting of a pattern as described above.
	You may assume that all known words provided are unique.
	
	Output
	
	For each test case, output
	
	Case #X: K
	where X is the test case number, starting from 1, and K indicates how many words in the alien language match the pattern.
	
	Limits
	
	Small dataset
	
	1 ≤ L ≤ 10
	1 ≤ D ≤ 25
	1 ≤ N ≤ 10
	Large dataset
	
	1 ≤ L ≤ 15
	1 ≤ D ≤ 5000
	1 ≤ N ≤ 500
	Sample
	
	
	Input 
	 	
	Output 
	 
	3 5 4
	abc
	bca
	dac
	dbc
	cba
	(ab)(bc)(ca)
	abc
	(abc)(abc)(abc)
	(zyx)bc
	Case #1: 2
	Case #2: 1
	Case #3: 3
	Case #4: 0

 * 
 * @author gan
 *
 */
public class AlienLanguage extends CodeJamCase {
	
	private int L = 0;
	private int D = 0;
	private int N = 0;
	private List<String> words = new ArrayList<String>();
	private int caseIndex = 0;
	
	private void runAlgorithm(int lineNumber,String line){
		
		if(lineNumber == 1){
			String[] languageInfos = line.split(" ");
			L = Integer.parseInt(languageInfos[0]);
			D = Integer.parseInt(languageInfos[1]);
			N = Integer.parseInt(languageInfos[2]);		
		}else if(lineNumber <= (D+1)){
			words.add(line);
		}else{
			List<String> tmpWords = new ArrayList<String>();
			tmpWords.addAll(words);
			String caseContent = line;
			int partNumber = 0;
			
			for(String subPart:caseContent.split("\\(|\\)")){
				if(tmpWords.size() == 0)
					break;
				if(subPart == null || subPart.trim().length() == 0)
					continue;
				if(caseContent.indexOf("("+subPart+")") >= 0)
					filterWords(partNumber++,subPart,tmpWords);
				else{
					for(char c:subPart.toCharArray()){
						if(tmpWords.size() == 0)
							break;
						filterWords(partNumber++,String.valueOf(c),tmpWords);
					}
				}
			}
			
			System.out.println(String.format("Case #%d: %d",++caseIndex,tmpWords.size()));
			tmpWords.clear();
		}
		
	}
	
	private void filterWords(int i,String part,List<String> tmpWords) {
		for(Iterator<String> iter = tmpWords.iterator();iter.hasNext();){
			String word = iter.next();
			if(part.indexOf(word.charAt(i)) < 0)
				iter.remove();
		}
	}

	@Override
	protected void runCase() {
		parseInput(new File("G:\\dep\\allien-language-largin.in"),new InputCaseLineParser() {
			@Override
			public void parseLine(int lineNumber, String line) {
				runAlgorithm(lineNumber,line);
			}
		});
	}

	public static void main(String[] args){
		new AlienLanguage().runCase();
	}
}
