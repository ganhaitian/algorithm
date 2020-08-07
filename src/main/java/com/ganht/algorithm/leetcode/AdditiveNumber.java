package com.ganht.algorithm.leetcode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 *
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number
 * in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 *              1 + 99 = 100, 99 + 100 = 199
 *
 *
 * Constraints:
 *
 * num consists only of digits '0'-'9'.
 * 1 <= num.length <= 35
 * Follow up:
 * How would you handle overflow for very large input integers?
 *
 *   Created by ganhaitian on 2016/11/22.
 */
public class AdditiveNumber {

    // 浜屽埛
    public boolean isAdditiveNumber2(String num){
        if(num.length() < 3){
            return false;
        }

        return isAdditiveNumber2(num , null, null);
    }

    // 澶氱畝娲侊紝浣嗗叾瀹炶繕鍙互鍔犱竴涓蹇樺綍
    private boolean isAdditiveNumber2(String str, BigInteger a, BigInteger b) {
        var expect = (a == null || b == null) ? null : a.add(b);
        for (int i = 1; i <= (str.startsWith("0") ? 1 : str.length()); i++) {
            var currNum = new BigInteger(str.substring(0, i));
            if (a != null && b != null) {
                int cmpResult = currNum.compareTo(expect);
                if (cmpResult < 0) {
                    continue;
                } else if (cmpResult > 0) {
                    return false;
                } else if (i >= str.length()) {
                    return true;
                }
            }

            if (i >= str.length()) {
                return false;
            }

            if (isAdditiveNumber2(str.substring(i), b, currNum)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAdditiveNumber(String num) {
        return selectNextNum(0,0,num,-2);
    }

    // 杩欎笅闈㈠啓鐨勬槸浠�涔堥浮宸寸帺鎰忓効锛孫MG
    private boolean selectNextNum(long preNum,long preSum,String str,int op){

        int nextOp = 0;
        if(op == -2){
            nextOp = -1;
        }else if(op == -1){
            nextOp = 0;
        }

        boolean cantTry = false;
        if(str.startsWith("0"))
            cantTry = true;

        String tmpSelectStr;
        long tmpSelectValue = 0;
        long tmpPreSum;
        for(int i = 1;i <= str.length();i ++){

            if(cantTry && i >= 2)
                break;

            tmpSelectStr = str.substring(0,i);

            try {
                tmpSelectValue = Long.parseLong(tmpSelectStr);
            }catch(NumberFormatException e ){
                System.out.println();
            }

            if(op == 0){
                if(tmpSelectValue < preSum)
                    continue;
                else if(tmpSelectValue > preSum)
                    break;

                if(i == str.length())
                    return true;
            }

            tmpPreSum = preNum + tmpSelectValue;
            if(selectNextNum(tmpSelectValue,tmpPreSum,str.substring(i,str.length()),nextOp))
                return true;
        }

        return false;
    }
    
    public class MinHeap {

        private int currentSize;
        private int capacity;
        private long[] queue;

        public MinHeap(int capacity)
        {
        	queue = new long[capacity];
        }

        public int size() {
        	return currentSize;
        }
        
        public void add(long x)
        {
            if(currentSize==0) {
                queue[currentSize++] = x;
            }else {
                adjustUP(currentSize++,x);
            }
        }

        public long getTop()
        {
            return queue[0];
        }

        public long remove()
        {
        	long cur = getTop();
        	long temp = queue[--currentSize];
            queue[0] = temp;
            adjustDown(0,temp);
            return cur;
        }

       private void adjustUP(int k,long value)
       {
           while(k>0)
           {
               int root = (k-1)>>>1;
               if(queue[root]<=value)
               {
                   break;
               }
               queue[k] = queue[root];
               k = root;
           }
           
           queue[k] = value;
       }


        private void adjustDown(int k,long vale)
        {
            int half = currentSize>>>1;
            while (k<half)
            {
                int child = (k<<1)+1;
                long childVal = queue[child];
                int RightChild = child+1;
                if(RightChild<currentSize && childVal>queue[RightChild])
                {
                    childVal = queue[child = RightChild];
                }
                if(childVal>vale)
                {
                    break;
                }
                queue[k] = queue[child];
                k = child;
            }
            queue[k] = vale;
        }

    }
    
    public String merge(List<List<String>> files) {
    	int N = files.size();
    	int M = files.get(0).size();
    	
    	int[] positions = new int[N];
    	MinHeap minHeap = new MinHeap(N * M);
    	Function<String, Long> parseTimeFunc = log -> Long.parseLong(log.split(" ")[0]);
    	
    	var timeMap = new HashMap<Long, Integer>();
    	for(int i = 0;i < N;i++) {
			long time = parseTimeFunc.apply(files.get(i).get(0));
			minHeap.add(time);
			timeMap.put(time, i);
		}
    	
    	StringBuilder result = new StringBuilder();
    	while(minHeap.size() > 0) {
    		long min = minHeap.remove();
    		int pos = timeMap.remove(min);
    		List<String> file = files.get(pos);
    		
    		result.append(file.get(positions[pos]++));
    		minHeap.add(parseTimeFunc.apply(file.get(positions[pos])));
    	}
    	
    	return result.toString();
    }
    
	public int findKthOddNum(int[] arr, int k) {
    	MinHeap minHeap = new MinHeap(arr.length);
    	int i = 0 ;
		for (;minHeap.size() < k; i++) {
			int num = arr[i];
			if(num % 2 == 1) {
				minHeap.add(num);
			}
		}

		// 沿用上一题当中的最小堆
		for (i = k; i < arr.length; i++) {
			int tmp = arr[i];
			if (tmp % 2 == 1 && tmp > minHeap.getTop()) {
				minHeap.remove();
				minHeap.add(tmp);
			}
		}
		
		return (int)minHeap.getTop();
	}

    public static void main(String[] args){
        System.out.println(new AdditiveNumber().isAdditiveNumber2("19928"));
    }
}
