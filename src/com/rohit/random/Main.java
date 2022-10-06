package com.rohit.random;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        rainWaterTrappingArea();
    }

    public static void rainWaterTrappingArea(){
        int[] arr= {3,0,0,2,0,4};

        int[] maxLeft = new int[arr.length];
        int[] maxRight = new int[arr.length];


        // putting values in maxLeft for each element
        int maxL = arr[0];
        maxLeft[0] = maxL;

        for (int i = 1; i < arr.length; i++) {
            if(maxL <= arr[i]){
                maxL =  arr[i];
            }
            maxLeft[i] = maxL;
        }

        System.out.println("maxLeft "+ Arrays.toString(maxLeft));

        // putting values in maxLeft for each element
        int maxR = arr[arr.length - 1];
        maxRight[arr.length - 1] = maxR;
        for (int i = arr.length - 2; i >=0; i--) {
            if(maxR <= arr[i]){
                maxR =  arr[i];
            }
            maxRight[i] = maxR;
        }
        System.out.println("maxRight "+ Arrays.toString(maxRight));

        // min of maxLeft and max right  - height of each element is the trapping of water
        // so add all
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total = total + Math.min(maxLeft[i],maxRight[i]) - arr[i];
        }
        System.out.println("trapping area is "+total);
    }
}
