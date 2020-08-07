package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 *
 *  Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number
 *  (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

     Example:
     Given a / b = 2.0, b / c = 3.0.
     queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
     return [6.0, 0.5, -1.0, 1.0, -1.0 ].

     The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
     where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

     According to the example above:

     equations = [ ["a", "b"], ["b", "c"] ],
     values = [2.0, 3.0],
     queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 */
public class EvaluateDivision {

    private Map<String,Variable> variableMap = new HashMap<>();
    private Map<Equation,Double> equationMap = new HashMap<>();

    private class Variable{
        private String v;
        private List<Variable> relations = new ArrayList<>();

        Variable(String v){
            this.v = v;
        }

        void addRelation(Variable v){
            this.relations.add(v);
        }

        public boolean equals(Object obj){
            if(!(obj instanceof Variable))
                return false;
            else{
                Variable v1 = (Variable)obj;
                return v1.v.equals(this.v);
            }
        }

        public int hashCode(){
            return v.hashCode();
        }

        public String toString(){
            return this.v;
        }
    }

    private class Equation{
        private String v1;
        private String v2;

        Equation(String v1,String v2){
            this.v1 = v1;
            this.v2 = v2;
        }

        public boolean equals(Object obj){
            if(!(obj instanceof Equation))
                return false;
            else{
                Equation e1 = (Equation)obj;
                return e1.v1.equals(v1) && e1.v2.equals(v2);
            }
        }

        public int hashCode(){
            return v1.hashCode() + v2.hashCode();
        }

        public String toString(){
            return this.v1 + ":" + this.v2;
        }
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for(int i = 0;i < equations.length;i++){
            String[] equation = equations[i];
            equationMap.put(new Equation(equation[0],equation[1]),values[i]);

            if(values[i] != 0){
                equationMap.put(new Equation(equation[1],equation[0]), 1d / values[i]);
            }


            Variable v1 = variableMap.get(equation[0]);
            Variable v2 = variableMap.get(equation[1]);

            if(v1 == null){
                v1 = new Variable(equation[0]);
                variableMap.put(v1.v,v1);
            }

            if(v2 == null){
                v2 = new Variable(equation[1]);
                variableMap.put(v2.v,v2);
            }

            v1.addRelation(v2);
            v2.addRelation(v1);
        }

        double[] result = new double[queries.length];
        for(int j = 0;j < queries.length ;j++){
            String[] query = queries[j];

            if(!variableMap.containsKey(query[0]) || !variableMap.containsKey(query[1])){
                result[j] = -1.0;
                continue;
            }

            if(query[0].equals(query[1])){
                result[j] = 1.0;
                continue;
            }

            Equation qE = new Equation(query[0],query[1]);
            if(equationMap.containsKey(qE)){
                result[j] = equationMap.get(qE);
                continue;
            }

            String start = query[0];
            String target = query[1];
            Variable targetV = variableMap.get(target);

            Stack<Variable> st = new Stack<>();
            st.push(variableMap.get(start));

            LinkedList<Variable> trace = new LinkedList<>();

            while(!st.empty()){
                Variable currV = st.pop();
                trace.add(currV);

                int tIndex = currV.relations.indexOf(targetV);

                if(tIndex >= 0){
                    targetV = currV.relations.get(tIndex);
                    trace.add(targetV);
                    break;
                }else{
                    boolean end = true;
                    for(Variable v:currV.relations){
                        if(trace.contains(v))
                            continue;

                        st.push(v);
                        end = false;
                    }

                    // 说明什么都没找到
                    if(end)
                        trace.removeLast();
                }

            }

            if(trace.size() > 1){
                double tmpResult = 1;
                for(int k = 0;k < trace.size() - 1;k++){
                    Variable t1 = trace.get(k);
                    Variable t2 = trace.get(k + 1);

                    tmpResult *= equationMap.get(new Equation(t1.v,t2.v));
                }

                result[j] = tmpResult;
            }else
                result[j] = -1.0;
        }

        return result;
    }

    public static void main(String[] args){
        String[][] a  = {{"a","b"},{"e","f"},{"b","e"}};
        double[] values = {3.4,1.4,2.3};
        String[][] queries = {{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}};

        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(a, values, queries)));
    }


}
