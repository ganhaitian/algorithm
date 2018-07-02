package com.ganht.algorithm.leetcode;

public class TrappingRainWater {

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

}
