package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three
 * product names from products after each character of searchWord is typed. Suggested products should have common prefix
 * with the searchWord. If there are more than three products with a common prefix return the three lexicographically
 * minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 * @author haitian.gan
 */
public class SearchSuggestionsSystem {

    // 完全没有难度，不知道为啥是中等难度
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        int lastPos = 0;
        var result = new ArrayList<List<String>>();
        for (int i = 1; i <= searchWord.length(); i++) {
            var w          = searchWord.substring(0, i);
            var onceResult = new ArrayList<String>();
            for (int j = lastPos; j < products.length && onceResult.size() < 3; j++) {
                var p         = products[j];
                var prefix    = p.substring(0, Math.min(i, p.length()));
                int cmpResult = prefix.compareTo(w);
                if (cmpResult == 0) {
                    if (onceResult.size() == 0) {
                        lastPos = j;
                    }
                    onceResult.add(p);
                } else if (cmpResult > 0) {
                    break;
                }
            }

            result.add(onceResult);
        }

        return result;
    }

    public static void main(String[] args){
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        new SearchSuggestionsSystem().suggestedProducts(products, "mouse");
    }

}
