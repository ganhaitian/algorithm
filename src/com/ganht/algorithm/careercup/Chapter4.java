package com.ganht.algorithm.careercup;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *  How to Approach:
 *  ----------------------------------------------------------------------------------------------
	Trees and graphs questions typically come in one of two forms:
	
	1 Implement a tree / find a node / delete a node / other well known algorithm
	2 Implement a modification of a known algorithm
	
	Either way, it is strongly recommended to understand the important tree algorithms prior to 
	your interview If you’re fluent in these, it’ll make the tougher questions that much easier! 
	We’ll list some of the most important
	
	WARNING: Not all binary trees are binary search trees
	----------------------------------------------------------------------------------------------
	When given a binary tree question, many candidates assume that the interviewer means 
	“binary search tree”, when the interviewer might only mean“binary tree” So, listen carefully 
	for that word “search ” If you don’t hear it, the interviewer may just mean a binary tree with 
	no particular ordering on the nodes If you aren’t sure, ask
	
	Binary Trees—”Must Know” Algorithms
	----------------------------------------------------------------------------------------------
	You should be able to easily implement the following algorithms prior to your interview:
	» In-Order: Traverse left node, current node, then right [usually used for binary search trees]
	» Pre-Order: Traverse current node, then left node, then right node
	» Post-Order: Traverse left node, then right node, then current node
	» Insert Node: On a binary search tree, we insert a value v, by comparing it to the root If v
	 > root, we go right, and else we go left We do this until we hit an empty spot in the tree
	Note: balancing and deletion of binary search trees are rarely asked, but you might want to have 
	some idea how they work It can set you apart from other candidates
	
	Graph Traversal—”Must Know” Algorithms
	----------------------------------------------------------------------------------------------
	You should be able to easily implement the following algorithms prior to your interview:
	» Depth First Search: DFS involves searching a node and all its children before proceed- ing to 
	  its siblings
	» Breadth First Search: BFS involves searching a node and its siblings before going on to any 
	  children
	  
 * @author ganhaitian
 *
 */
public class Chapter4 {
	
	class Node<E>{
		E data;
		Node<E> left;
		Node<E> right;
		Node(E e){
			this.data=e;
		}
	}
	
	class Tree<T>{
		Node<T> root;
		Tree(Node<T> root){
			this.root=root;
		}
		private int getHeight(Node<T> n){
			
			if(n==null)
				return 0;
			else{
				return 1+Math.max(getHeight(n.left),getHeight(n.right));
			}
		}
		Tree<T> getLeftTree(){
			return root==null?null:new Tree<T>(root.left);
		}
		Tree<T> getRightTree(){
			return root==null?null:new Tree<T>(root.right);
		}
		int getHeight(){
			if(root==null)
				return 0;
			else
				return getHeight(root);
		}
	}
	
	/**
	 * 4.1 Implement a function to check if a tree is balanced For the purposes of this question, 
	 * a balanced tree is defined to be a tree such that no two leaf nodes differ in distance from 
	 * the root by more than one
	 */
	public boolean isBalancedTree(Tree<Integer> tree){
		Tree<Integer> leftTree=new Tree<Integer>(tree.root.left);
		Tree<Integer> rightTree=new Tree<Integer>(tree.root.right);
		if(Math.abs(leftTree.getHeight()-rightTree.getHeight())<=1&&
		   isBalancedTree(leftTree)&&
		   isBalancedTree(rightTree))
			return true;
		else
			return false;
	}
	
	class Vertex{
		
		public char label;
		public boolean wasVisited;
		
		Vertex(char label){
			this.label=label;
			this.wasVisited=false;
		}
	}
	
	class Graph{
		
		private final int MAX_VERTS=20;
		private Vertex vertexList[];
		private int adjMat[][];
		private int nVerts;
		Stack<Integer> theStack=new Stack<Integer>();
		Queue<Integer> queue=new PriorityQueue<Integer>();
		
		public Graph(){
			vertexList=new Vertex[MAX_VERTS];
			
			adjMat=new int[MAX_VERTS][MAX_VERTS];
			for(int i=0;i<MAX_VERTS;i++){
				for(int j=0;j<MAX_VERTS;j++){
					adjMat[i][j]=0;
				}
			}
			
		}
		
		public int getAdjUnvisitedVertex(int v){
			for(int j=0;j<nVerts;j++)
				if(adjMat[v][j]==1&!vertexList[j].wasVisited)
					return j;
			return -1;
		}
		
		public void dfs(){
			
			vertexList[0].wasVisited=true;
			displayVertex(0);
			theStack.push(0);
			
			while(!theStack.isEmpty()){
				int v=getAdjUnvisitedVertex(theStack.peek());
				if(v==-1)
					theStack.pop();
				else{
					vertexList[v].wasVisited=true;
					displayVertex(v);
					theStack.push(v);
				}
			}
			
			for(int j=0;j<nVerts;j++)
				vertexList[j].wasVisited=false;
		}
		
		public void mst(){
			
			vertexList[0].wasVisited=true;
			displayVertex(0);
			theStack.push(0);
			
			while(!theStack.isEmpty()){
				int v=getAdjUnvisitedVertex(theStack.peek());
				int currentVertex=theStack.peek();
				if(v==-1)
					theStack.pop();
				else{
					vertexList[v].wasVisited=true;
					displayVertex(currentVertex);
					displayVertex(v);
					theStack.push(v);
				}
			}
			
			for(int j=0;j<nVerts;j++)
				vertexList[j].wasVisited=false;
		}
		
		public void bfs(){
			
			vertexList[0].wasVisited=true;
			displayVertex(0);
			queue.offer(0);
			
			while(queue.size()>0){
				int tmp=0;
				while((tmp=getAdjUnvisitedVertex(queue.peek()))>-1){
					queue.offer(tmp);
					displayVertex(tmp);
				}
				
				queue.poll();
			}
			
			for(int j=0;j<nVerts;j++)
				vertexList[j].wasVisited=false;
			
		}
		
		public void addVertex(char lab){
			vertexList[nVerts++]=new Vertex(lab);
		}
		
		public void addEdge(int start,int end){
			adjMat[start][end]=1;
			adjMat[end][start]=1;
		}
		
		public void displayVertex(int v){
			System.out.println(vertexList[v].label);
		}
		
	}
	
	/**
	 * 4.2 Given a directed graph, design an algorithm to find out whether there is a route be- tween 
	 * two nodes.
	 * @return
	 */
	public boolean findRoute(Graph graph,Vertex start,Vertex end){
		
		
		
		return false;
	}
	
	/**
	 * 4.3 Given a sorted (increasing order) array, write an algorithm to create a binary tree with 
	 * minimal height
	 */
	public Tree<Integer> generateBinaryTree(int[] input){
		int height=(int)Math.ceil(Math.log(input.length));
		return null;
	}
	
	/**
	 * 4.4 Given a binary search tree, design an algorithm which creates a linked list of all the nodes 
	 * at each depth (i e , if you have a tree with depth D, you’ll have D linked lists)
	 * @param tree
	 */
	public void generateDepthLinkList(Tree<Integer> tree){
		
		//preOrder(tree.root,1);
	}
	

	/**4.5 Write an algorithm to find the ‘next’ node (i e , in-order successor) of a given node in a 
	 * binary search tree where each node has a link to its parent
	 *  1.一个节点有右孩子，则在中序遍历中，该节点的后继是它的右子树的最左节点。
        2. 这个节点是它父亲的左孩子，则该节点的后继节点是它的父亲
        3. 这个节点是它父亲的右孩子，则需要一直向上搜索，直到它们n-1代祖先是它第n代祖先的左孩子，则它的后继就是第n个祖先。
        如果一直搜索到根节点，也没有找到n-1代祖先是它第n代祖先的左孩子，则该节点是整个树的中序遍历中的最后一个节点，即它没有后继。
	 */
	public Node<Integer> findTheNextNode(){
		return null;
	}
	
	/**
	 * 
	 */
	public Node<Integer> findCommonAncestor(Tree<Integer> tree,Node<Integer> node1,Node<Integer> node2){
		findCommonAncestor(tree.getLeftTree(),node1,node2);
		findCommonAncestor(tree.getRightTree(),node1,node2);
		return null;
	}
	
	public boolean contains(Tree<Integer> tree,Node<Integer> node1,Node<Integer> node2){
		
		if(tree.root.left.equals(node1)||tree.root.right.equals(node2))
			return true;
		
		return contains(tree.getLeftTree(),node1,node2)||
			   contains(tree.getRightTree(),node1,node2);
	}
	
}
