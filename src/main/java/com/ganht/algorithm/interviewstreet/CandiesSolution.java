package com.ganht.algorithm.interviewstreet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class CandiesSolution {

	public static void main(String[] args) throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader(
		new InputStreamReader(new FileInputStream(
		"/Users/ganhaitian/dep/lib/Candies_testcases/input01.txt")));
		
        String line = br.readLine();
        int N = Integer.parseInt(line);//The number of cases.
		
        class Child{
			Child pre;
			Child next;
			int rating;
			int num;//the number of the candies.
			Child(int rating){this.rating=rating;this.num=1;}
			void setNext(Child Child){this.next=Child;Child.pre=this;}
			void overlay(){
				
				if(this.num>this.next.num)
					return;
				
				this.num++;
				
				if(this.pre!=null&&this.rating<this.pre.rating)
					this.pre.overlay();
			}
		}
		
		int tmpRating=0;
		Child head=new Child(Integer.parseInt(br.readLine()));
		Child last=head;
		
		for(int i=1;i<N;i++){
			tmpRating=Integer.parseInt(br.readLine());
			Child _child=new Child(tmpRating);
			last.setNext(_child);
			last=_child;
			if(_child.rating<_child.pre.rating)
				_child.pre.overlay();
			else if(_child.rating>_child.pre.rating) 
				_child.num=_child.pre.num+1;
		}
		
		int result=0;
		
		while(head!=null){
			result+=head.num;
			head=head.next;
		}
		
		System.out.println(result);
	}
}
