package com.rohit.linkedList;

public class DLLMain {

    public static void main(String[] args) {
        System.out.println("Doubly linked list add first");
        DLL d1 = new DLL();
        d1.addFirst(10);
        d1.addFirst(20);
        d1.addFirst(30);
        d1.addFirst(40);
        d1.addLast(50);
        d1.addLast(60);
        d1.insertAtIndex(100,1);
        d1.display();
        System.out.println("inserting after index");
        d1.insertAfterIndex(200,1); // shd come at 2nd postn
        d1.insertAfterIndex(800,7);// shd come at last
        d1.display();

        // insert after value
        System.out.println("inserting after value");
        d1.insertAfterValue(800,1000);
        d1.display();

        System.out.println("deleting");
        d1.deleteFirst(); // 40 should be deleted
        d1.deleteFirst(); // 100 should be deleted
        d1.deleteLast(); // 1000 should be deleted
        d1.delete(5); // 60 should be deleted
        d1.display();
    }

}
