package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.TreeSet;

/*** 
	Problem A. Store Credit
	This contest is open for practice. You can try every problem as many times as you like, 
	though we won't keep track of which problems you solve. Read the Quick-Start Guide to 
	get started.
	
	Small input
	8 points	
	Large input
	25 points	
	
	Problem
	
	You receive a credit C at a local store and would like to buy two items. You first walk 
	through the store and create a list L of all available items. From this list you would 
	like to buy two items that add up to the entire value of the credit. The solution you 
	provide will consist of the two integers indicating the positions of the items in your 
	list (smaller number first).
	
	Input
	
	The first line of input gives the number of cases, N. N test cases follow. For each test case there will be:
	
	    One line containing the value C, the amount of credit you have at the store.
	    One line containing the value I, the number of items in the store.
	    One line containing a space separated list of I integers. Each integer P indicates the price of an item in the store.
	    Each test case will have exactly one solution.
	
	Output
	
	For each test case, output one line containing "Case #x: " followed by the indices of the
	 two items whose price adds up to the store credit. The lower index should be output first.
	
	Limits
	
	5 ≤ C ≤ 1000
	1 ≤ P ≤ 1000
	
	Small dataset
	
	N = 10
	3 ≤ I ≤ 100
	
	Large dataset
	
	N = 50
	3 ≤ I ≤ 2000
	
	Sample
	
	Input
		
	Output
	3
	100
	3
	5 75 25
	200
	7
	150 24 79 50 88 345 3
	8
	8
	2 1 9 4 4 56 90 3
	
	Case #1: 2 3
	Case #2: 1 4
	Case #3: 4 5
 * @author gan
 *
 */
public class StoreCredit extends CodeJamCase{

	public static void main(String[] args){
		new StoreCredit().runCase();
	}

	private void calculate(int caseIndex,String[] caseContent){
		
		int target = Integer.parseInt(caseContent[0]);
		TreeSet<Integer> itemsByPriceOrder = new TreeSet<Integer>();
		int itemNumber = Integer.parseInt(caseContent[1]);
		int tmpItemPrice = 0;
		
		for(String item:caseContent[2].split(" ")){
			tmpItemPrice = Integer.parseInt(item);
			if(tmpItemPrice >= target)
				continue;
			else{
				itemsByPriceOrder.add(tmpItemPrice);
			}
		}
		
		int i = 0;
		int j = itemsByPriceOrder.size() - 1;
		Integer[] items = new Integer[itemNumber];
		itemsByPriceOrder.toArray(items);
		
		while(i < j){
			if(items[i] + items [j] == target){
				System.out.println(String.format("Case #%d: %d %d",caseIndex,i,j));
				break;
			}else if(items[i] + items[j] < target)
				i++;
			else{
				j--;
			}
		}
	}
	
	@Override
	protected void runCase() {
		parseInput(new File("G:\\dep\\A-small-practice.in"),new InputCaseLineParser(){
			
			public void parseLine(int caseIndex,String[] caseContent) {
				calculate(caseIndex,caseContent);
			}

			public int getCaseLineNumber() {
				return 3;
			}
			
		});
	}
	
}
