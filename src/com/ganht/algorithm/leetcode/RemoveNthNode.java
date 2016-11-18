package com.ganht.algorithm.leetcode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * <p>
 * Subscribe to see which companies asked this question
 * <p>
 * Created by 17ZY-HPYKFD2 on 2016/11/4.
 */
public class RemoveNthNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 先是寻找链接的倒数第n个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null || head.next == null)
            return null;

        ListNode top = new ListNode(0);
        top.next = head;

        ListNode tmp = head;
        for(int i =0;i < (n - 1);i++)
            tmp = tmp.next;

        ListNode cursor1 = top;
        ListNode cursor2 = tmp;

        while(cursor2.next != null){
            cursor1 = cursor1.next;
            cursor2 = cursor2.next;
        }


        cursor1.next = cursor1.next.next;
        return top.next;
//        ListNode top = new ListNode(0);
//        top.next = head;
//
//        int count = 1;
//        ListNode prev = top;
//        while (count < n) {
//            prev = prev.next;
//            count++;
//        }
//
//        prev.next = prev.next.next;
//        return top.next;

    }

    public static void main(String[] args) {
        /*ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        //a.next = b;
        //b.next = c;
        //c.next = d;
        //d.next = e;

        ListNode n = new RemoveNthNode().removeNthFromEnd(a, 1);*/

        String url = "http://www.baidu.com?accountsKey=asdfds112323a&param1=0";
        url = url.replaceAll("accountsKey=\\w+","aaabbb");
    }
}



