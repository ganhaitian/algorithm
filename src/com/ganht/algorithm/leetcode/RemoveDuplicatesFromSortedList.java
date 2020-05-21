package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BaseLinkedListProblem;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 * @author haitian.gan
 */
public class RemoveDuplicatesFromSortedList extends BaseLinkedListProblem {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null){
            if(pre != null && pre.val == curr.val){
                pre.next = curr.next;
            }else{
                pre = curr;
            }

            curr = curr.next;
        }

        return head;
    }

    public static void main(String[] args){

    }

}
