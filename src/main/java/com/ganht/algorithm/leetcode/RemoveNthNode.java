package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BaseLinkedListProblem;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 * Created by 17ZY-HPYKFD2 on 2016/11/4.
 */
public class RemoveNthNode extends BaseLinkedListProblem {

    /**
     * 他这个挺好的，弄两个指针相当于，一起往后走
     * public ListNode removeNthFromEnd(ListNode head, int n) {
     *     ListNode dummy = new ListNode(0);
     *     dummy.next = head;
     *     ListNode first = dummy;
     *     ListNode second = dummy;
     *     // Advances first pointer so that the gap between first and second is n nodes apart
     *     for (int i = 1; i <= n + 1; i++) {
     *         first = first.next;
     *     }
     *     // Move first to the end, maintaining the gap
     *     while (first != null) {
     *         first = first.next;
     *         second = second.next;
     *     }
     *     second.next = second.next.next;
     *     return dummy.next;
     * }
     * @param head
     * @param n
     * @return
     */
    // 二刷
    public ListNode removeNthFromEnd2(ListNode head, int n){
        if(head == null){
            return null;
        }

        int no = iterate(head, n);
        if(no == n){
            return head.next;
        }

        return head;
    }

    public int iterate(ListNode node, int n) {
        if (node == null) {
            return 0;
        }

        int pos = iterate(node.next, n) + 1;
        if (pos == n + 1) {
            node.next = node.next.next;
        }

        return pos;
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
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        ListNode n = new RemoveNthNode().removeNthFromEnd2(a, 5);

        //String url = "http://www.baidu.com?accountsKey=asdfds112323a&param1=0";
        //url = url.replaceAll("accountsKey=\\w+","aaabbb");
    }
}



