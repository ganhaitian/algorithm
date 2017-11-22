package com.ganht.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class MovingAverageFromDataStream {

    private Deque<Integer> stream;
    private int size;

    public MovingAverageFromDataStream(int size) {
        this.stream = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        this.stream.addFirst(val);
        if(this.stream.size() > this.size)
            this.stream.removeLast();

        return (double) this.stream.stream().mapToInt(t -> t).sum() / this.stream.size();
    }

    public static void main(String[] args){
        MovingAverageFromDataStream m = new MovingAverageFromDataStream(1);
        System.out.println(m.next(4));
        System.out.println(m.next(0));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

}
