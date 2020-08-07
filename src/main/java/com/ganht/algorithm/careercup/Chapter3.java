package com.ganht.algorithm.careercup;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Stack;

public class Chapter3 {
	
	/**
	 * 3.5 Implement a MyQueue class which implements a queue using two stacks
	 * @author ganhaitian
	 *
	 */
	class MyQueue extends AbstractQueue<Integer>{
		Stack<Integer> enQueueStack=new Stack<Integer>();
		Stack<Integer> deQueueStack=new Stack<Integer>();
		@Override
		public boolean offer(Integer e) {
			enQueueStack.push(e);
			return true;
		}
		@Override
		public Integer peek() {
			return deQueueStack.peek();
		}
		@Override
		public Integer poll() {
			while(enQueueStack.size()>0){
				deQueueStack.push(enQueueStack.pop());
			}
			return deQueueStack.pop();
		}
		@Override
		public Iterator<Integer> iterator() {
			return null;
		}
		@Override
		public int size() {
			return enQueueStack.size()+deQueueStack.size();
		}
		
	}
	
	/**
	 * 36 Write a program to sort a stack in ascending order You should not make any assump- 
	 * tions about how the stack is implemented The following are the only functions that 
	 * should be used to write this program: push | pop | peek | isEmpty
	 * @param args
	 */
	 public static Stack<Integer> sortStack(Stack<Integer> input){
		 Stack<Integer> data=new Stack<Integer>();
		 while(!input.isEmpty()){
			 int tmp=input.pop();
			 while(!data.isEmpty()&&data.peek()>tmp){
				 input.push(data.pop());
			 }
			 data.push(tmp);
		 }
		 return data;
	 }
	
	public static void main(String[] args){
//		MyQueue myQ=new Chapter3().new MyQueue();
//		myQ.offer(5);myQ.offer(3);myQ.offer(10);myQ.offer(1);
//		while(myQ.size()>0){
//			System.out.println(myQ.poll());
//		}
		Stack<Integer> input=new Stack<Integer>();
		input.push(5);input.push(4);input.push(3);input.push(2);input.push(1);
		System.out.println(sortStack(input));
	}
	
}
