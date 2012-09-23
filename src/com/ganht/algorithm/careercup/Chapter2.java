package com.ganht.algorithm.careercup;

import java.util.Hashtable;

public class Chapter2 {
	
	//链表结点类
	class Node{
		Node next=null;
		int data;
		public Node(int d){this.data=d;}
		void appendToTail(int d){
			Node end=new Node(d);
			Node n=this;
			while(n.next!=null){n=n.next;}
			n.next=end;
		}
	}
	
	//删除链表中的一个结点
	Node deleteNode(Node head,int d){
		
		Node n=head;
		if(n.data==d){
			return head.next;
		}
		
		while(n.next!=null){
			if(n.next.data==d){
				n.next=n.next.next;
				return head;
			}
			n=n.next;
		}
		
		return head;
	}
	
	/**
	 *  Write code to remove duplicates from an unsorted linked list 
		FOLLOW UP
		How would you solve this problem if a temporary buffer is not allowed? 
	 */
	public void removeDuplicatedNode(Node n){
		
//		Hashtable<Integer,Boolean> buffer=new Hashtable<Integer,Boolean>();
//		Node previous=null;
//		while(n!=null){
//			if(buffer.containsKey(n.data)) previous.next=n.next;
//			else{
//				buffer.put(n.data,true);
//				previous=n;
//			}
//			n=n.next;
//		}
		
		Node head=n;
		Node tmp=head;
		while(n!=null){
			Node previous=null;
			while(tmp!=n){
				if(tmp.data==n.data){
					if(previous!=null) previous.next=tmp.next;
					else head=n;
					break;
				}					
				previous=tmp;
				tmp=tmp.next;
			}
			tmp=head;
			n=n.next;
		}
		
	}
	
	/**
	 * Implement an algorithm to find the nth to last element of a singly linked list.
	 * Assumption: The minimum number of nodes in list is n 
		Algorithm:
		1 Create two pointers, p1 and p2, that point to the beginning of the node 
		2 Increment p2 by n-1 positions, to make it point to the nth node from the beginning (to 
		  make the distance of n between p1 and p2) 
		3 Check for p2->next == null if yes return value of p1, otherwise increment p1 and p2  
		  If next of p2 is null it means p1 points to the nth node from the last as the distance 
		  between the two is n 
		4 Repeat Step 3  
	 * @param head
	 * @param n
	 * @return
	 */
	public Node nthToLast(Node head,int n){
		
		if(head==null||n<1)
			return null;
		Node p1=head;
		Node p2=head;
		for(int j=0;j<n-1;++j){
			if(p2==null)
				return null;
			p2=p2.next;
		}
		
		while(p2.next!=null){
			p1=p1.next;
			p2=p2.next;
		}
		
		return p1;
	}
	
}
