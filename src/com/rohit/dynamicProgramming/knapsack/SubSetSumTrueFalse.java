package com.rohit.dynamicProgramming.knapsack;

public class SubSetSumTrueFalse {

    public static void main(String[] args) {
        //QUESTION: recursion (true false)
        subset_question();

        //QUESTION: iterative (true false)- FOUNDATION
        int[] arr = {2,3,4,6,7,8};
        int sum = 11;
        System.out.println("iterative ans is "+subset_iterative(arr, sum));

        // -----variation of above foundation-----

        //QUESTION: count of subset sum with a given sum
        int[] arr1 = {2,3,4,6,7,8};
        int sum1 = 11;
        count_subset_iterative(arr1, sum1);

        //QUESTION: count of subset sum with a given difference
        count_difference_subset_iterative();

        //QUESTION: target sum (add + or - to get a sum )
        target_sum_subset_iterative();

        //QUESTION: equal sum partition
        equal_sum_subset_iterative();
    }

    public static void subset_question(){
        int[] arr = {2,3,4,6,7,8};
        int sum = 11;
        int n = arr.length ;

        // matrix
        boolean[][] t = new boolean[n+1][sum+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = false;
            }
        }

        System.out.println("recursive ans is "+subset(arr,n,sum,t));
    }

    public static boolean subset(int[] arr, int n , int sum, boolean[][]t){
        // base condition
        if(n == 0 && sum == 0){
            return true;
        }
        if(n == 0 && sum != 0){
            return false;
        }
        if(n != 0 && sum == 0){
            return true;
        }

        // memoization
        if(t[n][sum]){
            return t[n][sum];
        }else if (arr[n-1] <= sum){
            // choice diagram - eligible (put or donot put)
            boolean val1 = subset(arr, n-1,sum - arr[n-1],t);
            boolean val2 = subset(arr, n-1,sum,t);
            return t[n][sum] = val1 || val2;
        }else{ // not eligible
            return t[n][sum]  = subset(arr, n-1,sum,t);
        }
    }


    public static boolean subset_iterative(int[] arr , int sum){


        int n = arr.length;

        // matrix
        boolean[][] t = new boolean[n+1][sum+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // initializing with recursive base conditions
                if(i == 0){ // make all columns false excluding first which is handles below
                    t[i][j] = false;
                }
                if(j == 0){ // first row and remainng will be true
                   t[i][j] = true;
                }

                //v.v.v.v.imp
                // handled i ==0 here as above are 2 conditons for i and j
                if(i > 0 && j > 0){
                    if (arr[i-1] <= j){
                        // choice diagram - eligible (put or donot put)
//                    boolean val1 = subset(arr, i-1,j - arr[i-1],t);
//                    boolean val2 = subset(arr, i-1,j,t);
                        boolean val1 = t[i-1][j - arr[i-1]]; // put
                        boolean val2 = t[i-1][j]; // donot
                        t[i][j] = val1 || val2;
                    }else{ // not eligible
                        // t[n][sum]  = subset(arr, i-1,j,t);
                        t[n][sum]  =  t[i-1][j]; // donot
                    }
                }
            }
        }
        return t[n][sum]; // last value to be returned
    }

    // count of subset sum with a given sum
    public static int count_subset_iterative(int[] arr, int sum){

        int n = arr.length ;

        // matrix
        int[][] t = new int[n+1][sum+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // initializing with recursive base conditions
                if(i == 0){ // make all columns false excluding first which is handles below
                    t[i][j] = 0;
                }
                if(j == 0){ // first row and remainng will be true
                    t[i][j] = 1;
                }

                //v.v.v.v.imp
                // handled i ==0 here as above are 2 conditons for i and j
                if(i > 0 && j > 0){
                    if (arr[i-1] <= j){
                        // choice diagram - eligible (put or donot put)
//                    boolean val1 = subset(arr, i-1,j - arr[i-1],t);
//                    boolean val2 = subset(arr, i-1,j,t);
                        int val1 = t[i-1][j - arr[i-1]]; // put
                        int val2 = t[i-1][j]; // donot
                        t[i][j] = val1 + val2;
                    }else{ // not eligible
                        // t[n][sum]  = subset(arr, i-1,j,t);
                        t[n][sum]  =  t[i-1][j]; // donot
                    }
                }
            }
        }
        return t[n][sum]; // last value to be returned
    }

    // count of subset sum with a given difference
    public static int count_difference_subset_iterative(){

        int[] arr = {1,1,2,3};
        int difference = 1;
        int n = arr.length ;

        /*
        s1 - s2 = diff
        s1 + s2 = total of all elements
        ------------------------------ (add)
        2s1     = diff + total

        s1 = diff + total / 2
         */

        int total = 0;
        for (int num : arr){
            total = total + num;
        }
        int sum = difference + total /2;
        // we have reduced problem to subset sum problem
        return count_subset_iterative(arr, sum);
    }

    // target sum (add + or - to get a sum )
    public static int target_sum_subset_iterative(){

        int[] arr = {1,1,2,3};
        int sum = 1;
        int n = arr.length ;

        /*
        (+1, +2)      (-1,-3)
        (1    2)   -  (1,  3)

        s1         - s2       = diff i.e sum
        s1 + s2 = total of all elements
        ------------------------------ (add)
        2s1     = diff + total

        s1 = diff + total / 2
         */

        int total = 0;
        for (int num : arr){
            total = total + num;
        }
        int sum1 = sum + total /2;
        // we have reduced problem to subset sum problem
        return count_subset_iterative(arr, sum1);
    }
    public static void equal_sum_subset_iterative(){
        int[] arr = {1,5,11,5};

        // if we get total as odd, then not possible
        //if even then total/2 is sum


        int total = 0;
        for (int num : arr){
            total = total + num;
        }
        int sum = total /2;
        // we have reduced problem to subset sum problem
        System.out.println("equal sum ans is "+subset_iterative(arr, sum));
    }
    }
