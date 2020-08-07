package com.ganht.algorithm.leetcode;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
 * are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    /**
     * 思路好巧妙呀，左边捋一下，右边捋一下，求交集
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int maxHeight = 0;
        for (int h : height) {
            if (h > maxHeight)
                maxHeight = h;
        }

        int result = 0;
        int[][][] tmp = new int[height.length][maxHeight][4];
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < height.length; x++) {
                int[] block = tmp[x][y];
                if (height[x] > y) {
                    if(y < maxHeight - 1)
                        tmp[x][y + 1][2] = 1;

                    if(x < height.length - 1)
                        tmp[x + 1][y][3] = 1;
                }else{

                }

                if (height[x + 1] > y) {
                    block[1] = 1;
                }

                if (block[1] == 1 && block[3] == 1 && (y == 0 || block[2] == 1)) {
                    tmp[x][y + 1][2] = 1;
                }
            }
        }

        return 0;
    }

    public int trap1(int[] height){
        int maxHeight = 0;
        for (int h : height) {
            if (h > maxHeight)
                maxHeight = h;
        }

        int[][] tmp = new int[height.length][maxHeight];
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height[i]; j++) {
                tmp[i][j] = 1;// 1表示是bar
            }
        }

        /*for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < height.length; j++) {
                if(){

                }

                tmp[j][i]
            }
        }*/

        return 0;
    }

}
