package com.ganht.algorithm.introduction;

/**
 * 链表谜题
 * Created by ganhaitian on 2015/7/3.
 */
public class LinkedLists {

    static class Node {
        Node next = null;
        Object data;

        public Node(Object d) {
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

    public Node deleteNode(Node head, Object d) {
        Node n = head;
        if (n == null)
            return null;

        while (n.next != null) {
            if (n.next.data.equals(d)) {
                Node deleteNode = n.next;
                n.next = deleteNode.next;
                return deleteNode;
            }
            n = n.next;
        }
        return null;
    }

    /**
     * 写一段代码来从一个未排序的链表中移除重复的元素，例如：FOLLOW UP
     * 如果在禁止使用临时存储的情况下该怎样解答这个问题？
     *
     * @return
     */
    public Node removeDuplicate(Node head) {
        if (head == null)
            return null;

        // 这里是审错题，当成是连续重复了
        /*Node n = head;
        while (n.next != null) {
            if (n.data.equals(n.next.data)) {
                if (n.next.next != null) {
                    n.next = n.next.next;
                }
            } else {
                n = n.next;
            }
        }*/

        /*Node m = head;
        Map<Object, Integer> buffer = new HashMap<Object, Integer>();
        buffer.put(m.data, 0);
        while (m.next != null) {
            if (!buffer.containsKey(m.next.data)) {
                buffer.put(m.next.data, 0);
                m = m.next;
            } else {
                m.next = m.next != null ? m.next.next : null;
            }
        }
        print(head);*/

        // 不需要buffer的作法，两次遍历
        Node o = head;
        while(o != null){
            Node p = o.next;
            Node previous = o;
            while(p != null){
                if(p.data.equals(o.data)){
                    previous.next = p.next;
                }
                previous = p;
                p = p.next;
            }
            o = o.next;
        }
        print(head);

        return head;
    }

    private void print(Node head){
        while(head != null){
            System.out.print(head.data + ",");
            head = head.next;
        }
    }

    public static Node build(Object[] input){
        Node tail = new Node(input[0]);
        Node head = tail;
        for(int i = 1;i < input.length;i++){
            tail.next = new Node(input[i]);
            tail = tail.next;
        }
        return head;
    }

    public static void main(String[] args) {
        new LinkedLists().removeDuplicate(build(new Object[]{"F","O","L","L","O","W"," ","U","P"}));
    }

}
