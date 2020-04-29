package com.ganht.algorithm.leetcode;

/**
 * @author haitian.gan
 */
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        int lastBuy = 0;
        int totalProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int nextDay = i + 1;
            if (nextDay == prices.length || prices[nextDay] < prices[i]) {
                if(i > lastBuy){
                    totalProfit += prices[i] - prices[lastBuy];
                }

                lastBuy = nextDay;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int[] input = {7,6,4,3,1,5};
        new BestTimeToBuyAndSellStockII().maxProfit(input);
    }

}
