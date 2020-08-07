package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * Example:
 * 
 * Input: board = [ 
 * ['o','a','a','n'], 
 * ['e','t','a','e'], 
 * ['i','h','k','r'],
 * ['i','f','l','v'] 
 * ] 
 * 
 * words = ["oath","pea","eat","rain"]
 * 
 * Output: ["eat","oath"]
 * 
 * 
 * Note:
 * 
 * All inputs are consist of lowercase letters a-z. The values of words are
 * distinct.
 * 
 * @author ganha
 *
 */
public class WordSearchII {

	// 四个方向
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

	public List<String> findWords(char[][] board, String[] words) {
		int m = board.length;
		int n = board[0].length;
		// 构建以首字母为key的map
		var capitalMap = new HashMap<Character, List<int[]>>();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				capitalMap.computeIfAbsent(board[r][c], k -> new ArrayList<>()).add(new int[]{r, c});
			}
		}

		var result = new ArrayList<String>();
		for(String word : words){
			var posList = capitalMap.get(word.charAt(0));
			if(posList == null){
				continue;
			}

			var visited = new boolean[m][n];
			var queue   = new LinkedList<>(posList);
			int deep    = 0;
			while (queue.size() > 0) {
				if (++deep >= word.length()) {
					result.add(word);
					break;
				}

				int size = queue.size();
				for (int i = 0; i < size; i++) {
					int[] pos = queue.poll();
					for (var dir : dirs) {
						int r = pos[0] + dir[0];
						int c = pos[1] + dir[1];
						if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || board[r][c] != word.charAt(deep)) {
							continue;
						}

						queue.offer(new int[]{c, r});
						visited[r][c] = true;
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		char[][] board = {
				{'o', 'a', 'a', 'n'},
				{'e', 't', 'a', 'e'},
				{'i', 'h', 'k', 'r'},
				{'i', 'f', 'l', 'v'},
		};

		String[] words = {"oath","pea","eat","rain"};
		new WordSearchII().findWords1(board, words);
	}

	char[][] _board = null;
	ArrayList<String> _result = new ArrayList<String>();

	public List<String> findWords1(char[][] board, String[] words) {

		// Step 1). Construct the Trie
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;

			for (Character letter : word.toCharArray()) {
				if (node.children.containsKey(letter)) {
					node = node.children.get(letter);
				} else {
					TrieNode newNode = new TrieNode();
					node.children.put(letter, newNode);
					node = newNode;
				}
			}
			node.word = word;  // store words in Trie
		}

		this._board = board;
		// Step 2). Backtracking starting for each cell in the board
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[row].length; ++col) {
				if (root.children.containsKey(board[row][col])) {
					backtracking(row, col, root);
				}
			}
		}

		return this._result;
	}

	private void backtracking(int row, int col, TrieNode parent) {
		Character letter = this._board[row][col];
		TrieNode currNode = parent.children.get(letter);

		// check if there is any match
		if (currNode.word != null) {
			this._result.add(currNode.word);
			currNode.word = null;
		}

		// mark the current letter before the EXPLORATION
		this._board[row][col] = '#';

		// explore neighbor cells in around-clock directions: up, right, down, left
		int[] rowOffset = {-1, 0, 1, 0};
		int[] colOffset = {0, 1, 0, -1};
		for (int i = 0; i < 4; ++i) {
			int newRow = row + rowOffset[i];
			int newCol = col + colOffset[i];
			if (newRow < 0 || newRow >= this._board.length || newCol < 0
					|| newCol >= this._board[0].length) {
				continue;
			}
			if (currNode.children.containsKey(this._board[newRow][newCol])) {
				backtracking(newRow, newCol, currNode);
			}
		}

		// End of EXPLORATION, restore the original letter in the board.
		this._board[row][col] = letter;

		// Optimization: incrementally remove the leaf nodes
		if (currNode.children.isEmpty()) {
			parent.children.remove(letter);
		}
	}

}

class TrieNode {
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	String word = null;
	public TrieNode() {}
}
