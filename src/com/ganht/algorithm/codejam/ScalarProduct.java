package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.Arrays;

/**
 *  
	You are given two vectors v1=(x1,x2,...,xn) and v2=(y1,y2,...,yn). The scalar product of these 
	vectors is a single number, calculated as x1y1+x2y2+...+xnyn.
	
	Suppose you are allowed to permute the coordinates of each vector as you wish. Choose two permutations
	such that the scalar product of your two new vectors is the smallest possible, and output that 
	minimum scalar product.
	
	Input
	
	The first line of the input file contains integer number T - the number of test cases. For each 
	test case, the first line contains integer number n. The next two lines contain n integers each, 
	giving the coordinates of v1 and v2 respectively.
	Output
	
	For each test case, output a line
	
	Case #X: Y
	where X is the test case number, starting from 1, and Y is the minimum scalar product of all 
	permutations of the two given vectors.
	Limits
	
	Small dataset
	
	T = 1000
	1 ≤ n ≤ 8
	-1000 ≤ xi, yi ≤ 1000
	
	Large dataset
	
	T = 10
	100 ≤ n ≤ 800
	-100000 ≤ xi, yi ≤ 100000
	
	Sample
	
	Input 
	 	
	Output 
	 
	2
	3
	1 3 -5
	-2 4 1
	5
	1 2 3 4 5
	1 0 1 0 1
	
	Case #1: -25
	Case #2: 6

 * @author gan
 *
 */
public class ScalarProduct extends CodeJamCase {

	private void calculateCase(int caseIndex,String[] caseContent ){
		
		int result = 0;
		int vectorLength = Integer.parseInt(caseContent[0]);
		String vector1Str = caseContent[1];
		String vector2Str = caseContent[2];
		
		int[] vector1 = transVectorStringToArray(vector1Str,vectorLength);
		int[] vector2 = transVectorStringToArray(vector2Str,vectorLength);
		
		Arrays.sort(vector1);
		Arrays.sort(vector2);
		
		for(int i = 0;i < vector1.length ;i++){
			result += vector1[i] * vector2[vectorLength - i -1];
		}
		
		/*int positiveStartIndex = -1;
		int positiveEndIndex = -1;
		int negtiveStartIndex = -1;
		int negtiveEndIndex = -1;
		
		for(int index = 0;index < vector2.length;index++){
			if(vector2[index] >= 0 && positiveStartIndex == -1){
				positiveStartIndex = index;
				negtiveEndIndex = index - 1;
			}
			if(vector2[index] < 0 && negtiveStartIndex == -1){
				negtiveStartIndex = index;
			}
		}
		
		if(positiveStartIndex >= 0)
			positiveEndIndex = vectorLength - 1;
		else{
			negtiveEndIndex = vectorLength - 1;
		}
		
		for(int v1e:vector1){
			if(v1e < 0 ){
				if(positiveStartIndex >= 0 && positiveStartIndex <= positiveEndIndex)
					result += v1e * vector2[positiveEndIndex--];
				else{
					result += v1e * vector2[negtiveEndIndex--]; 
				}
			}
			if(v1e >= 0){
				if(positiveStartIndex >= 0 && positiveStartIndex <= positiveEndIndex ){
					result += v1e * vector2[positiveEndIndex--];
				}else{	
					result += v1e * vector2[negtiveEndIndex--];
				}
			}
		}*/
		
		System.out.println(String.format("Case #%d: %d",caseIndex,result));
		
	}
	
	private int[] transVectorStringToArray(String vectorString,int vectorLength){
		int[] vector = new int[vectorLength];
		int index = 0;
		for(String vectorElement:vectorString.split(" ")){
			vector[index++] = Integer.parseInt(vectorElement);
		}
		return vector;
	}
	
	@Override
	protected void runCase() {
		parseInput(new File("G:\\dep\\minimum-scalar-product.in"), new InputCaseLineParser() {
			
			@Override
			public void parseLine(int caseIndex, String[] line) {
				try{
					calculateCase(caseIndex,line);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			@Override
			public int getCaseLineNumber() {
				return 3;
			}
		});
	}

	public static void main(String[] args){
		new ScalarProduct().runCase();
	}
	
}
