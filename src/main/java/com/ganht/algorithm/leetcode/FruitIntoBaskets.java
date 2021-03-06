package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2,
 * then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type
 * of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 * @author haitian.gan
 */
public class FruitIntoBaskets {

    /**
     * leetcode上面的例题稍微优雅一点，相当于是做了两个指针
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        if(tree == null || tree.length <= 0){
            return 0;
        }

        int previousType = -1;
        int continueNum = 0;
        Map<Integer, Integer> tmpBasket = new HashMap<>();
        int tmpSumMax = 1;
        for (int type : tree) {
            if (!tmpBasket.containsKey(type) && tmpBasket.size() >= 2) {
                tmpBasket = new HashMap<>();
                tmpBasket.put(previousType, continueNum);
            }

            if(type == previousType){
                continueNum ++;
            }else{
                previousType = type;
                continueNum = 1;
            }

            tmpBasket.compute(type, (k, v) -> v == null ? 1 : v + 1);
            // 计算最大值
            int total = tmpBasket.values().stream().mapToInt(v -> v).sum();
            if (total > tmpSumMax) {
                tmpSumMax = total;
            }
        }

        return tmpSumMax;
    }

    public static void main(String[] args) {
        //int[] test = {1,2,1};
        //int[] test = {1,2,3,2,2};
        int[] test = {0,1,2,2};
        //int[] test = {3,3,3,1,2,1,1,2,3,3,4};
        //int[] test = {1, 0, 1, 4, 1, 4, 1, 2, 3};
        System.out.println(new FruitIntoBaskets().totalFruit(test));
    }

}
