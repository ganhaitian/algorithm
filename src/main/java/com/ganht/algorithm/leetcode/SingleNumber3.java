package com.ganht.algorithm.leetcode;

import java.util.HashMap;

/**
 * Created by lenovo on 2016/8/11.
 */
public class SingleNumber3 {

    /**
     * 我的这个办法太low了，主要面临的问题就是有可能占用大量的内存
     * 巧妙的办法就是用异或，这里用到异或三个关键的特性，就是交换率，a^a = 0,以及a ^ 0 = a
     * 这样，如果所有输入的数字异或在一起，一对一对出现的，会得到0，0异或单独的那两个字母，最后就只剩下两个单独字母异或的结果
     * 如果a ^ b = result,那么a ^ result = b，b ^ result = a
     * 那么最后，问题就转换成了，如何将a和b区分开，这就用到了一个trick，a & (~(a-1))会或得这个二进制数字最低为1的那一位
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        HashMap<Integer,Integer> cache = new HashMap<>();

        for(int i = 0;i < nums.length;i++){
            if(cache.containsKey(nums[i])){
                cache.remove(nums[i]);
            }else{
                cache.put(nums[i],1);
            }
        }

        Integer[] result = new Integer[cache.size()];
        result = cache.keySet().toArray(result);

        int[] r = new int[result.length];
        for(int j = 0;j < result.length;j++){
            r[j] = result[j];
        }

        return r;
    }

    public static void main(String[] args){
        int[] r = new SingleNumber3().singleNumber(new int[]{3,3,2,1,1,9,8,8});
    }
}
