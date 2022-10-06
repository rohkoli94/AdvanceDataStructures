package com.rohit.stacksAndQueues;

public class DyanamicStack extends  CustomStack{

    DyanamicStack(){
        super();
       // System.out.println("dynamic stack constructor default");
    }

    DyanamicStack(int size){
        super(size);
        //System.out.println("dynamic stack constructor with size");
    }

    @Override  public void push(int val) throws  StackQuesException{
        if(isFull()){
            // copy array * 2

            int[] temp = new int[this.data.length * 2];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
       super.push(val);
    }
}
