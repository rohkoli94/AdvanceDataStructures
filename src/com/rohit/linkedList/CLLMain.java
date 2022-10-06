package com.rohit.linkedList;

public class CLLMain {

    public static void main(String[] args) {
        CLL c1 = new CLL();
        c1.insert(10);
        c1.insert(20);
        c1.insert(30);
        c1.insert(40);
        c1.display();
        c1.delete(10);
        c1.display();
    }
}
