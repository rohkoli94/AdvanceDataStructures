package com.rohit.dynamicProgramming.knapsack;

public class MinimumSubsetSumdifference {

    public static void main(String[] args) {


        //v.i.i.i.i.i.imp - only works with positive integers

        /*
        s2 - s1
        range(total of all elements) - s1 - s1
        range - 2s1

        s1 is half of range (it might have numbers which do not satisfy, so check from subset sum true false method)
         */

        minimumSubsetsumdifference();
    }

    private static void minimumSubsetsumdifference() {

        int[] arr = {1, 6, 11, 5};

        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            range = range + arr[i];
        }

        // subset sum (true false code) - requires arr and sum to be found
        // so it will store all values till range (max value)
        int n = arr.length;
        boolean[][] t = new boolean[n+1][range+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // initializing with recursive base conditions
                if(i == 0){
                    t[i][j] = false;
                }
                if(j == 0){
                    t[i][j] = true;
                }

                if(i >0 && j> 0){
                    // choice diagram
                    if(arr[i-1] <= j){ // eligible
                        boolean val1 = t[i-1][j - arr[i-1]]; //put
                        boolean val2  = t[i-1][j]; //don't
                        t[i][j] = val1 || val2;

                    }else{ //  not eligible
                        t[i][j] = t[i-1][j];//don't
                    }
                }
            }
        }

         // once data is filled
        // loop into last row till half and apply min (range - 2s1)
        // n is last row
        // range is last so range / 2
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= range/2; j++) {
            if(t[n][j]){
                int val = Math.abs(range - (2*j));
                min = Math.min(min,val);
            }
        }
        System.out.println("min difference is "+min);
    }
}
