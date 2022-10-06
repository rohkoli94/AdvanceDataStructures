package com.rohit.linkedList;

import com.rohit.MyException;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws MyException {



        System.out.println("adding from start");
        SLL s1 = new SLL();
        s1.addFirst(10);
        s1.addFirst(20);
        s1.addFirst(30);
        s1.addFirst(40);
        s1.addFirst(50);
        s1.display();

        System.out.println();
        System.out.println("adding from start using generics");
        SLLGenerics<String> s2 = new SLLGenerics<>();
        s2.addFirst("apple");
        s2.addFirst("ball");
        s2.addFirst("cat");
        s2.display();

        System.out.println();
        System.out.println("adding from end");
        SLL s3 = new SLL();
        s3.addLast(10);
        s3.addLast(20);
        s3.addLast(30);
        s3.addLast(40);
        s3.addLast(50);
        s3.display();

        System.out.println();
        System.out.println("adding from tail using generics");
        SLLGenerics<String> s4 = new SLLGenerics<>();
        s4.addLast("apple");
        s4.addLast("ball");
        s4.addLast("cat");
        s4.display();

        System.out.println();
        System.out.println("adding from tail reusable");
        SLL s5 = new SLL();
        s5.addLastReusable(10);
        s5.addLastReusable(20);
        s5.addLastReusable(30);
        s5.addLastReusable(40);
        s5.addLastReusable(50);
        s5.display();

        System.out.println();
        System.out.println("adding from tail using reusable generics");
        SLLGenerics<String> s6 = new SLLGenerics<>();
        s6.addLastReusable("apple");
        s6.addLastReusable("ball");
        s6.addLastReusable("cat");
        s6.display();

        System.out.println();
        System.out.println("adding from end but donot use tail");
        SLL s7 = new SLL();
        s7.addLastWithNoTail(10);
        s7.addLastWithNoTail(20);
        s7.addLastWithNoTail(30);
        s7.addLastWithNoTail(40);
        s7.addLastWithNoTail(50);
        s7.display();

        System.out.println();
        System.out.println("adding from end but don't use tail generics");
        SLLGenerics<String> s8 = new SLLGenerics<>();
        s8.addLastWithNoTail("apple");
        s8.addLastWithNoTail("ball");
        s8.addLastWithNoTail("cat");
        s8.display();

        System.out.println();
        System.out.println("inserting at particular index");
        SLL s9 = new SLL();
        s9.addFirst(10);
        s9.addFirst(20);
        s9.addFirst(30);
        s9.addFirst(40);
        try{
            s9.insert(100,3);
            s9.insert(200,0);
           // s9.insert(300,500); // uncomment to check exception handling
            s9.display();
            s9.size();
        }catch(Exception e){
            System.out.println("ERROR inside catch block :: "+e.getMessage());
        }


        System.out.println();
        SLL s10 = new SLL();
        s10.addFirst(10);
        s10.addFirst(20);
        s10.addFirst(30);
        s10.addFirst(40);
        s10.display();

        System.out.println("delete first");
        s10.deleteFirst();
        s10.display();

        System.out.println();
        System.out.println("delete last");
        s10.deleteLast();
        s10.display();

        System.out.println();
        System.out.println("delete at specific index");
        try{
            s10.delete(1);
            s10.display();
            // s10.delete(2); // uncomment to get error inside catch block
        }catch (Exception e){
                System.out.println("ERROR inside catch block :: "+e.getMessage());
        }

        System.out.println();
        System.out.println("find node with value");
        SLL s11 = new SLL();
        s11.addFirst(10);
        s11.addFirst(20);
        s11.addFirst(30);
        s11.addFirst(40);
        System.out.println("ans is custom toString method" + s11.find(20));

        // recursion insert
        System.out.println();
        System.out.println("Recursion insert");
        SLL s0 = new SLL();
        s0.insertRec(100,0);
        s0.insertRec(200,1);
        s0.insertRec(300,1);
        s0.display();

    }

}
