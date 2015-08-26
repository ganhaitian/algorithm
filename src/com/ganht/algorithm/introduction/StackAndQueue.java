package com.ganht.algorithm.introduction;

/**
 * 栈与队列
 * Created by ganhaitian on 15/7/20.
 */
public class StackAndQueue {

    // 栈的实现，其实是基于单链表
    public static class Stack<T extends Comparable> implements Comparable<Stack> {

        //private Stack<T> minStack = new Stack<T>();
        private int size;
        private String name;

        public Stack(String name) {
            this.name = name;
        }

        public Stack() {
            this(null);
        }

        @Override
        public int compareTo(Stack t) {
            return 1;
        }

        public class Node<T> {
            T data;
            Node<T> next;

            public Node(T t) {
                this.data = t;
            }
        }

        Node<T> top;

        public T pop() {
            if (top != null) {
                T data = top.data;
                top = top.next;

                // 处理最小值的栈
                //if (minStack.peek().equals(top)) {
                //    minStack.pop();
                //}

                size--;
                return data;
            } else {
                return null;
            }
        }

        public void push(T item) {
            //if (minStack.size() == 0 || item.compareTo(min()) < 0) {
            //    minStack.push(item);
            //}

            Node<T> newNode = new Node<T>(item);
            newNode.next = top;
            top = newNode;

            size++;
        }

        public T peek() {
            if (top != null) {
                return top.data;
            }
            return null;
        }

        public T min() {
            //return minStack.peek();
            return null;
        }

        public int size() {
            return size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * 把一个数组拆成三个栈来表示
     * 感觉太简单了，所以先略过了
     */
    public void oneArrayTo3Stacks() {

    }

    /**
     * How would you design a stack which, in addition to push and pop,
     * also has a function min which returns the minimum element? Push,
     * pop and min should all operate in 0(1) time.
     */
    public void designStack() {
        // 这个题，直接见这个类的Stack子类
        // 思路就是用一个私有变量来记录
        // fuck，我把这个问题想得实在是太简单了
        // 如果只是一直往里面加元素，还好办，但是如果有中途弹出元素的情况呢，需要回滚这个值
        // 如果一个问题太简单了，通常都是有陷井的
    }

    /**
     * Imagine a (literal) stack of plates. If the stack gets too high,
     * it migh t topple. Therefore, in real life, we would likely start
     * a new stack when the previous stack exceeds some threshold. Implement
     * a data structure SetOfStacks that mimics this. SetOf- Stacks
     * should be composed of several stacks and should create a new stack once
     * the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks. pop()
     * should be have identically to a single stack(that is,pop() should return
     * the same values as it would if there were just a single stack).
     */
    public void stackOfPlates() {

    }

    public class SetOfStacks {

        private Stack<Stack<Integer>> stackSets;
        private int maxLength;

        public SetOfStacks(int maxLength) {
            this.maxLength = maxLength;
        }

        public void push(Integer e) {
            Stack<Integer> currStack = stackSets.peek();
            if (currStack == null) {
                currStack = new Stack<Integer>();
                stackSets.push(currStack);
            } else {
                if (currStack.size() >= maxLength) {
                    currStack = new Stack<Integer>();
                    stackSets.push(currStack);
                }
            }
            currStack.push(e);
            //10.5.100.100
        }

        public Integer pop() {
            Stack<Integer> currStack = stackSets.peek();
            if (currStack == null)
                return null;
            else {
                Integer e = currStack.pop();
                if (currStack.size() == 0) {
                    stackSets.pop();
                }
                return e;
            }
        }
    }

    /**
     * In the classic problem of the Towers of Hanoi, you have 3 towers and N
     * disks of different sizes which can slide onto any tower. The puzzle starts
     * with disks sorted in ascending order of size from top to bottom
     * (i.e., each disk sits on top of an even larger one).
     * You have the following constraints:
     * <p/>
     * (1) Only one disk can be moved at a time.
     * (2) A disk is slid off the top of one tower onto the next rod.
     * (3) A disk can only be placed on top of a larger disk.
     * <p/>
     * Write a program to move the disks from the first tower to the last using Stacks.
     */
    public void towersOfHanoi(int towerHeight) {
        // 貌似需要用
        new HanoiTower(towerHeight).run();
    }

    public class HanoiTower {

        private Stack<Integer> tower1 = new Stack<Integer>("1");
        private Stack<Integer> tower2 = new Stack<Integer>("2");
        private Stack<Integer> tower3 = new Stack<Integer>("3");

        public HanoiTower(int initialHeight) {
            for (int i = initialHeight; i > 0; i--) {
                tower1.push(i);
            }
        }

        public void run() {
            move(tower1, tower3, tower2, tower1.size());
        }

        public void move(Stack<Integer> from, Stack<Integer> to, Stack<Integer> trans, int nums) {
            if (nums == 1) {
                Integer e = from.pop();
                to.push(e);

                System.out.println("move:" + e + ",from t" + from.getName() + " to t" + to.getName());
            } else {
                move(from, trans, to, nums - 1);
                move(from, to, trans, 1);
                move(trans, to, from, nums - 1);
            }
        }
    }

    /**
     * Implement a MyQueue class which implements a queue using two stacks.
     */
    public void queueStack() {
        //见MyQueue class
    }

    public class MyQueue {

        private Stack<Integer> stack1 = new Stack<Integer>();
        private Stack<Integer> stack2 = new Stack<Integer>();

        public MyQueue() {

        }

        public Integer pop() {
            if (stack2.size() > 0) {
                return stack2.pop();
            } else {
                while (stack1.size() > 0) {
                    stack2.push(stack1.pop());
                }
                if (stack2.size() > 0) {
                    return stack2.pop();
                } else
                    return null;
            }
        }

        public void push(Integer e) {
            stack1.push(e);
        }
    }

    /**
     * Write a program to sort a stack in ascending order (with biggest items on top).
     * You may use at most one additional stack to hold items, but you may not copy
     * the elements into any other data structure (such as an array). The stack
     * supports the following operations: push, pop, peek, and isEmpty
     */
    public void sortStack(Stack<Integer> stack) {
        Stack<Integer> adtStack = new Stack<Integer>();
        int tmpE;
        while (stack.size() > 0) {
            tmpE = stack.pop();

            if (adtStack.peek() == null) {
                adtStack.push(tmpE);
                continue;
            }

            while (adtStack.peek() != null && tmpE > adtStack.peek()) {
                //tmpE = stack.pop();
                stack.push(adtStack.pop());
            }

            adtStack.push(tmpE);
        }

        while(adtStack.size() > 0){
            stack.push(adtStack.pop());
        }
    }

    /**
     * An animal shelter holds only dogs and cats, and operates on a strictly
     * "first in, first out" basis. People must adopt either the "oldest"
     * (based on arrival time) of all animals at the shelter, or they can select
     * whether they would prefer a dog or a cat (and will receive the oldest
     * animal of that type). They cannot select which specific animal they would
     * like. Create the data structures to maintain this system and implement
     * operations such as enqueue, dequeue Any, dequeue Dog and dequeue Cat.
     * You may use the built-in LinkedList data structure
     */
    public void animalShelter(){
       // 见AnimalShelter类
       // 如果用linkedlist现成的api遍历当然简单，关键是这样很复杂，要越过这个

    }

    public class AnimalShelter{

        public void dequeueAny(){

        }

        public void dequeueDog(){

        }

        public void dequeueCat(){

        }
    }

    public static void main(String[] args) {
        //new StackAndQueue().towersOfHanoi(6);
        Stack<Integer> test1 = new Stack<Integer>();
        test1.push(3);
        test1.push(1);
        test1.push(10);
        test1.push(11);
        test1.push(7);

        new StackAndQueue().sortStack(test1);
    }
}
