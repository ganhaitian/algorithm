package com.ganht.algorithm.introduction;

public class Sort {

	public static void insertionSort(int[] input){
		if(input == null)
			throw new IllegalArgumentException();
		
		int key = 0;
		int i = 0;
		for(int j = 1;j < input.length;j++){
			i = j - 1;
			key = input[j];
			while(i >= 0 && key < input[i]){
				input[i+1] = input[i]; 
				i = i - 1;
			}
			input[i+1] = key;
		}
		
	}
	
	public static void main(String[] args){
		int[] testCase = {5,2,4,6,1,3};
		insertionSort(testCase);
	}
	
}
