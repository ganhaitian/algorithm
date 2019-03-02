package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 *
 * @author haitian.gan
 */
public class FlattenNestedListIterator {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * 好像不让用额外变量的，所以暂时不能展开
     * 妈的，不让用List，可以用栈？这是什么玩意儿
     */
    public class NestedIterator implements Iterator<Integer> {

        //private List<Integer> flattenList = new ArrayList<>();
        private int currPos = 0;
        private boolean hasNext;
        private List<NestedInteger> nestedList;

        public NestedIterator(List<NestedInteger> nestedList) {
            //flatten(nestedList);
            this.nestedList = nestedList;
            if(nestedList.size() > 0){
                this.hasNext = true;
            }
        }

        /*private void flatten(List<NestedInteger> nestedList) {
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    flattenList.add(ni.getInteger());
                } else {
                    flatten(ni.getList());
                }
            }
        }
*/
        @Override
        public Integer next() {
            currPos ++;
            return iterate(this.nestedList, new AtomicInteger(0));
            //return flattenList.iterator().next();
        }

        private Integer iterate(List<NestedInteger> nestedList, AtomicInteger ai){
            for(Iterator<NestedInteger> iter = nestedList.iterator();iter.hasNext();){
                NestedInteger ni = iter.next();
                if(ni.isInteger()){
                    if(ai.incrementAndGet() == currPos){
                        this.hasNext = iter.hasNext();
                        return ni.getInteger();
                    }
                }else{
                    iterate(ni.getList(), ai);
                }
            }

            return null;
        }

        @Override
        public boolean hasNext() {
            //return flattenList.iterator().hasNext();
            return this.hasNext;
        }
    }

    public static void main(String[] args){
        class DefaultNestedInteger implements NestedInteger{


            public DefaultNestedInteger(int i){

            }

            @Override
            public boolean isInteger() {
                return false;
            }

            @Override
            public Integer getInteger() {
                return null;
            }

            @Override
            public List<NestedInteger> getList() {
                return null;
            }

        }

    }

}
