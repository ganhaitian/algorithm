package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BaseLinkedListProblem;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first
 * and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * @author haitian.gan
 */
public class AddTwoNumbersII extends BaseLinkedListProblem {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newl1 = reverse(l1);
        ListNode newl2 = reverse(l2);

        ListNode pre = null;
        ListNode head = null;
        int borrow = 0,tmpSum = 0;
        while(newl1 != null || newl2 != null){
            tmpSum = (newl1 == null ? 0 : newl1.val) + (newl2 == null ? 0 : newl2.val) + borrow;
            borrow = tmpSum / 10;

            ListNode node = new ListNode(tmpSum % 10 );
            if(pre != null){
                pre.next = node;
            }else{
                head = node;
            }

            pre = node;
            if(newl1 != null)
                newl1 = newl1.next;
            if(newl2 != null)
                newl2 = newl2.next;
        }

        head = reverse(head);
        if(borrow > 0){
            ListNode m = new ListNode(borrow);
            m.next = head;
            head = m;
        }

        return head;
    }

    private ListNode reverse(ListNode node){
        ListNode curr = node;
        ListNode pre = null,tmp;
        while(curr != null){
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }

        return pre;
    }

    public static void main(String[] args){
        int[] l1 = {5};
        int[] l2 = {5};

        new AddTwoNumbersII().addTwoNumbers(buildListFromArray(l1), buildListFromArray(l2));
    }

}
