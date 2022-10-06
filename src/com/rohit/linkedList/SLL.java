package com.rohit.linkedList;


import com.rohit.MyException;

public class SLL {

    private Node head;
    private Node tail;
    private int size;

    SLL(){
        this.size = 0;
    }


    // add elements from first (from head)
    public void addFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(tail == null){
         tail = head;
        }
        size++;
    }


    // add elements from last (from tail)
    public void addLast(int value){
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

    public void addLastReusable(int value){

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
    public void addLastWithNoTail(int val){
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
        // here code means it is at last position.

        temp.next = node;
    }

    //insert at index
    public void insert(int val, int index) throws MyException{
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
    public int deleteFirst(){
        // check end case
        if(size == 0){
            return -1;
        }
        int removedVal = head.value;
        head = head.next; // move ahead ahead
        if(head == null){ // it means only one element is present
            tail = null;  // so make tail as null
        }
        size--;
        return removedVal;
    }

    // delete last
    public int deleteLast(){
       if(size <= 1){
          return deleteFirst();
       };
       Node secondLast = get(size - 2);
       int removedVal = tail.value;
       tail = secondLast;
       tail.next = null;   // point to null as it is last element
       size--;
       return removedVal;
    }

    // delete at specific index
    public int delete(int index) throws MyException{
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
        int removedVal = prev.next.value;
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
    public Node find(int val){
        Node temp = head;
        while(temp != null){
            if(temp.value == val){
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



    //RECURSION
    // insert using recursion (use head only)
    public void insertRec(int val, int index){
//        if(index >= size){
//            System.out.println("cannot insert");
//            return;
//        }
       head = insertRec(val, index, head);
    }

    private Node insertRec(int val, int index, Node node){
        // base condition
        if(index == 0){
            Node temp = new Node(val, node); //temp.val = val and temp.next = node
            size++;
            return temp;
        }
        // reduce the index and assign the Node
        // (which we will get from base condition to current.next and it goes backwards)
        // and finally assign to head while returning back in up call
        node.next = insertRec(val, index-1, node.next);
        return node;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list
    // Q.1 Remove duplicates
    public Node removeDupliactes(){
        if(head == null){
            return null;
        };
        Node temp = head;
        while(temp.next != null){
            if(temp.value == temp.next.value){
                temp.next = temp.next.next;
                size--;
            }else{
                temp = temp.next;
            }
        }
        tail = temp;
        return temp;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/
    //Q.2 Merge two sorted linked list (Not in use - refer mergeLeetcode method)

    public SLL merge(SLL list1, SLL list2){
        Node f = list1.head;
        Node s = list2.head;

        SLL ans = new SLL();
        while(f != null && s != null){
            if(f.value < s.value){
                ans.addLast(f.value);
                f = f.next;
            }else{
                ans.addLast(s.value);
                s = s.next;
            }
        }

        while (f != null){
            ans.addLast(f.value);
            f = f.next;
        }
        while (s != null){
            ans.addLast(s.value);
            s = s.next;
        }

        return ans;

    }

    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/
    // Q.2 Merge two sorted linked list
// return head as Node, as leetcode iterates internally from this head
    public Node mergeLeetcode(SLL list1, SLL list2){

        Node f = list1.head;
        Node s = list2.head;

        Node dummyHead = new Node();
        Node tail = dummyHead;// shalllow copy

        while(f != null && s != null){
            if(f.value < s.value){
                tail.next = f; // assign at tail next
                f = f.next;
                tail = tail.next; // move tail ahead
            }else{
                tail.next = s; // assign at tail next
                s = s.next;
                tail = tail.next; // move tail ahead
            }
        }
        // either one has finished early
        tail.next = (f != null) ? f : s;
        return dummyHead.next; // as only dummyhead will have initial value as 0 due to empty object and shallow copy of tail make  the changes to dummy head as well
    }


    //sort (bubble sort)

    public void bubbleSort(){
        bubbleSort(size -1,0);
    }
    public void bubbleSort(int row, int col){
        // base condition
        if(row == 0){
           return;
        };

        if(col < row){
            Node f = get(col);
            Node s = get(col+1);
            if(f.value > s.value){
                // swap
                // check 3 cases
                if(f == head ){  //case 1 (head needs to be swapped)
                    head = s;
                    f.next = s.next;
                    s.next = f;
                }else if(s == tail){ //case 2 (tail needs to be swapped)
                    Node prev = get(col -1);
                    prev.next = s;
                    tail = f;
                    f.next = null;
                    s.next = f;
                }else{ //case 3 (middle element needs to be swapped)
                    Node prev = get(col -1);
                    prev.next = s;
                    f.next = s.next;
                    s.next = f;
                }
            }
            bubbleSort(row, col +1);
        }else{
            bubbleSort(row-1, 0);
        }
    }

    // reverse a linkedList (recursion)
    private void reverse(Node node){
        // base condition
        if(node == tail){
            head = tail;
            return;
        }
        // go till last
        reverse(node.next);
        // while coming back change the direction
        tail.next = node;
        tail = node;
        tail.next = null;  // as it is required in last place to point to null
    }

    // in - place reverse using iteration (no tail)

    public Node reverseUsingWithoutTail(Node head){
        if(size == 1){
            return head;
        }
        Node prev = null;
        Node present = head;
        Node next = null;
        if(present != null){
            next = present.next;
        }

        while(present != null){
            present.next = prev;
            //move pointers ahead
            prev = present;
            present = next;
            if(next != null){
                next = next.next;
            }
        }
        // when loop terminates then prev will be at last position, so assign head
        head = prev;
        return head;
    }
    private class Node{
        private int value;
        private Node next;

        @Override
        public String toString() {
            return "Node {" +
                    "value " + value +
                    "}";
        }

        public Node() {
        }
        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
//        SLL s1 = new SLL();
//        s1.addLast(1);
//        s1.addLast(1);
//        s1.addLast(2);
//        s1.display();
//        s1.removeDupliactes();
//        s1.display();

//        SLL s2 = new SLL();
//        SLL s3 = new SLL();
//        s2.addLast(1);
//        s2.addLast(2);
//        s2.addLast(4);
//        s3.addLast(1);
//        s3.addLast(3);
//        s3.addLast(4);
//
//       SLL s4 = new SLL();
//       s4.head = s3.mergeLeetcode(s2,s3);
//        s4.display();


        // bubble sort
//        SLL list = new SLL();
//        for (int i = 7; i > 0; i--) {
//            list.addLast(i);
//        }
//        list.display();
//        list.bubbleSort();
//        list.display();

        System.out.println("reverse using recursion");
        SLL s4 = new SLL();
        s4.addLast(1);
        s4.addLast(2);
        s4.addLast(3);
        s4.addLast(4);
        s4.addLast(5);
        s4.addLast(6);
        s4.display();
        s4.reverse(s4.head);
        s4.display();
    }

}
