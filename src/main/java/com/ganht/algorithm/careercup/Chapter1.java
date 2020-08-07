package com.ganht.algorithm.careercup;

public class Chapter1 {
	
	public static boolean checkUnique(String input){
		
		int tmp=1;
		for(int i=0;i<input.length();i++){
			if(tmp%input.charAt(i)!=0)
				tmp=input.charAt(i)*tmp;				
			else
				return false;
		}
		
		return true;
	}
	
	public static boolean isUniqueChars2(String str) {
		  boolean[] char_set = new boolean[256];
		  for (int i = 0; i < str.length(); i++) {
		    int val = str.charAt(i);
		    if (char_set[val]) return false;
		    char_set[val] = true;
		  }
		  return true;
	}
	
	public static boolean isUniqueChars(String str) {
		  int checker = 0;
		  for (int i = 0; i < str.length(); ++i) {
		    int val = str.charAt(i) - 'a';
		    if ((checker & (1 << val)) > 0) return false;
		    checker |= (1 << val);
		  }
		  return true;
	}
	
	public static String reverse(String input){
		char[] result=new char[input.length()];
		for(int i=0;i<input.length();i++){
			result[input.length()-i-1]=input.charAt(i);
		}
		return new String(result);
	}

	public static String deleteDuplicate(String input){
		int tmp=1;
		StringBuilder result=new StringBuilder();
		for(int i=0;i<input.length();i++){
			if(tmp%input.charAt(i)!=0){
				tmp=input.charAt(i)*tmp;
				result.append(input.charAt(i));
			}
		}
		return result.toString();
	}
	
	public static String replaceBlank(String input){
		return input.replaceAll(" ","%20");
	}
	
	public static int[][] rotateImage(int[][] input){
		int[][] result=new int[input.length][input.length];
		for(int i=0;i<input.length;i++){
			for(int j=0;j<input[i].length;j++){
				result[j][input.length-1-i]=input[i][j];
			}
		}
		return result;
	}
	
	public static void setZeros(int[][] matrix) {
		    int[] row = new int[matrix.length]; 
		    int[] column = new int[matrix[0].length];
		    // Store the row and column index with value 0
		    for (int i = 0; i < matrix.length; i++) {
		      for (int j = 0; j < matrix[0].length;j++) {
		        if (matrix[i][j] == 0) {
		          row[i] = 1; 
		          column[j] = 1;
		         }
		      }
		    }
		 
		    // Set arr[i][j] to 0 if either row i or column j has a 0
		    for (int i = 0; i < matrix.length; i++) {
		      for (int j = 0; j < matrix[0].length; j++) {
		        if ((row[i] == 1 || column[j] == 1)) {
		          matrix[i][j] = 0;
		        }
		      }
		    }
	}
	
	public static int[][] setMatrixValue(int[][] input){
		return null;
	}
	
	public static boolean isRotation(String s1,String s2){
		String _s1=s1+s1;
		return isSubtring(_s1,s2);
	}
	
	private static boolean isSubtring(String s1,String s2){
		return s1.indexOf(s2)>0;
	}
	
	public static void main(String[] args){

//		int[][] result=rotateImage(
//		new int[][]
//		{{4,5,9,7,1},
//		 {7,8,0,-1,3},
//		 {6,10,4,2,3},
//		 {2,9,7,4,2},
//		 {3,8,1,6,4}});
		
//		for(int i=0;i<result.length;i++){
//			for(int j=0;j<result.length;j++){
//				System.out.print(result[i][j]+",");
//			}
//			System.out.println();
//		}
		
		System.out.println(isRotation("ercupcare","careercup"));
		
	}

}
