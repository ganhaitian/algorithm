package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
	A company is located in two very tall buildings. The company intranet connecting the buildings consists 
	of many wires, each connecting a window on the first building to a window on the second building.
	
	You are looking at those buildings from the side, so that one of the buildings is to the left and one 
	is to the right. The windows on the left building are seen as points on its right wall, and the windows
	 on the right building are seen as points on its left wall. Wires are straight segments connecting a 
	 window on the left building to a window on the right building.
	
	
	
	You've noticed that no two wires share an endpoint (in other words, there's at most one wire going out 
	of each window). However, from your viewpoint, some of the wires intersect midway. You've also noticed 
	that exactly two wires meet at each intersection point.
	
	On the above picture, the intersection points are the black circles, while the windows are the white circles.
	
	How many intersection points do you see?
	
	Input
	
	The first line of the input gives the number of test cases, T. T test cases follow. Each case begins 
	with a line containing an integer N, denoting the number of wires you see.
	
	The next N lines each describe one wire with two integers Ai and Bi. These describe the windows that 
	this wire connects: Ai is the height of the window on the left building, and Bi is the height of the 
	window on the right building.
	
	Output
	
	For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) 
	and y is the number of intersection points you see.
	
	Limits
	
	1 ≤ T ≤ 15.
	1 ≤ Ai ≤ 104.
	1 ≤ Bi ≤ 104.
	Within each test case, all Ai are different.
	Within each test case, all Bi are different.
	No three wires intersect at the same point.
	
	Small dataset
	
	1 ≤ N ≤ 2.
	
	Large dataset
	
	1 ≤ N ≤ 1000.
	
	Sample
	
	
	Input 
	 	
	Output 
	 
	2
	3
	1 10
	5 5
	7 7
	2
	1 1
	2 2
	Case #1: 2
	Case #2: 0


 * @author gan
 *
 */
public class RopeIntranet extends CodeJamCase {

	private int caseNumber = 0;
	private int caseEndLineNumber = 0;
	private List<Wire> wires = new ArrayList<Wire>();
	private List<Integer> rightPoints = new ArrayList<Integer>();
	private int intersections = 0;
	private int caseIndex = 0;
	
	private class Wire implements Comparable<Wire>{
		public int left;
		public int right;
		@Override
		public int compareTo(Wire o) {
			if(o!=null){
				if(this.left < o.left)
					return -1;
				else if(this.left > o.left)
					return 1;
				else
					return 0;
			}
			return 1;
		}
		
		public String toString(){
			return "left:"+left+",right:"+right;
		}
	}
	
	private void runAlgorithm(int lineNumber,String line){
		if(lineNumber == 1){
			this.caseNumber = Integer.parseInt(line);
		}else{
			if(caseEndLineNumber == 0){
				caseEndLineNumber = lineNumber + Integer.parseInt(line);
			}else if(lineNumber <= caseEndLineNumber){
				String[] wireInfo = line.split(" ");
				Wire wire = new Wire();
				wire.left  = Integer.parseInt(wireInfo[0]);
				wire.right = Integer.parseInt(wireInfo[1]);
				rightPoints.add(wire.right);
				wires.add(wire);
			}
			
			if(lineNumber == caseEndLineNumber){
				caseEndLineNumber = 0;
				
				Collections.sort(wires);
				Collections.sort(rightPoints);
				
				if(wires.size() >= 2){
					for(int index = 0;index < wires.size() ; index++){
						Wire wire1 = wires.get(index);
						for(int y = index + 1;y < wires.size();y++){
							Wire wire2 = wires.get(y);
							int tmp = wire1.right - wire2.right + wire2.left - wire1.left;
							if(tmp == 0)
								continue;
							double _x = ((double)(wire2.left - wire1.left))/(double)(tmp);
							if(_x < 1 && _x > 0){
								intersections ++;
							}
						}
						/*int smallerPoints = rightPoints.indexOf(wire.right);
						intersections += Math.min(wires.size() - (index + 1), smallerPoints);*/
					}
				}
				
				wires.clear();
				rightPoints.clear();
				
				System.out.println(String.format("Case #%d: %d",++caseIndex,intersections));
				
				intersections = 0;
			}
		}
		
	}
	
	@Override
	protected void runCase() {
		super.parseInput(new File("G:\\dep\\code-jam-case\\rope-intranet-large.in"), new InputCaseLineParser() {
			
			@Override
			public void parseLine(int lineNumber, String line) {
				runAlgorithm(lineNumber,line);
			}
		});
	}
	
	public static void main(String[] args){
		new RopeIntranet().runCase();
	}

}
