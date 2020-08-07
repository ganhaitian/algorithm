package com.ganht.algorithm.leetcode;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int fromFirst = cost[0] + calMinCost(cost,0);
        int fromSecond = cost[1] + calMinCost(cost,1);

        return Math.min(fromFirst,fromSecond);
        /*int step = cost[1] <= cost[0] ? 1 : 0;
        int result = 0;
        while(true){
            result += cost[step];
            if(step + 2 >= cost.length)
                break;

            if(cost[step + 2] <= cost[step + 1]){
                step = step + 2;
            }else
                step = step + 1;
        }

        return result;*/
    }

    private int calMinCost(int[] cost,int start){
        if(start >= cost.length)
            return 0;

        int next1 = start + 1;
        int next1Cost = (next1 >= cost.length ? 0 : cost[next1]) + calMinCost(cost,next1);

        int next2 = start + 2;
        int next2Cost = (next2 >= cost.length ? 0 : cost[next2]) + calMinCost(cost,next2);

        return Math.min(next1Cost,next2Cost);
    }

    public static void main(String[] args){
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
