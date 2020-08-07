package com.ganht.algorithm.leetcode;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to read n
 * characters. Your method read may be called multiple times.
 *
 *
 *
 * Method read4:
 *
 * The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
 *
 * The return value is the number of actual characters read.
 *
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 *
 * Definition of read4:
 *
 *     Parameter:  char[] buf
 *     Returns:    int
 *
 * Note: buf[] is destination not source, the results from read4 will be copied to buf[]
 * Below is a high level example of how read4 works:
 *
 * File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
 * char[] buf = new char[4]; // Create buffer with enough space to store characters
 * read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
 * read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
 * read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file
 *
 *
 * Method read:
 *
 * By using the read4 method, implement the method read that reads n characters from the file and store it in the buffer
 * array buf. Consider that you cannot manipulate the file directly.
 *
 * The return value is the number of actual characters read.
 *
 * Definition of read:
 *
 *     Parameters:	char[] buf, int n
 *     Returns:	int
 *
 * Note: buf[] is destination not source, you will need to write the results to buf[]
 *
 *
 * Example 1:
 *
 * File file("abc");
 * Solution sol;
 * // Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
 * sol.read(buf, 1); // After calling your read method, buf should contain "a". We read a total of 1 character from the
 * file, so return 1.
 * sol.read(buf, 2); // Now buf should contain "bc". We read a total of 2 characters from the file, so return 2.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 * Example 2:
 *
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 4); // After calling your read method, buf should contain "abc". We read a total of 3 characters from the
 * file, so return 3.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 *
 *
 * Note:
 *
 * Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
 * The read function may be called multiple times.
 * Please remember to RESET your class variables declared in Solution, as static/class variables are persisted across multiple
 * test cases. Please see here for more details.
 * You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 * It is guaranteed that in a given test case the same buffer buf is called by read.
 * @author haitian.gan
 */
public class ReadNCharactersGivenRead4II{

    private StringReader testStr = new StringReader("abcde");
    private StringReader cache;

    private int read4(char[] buf){
        try {
            return testStr.read(buf);
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (n <= 0) {
            return 0;
        }

        // 初始化
        Arrays.fill(buf, '\u0000');

        int readNum = 0;
        /*if (surplus != null && surplus.length > 0) {
            int num = Math.min(n, surplus.length);
            System.arraycopy(surplus, 0, buf, 0, num);
            readNum = null;

            surplus = null;// 重置掉
        }*/

        if (cache != null) {
            try {
                int num = cache.read(buf, 0, n);
                if (num != -1) {
                    if(num >= n){
                        return n;
                    }else{
                        readNum = num;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        do {
            char[] tmp         = new char[4];
            int    read4Result = read4(tmp);
            if (read4Result == 0) {
                break;
            }

            int copyNum = readNum + read4Result > n ? n - readNum : read4Result;

            // 拷贝到buf上
            System.arraycopy(tmp, 0, buf, readNum, copyNum);

            readNum += read4Result;
            if (readNum > n) {
                char[] surplus = new char[readNum - n];
                System.arraycopy(tmp, read4Result - (readNum - n), surplus, 0, readNum - n);
                cache = new StringReader(new String(surplus));
            }

        } while (readNum < n);

        return Math.min(n, readNum);
    }

    public static void main(String[] args){
        ReadNCharactersGivenRead4II test = new ReadNCharactersGivenRead4II();
        char[] input = new char[1];
        int result = test.read(input, 1);
        result = test.read(input, 1);
        result = test.read(input, 1);
        result = test.read(input, 1);
        result = test.read(input, 1);
        result = test.read(input, 1);
        result = test.read(input, 1);
        System.out.println(result);
    }

}
