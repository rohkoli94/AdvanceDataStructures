package com.rohit.stacksAndQueues;

public class QueueMain {

    public static void main(String[] args) throws StackQuesException{
//        CustomQueue queue = new DynamicQueue(1);
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());

        CustomCircularQueue c1 = new CustomCircularDynamicQueue(1);
        c1.add(1);
        c1.add(2);
        c1.add(3);
        c1.add(4);
        c1.display();
        c1.remove();
        c1.add(100);
        c1.display();
    }
}
