package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *   Given a rows x cols screen and a sentence represented by a list of non-empty words, find how
 *   many times the given sentence can be fitted on the screen.

     Note:

     A word cannot be split into two lines.
     The order of words in the sentence must remain unchanged.
     Two consecutive words in a line must be separated by a single space.
     Total words in the sentence won't exceed 100.
     Length of each word is greater than 0 and won't exceed 10.
     1 ≤ rows, cols ≤ 20,000.
     Example 1:

     Input:
     rows = 2, cols = 8, sentence = ["hello", "world"]

     Output:
     1

     Explanation:
     hello---
     world---

     The character '-' signifies an empty space on the screen.
     Example 2:

     Input:
     rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

     Output:
     2

     Explanation:
     a-bcd-
     e-a---
     bcd-e-

     The character '-' signifies an empty space on the screen.
     Example 3:

     Input:
     rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

     Output:
     1

     Explanation:
     I-had
     apple
     pie-I
     had--

     The character '-' signifies an empty space on the screen.
 *
 */
public class SentenceScreenFitting {

    public int wordsTypeing1(String[] sentence,int rows,int cols){
        String totalStr = String.join("-",sentence);

        int offset = 0;
        int times = 0;
        for(int i = 1;i <= rows;i ++){
            char tail = totalStr.charAt(offset + cols - 1);
            if(tail == '-'){
                offset += cols;
            }else{

            }
        }


        return 0;
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        Map<String,Integer> cache = new HashMap<>();

        int count = 0;
        int lines = 1;
        int i =0;
        int times = 0;
        for(;;){

            if(count == 0){
                Integer lastTimes = cache.get(sentence[i]);
                if(lastTimes != null){
                    int leftLines = rows - lines + 1;
                    int unitTimes = times - lastTimes;

                }
            }

            int addLength = sentence[i].length() + (count == 0 ? 0 : 1);
            boolean next = false;
            if(count + addLength >= cols){

                if(count + addLength == cols)
                    next = true;

                count = 0;
                lines ++;
            }
            else {
                next = true;
                count += addLength;
            }

            if(next) {
                if(i + 1 >= sentence.length)
                    times ++;

                i = (i + 1) % sentence.length;
            }

            if(lines > rows)
                break;
        }

        return times;
    }

    public static void main(String[] args){
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"a"},20000,20000));
    }

}
