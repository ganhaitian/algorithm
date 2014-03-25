package com.ganht.algorithm.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaseInputReader {

	private BufferedReader bufferedRader;
	
	public CaseInputReader(BufferedReader bufferedReader){
		this.bufferedRader = bufferedReader;
	}
	
	public List<String> readLines(int lineNumber){
		if(bufferedRader != null){
			String tmpLine = null;
			List<String> result = new ArrayList<String>();
			for(int index = 0;index < lineNumber;index ++){
				try {
					tmpLine = bufferedRader.readLine();
					if(tmpLine != null){
						result.add(tmpLine);
					}else
						return result;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
		return null;
	}
	
	public void close() throws IOException{
		bufferedRader.close();
	}
	
	public static CaseInputReader build(BufferedReader bufferedReader){
		return new CaseInputReader(bufferedReader);
	}
	
}
