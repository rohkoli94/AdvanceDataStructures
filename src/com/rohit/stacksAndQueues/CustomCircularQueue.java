package com.rohit.stacksAndQueues;

public class CustomCircularQueue {

    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    int front = 0;
    int end = 0;
    int size = 0;

    public CustomCircularQueue() {
        this(DEFAULT_SIZE);
        // System.out.println(" CircularQueue constructor default");
    }

    public CustomCircularQueue(int size) {
        this.data = new int[size];
        //System.out.println("CircularQueue  constructor with size");
    }

    // add
    public void add(int val){
        if(isFull()){
            System.out.println("circular queue is full");
            return;
        }
        this.data[end++] = val;
        end = end % data.length; // to go to 0 index if exceeds length;
        size++;
    }

    public int remove(){
        if(isEmpty()){
            System.out.println("circular queque is empty");
            return -1;
        }
        int removed = this.data[front++];
        front = front % data.length; // to go to 0 index if exceeds length;
        size--;
        return removed;
    }

    // display
    public void display(){
    if(isEmpty()){
        System.out.println("nothing to print");
        return;
    }
    int i = front;
    do{
        System.out.print(data[i] + "<-- ");
        i++;
        i = i % data.length;// to go to 0 index if exceeds length;
    }while(i != end);
        System.out.println("END");
    }
    public boolean isFull(){
        if(size == data.length){
            return true;
        }
        return false;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
}
