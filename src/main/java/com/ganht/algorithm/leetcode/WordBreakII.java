package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {

	private Set<String> wordSet;
	private Map<String, List<String>> cache = new HashMap<>();

	public List<String> wordBreak(String s, List<String> wordDict) {
		wordSet = new HashSet<>(wordDict);
		return doWordBreak(s);
	}
	
	private List<String> doWordBreak(String s) {
		List<String> cacheResult = cache.get(s);
		if (cacheResult != null) {
			return cacheResult;
		}

		List<String> result = new ArrayList<>();
		for (int i = 1; i <= s.length(); i++) {
			String tmpS = s.substring(0, i);
			if (wordSet.contains(tmpS)) {
				if(i >= s.length()) {
					result.add(tmpS);
				}else {
					doWordBreak(s.substring(i)).forEach(word -> result.add(tmpS + " " + word));
				}
			}
		}

		cache.put(s, result);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stubk
		String s = "catsanddog";
		List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		List<String> result = new WordBreakII().wordBreak(s, dict);
		System.out.println(result);
	}

}
