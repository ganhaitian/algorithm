package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

     Example:

     Input:
     [
     1->4->5,
     1->3->4,
     2->6
     ]
     Output: 1->1->2->3->4->4->5->6
 *
 */
public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class ListNodeWrapper implements Comparable<ListNodeWrapper>{
        int index;
        ListNode node;

        @Override
        public int compareTo(ListNodeWrapper o) {
            if(o == null)
                return 1;

            int firstCompareResult = Integer.compare(this.node.val,o.node.val);
            if(firstCompareResult == 0)
                return Integer.compare(index,o.index);
            else
                return firstCompareResult;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;

        TreeSet<ListNodeWrapper> minSet = new TreeSet<>();
        for(int index = 0;index < lists.length;index++) {
            if(lists[index] == null)
                continue;

            ListNodeWrapper nodeWrapper = new ListNodeWrapper();
            nodeWrapper.node = lists[index];
            nodeWrapper.index = index;

            minSet.add(nodeWrapper);
        }

        ListNode head = null;
        ListNode result = null;
        while(true){
            ListNodeWrapper nodeWrapper = minSet.pollFirst();
            if(nodeWrapper == null)
                break;

             if(result == null){
                 result = new ListNode(nodeWrapper.node.val);
                 head = result;
             }else{
                 result.next = new ListNode(nodeWrapper.node.val);
                 result = result.next;
             }

             if(nodeWrapper.node.next != null){
                 ListNodeWrapper newNodeWrapper = new ListNodeWrapper();
                 newNodeWrapper.node = nodeWrapper.node.next;
                 newNodeWrapper.index = nodeWrapper.index;

                 minSet.add(newNodeWrapper);
             }
        }

        return head;
    }

    public static void main(String[] args){
        List<ListNode> nodeList = new ArrayList<>();
        List<String> nodeStrList = Arrays.asList(
                "1->4->5",
                "1->3->4",
                "2->6");

        for(String nodeStr:nodeStrList){
            String[] nodeParts = nodeStr.split("->");

            ListNode previous = null;
            ListNode head = null;
            for(String nodePart:nodeParts){
                ListNode node = new ListNode(Integer.parseInt(nodePart));
                if(previous == null){
                    previous = node;
                    head = node;
                }else{
                    previous.next = node;
                    previous = node;
                }
            }

            nodeList.add(head);
        }

        ListNode[] input = new ListNode[nodeList.size()];
        nodeList.toArray(input);
        new MergeKSortedLists().mergeKLists(input);
    }

}
