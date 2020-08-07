package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a chemical formula (given as a string), return the count of each atom.
 *
 * An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 *
 * 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1,
 * no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 *
 * Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
 *
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are
 * formulas.
 *
 * Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order),
 * followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its
 * count
 * (if that count is more than 1), and so on.
 *
 * Example 1:
 * Input:
 * formula = "H2O"
 * Output: "H2O"
 * Explanation:
 * The count of elements are {'H': 2, 'O': 1}.
 * Example 2:
 * Input:
 * formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation:
 * The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * Example 3:
 * Input:
 * formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation:
 * The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 * Note:
 *
 * All atom names consist of lowercase letters, except for the first character which is uppercase.
 * The length of formula will be in the range [1, 1000].
 * formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 * @author haitian.gan
 */
public class NumberOfAtoms {

    public String countOfAtoms1(String formula) {
        int                         N     = formula.length();
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new TreeMap<>());

        for (int i = 0; i < N;) {
            if (formula.charAt(i) == '(') {
                stack.push(new TreeMap<>());
                i++;
            } else if (formula.charAt(i) == ')') {
                Map<String, Integer> top = stack.pop();
                int iStart = ++i, multiplicity = 1;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                if (i > iStart) multiplicity = Integer.parseInt(formula.substring(iStart, i));
                for (String c: top.keySet()) {
                    int v = top.get(c);
                    stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
                }
            } else {
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String name: stack.peek().keySet()) {
            ans.append(name);
            int multiplicity = stack.peek().get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        return new String(ans);
    }

    public String countOfAtoms(String formula) {
        formula = formula + "\r";
        Deque<Map<String,Integer>> stack = new LinkedList<>();
        // 初始化根
        Map<String, Integer> rootMap = new TreeMap<>();
        stack.push(rootMap);

        Map<String, Integer> readyPop = null;
        int tmpNum = 0;
        StringBuilder ele = new StringBuilder();
        for(char c : formula.toCharArray()){
            Map<String, Integer> currMap = stack.peek();
            if(c == '('){
                Map<String,Integer> sub = new TreeMap<>();
                stack.push(sub);
            }

            if(c == ')'){
                readyPop = stack.pop();
            }

            if(Character.isDigit(c)){
                tmpNum = tmpNum * 10 + (c - '0');
            } else if (Character.isUpperCase(c) || c == '\r' || c == '(' || c == ')') {
                int multiple = tmpNum == 0 ? 1 : tmpNum;
                if (readyPop != null) {
                    readyPop.replaceAll((k, v) -> {
                        int val = v * multiple;
                        currMap.compute(k, (_k,_v) -> {
                            if( _v == null){
                                return 1;
                            }else{
                                return _v + val;
                            }
                        });
                        return val;
                    });
                }else{
                    if(ele.length() > 0){
                        currMap.compute(ele.toString(), (k,v) -> v == null ? multiple : v + multiple);
                    }
                }

                ele = new StringBuilder();
                if(c != '(' && c != ')')
                    ele.append(c);

                tmpNum = 0;
            }else if(Character.isLowerCase(c)){
                ele.append(c);
            }
        }

        StringBuilder result = new StringBuilder();
        rootMap.forEach((k,v) ->{
            result.append(k);
            if(v > 1){
                result.append(v);
            }
        });

        return result.toString();
    }

    public static void main(String[] args){
        new NumberOfAtoms().countOfAtoms("Mg(OH)2");
    }

}
