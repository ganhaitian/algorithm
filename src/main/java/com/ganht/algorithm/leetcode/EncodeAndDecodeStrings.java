package com.ganht.algorithm.leetcode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
 * decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 *
 *
 * Note:
 *
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized
 * enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode
 * algorithm.
 * @author haitian.gan
 */
public class EncodeAndDecodeStrings {

    // 思路是对的，写复杂了
    public static class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            if(strs == null || strs.size() <= 0){
                return null;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // StringBuilder         stringBuilder         = new StringBuilder();
            byte[] tmpCache;
            for (String str : strs) {
                try {
                    tmpCache = str.getBytes(StandardCharsets.UTF_8);
                    byteArrayOutputStream.write(intToByteArray(tmpCache.length));
                    byteArrayOutputStream.write(tmpCache);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            if(s == null){
                return new ArrayList<>();
            }

            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
            List<String>         result      = new ArrayList<>();
            int                  sepIndex    = 0;
            byte[]               lengthBytes = new byte[4];
            byte[]               readBuffer  = new byte[1000];
            while (true) {
                try {
                    int readNum = inputStream.read(lengthBytes);
                    if (readNum == -1) {
                        break;
                    }

                    int    length = byteArrayToInt(lengthBytes);
                    String str;
                    if (length == 0) {
                        str = "";
                    } else {
                        byte[] strBytes = new byte[length];
                        readNum = inputStream.read(strBytes);
                        if (readNum == -1) {
                            break;
                        }

                        str = new String(strBytes);
                    }

                    result.add(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

        private int byteArrayToInt(byte[] b) {
            return   b[3] & 0xFF |
                    (b[2] & 0xFF) << 8 |
                    (b[1] & 0xFF) << 16 |
                    (b[0] & 0xFF) << 24;
        }

        private byte[] intToByteArray(int a) {
            return new byte[] {
                    (byte) ((a >> 24) & 0xFF),
                    (byte) ((a >> 16) & 0xFF),
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)
            };
        }

        public static void main(String[] args) {
            Codec        codec = new Codec();
            String       str   = codec.encode(Arrays.asList("",""));
            List<String> str1  = codec.decode(str);
        }
    }

}
