package com.ganht.algorithm.codejam;

public interface InputCaseBlockParser {

	public void parseLine(int caseIndex,String[] line);
	
	public int getCaseLineNumber();
	
}
