package com.rohit.linkedList;

public class CLL {

    private Node head;
    private Node tail;

    public void insert(int val){
        Node node = new Node(val);
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
    }

    public void display(){
        if(head == null){
            System.out.println("list is empty");
            return;
        }
        Node temp = head;
        do{
            System.out.print(temp.val + "--> ");
            temp = temp.next;
        }while(temp != head);
        System.out.println("HEAD");
    }

    public void delete(int val){
        if(head == null){
            System.out.println("list is empty");
        }
        Node temp = head;
        if(temp.val == val){
            head = temp.next;
            tail.next = head;
            return;
        }
        do{
            if(temp.next.val == val){
                if(temp.next == tail){
                    tail = temp;
                }
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }while(temp != head);
        System.out.println("element not found to delete");
    }

    private class Node{
        private int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
