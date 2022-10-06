package com.rohit.stacksAndQueues;

import com.rohit.MyException;

public class StackMain {

    public static void main(String[] args) throws StackQuesException {
        CustomStack stack = new DyanamicStack(1);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
       // System.out.println(stack.pop());
    }
}
