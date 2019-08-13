package com.ganht.algorithm.bytedance;

import com.ganht.algorithm.util.tuple.ThreeTuple;
import com.ganht.algorithm.util.tuple.Tuple;

/**
 * 分组翻转
 *
 * 这其实是一道变形的链表反转题，大致描述如下
 * 给定一个单链表的头节点 head,实现一个调整单链表的函数，使得每K个节点之间为一组进行逆序，并且从链表的尾部开始组起，头部剩余节点数量不够一组的不需要逆序。
 * （不能使用队列或者栈作为辅助）
 *
 * 例如：
 * 链表:1->2->3->4->5->6->7->8->null, K = 3。那么 6->7->8，3->4->5，1->2各位一组。调整后：1->2->5->4->3->8->7->6->null。其中 1，2不调整，因为不够一组。
 *
 * @author haitian.gan
 */
public class FlipGroup {

    private static class Node {
        private int  value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public ThreeTuple<Node, Integer, Node> flip(int k, Node node, int numBefore) {
        Node next = node.next;
        if (next == null) {
            return Tuple.tuple(node, 1, node);
        }

        ThreeTuple<Node, Integer, Node> result = flip(k, node.next, numBefore + 1);
        if (result.second < k && numBefore + 1 >= k) {
            node.next = next.next;
            next.next = node;
            return Tuple.tuple(node, result.second + 1, result.third);
        } else {
            node.next = result.third;
            return Tuple.tuple(node, 1, node);
        }
    }

    public static void main(String[] args) {
        Node n8 = new Node(8, null);
        Node n7 = new Node(7, n8);
        Node n6 = new Node(6, n7);
        Node n5 = new Node(5, n6);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);

        Node result = new FlipGroup().flip(3, n1, 0).first;
        System.exit(0);
    }

}
