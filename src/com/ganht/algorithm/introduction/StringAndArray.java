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

    public static void main(String[] args) {
        //System.out.println(new StringAndArray().determineUniqueString("abcdefga"));
        System.out.println(new StringAndArray().reverseString("abcdefgh"));
    }

}
