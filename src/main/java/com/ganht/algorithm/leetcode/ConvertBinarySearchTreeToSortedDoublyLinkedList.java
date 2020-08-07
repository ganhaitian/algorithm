package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BinaryTreeProblem;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in
 * place.
 * 
 * You can think of the left and right pointers as synonymous to the predecessor
 * and successor pointers in a doubly-linked list. For a circular doubly linked
 * list, the predecessor of the first element is the last element, and the
 * successor of the last element is the first element.
 * 
 * We want to do the transformation in place. After the transformation, the left
 * pointer of the tree node should point to its predecessor, and the right
 * pointer should point to its successor. You should return the pointer to the
 * smallest element of the linked list.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: root = [4,2,5,1,3]
 * 
 * 
 * Output: [1,2,3,4,5]
 * 
 * Explanation: The figure below shows the transformed BST. The solid line
 * indicates the successor relationship, while the dashed line means the
 * predecessor relationship.
 * 
 * Example 2:
 * 
 * Input: root = [2,1,3] Output: [1,2,3] Example 3:
 * 
 * Input: root = [] Output: [] Explanation: Input is an empty tree. Output is
 * also an empty Linked List. Example 4:
 * 
 * Input: root = [1] Output: [1]
 * 
 * 
 * Constraints:
 * 
 * -1000 <= Node.val <= 1000 Node.left.val < Node.val < Node.right.val All
 * values of Node.val are unique. 0 <= Number of Nodes <= 2000
 * 
 * @author ganha
 *
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList extends BinaryTreeProblem {

	private static final int MIN = 0;
	private static final int MAX = 1;

	private TreeNode result;

	public TreeNode treeToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}

		var mm = getMinMaxChildren(root);
		result.left = mm[MAX];
		result.left.right = result;
		return result;
	}

	private TreeNode[] getMinMaxChildren(TreeNode node) {
		if (node == null) {
			return null;
		}

		TreeNode min = null;
		TreeNode max = null;

		var leftMM = getMinMaxChildren(node.left);
		if (leftMM == null) {
			if (result == null) {
				result = node;
			}

			min = node;
		} else {
			min = leftMM[MIN];
			node.left = leftMM[MAX];
			node.left.right = node;
		}

		var rightMM = getMinMaxChildren(node.right);
		if (rightMM == null) {
			max = node;
		} else {
			max = rightMM[MAX];
			node.right = rightMM[MIN];
			node.right.left = node;
		}

		return new TreeNode[] { min, max };
	}

	// 下面是官方的解法，更简洁，更灵巧
	// the smallest (first) and the largest (last) nodes
	TreeNode first = null;
	TreeNode last = null;

	public void helper(TreeNode node) {
		if (node != null) {
			// left
			helper(node.left);
			// node
			if (last != null) {
				// link the previous node (last)
				// with the current one (node)
				last.right = node;
				node.left = last;
			} else {
				// keep the smallest node
				// to close DLL later on
				first = node;
			}
			last = node;
			// right
			helper(node.right);
		}
	}

	public TreeNode treeToDoublyList1(TreeNode root) {
		if (root == null)
			return null;

		helper(root);
		// close DLL
		last.right = first;
		first.left = last;
		return first;
	}

	public static void main(String[] args) {
		Integer[] input = { 4, 2, 5, 1, 3 };
		new ConvertBinarySearchTreeToSortedDoublyLinkedList().treeToDoublyList(buildTreeFromArray(input));
	}

}
