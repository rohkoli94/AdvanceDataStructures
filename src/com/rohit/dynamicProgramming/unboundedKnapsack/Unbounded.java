package com.rohit.dynamicProgramming.unboundedKnapsack;

public class Unbounded {

    public static void main(String[] args) {
        // rod cutting maximum cost - recursion
        rodCuttingMaxProfit_recursion();

        // rod cutting maximum cost - iterative
        rodCuttingMaxProfit_iterative();

        //--------------------------------------

        // Coin change problem _ maximum no of ways.

        System.out.println();

        coinChangeMaxCount_iterative();

    }



    /*
    Unbounded knapsack
    (1) length
    (2) capacity

     */
    private static void rodCuttingMaxProfit_recursion() {
       int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };

       // below are not given
       int n = price.length;
        int W =  price.length; // here length is capacity (W)
        //item or length array is not given
        int[] lengths = new int[n];

        for (int i = 0; i < n; i++) {
            lengths[i] = i + 1;
        }
        // matrix
        int[][] t = new int[lengths.length + 1][n +1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = -1;
            }
        }

        int maxProfit= rodCuttingMaxProfit(lengths,price, n, W,t);
        System.out.println("recusion max profit of rod cutting is "+maxProfit);

    }

    public static int rodCuttingMaxProfit(int[] lengths,int[] price, int n,int L, int[][] t){
        // base condition
        if(n == 0 || L == 0){
            return 0;
        };

        // memoization
        if(t[n][L] != -1){
            return t[n][L];
        }else{
            // choice diagram
            if(lengths[n-1] <= L){
                // unbounded knapsack , so we are not doing n-1 while putting
                int val1 = price[n-1] + rodCuttingMaxProfit(lengths,price,n,L - lengths[n-1],t);
                int val2 = rodCuttingMaxProfit(lengths,price,n-1,L,t);
              return t[n][L] = Math.max(val1,val2);
            }else{
                return t[n][L] = rodCuttingMaxProfit(lengths,price,n-1,L,t);
            }
        }
    }

    private static void rodCuttingMaxProfit_iterative() {
        /*
        unbounded knapsaack
        we need
        arr of length
        capacity

        currently we dont have anyting
         */
        int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };

        int n = price.length;
        int W = price.length;

        int[] lengths = new int[n];

        for (int i = 0; i < n; i++) {
            lengths[i] = i + 1;
        }

        int[][] t = new int[n+1][n+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                //initilization
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }else{
                    if(lengths[i-1] <= j){
                        int val1 = price[i-1] + t[i][j - lengths[i-1]];
                        int val2 = t[i-1][j];
                        t[i][j] = Math.max(val1,val2);
                    }else{
                        t[i][j] = t[i-1][j];
                    }
                }
            }
        }

        //return last block
        int max = t[n][n];
        System.out.println("iterative max profit is "+max);
    }

    // coin change max count
    private static void coinChangeMaxCount_iterative() {

        int[] coins = {1,2,3};
        int sum = 4;

        // this is similar to count of subset sum problem
        int n = coins.length;
        int[][]t = new int[n+1][sum+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // initilizatino
                if(i == 0){
                    t[i][j] = 0;
                }
                if(j == 0){
                    t[i][j] = 1;  // overrride 1st (0,0) to 1
                }

                if(i > 0 && j > 0 ){
                    // choice diagram
                    if(coins[i-1] <= j){
                        int val1 = t[i][j - coins[i-1]]; // put (unbounded so i)
                        int val2 = t[i-1][j]; // donot
                        t[i][j] = val1 + val2;
                    }else{
                        t[i][j] = t[i-1][j]; // donot
                    }
                }
            }
        }
       // print(t);
        // last block is the answer
        System.out.println("coin change problem _ maximum no of ways "+ t[n][sum]);
    }

    public static void print(int[][] t){
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                System.out.print(t[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
