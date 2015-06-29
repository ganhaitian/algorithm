package com.ganht.algorithm.introduction;

/**
 * 字符串和数组系列
 * Created by ganhaitian on 2015/6/24.
 */
public class StringAndArray {

    /**
     * Implement an algorithm to determine if a string has all unique characters. What
     * if you cannot use additional data structures?
     * <p/>
     * 判断字符串，是否有重复
     *
     * @return
     */
    public boolean determineUniqueString(String input) {

        char[] chars = input.toCharArray();
        // 先要搞清楚是acsii字符，还是unicode字符
        boolean[] charSet = new boolean[256];
        for (int i = 0; i < chars.length; i++) {
            int var = chars[i];
            if (charSet[var]) {
                return false;
            }
            charSet[var] = true;
        }

        // 不允许借用额外数据结构的第一种办法，两层循环比较
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j])
                    return false;
            }
        }

        // 不允许借用额外数据结构的第二种办法，用int数的位来记录出现过的状态
        int checker = 0;
        for (int i = 0; i < chars.length; i++) {
            int var = chars[i] - 'a';
            if ((1 << var & checker) > 0) {
                return false;
            }
            checker |= 1 << var;
        }
        return true;
    }

    /**
     * 反转字符串
     *
     * @param input
     * @return
     */
    public String reverseString(String input) {
        // 第一种方法，倒序遍历，重新拼装
        char[] orgCharSet = input.toCharArray();
        char[] newCharSet = new char[input.length()];
        int tmpIndex = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            newCharSet[tmpIndex++] = orgCharSet[i];
        }

        // 第二种方法，从中间往回遍历，把前后两段的字节进行调换
        int n = input.length() - 1;
        for (int j = (n - 1) >> 1; j >= 0; --j) {
            char tmp = orgCharSet[j];
            orgCharSet[j] = orgCharSet[n - j];
            orgCharSet[n - j] = tmp;
        }

        return new String(newCharSet);
    }

    /**
     * Given two strings, write a method to decide if one is a permutation of the other
     * 判断一个字符串是否是另外一个字符串的全排列
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isPermutation(String a, String b) {
        if (a == null || b == null || a.length() != b.length())
            return false;

        int[] counter = new int[256];
        for (int i = 0; i < a.length(); i++) {
            counter[a.charAt(i)]++;
        }

        for (int j = 0; j < b.length(); j++) {
            if (--counter[b.charAt(j)] < 0)
                return false;
        }

        return true;
    }

    /**
     * Write a method to replace all spaces in a string with'%20'. You may assume that
     * the string has sufficient space at the end of the string to hold the additional
     * characters, and that you are given the "true" length of the string.
     * (Note: if implementing in Java, please use a character array so that you can perform this operation in place.)
     * <p/>
     * EXAMPLE
     * Input: "Mr John Smith"
     * Output: "Mr%20Dohn%20Smith"
     *
     * @return
     */
    public String replaceSpace(char[] input, int length) {
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] == ' ') {
                spaceCount++;
            }
        }

        int newLength = length + spaceCount * 2;
        input[newLength] = '\0';
        for (int i = length - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                input[newLength - 1] = '0';
                input[newLength - 2] = '2';
                input[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                input[newLength - 1] = input[i];
                newLength--;
            }
        }

        return new String(input);
    }

    /**
     * Implement a method to perform basic string compression using the counts
     * of repeated characters. For example, the string aabcccccaa a would become
     * a2b1
     * c5a3. If the "compressed" string would not become smaller than the
     * original string, your method should return the original string
     *
     * @param input
     * @return
     */
    public String compressString(String input) {
        char[] charSet = input.toCharArray();
        StringBuffer newString = new StringBuffer();
        // 这里面也是有小技巧的，为了避免边界条件的判断，可以变化初始化值
        char lastChar = '\0';
        int count = 0;
        for (int i = 0; i < charSet.length; i++) {
            if (lastChar != input.charAt(i)) {
                lastChar = input.charAt(i);
                if (count > 0)
                    newString.append(count);
                newString.append(input.charAt(i));
                count = 1;
            } else {
                count++;
            }
        }

        newString.append(count);
        if (newString.length() == input.length())
            return input;

        // 新方法，改变初始化值，来避免边界条件的特殊判断
        char lastChar1 = input.charAt(0);
        int count1 = 1;
        for (int i = 0; i < charSet.length; i++) {
            if (lastChar1 != input.charAt(i)) {
                newString.append(lastChar1).append(count1);
                count1 = 1;
                lastChar1 = input.charAt(i);
            } else {
                count1++;
            }
        }

        newString.append(lastChar1).append(count1);
        return newString.toString();
    }

    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is
     * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in
     * place?
     *
     * @param matrix
     */
    public void rotateMatrix(int[][] matrix) {
        int tmp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
               tmp = matrix[i][j];

            }
        }
    }

    public static void main(String[] args) {
        //System.out.println(new StringAndArray().determineUniqueString("abcdefga"));
        //System.out.println(new StringAndArray().reverseString("abcdefgh"));
        //System.out.println(new StringAndArray().isPermutation("abbcd","babca"));
        //System.out.println(new StringAndArray().replaceSpace("Mr John Smith"));
        //System.out.println(new StringAndArray().replaceSpace(new String("Mr John Smith                                ").toCharArray(), 13));
        System.out.println(new StringAndArray().compressString("aabcccccaa"));
    }

}
