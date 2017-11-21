package com.ganht.algorithm.test;

import org.apache.commons.lang.StringUtils;

/**
 * Created by haitian.gan on 2017/8/30.
 */
public class DeleteEsc {

  /**
   * 输入：一个带转义字符’\b’的字符串
   * 要求：删除转义字符’\b’和它前面的字符，如果遇到多个连续的’\b’，则删除相同数量的转义字符和前面的字符
   * 输出：最终的结果字符串
   * 举例：输入"abc\b\bd\b\bghi"，期望输出"ghi"
   */
  public String deleteEsc(String input) {
    if (StringUtils.isEmpty(input)) {
      return null;
    }

    char[] result = new char[input.length()];
    int pos = 0;
    for (char c : input.toCharArray()) {
      if (c != '\b') {
        result[pos++] = c;
      } else {
        if (pos > 0) {
          pos--;
        }
      }
    }

    return String.valueOf(result, 0, pos);
  }

  public static void main(String[] args) {

  }

}
