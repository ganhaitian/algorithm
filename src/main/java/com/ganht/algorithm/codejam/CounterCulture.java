package com.ganht.algorithm.codejam;

/**
 * In the Counting Poetry Slam, a performer takes the microphone, chooses a number N,
 * and counts aloud from 1 to N. That is, she starts by saying 1, and then repeatedly
 * says the number that is 1 greater than the previous number she said, stopping after
 * she has said N.
 *
 * 思路：因为翻转最多是可以在当前的位数下，提高向后数的进度，而不能跨位数。所以如果要数到比如123000
 * 那必须，要先数出10,然后到100,1000等等，直到123000对应的位数。
 * 这个题的关键是，数到过程中，判断翻转几次最合适。
 * 看google的问题分析，他相当于总结了一个定理，就是比如从1000数到9999，最快只需要翻转一次，而决定
 * 翻转的点就是把9999劈成两半，然后让左边两位数到99，翻转再让右边数到99，就是最快了。但他没有证明
 * 这个定理
 *
 * Created by ganhaitian on 2015/6/4.
 */
public class CounterCulture extends CodeJamCase{

    @Override
    protected void runCase() {

    }

    public static void main(String[] args){

    }

}
