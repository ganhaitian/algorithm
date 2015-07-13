package com.ganht.algorithm.introduction;

/**
 * 链表谜题
 * Created by ganhaitian on 2015/7/3.
 */
public class LinkedLists {

    static class Node<T> {
        Node<T> next = null;
        T data;

        public Node(T d) {
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
        while (o != null) {
            Node p = o.next;
            Node previous = o;
            while (p != null) {
                if (p.data.equals(o.data)) {
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

    /**
     * Implement an algorithm to find the kth to last element of a singly linked list.
     * @param head
     * @param k
     * @return
     */
    public Object findKthToLast(Node head, int k) {
        if (head == null)
            return null;

        int cursor2 = 0;
        Node tmp = head;

        while (cursor2 < k) {
            tmp = tmp.next;
            cursor2++;
        }

        Node t = head;
        while (tmp != null) {
            t = t.next;
            tmp = tmp.next;
        }

        return t.data;
    }

    /**
     * Implement an algorithm to delete a node in the middle of a singly linked list,
     * given only access to that node.
     * @return
     */
    public Object deleteNode(Node deleteNode){
        // 注意，这个解法并不适用于最后一个结点的情况
        Node nextNode = deleteNode.next;
        deleteNode.data = nextNode.data;
        deleteNode.next = nextNode.next;
        return deleteNode;
    }

    /**
     *  Write code to partition a linked list around a value x, such that all nodes
     *  less than x come before alt nodes greater than or equal to x.
     * @return
     */
    public Node partitionLinkedList(Node<Integer> head,int x){
        // 移动结点的代价好高，好繁琐啊
        // 思路就是，先遍历
        /*Node<Integer> t = head;
        Node<Integer> base = null;
        while(t != null){
            if(x == t.data) {
                base = t;
                break;
            }
            t = t.next;
        }

        Node<Integer> tmp = new Node<Integer>(base.data);
        deleteNode(base);
        tmp.next = head;
        head = tmp;
        print(tmp);

        Node<Integer> u = head.next;
        while(u != null){
            if(u.data < x){
                tmp = new Node(u.data);
                tmp.next = head;
                head = tmp;

                deleteNode(u);
            }else
                u = u.next;
        }

        print(head);*/

        // 上面这个太烦琐了，还得删结点
        // 简单的思路是用两个链，一个存比他小的，一个存比他大的，然后merge
        Node<Integer> smaller = null;
        Node<Integer> larger = null;

        Node<Integer> tmp = head;
        Node<Integer> next;
        while(tmp != null){
            next = tmp.next;
            if(tmp.data < x){
                tmp.next = smaller;
                smaller = tmp;
            }else if(tmp.data > x){
                tmp.next = larger;
                larger = tmp;
            }
            tmp = next;
        }

        Node<Integer> mid = new Node<Integer>(x);
        if(smaller == null){
            mid.next = larger;
            return mid;
        }else{
            head = smaller;
            while(smaller.next != null){
                smaller = smaller.next;
            }
            smaller.next = mid;
            mid.next = larger;
        }

        print(head);
        return head;
    }

    /**
     * You have two numbers represented by a linked list, where each node contains
     * a single digit. The digits are stored in reverse order, such that the 1's digit
     * is at the head of the list. Write a function that adds the two numbers and
     * returns the sum as a linked list.
     * SOLUTION
     * FOLLOW UP
     * Suppose the digits are stored in forward order. Repeat the above problem.
     */
    public Node add(Node<Integer> head1,Node<Integer> head2){
        // 这个是倒序排列的时候
        Node<Integer> t1 = head1;
        Node<Integer> t2 = head2;
        Node<Integer> result = null;
        Node<Integer> prev = null;
        int carry = 0;
        int tmpSum;
        while(t1 != null || t2 != null){
            tmpSum = (t1 != null ? t1.data : 0) + (t2 != null ?t2.data : 0) + carry;
            carry = tmpSum / 10;
            Node<Integer> p = new Node<Integer>(tmpSum % 10);
            if(prev != null)
                prev.next = p;

            prev = p;
            if(result == null)
                result = p;
            if(t1 != null)
                t1 = t1.next;
            if(t2 != null)
                t2 = t2.next;
        }
        print(result);
        return result;
    }

    /**
     * Given a circular linked list, implement an algorithm which returns the node
     * at the beginning of the loop.
     *
     * @param head
     * @return
     */
    public Node findStartOfLoop(Node head){
        // 想解决这道题，首先要自己推出来两个定理
        // 一个就是，如果存在环(注意环的循环点不一定是头)，用一快一慢两个结点去遍历，他们肯定会相遇
        // 另外一个就是，如果设环外的长度是K，那么一快一慢两个结点相遇的位置是在环内距离环的入口点
        // K的位置，即是从入口点算走了LOOP_SIZE - K步

        Node f = head; // 快
        Node s = head; // 慢

        while(f.next != null){
            f = f.next.next;
            s = s.next;
            if(s == f)
                break;
        }

        s = head;
        while(s != f){
            s = s.next;
            f = f.next;
        }

        return s;
    }

    private void print(Node head) {
        while (head != null) {
            System.out.print(head.data + ",");
            head = head.next;
        }
        System.out.println();
    }

    public static Node build(Object[] input) {
        Node tail = new Node(input[0]);
        Node head = tail;
        for (int i = 1; i < input.length; i++) {
            tail.next = new Node(input[i]);
            tail = tail.next;
        }
        return head;
    }

    public static void main(String[] args) {
        //new LinkedLists().removeDuplicate(build(new Object[]{"F","O","L","L","O","W"," ","U","P"}));
        //Object result = new LinkedLists().findKthToLast(
        //        build(new Object[]{"F", "O", "L", "L", "O", "W", " ", "U", "P"}), 3);
        //System.out.println(result.toString());
        /*new LinkedLists().partitionLinkedList(
                build(new Object[]{4,1,2,7,8,5,3,9}),5
        );*/

        Node result = new LinkedLists().add(
                build(new Object[]{5,9,3,4,2}),
                build(new Object[]{9,1,0,4,3,1,6})
        );
    }

}
