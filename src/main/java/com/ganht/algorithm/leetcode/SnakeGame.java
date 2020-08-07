package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not
 * familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's
 * score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten
 * by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 *
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 * @author haitian.gan
 */
public class SnakeGame {

    private int                 width;
    private int                 height;
    private int[][]             food;
    private int                 score;
    private int[][]             blocks;
    private int                 foodIndex;
    private int[][]             currPos;
    private LinkedList<int[][]> snakeBlocks = new LinkedList<>();

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;

        this.blocks = new int[height][width];
        this.blocks[0][0] = 1;

        if(food.length > 0){
            this.blocks[food[0][0]][food[0][1]] = 2;
        }

        this.foodIndex = 0;
        this.currPos = new int[][]{{0, 0}};
        this.snakeBlocks.addLast(this.currPos);
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int[][] newCurrPos = null;
        switch (direction) {
            case "U":
                if (currPos[0][0] == 0) {
                    return -1;
                }

                newCurrPos = new int[][]{{currPos[0][0] - 1, currPos[0][1]}};
                break;
            case "L":
                if (currPos[0][1] == 0) {
                    return -1;
                }

                newCurrPos = new int[][]{{currPos[0][0], currPos[0][1] - 1}};
                break;
            case "R":
                if (currPos[0][1] == width - 1) {
                    return -1;
                }

                newCurrPos = new int[][]{{currPos[0][0], currPos[0][1] + 1}};
                break;
            case "D":
                if (currPos[0][0] == height - 1) {
                    return -1;
                }

                newCurrPos = new int[][]{{currPos[0][0] + 1, currPos[0][1]}};
                break;
            default:
                return -1;
        }

        boolean needClearTail = false;
        boolean collideSelf   = false;
        // 判断新位置的情况
        int newPosValue = blocks[newCurrPos[0][0]][newCurrPos[0][1]];
        if(newPosValue == 1){
            // 什么都不做
            collideSelf = true;
        }else if(newPosValue == 2){
            blocks[newCurrPos[0][0]][newCurrPos[0][1]] = 1;

            // 把新的食物弹出来
            this.foodIndex ++;
            if(this.foodIndex < food.length){
                blocks[food[foodIndex][0]][food[foodIndex][1]] = 2;
            }

            // 加分
            this.score ++;
        }else{
            blocks[newCurrPos[0][0]][newCurrPos[0][1]] = 1;
            needClearTail = true;
        }

        // 撞到自己就不用
        if(!collideSelf){
            snakeBlocks.addLast(newCurrPos);
        }

        if(needClearTail){
            // 要把结尾清掉
            int[][] tailPos = snakeBlocks.pollFirst();
            if(tailPos != null){
                blocks[tailPos[0][0]][tailPos[0][1]] = 0;
            }
        }

        this.currPos = newCurrPos;
        return this.score;
    }

    public static void main(String[] args) {
        int[][]   food         = {{2, 0}, {0, 0}, {0, 2}, {2, 2}};
        SnakeGame game         = new SnakeGame(3, 3, food);
        String[]  moveSequence = {"D", "D", "R", "U", "U", "L", "D", "R", "R", "U", "L", "D"};
        for (String direction : moveSequence) {
            System.out.println(game.move(direction));

            for(int[] block : game.blocks){
                System.out.println(Arrays.toString(block));
            }
        }
    }

}
