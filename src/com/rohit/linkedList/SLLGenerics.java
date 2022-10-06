package com.rohit.linkedList;


import com.rohit.MyException;

public class SLLGenerics<T> {

    private Node head;
    private Node tail;
    private int size;

    SLLGenerics(){
        this.size = 0;
    }

    // add elements first (from start)
    public void addFirst(T val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(tail == null){
         tail = head;
        }
        size++;
    }

    // add elements from last (from tail)
    public void addLast(T value){
        Node node = new Node(value);
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = tail;
        }
        size++;
    }

    public void addLastReusable(T value){
        if(tail == null){
            addFirst(value);
            return;
        };
        Node node = new Node(value);
        tail.next = node;
        tail = node;
        size++;

    }
    // add from end (donot use tail)
    public void addLastWithNoTail(T val){
        Node node = new Node(val);
        if(head == null){ // add starting position
            node.next =  head;
            head = node;
            return;
        }
        // already node present
        // traverse using temp node
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        // here code means it is at last position
        temp.next = node;
    }

    //insert at index
    public void insert(T val, int index) throws MyException {
        // check exceptions
        if(index < 0 || index > size){
            throw new MyException("index cannot be less than 0 or greater than size");
        }

        // if index is 0, insert first
        if(index == 0){
            addFirst(val);
            return;
        }
        // if index is size , insert last
        if(index == size){
            addLast(val);
            return;
        }
        /* If in between, use temp and go to index - 1
            make new node (val ,point temp.next to next)
            assign temp .next = node;
         */
        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        Node node = new Node(val,temp.next);
        temp.next = node;
        size++;
    }

    // delete first
    public T deleteFirst(){
        // check end case
        if(size == 0){
            return null;
        }
        T removedVal = (T)head.value;
        head = head.next; // move ahead ahead
        if(head == null){ // it means only one element is present
            tail = null;  // so make tail as null
        }
        size--;
        return removedVal;
    }

    // delete last
    public T deleteLast(){
        if(size <= 1){
            return deleteFirst();
        };
        Node secondLast = get(size - 2);
        T removedVal = (T)tail.value;
        tail = secondLast;
        tail.next = null;   // point to null as it is last element
        size--;
        return removedVal;
    }

    // delete at specific index
    public T delete(int index) throws MyException{
        if(index < 0 || index >= size){
            throw new MyException("index cannot be less than 0 or greater than equal to size");
        }
        if(index == 0){
            return  deleteFirst();
        }
        if(index == size -1){
            return  deleteLast();
        }
        Node prev = get(index - 1);
        T removedVal = (T)prev.next.value;
        prev.next = prev.next.next;
        return removedVal;
    }
    public Node get(int index){
        Node temp = head; // as already pointing to 0
        for (int i = 0; i < index; i++) { // if index is 4 it will properly go to that
            temp = temp.next;
        };
        return temp;
    }


    // find node with specific value
    public Node find(T val){
        Node temp = head;
        while(temp != null){
            if(temp.value.equals(val)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // display elements
    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.value +"--> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    // size
    public int size(){
        return this.size;
    }

    private class Node{
        private Object value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


}
