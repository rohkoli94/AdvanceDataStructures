package com.rohit.dynamicProgramming.mcm;

public class EggDroppingProblem {

    public static void main(String[] args) {

        // count  minimum attempt to find critical point in worsdt case

        System.out.println("ans is "+eggDrop());
    }

    private static int eggDrop() {
        int f = 10;
        int e =3;

        int[][] t= new int[e+1][f+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = -1;
            }
        }
       return solve(e,f,t);
    }

    private static int solve(int e, int f,int[][] t) {

        // base condition
        if(f == 0 || f ==1 || e == 1){
            return t[e][f] = f;
        }

        // memoization
        if(t[e][f] != -1){
            return  t[e][f];
        }

        else{
            //k loop
        /*
            attempt = 1 + check broken egg or not broken
            then take min of that
         */
            int ans = Integer.MAX_VALUE;
            for (int k = 1; k <= f ; k++) {
                int temp = 1 + Math.max(solve(e-1,k-1,t),solve(e,f-k,t));
                ans = Math.min(ans,temp);
            }
            return t[e][f] = ans;
        }
    }
}
