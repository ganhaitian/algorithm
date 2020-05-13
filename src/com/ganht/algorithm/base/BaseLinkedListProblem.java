package com.ganht.algorithm.base;

public class BaseLinkedListProblem {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode buildListFromArray(int[] array){
        ListNode head = null,pre = null;
        for(int i = 0;i < array.length;i++){
            ListNode node = new ListNode(array[i]);
            if(i == 0){
                head = node;
            }

            if(pre !=  null){
                pre.next = node;
            }

            pre = node;
        }

        return head;
    }

}
