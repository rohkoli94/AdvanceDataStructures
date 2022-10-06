package com.rohit.dynamicProgramming.mcm;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        // count  minimum cost of matrix multiplication
        System.out.println("ans is "+recursion_matrix());
    }

    private static int recursion_matrix() {
        int[] arr = {40,10,20,30,40};
        //
        int i = 1;
        int j = arr.length - 1;

        int[][] t= new int[arr.length + 1][arr.length + 1];

        for (int y = 0; y < t.length; y++) {
            for (int z = 0; z < t[y].length; z++) {
                t[y][z] = -1;
            }
        }

        /*
        1. find i and j
        2. find base condition
        3. find partition k loop condition
        4. formula
        5.min/max
         */

        return solve(arr,i, j,t);
    }

    private static int solve(int[] arr, int i, int j,int[][] t) {
        // base condition
        if(i >= j){
           return t[i][j] = 0;
        }

        // memoization
        if(t[i][j] != -1){
            return  t[i][j];
        }else{
            // k loop and partition
            int ans = Integer.MAX_VALUE;
            for (int k = i; k < j ; k++) {
                int temp = solve(arr,i, k,t) + solve(arr,k+1, j,t) + arr[i-1] * k * j;
                ans = Math.min(ans, temp);
            }
            return  t[i][j] = ans;
        }
    }
}
