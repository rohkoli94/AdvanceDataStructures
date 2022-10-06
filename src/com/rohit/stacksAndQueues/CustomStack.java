package com.rohit.stacksAndQueues;

import com.rohit.MyException;

public class CustomStack {

    protected int[] data;
    private static final int DEFAULT_SIZE = 10;
    int end = 0;

    public CustomStack() {
       this(DEFAULT_SIZE);
       // System.out.println("custom stack constructor default");
    }

    public CustomStack(int size) {
        this.data = new int[size];
        //System.out.println("custom stack constructor with size");
    }

    // push
    public void push(int val) throws StackQuesException{
        if(isFull()){
            throw new StackQuesException("stack is full !!");
        }
        this.data[end++] = val;
    }

    //pop
    public int pop() throws StackQuesException{
        if(isEmpty()){
            throw new StackQuesException("data is empty, cannot pop!!");
        }
        int removed = this.data[--end];
        return removed;
    }

    // full
    public boolean isFull(){
        return end == data.length;
    }

    //empty
    public boolean isEmpty(){
        return end == 0;
    }
}
