package com.rohit.stacksAndQueues;

import java.util.*;


public class Example {

    Stack<Integer> s1 = new Stack<>();


    public static int counter(ArrayList<Integer> list,int price){
        int counter = 0;
        list.add(price);


        if(list.size() == 1){
            return 1;
        }

        for (int i = list.size() - 2; i >= 0 ; i--) {
            if(list.get(i)  <= price){
                counter++;
            }else{
                break;
            }
        }

            counter = counter + 1;
      //  System.out.println("ans is "+counter);
        return counter;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list  = new ArrayList<>();

        counter(list, 100);
        counter(list, 80);
        counter(list, 60);
        counter(list, 70);
        counter(list, 60);
        counter(list, 75);
        counter(list, 85);

        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);

       // System.out.println("stack peep "+stack.peek());

        System.out.println(stack.pop()); //4
        System.out.println(stack.pop()); //3
        System.out.println(stack.pop());//2
        System.out.println(stack.pop());//1

        // QUEUE is an interface (parent) which is implemented by LinkedList (child)
        System.out.println("queue");
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

       // System.out.println("queue peep "+queue.peek());

        System.out.println(queue.remove()); //1
        System.out.println(queue.remove());//2
        System.out.println(queue.remove());//3
        System.out.println(queue.remove());//4

        // DEQUE is an interface (parent) which is implemented by ArrayDeque (child)
        System.out.println("Deque");
        Deque <Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.addFirst(2);
        deque.addLast(3);

        System.out.println(deque.remove()); //2
        System.out.println(deque.removeFirst()); //1
        System.out.println(deque.removeLast()); //3
    }
}
