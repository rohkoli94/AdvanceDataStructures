package com.rohit.stacksAndQueues;

public class CustomCircularDynamicQueue extends  CustomCircularQueue{

    public CustomCircularDynamicQueue() {
        super();
    }

    public CustomCircularDynamicQueue(int size) {
        super(size);
    }

    @Override
    public void add(int val){
        if(isFull()){
            int[] temp = new int[this.data.length * 2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        super.add(val);
    }
}
