package com.rohit.stacksAndQueues;

public class CustomQueue {

    protected int[] data;
    private static final int DEFAULT_SIZE = 10;
    int end = 0;

    public CustomQueue() {
        this(DEFAULT_SIZE);
        // System.out.println(" CustomQueue constructor default");
    }

    public CustomQueue(int size) {
        this.data = new int[size];
        //System.out.println("CustomQueue  constructor with size");
    }

    public void add(int val) throws StackQuesException{
        if(isFull()){
            throw new StackQuesException("queue is full !!");
        }
        this.data[end++] = val;
    }


    public int remove() throws StackQuesException{
        if(isEmpty()){
            throw new StackQuesException("data is empty, cannot remove!!");
        }

        int removed = this.data[0];
        for (int i = 1; i < data.length; i++) {
            data[i-1] = data[i];
        }
        end--;
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
