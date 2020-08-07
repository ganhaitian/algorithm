package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BaseLinkedListProblem;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList extends BaseLinkedListProblem {

    private ListNode newHead;

    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }

        reverse(head);
        head.next = null;
        return newHead;
    }

    private ListNode reverse(ListNode node){
        if(node == null){
            return null;
        }

        ListNode newPre = reverse(node.next);
        if(newPre == null){
            newHead = node;
        }else{
            newPre.next = node;
        }

        return node;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,4,5};
        ListNode last = null;
        ListNode head = null;
        for(int i = 0;i < nums.length;i++){
            ListNode node = new ListNode(nums[i]);
            if(last != null){
                last.next = node;
            }else{
                head = node;
            }

            last = node;
        }

        new ReverseLinkedList().reverseList1(head);
    }

}
