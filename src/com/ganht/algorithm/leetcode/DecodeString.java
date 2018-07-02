package com.ganht.algorithm.leetcode;

import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {

        StringBuilder stringBuilder = new StringBuilder();

        int tmpStart = 0;
        int tmpEnd = -1;
        int lastEnd = -1;
        Stack<String> sbStack = new Stack<>();

        for(int i = 0;i < s.length();i ++){
            char c = s.charAt(i);
            if(c == '['){
                if(sbStack.empty())
                    tmpStart = i;

                sbStack.push("[");
            }else if(c == ']'){
                sbStack.pop();

                if(!sbStack.empty())
                    continue;

                tmpEnd = i;

                String readyToDecodeStr = s.substring(tmpStart + 1,tmpEnd);
                String decodeStr;
                if(readyToDecodeStr.indexOf('[') == -1 && readyToDecodeStr.indexOf(']') == -1){
                    decodeStr = readyToDecodeStr;
                }else
                    decodeStr = decodeString(readyToDecodeStr);

                int kStart;
                for(kStart = tmpStart - 1;;kStart --){
                    char tmpK = ' ';
                    if(kStart >= 0)
                        tmpK = s.charAt(kStart);

                    if(!(tmpK >= '0' && tmpK <= '9')){
                        break;
                    }
                }

                int k = Integer.parseInt(s.substring(kStart + 1,tmpStart));
                stringBuilder.append(s.substring(lastEnd + 1,kStart + 1));

                for(int j = 1;j <= k;j++){
                    stringBuilder.append(decodeStr);
                }

                lastEnd = tmpEnd;
            }
        }

        if(tmpEnd < s.length() - 1){
            stringBuilder.append(s.substring(tmpEnd + 1,s.length()));
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args){
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }

}
