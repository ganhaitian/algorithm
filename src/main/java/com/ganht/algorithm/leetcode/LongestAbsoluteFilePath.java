package com.ganht.algorithm.leetcode;

import java.util.Stack;

/**
 *
 * Created by haitian.gan on 2017/3/31.
 */
public class LongestAbsoluteFilePath {

    // 唉，想得真复杂啊。为什么要一个一个字符遍历呢
    // 直接一段一段来效率多高啊
    public int lengthLongestPath(String input) {
        input = input + "\n";

        Stack<String> path = new Stack<>();
        int pathCount = 0;
        int maxLength = 0;

        int partStart = 0;
        char curr;
        int level = 0;
        int lastLevel = 0;
        for(int i = 0;i < input.length();i++){
            curr =input.charAt(i);
            if(curr == '\n'){

                while(lastLevel >= level && path.size() > 0){
                    String t = path.pop();
                    pathCount -= t.length();

                    lastLevel--;
                }

                //path.pop();
                String part = "\\" + input.substring(partStart,i);
                pathCount += part.length();
                path.push(part);

                // file
                if(part.indexOf('.') > 0){
                    if(pathCount > maxLength)
                        maxLength = pathCount;
                }

                partStart = i + 1;
                lastLevel = level;
                level = 0;

            }else if(curr == '\t'){
                partStart++;
                level++;
            }
        }

        return Math.max(maxLength - 1,0);
    }

    public static void main(String[] args){
        int result =new LongestAbsoluteFilePath().lengthLongestPath("a\n\tb.txt");
        System.out.println(result);
    }

}
