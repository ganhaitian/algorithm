package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number
 * of different integers in that subarray is exactly K.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of A.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * Example 2:
 *
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * Note:
 *
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class SubarraysWithKDifferentIntegers {

    // 超时了
    public int subarraysWithKDistinct(int[] A, int K) {
        Set<Integer> window = new HashSet<>();

        int result = 0;
        /*for(int i = 0;i < A.length;i++){
            window.add(A[i]);
            if(window.size() == K){
                result ++;
            }
        }*/

        int i = 0,j = 0;
        while(i < A.length){
            window.add(A[j]);
            if(window.size() < K){
                j++;
            }else if(window.size() == K){
                result++;
                j++;
            }

            if(window.size() > K || j >= A.length){
                window.clear();
                i ++;
                j = i;
            }
        }

        return result;
    }

    class Window {
        Map<Integer, Integer> count;
        int nonzero;

        Window() {
            count = new HashMap();
            nonzero = 0;
        }

        void add(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.get(x) == 1)
                nonzero++;
        }

        void remove(int x) {
            count.put(x, count.get(x) - 1);
            if (count.get(x) == 0)
                nonzero--;
        }

        int different() {
            return nonzero;
        }
    }

    /**
     * 这个逻辑有点儿绕，用的是滑动窗口(slide window)。它基于的是以下的推论:
     * 对于子数组i1,i2,i3....ij(后面那个是下标)，如果{im,ij}和{in,ij}是合法的序列，且m < n，那么m和n之间的那些下标到ij同样都是合法序列。
     * 可以理解成连续性
     *
     * 所以对于i1,i2,i3....ij这个序列，想针对其中某一点ik，计算从i1起，以ik结尾，有多少个满足条件的序列，只要算一个最小的满足条件的下标，和最大
     * 的满足条件的下标一减再加1就行了
     *
     * 比如1,2,1,2,3这个数组，想算index=3，即2这个点，以2结尾的满足条件的序列有多少个？就先看最小标，那就是第一个1。因为1,2,1,2满足条件
     * 而最大的小标就是index=2的那个1 。所以结果是3- 1 + 1 = 3。
     *
     * 所以算法就是：遍历数组，对于每一个下标，计算从头开始并以该下标结尾的满足条件的序列个数，最后加在一起就可以了。
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct1(int[] A, int K) {
        Window window1 = new Window();
        Window window2 = new Window();
        int ans = 0, left1 = 0, left2 = 0;

        for (int right = 0; right < A.length; ++right) {
            int x = A[right];
            window1.add(x);
            window2.add(x);

            while (window1.different() > K)
                window1.remove(A[left1++]);
            while (window2.different() >= K)
                window2.remove(A[left2++]);

            ans += left2 - left1;
        }

        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1,2,1,2,3};
        System.out.println(new SubarraysWithKDifferentIntegers().subarraysWithKDistinct1(nums, 2));
    }

}
