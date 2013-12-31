package com.ganht.algorithm.acm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * A Firetruck
	
	The Center City fire department collaborates with the transportation department to maintain
	maps of the city which reflects the current status of the city streets. On any given day, several
	streets are closed for repairs or construction. Fire fighters need to be able to select routes from the
	fire stations to fires that do not use closed streets.
	
	   Central City is divided into non-overlapping fire districts, each containing a single fire station.
	When a fire is reported, a central dispatcher alerts the fire station of the district where the fire is
	located and gives a list of possible routes from the fire station to the fire. You must write a program
	that the central dispatcher can use to generate routes from the district fire stations to the fires.
	Input
	
	The city has a separate map for each fire district. Street corners of each map are identified by
	positive integers less than 21, with the fire station always on corner #1. The input file contains
	several test cases representing different fires in different districts.
		• The first line of a test case consists of a single integer which is the number of the street corner
		closest to the fire.
		• The next several lines consist of pairs of positive integers separated by blanks which are
		the adjacent street corners of open streets. (For example, if the pair 4 7 is on a line in the
		file, then the street between street corners 4 and 7 is open. There are no other street corners
		between 4 and 7 on that section of the street.)
		• The final line of each test case consists of a pair of 0’s.
		
	Output
	
	For each test case, your output must identify the case by number (CASE #1, CASE #2, etc). It must
	list each route on a separate line, with the street corners written in the order in which they appear
	on the route. And it must give the total number routes from fire station to the fire. Include only
	routes which do not pass through any street corner more than once. (For obvious reasons, the fire
	department doesn’t want its trucks driving around in circles.)
		
		Output from separate cases must appear on separate lines.
		
		The following sample input and corresponding correct output represents two test cases.

	Sample Input
	6

	0
	
	Sample Output
	CASE 1:
	1 2 3 4 6
	1 2 3 5 6
	1 2 4 3 5
	1 2 4 6
	1 3 2 4 6
	1 3 4 6
	1 3 5 6
	There are
	CASE 2:
	1 3 2 5 7
	1 3 4
	1 5 2 3 4
	1 5 7 8 9
	1 6 4
	1 6 9 8 7
	1 8 7 5 2
	1 8 9 6 4
	
	There are
	6
	7 routes from the fire station to street corner 6.
	8 9 6 4
	6 4
	5 2 3 4
	3 4
	8 routes from the fire station to street corner 4.

 * 
 * 
 */
public class Firetruck {
	
	public class StreetCorner implements Comparable<StreetCorner>{
		
		private List<StreetCorner> siblingCorners = new ArrayList<StreetCorner>();
		
		private int id;
		
		public StreetCorner(int id){
			this.id = id;
		}
		
		public void addSiblingCorners(StreetCorner _corner){
			this.siblingCorners.add(_corner);
		}
		
		public boolean equals(Object target){
			if(target instanceof StreetCorner && 
			 ((StreetCorner)target).id == this.id){
				return true;
			}else
				return false;
		}
		
		public int hashCode(){
			return new Integer(id).hashCode();
		}

		@Override
		public int compareTo(StreetCorner o) {
			return this.id - o.id;
		}
		
		public String toString(){
			return String.valueOf(id);
		}
		
		public void sortSiblings(){
			Collections.sort(this.siblingCorners);
		}
	}
	
	public void genMap(File file){
		try {
			
			FileReader     reader  = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);
			
			int targetFirePosition = 0;
			int caseCount = 0;
			List<String> siblingsInfos = new ArrayList<String>();
			
			String line = bReader.readLine();
			while(line!=null){
				line = line.trim();
				if(!line.contains(" ")){
					targetFirePosition = Integer.parseInt(line);
					System.out.println("CASE "+(++caseCount));
				}else if(line.equals("0 0")){
					genMap(targetFirePosition,siblingsInfos);
					targetFirePosition = 0;
					siblingsInfos.clear();
				}else if(line.contains(" ")){
					siblingsInfos.add(line);
				}
				line = bReader.readLine();
			}
			
			bReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void genMap(int targetFirePosition, List<String> siblingsInfos) {
		
		List<StreetCorner> allCorners = new ArrayList<StreetCorner>();
		
		for(String siblingInfo:siblingsInfos){
			String[] siblings = siblingInfo.split(" ");
			
			StreetCorner A = new StreetCorner(Integer.parseInt(siblings[0]));
			StreetCorner B = new StreetCorner(Integer.parseInt(siblings[1]));
			
			if(allCorners.contains(A)){
				A = allCorners.get(allCorners.indexOf(A));
			}else{
				allCorners.add(A);
			}
			
			if(allCorners.contains(B)){
				B = allCorners.get(allCorners.indexOf(B));
			}else{
				allCorners.add(B);
			}
			
			A.addSiblingCorners(B);
			B.addSiblingCorners(A);
			
		}
		
		//Sort it.
		Collections.sort(allCorners);
		
		List<StreetCorner> touchedCorners = new ArrayList<StreetCorner>();
		touchedCorners.add(allCorners.get(0));
		
		//Get the result.
		int routesNum = genMap(allCorners.get(0),touchedCorners,targetFirePosition);
		
		System.out.println(String.format(
			"There are %d routes from the firestation to streetcorner %d",
			routesNum,targetFirePosition)
		);
	}

	private int genMap(StreetCorner head,
			List<StreetCorner> touchedCorners,
			int target) {
		
		if(head == null)
			return -1;
	
		List<StreetCorner> siblings = head.siblingCorners;
		int routesNum = 0;
		
		for(StreetCorner sibling:siblings){
			if(sibling.id == target){
				StreetCorner targetCorner = new StreetCorner(target);
				touchedCorners.add(targetCorner);
				print(touchedCorners);
				touchedCorners.remove(targetCorner);
				routesNum ++;
			}else{
				if(!touchedCorners.contains(sibling)){
					touchedCorners.add(sibling);
					routesNum += genMap(sibling,touchedCorners,target);
					touchedCorners.remove(sibling);
				}
			}
		}
		
		return routesNum;
	}

	private void print(List<StreetCorner> touchedCorners) {
		System.out.println(touchedCorners);
	}
	
	public static void main(String[] args){
		new Firetruck().genMap(
			new File(
				Firetruck.class.getClassLoader().
				getResource("firetruck.input").
				getPath()
		));
	}
	
	
}


