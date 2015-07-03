package com.ganht.algorithm.introduction;

/**
 * 链表谜题
 * Created by ganhaitian on 2015/7/3.
 */
public class LinkedLists {

    class Node {
        Node next = null;
        int data;

        public Node(int d) {
            this.data = d;
        }

        public void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }

    public Node deleteNode(Node head, int d) {
        Node n = head;
        if (n == null)
            return null;

        while (n.next != null) {
            if (n.next.data == d) {
                Node deleteNode = n.next;
                n.next = deleteNode.next;
                return deleteNode;
            }
            n = n.next;
        }
        return null;
    }


}
