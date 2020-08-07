package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Example 1:
 * 
 * Input: num = "123", target = 6 Output: ["1+2+3", "1*2*3"] Example 2:
 * 
 * Input: num = "232", target = 8 Output: ["2*3+2", "2+3*2"] Example 3:
 * 
 * Input: num = "105", target = 5 Output: ["1*0+5","10-5"] Example 4:
 * 
 * Input: num = "00", target = 0 Output: ["0+0", "0-0", "0*0"] Example 5:
 * 
 * Input: num = "3456237490", target = 9191 Output: []
 * 
 * 
 * Constraints:
 * 
 * 0 <= num.length <= 10 num only contain digits.
 * 
 * @author ganha
 *
 */
public class ExpressionAddOperators {

	/**
	 * 和我的意思差不多吧
	 * 
	 * @param num
	 * @param target
	 * @return
	 */

	// 感觉用递归就行
	public List<String> addOperators(String num, int target) {
		return null;
	}

	// 2325
	// 36 = (23 - 5) * 2
	private void subAddOperators(String num, int target) {
		if (num.length() <= 1) {
			var n = Integer.parseInt(num);
			if (target == n) {

			}
		}

		for (int i = 1; i < num.length(); i++) {
			var subStr = num.substring(0, i);
			var sub = Integer.parseInt(subStr);
			subAddOperators(num.substring(i), target - sub);
		}
	}
	
	public static void main(String[] args){
		new ExpressionAddOperators().addOperators1("123456", 30);
	}

	// leetcode上的标准答案，也是用的递归
	public ArrayList<String> answer;
	public String digits;
	public long target;

	// 有点小高级
	public void recurse(int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
		String nums = this.digits;

		// Done processing all the digits in num
		if (index == nums.length()) {

			// If the final value == target expected AND
			// no operand is left unprocessed
			if (value == this.target && currentOperand == 0) {
				StringBuilder sb = new StringBuilder();
				ops.subList(1, ops.size()).forEach(v -> sb.append(v));
				this.answer.add(sb.toString());
			}
			return;
		}

		// Extending the current operand by one digit
		currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
		String current_val_rep = Long.toString(currentOperand);
		int length = nums.length();

		// To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
		// valid operand. Hence this check
		if (currentOperand > 0) {

			// NO OP recursion
			recurse(index + 1, previousOperand, currentOperand, value, ops);
		}

		// ADDITION
		ops.add("+");
		ops.add(current_val_rep);
		recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
		ops.remove(ops.size() - 1);
		ops.remove(ops.size() - 1);

		if (ops.size() > 0) {

			// SUBTRACTION
			ops.add("-");
			ops.add(current_val_rep);
			recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
			ops.remove(ops.size() - 1);
			ops.remove(ops.size() - 1);

			// MULTIPLICATION
			ops.add("*");
			ops.add(current_val_rep);
			recurse(index + 1, currentOperand * previousOperand, 0,
					value - previousOperand + (currentOperand * previousOperand), ops);
			ops.remove(ops.size() - 1);
			ops.remove(ops.size() - 1);
		}
	}

	public List<String> addOperators1(String num, int target) {

		if (num.length() == 0) {
			return new ArrayList<String>();
		}

		this.target = target;
		this.digits = num;
		this.answer = new ArrayList<String>();
		this.recurse(0, 0, 0, 0, new ArrayList<String>());
		return this.answer;
	}

}
