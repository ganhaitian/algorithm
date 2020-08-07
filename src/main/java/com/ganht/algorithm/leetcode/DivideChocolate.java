package com.ganht.algorithm.leetcode;

import java.util.Arrays;

/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts,
 * each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 *
 * Constraints:
 *
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 * @author haitian.gan
 */
public class DivideChocolate {

    // 这个题太他妈的巧妙了。我拿最小，隐含的意思是其它人的块儿都比我大。
    // 而最理想的情况是我分到的是一个总数的平均数
    // 所以这就转化成了一个查找问题，找到从0到平均数之间，符合条件的那个数，而判断条件就是某个数分出的块儿是不是大于K + 1
    //
    // 这里最巧妙的隐含条件是：我们按紧凑的方式把a分组成了每个组都比mid大的情况，如果这时总组数大于K。那说明就是分不成比mid小的情况，因为我们希望
    // 每个组都尽量的大，趋向于平均数，如果把某个组的边界数字匀给旁边的组，被匀的组小了，那就破坏了已经达成平衡的结果
    public int maximizeSweetness(int[] a, int K) {
        int low = 0;
        int N = a.length;
        int high = Arrays.stream(a).sum()/(K+1);
        while(low<high){
            int mid = (low+high+1)/2;
            if(check(a, mid, K)){
                low=mid;
            } else {
                high=mid-1;
            }
        }

        return low;
    }

    public boolean check(int[] a, int mid, int K){
        int count=0;
        int cumsum=0;
        for(int num:a){
            cumsum+=num;
            if(cumsum>=mid){
                count++;
                cumsum=0;
            }
        }
        return count>=K+1;
    }

    public static void main(String[] args){
        int[] input = {1,2,3,4,5,6,7,8,9};
        new DivideChocolate().maximizeSweetness(input, 5);
    }

}
