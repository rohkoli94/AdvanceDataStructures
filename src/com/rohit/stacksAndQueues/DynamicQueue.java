package com.rohit.stacksAndQueues;

public class DynamicQueue extends  CustomQueue{

    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int size) {
        super(size);
    }
    @Override
    public void add(int val) throws  StackQuesException {
    if(isFull()){
        // copy array * 2

        int[] temp = new int[this.data.length * 2];

        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
    super.add(val);
    }
}
