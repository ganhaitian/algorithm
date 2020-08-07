package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 *
 * Note: 1 ≤ k ≤ n ≤ 109.
 *
 * Example:
 *
 * Input:
 * n: 13   k: 2
 *
 * Output:
 * 10
 *
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * @author haitian.gan
 */
public class KthSmallestInLexicographicalOrder {

    /**
     * 这道题是之前那道Lexicographical Numbers的延伸，之前让按字典顺序打印数组，而这道题让我们快速定位某一个位置，
     * 那么我们就不能像之前那道题一样，一个一个的遍历，这样无法通过OJ，这也是这道题被定为Hard的原因。那么我们得找出能够快速定位的方法，
     * 我们如果仔细观察字典顺序的数组，我们可以发现，其实这是个十叉树Denary Tree，就是每个节点的子节点可以有十个，比如数字1的子节点就是10到19，
     * 数字10的子节点可以是100到109，但是由于n大小的限制，构成的并不是一个满十叉树。我们分析题目中给的例子可以知道，数字1的子节点有4个(10,11,12,13)，
     * 而后面的数字2到9都没有子节点，那么这道题实际上就变成了一个先序遍历十叉树的问题，那么难点就变成了如何计算出每个节点的子节点的个数，我们不
     * 停的用k减去子节点的个数，当k减到0的时候，当前位置的数字即为所求。现在我们来看如何求子节点个数，比如数字1和数字2，我们要求按字典遍历顺序
     * 从1到2需要经过多少个数字，首先把1本身这一个数字加到step中，然后我们把范围扩大十倍，范围变成10到20之前，但是由于我们要考虑n的大小，由于
     * n为13，所以只有4个子节点，这样我们就知道从数字1遍历到数字2需要经过5个数字，然后我们看step是否小于等于k，如果是，我们cur自增1，k减去step；
     * 如果不是，说明要求的数字在子节点中，我们此时cur乘以10，k自减1，以此类推，直到k为0推出循环，此时cur即为所求：
     *
     * 妈的，十叉树
     * @param n
     * @return
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            int steps = calSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr += 1;
                k -= steps;
            } else {
                curr *= 10;
                k -= 1;
            }
        }

        return curr;
    }

    //use long in case of overflow
    public int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }

    public static void main(String[] ags){
        new Solution().ReplaceUnderlines("^(\\_)");
    }
    
    public static class Solution {
        /**
         * 替换自定字符串中的下划线为指定字符串
         * "^((^|$|[ ,+])9494)"

         * @param regularString string字符串 输入的正则表达式字符串
         * @return string字符串
         */
        public String ReplaceUnderlines (String regularString) {
            // write code here
        	int count = 0;
        	char last = '\0';
        	StringBuilder result = new StringBuilder();
        	for(int i = 0;i < regularString.length();i++) {
        		char c = regularString.charAt(i);
        		if(c == '_' && last != '\\' && count <= 0) {
        			result.append("(^|$|[ ,+])");
        		}else {
        			result.append(c);
        			if(c == '[') {
        				count ++;
        			}else if(c == ']') {
        				count --;
        			}
        		}
        		
        		last = c;
        	}
        	
        	return result.toString();
        }
    }
    
    //1#2#3$4
    //1#2#3 14#3 41
    //1#17	
    //7#6$5#12
    private static void transfer(String input) {
    	List<Integer> numList = new ArrayList<>();
    	int lastPos = -1;
    	char lastOp = '\0';
    	int firstDPos = -1;
    	for(int i = 0;i <= input.length();i++) {
    		char c = i >= input.length() ? '#' : input.charAt(i);
    		if(c == '#') {
    			if(lastOp == '#' || lastOp == '\0') {
    				numList.add(Integer.parseInt(input.substring(lastPos + 1, i)));
    			}else if(lastOp == '$') {
    				numList.add(calSub(input.substring(firstDPos, i)));
    				firstDPos = -1;
    			}
    			lastPos = i;
    			lastOp = c;
    		}else if(c == '$') {
    			if(firstDPos == -1) {
    				firstDPos = lastPos + 1;
    			}
    			
    			lastOp = c;
    		}
    	}
    	
    	int result = numList.get(0);
    	for(int i = 1;i < numList.size();i++) {
    		result = 2 * result + 3 * numList.get(i) + 4;
    	}
    	
    	System.out.println(result);
    }
    
    private static int calSub(String input) {
    	String[] nums = input.split("\\$");
    	int result = Integer.parseInt(nums[0]);
    	for(int i = 1;i < nums.length;i++) {
    		int curr = Integer.parseInt(nums[i]);
    		result = 3 * result + curr + 2;
    	}
    	
    	return result;
    }

}
