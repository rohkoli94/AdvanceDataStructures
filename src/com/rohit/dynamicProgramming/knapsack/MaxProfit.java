package com.rohit.dynamicProgramming.knapsack;

public class MaxProfit {

    public static void main(String[] args) {

        // recursion
        knapSack_Question_Recurive_MaxProfit();
        // iteration
        System.out.println("iterative max profit is "+ knapSack_Question_Iterative_MaxProfit());

        /* Complexity Analysis for both:
        Time Complexity: O(N*W).
        Auxiliary Space: O(N*W).
        */

    }

    public static void knapSack_Question_Recurive_MaxProfit(){
        // MAX PROFIT
        /*
         recursive + memoization (top - down)
         (1) matrix with +1 size (initialize with -1)
         (2) base condition
         (3) choice diagram
         */


        int[] weight = {10,20,30};
        int[] value =  {60,100,120};
        int W =  50;
        int n = weight.length;

        int[][] t = new int[n+1][W +1];

        // initialize matrix with -1
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = -1;
            }
        }

        System.out.println("recursive max profit is "+knapSack(weight,value,n,W,t));
    }

    public static int knapSack(int[] weight, int[] value, int n , int W, int[][] t){
        // base condition
        if(n == 0 || W == 0){
            return 0;
        }
        // memoization to avoid recursive call
        if(t[n][W] != -1){
         return  t[n][W];
        }
            // choice diagram
        if(weight[n-1] <= W){
            // 2 choices, put or don't put
            return   t[n][W] = Math.max(
                    value[n-1] + knapSack(weight,value,n-1,W- weight[n-1],t),
                    knapSack(weight,value,n-1,W,t)
                    );
        }else{
            return  t[n][W] = knapSack(weight,value,n-1,W,t);
        }
    }


    public static int knapSack_Question_Iterative_MaxProfit(){

        // MAX PROFIT
        /*
         iterative + memoization (bottom - up)
         (1) matrix with +1 size
         (2) iterate over maatrix
            (1) initialize with recursive base condition
            (2) choice diagram ( recursive call with will replaced by t[][] of changeable values)
         */

        int[] weight = {10,20,30};
        int[] value =  {60,100,120};
        int W =  50;
        int n = weight.length;

        int[][] t = new int[n+1][W +1];

        // loop through matrix
        // n from recursive code means i
        // W from recursive code means j

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length ; j++) {
                // initialize matrix with recursive base condition
                if(i == 0 || j == 0){
                    t[i][j] = 0; // not needed as be default it is 0
                }
                else if(weight[i-1] <= j){ // v.v.v.vimp (else if block)
                    // recursive call with will replaced by t of changeable values
                    // choice diagram
                    // recursion code
                    // value[n-1] + knapSack(weight,value,n-1,W- weight[n-1],t),
                    // knapSack(weight,value,n-1,W,t)
                    int val1 =  value[i-1] +  t[i-1][j- weight[i-1]];
                    int val2 = t[i-1][j];
                    t[i][j] = Math.max(val1,val2);
                }else{
                    // recursion code
                    // knapSack(weight,value,n-1,W,t)
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[n][W]; // it will be max value

    }


}
