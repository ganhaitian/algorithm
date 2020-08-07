package com.ganht.algorithm.leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
 * and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * @author haitian.gan
 */
public class AddTwoNumbers {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode previousNode = null;
        ListNode resultNode = null;
        int carry = 0;
        while(l1 != null || l2 != null){
            int tmpVal = ( l1 == null ? 0 : l1.val ) + (l2  == null ? 0 : l2.val) + carry;
            carry = tmpVal / 10;

            ListNode tmpNode = new ListNode(tmpVal % 10);
            if(resultNode == null){
                resultNode = tmpNode;
                previousNode = tmpNode;
            }else{
                previousNode.next = tmpNode;
                previousNode = tmpNode;
            }

            if(l1 != null){
                l1 = l1.next;
            }

            if(l2 != null){
                l2 = l2.next;
            }
        }

        if(carry > 0){
            previousNode.next = new ListNode(1);
        }

        return resultNode;
    }

    public static void main(String[] args){

    }

}
