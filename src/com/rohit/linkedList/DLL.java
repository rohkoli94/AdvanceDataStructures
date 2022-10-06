package com.rohit.linkedList;

public class DLL {
    private Node head;

    // add first
    public void addFirst(int val){
        Node node = new Node(val);
        node.next = head;
        node.prev = null;
        if(head != null){
            head.prev = node;
        }
        head = node;
    }

    public void addLast(int val){
        if(head == null){
            addFirst(val);
            return;
        }
        // traverse as we don't have tail
        Node temp =  head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node node = new Node(val);
        node.prev = temp;
        temp.next = node;
    }

    public int size(){
        int counter = 0;
        Node temp = head;
        while(temp != null){
            counter++;
            temp = temp.next;
        }
        return counter;
    }

    public void insertAtIndex(int val, int index){
        if(index < 0 && index > size()) {
            System.out.println("index cannot be less than 0 or greater than size" +size());
        }
        if(index == 0){
           addFirst(val);
           return;
        }
        if(index == size()){
            addLast(val);
            return;
        }
        Node temp = get(index - 1);
        Node node = new Node(val);
        node.next = temp.next;
        temp.next.prev = node;

        temp.next = node;

        node.prev = temp;

    }

    // insertAfterIndex()

    public void insertAfterIndex(int val, int index){
        if(index < 0 || index >= size()){
            System.out.println("index cannot be less than 0 or greater than size" +size());
        }
        if(head == null){
            addFirst(val);
            return;
        }
        if(index == size() - 1){
            addLast(val);
            return;
        }
        Node temp = get(index);
        Node node = new Node(val);
        node.next = temp.next;
        temp.next.prev = node;
        temp.next = node;
        node.prev = temp;
    }

    // findByValue
    public Node findByValue(int val){
        Node temp = head;
        while(temp != null){
            if(temp.value == val){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    // insertAfterValue
     public void insertAfterValue(int valTofind, int valuetoBeInserted){
         Node prevNode = findByValue(valTofind);
         if(prevNode == null){
             System.out.println("Value "+valTofind +" not found");
            return;
         }
          Node node = new Node(valuetoBeInserted);
             node.next =prevNode.next;
             prevNode.next = node;
             // check end case as it might be last value and we are inserting after that
             if(node.next != null){
                 node.next.prev = node;
             }
             node.prev = prevNode;
     }



     // delete first
    public int deleteFirst(){
        if(size() == 0) {
            System.out.println("nothing to delete");
            return -1;
        }
        int removed = head.value;
        head = head.next;
        head.prev = null;
        return removed;
    }

    public int deleteLast(){
        if(head.next == null){
            return deleteFirst();
        }
        Node prevNode = head;
        while(prevNode.next != null){
            prevNode = prevNode.next;
        }
        int removed = prevNode.value;
        prevNode.prev.next = null;
        prevNode.prev = null;
        return removed;
    }

    public int delete(int index){
        if(index < 0 || index >= size()){
            System.out.println("index cannot be less than - and  greater than size");
            return -1;
        }
        if(index == 0){
            return deleteFirst();
        };
        if(index == size() -1){
            return  deleteLast();
        }
        Node prevNode = get(index -1 );
        int removed = prevNode.next.value;
        prevNode.next.next.prev = prevNode;
        prevNode.next.prev = null;
        prevNode.next = prevNode.next.next;


        return removed;
    }

    public Node get(int index){
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void display(){
        Node temp = head;
        Node last = null;
        while(temp != null){
            System.out.print(temp.value + "-->");
            last = temp;
            temp = temp.next;
        }
        System.out.println(" END");

        System.out.println("printing reverse");
        while(last != null){
            System.out.print(last.value +"-->");
            last = last.prev;
        }
        System.out.print(" START");
        System.out.println();
    }

    private class Node{
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
