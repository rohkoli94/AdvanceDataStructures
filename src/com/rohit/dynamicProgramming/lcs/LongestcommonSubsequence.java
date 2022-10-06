package com.rohit.dynamicProgramming.lcs;

public class LongestcommonSubsequence {

    public static void main(String[] args) {

        // longest common subsequence
        recursion_subsequence();

        String X = "abcd";
        String Y = "abdelfkg";
        // iterative  count longest common subsequence (LCM)
        System.out.println("iterative subsequence "+iterative_subsequence(X, Y));


        // iterative  count longest common subset (LC subset)
        System.out.println("iterative subset "+iterative_subset());

        // iterative  count longest palindromic subsequence
        System.out.println("iterative palindromic subsequence "+iterative_palindrome());

        // iterative  count shortest common supersequence
        System.out.println("iterative shortest common supersequence "+iterative_scs());


        // print LCM
        System.out.println("iterative  print LCM "+iterative_print_subsequence());

        // print Shortest common supersequence
        System.out.println("iterative  print supersequence "+iterative_print_supersequence());
    }

    private static String iterative_print_subsequence() {
        String X = "abcd";
        String Y = "abdelfkg";

        int m = X.length();
        int n = Y.length();

        int[][] t = new int[m+1][n+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // intiilization
                if(i == 0 || j == 0){
                    t[i][j]  = 0;
                }else{
                    if(X.charAt(i-1) == Y.charAt(j-1)){
                        t[i][j] = 1 + t[i-1][j-1];
                    }else{
                        t[i][j] = Math.max(t[i][j-1],t[i-1][j]);
                    }
                }
            }
        }

//        for (int i = 0; i < t.length; i++) {
//            for (int j = 0; j < t[i].length; j++) {
//                System.out.print(t[i][j]+" ");
//            }
//            System.out.println();
//        }

        String ans = "";
        int i = m;
        int j = n;
      //  System.out.println("i "+i);
      //  System.out.println("j "+j);
        while(i > 0 && j >0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) { // equal
                ans = ans + X.charAt(i - 1);
                i--;
                j--;
            } else if (t[i][j-1] > t[i-1][j]) { //  go to max direction
                j--;
            } else {
                i--;
            }
        }
        return ans;
    }

    private static String iterative_print_supersequence() {
        String X = "abcdlxkxydya";
        String Y = "zxya";

        int m = X.length();
        int n = Y.length();

        int[][] t = new int[m+1][n+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // intiilization
                if(i == 0 || j == 0){
                    t[i][j]  = 0;
                }else{
                    if(X.charAt(i-1) == Y.charAt(j-1)){
                        t[i][j] = 1 + t[i-1][j-1];
                    }else{
                        t[i][j] = Math.max(t[i][j-1],t[i-1][j]);
                    }
                }
            }
        }

//        for (int i = 0; i < t.length; i++) {
//            for (int j = 0; j < t[i].length; j++) {
//                System.out.print(t[i][j]+" ");
//            }
//            System.out.println();
//        }

        String ans = "";
        int i = m;
        int j = n;
      //  System.out.println("i "+i);
      //  System.out.println("j "+j);
        while(i > 0 && j >0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) { // equal
                ans = ans + X.charAt(i - 1);
                i--;
                j--;
            } else if (t[i][j-1] > t[i-1][j]) { //  go to max direction
                ans = ans + Y.charAt(j - 1);
                j--;
            } else {
                ans = ans + X.charAt(i - 1);
                i--;
            }
        }
        // any one will execute
        while(i > 0){
            ans = ans + X.charAt(i);
            i--;
        }
        while(j > 0){
            ans = ans + X.charAt(i);
            j--;
        }
        return ans;
    }

    private static int iterative_scs() {
            String X = "abcdegcbak";
            String Y = "hdyeabcd";

            // formula
       // count  = str1(length) + str2(length) - lcm(count)

        return X.length() + Y.length() + iterative_subsequence(X, Y);
        }

    private static int iterative_palindrome() {
        String X = "abcdegcbak";
        //String Y is reverse
        String Y= "";

        for (int i = X.length() - 1; i >= 0 ; i--) {
            Y = Y + X.charAt(i);
        }
       // System.out.println("reverse Y is "+Y);
        return iterative_subsequence(X, Y);
    }

    private static int iterative_subsequence( String X ,String Y ) {


        int m = X.length();
        int n = Y.length();

        int[][] t = new int[m+1][n+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // intiilization
                if(i == 0 || j == 0){
                    t[i][j]  = 0;
                }else{
                    if(X.charAt(i-1) == Y.charAt(j-1)){
                        t[i][j] = 1 + t[i-1][j-1];
                    }else{
                        t[i][j] = Math.max(t[i][j-1],t[i-1][j]);
                    }
                }
            }
        }

        return t[m][n];
    }

    private static int iterative_subset() {
        String X = "abcd";
        String Y = "abdelfkg";

        int m = X.length();
        int n = Y.length();

        int[][] t = new int[m+1][n+1];

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                // intiilization
                if(i == 0 || j == 0){
                    t[i][j]  = 0;
                }else{
                    if(X.charAt(i-1) == Y.charAt(j-1)){
                        ans = Math.max(ans,t[i][j] = 1 + t[i-1][j-1]);
                    }else{
                        t[i][j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    private static void recursion_subsequence() {

        String X = "abcd";
        String Y = "abdelfkg";

        int m = X.length();
        int n = Y.length();

        int[][] t = new int[m+1][n+1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = -1;
            }
        }
        System.out.println(subsequence(X, Y, m , n, t));

    }
    public static int subsequence(String X, String Y, int m , int n,int[][] t ){
        // base condition
        if(m == 0 || n == 0){
            return 0;
        }

        // memoization
        if(t[m][n] != -1){
            return t[m][n];
        }
        else if(X.charAt(m-1) == Y.charAt(n-1)){
           return t[m][n] = 1 + subsequence(X,Y, m-1, n-1,t);
        }else{
           return t[m][n] =  Math.max (
                    subsequence(X,Y, m, n-1,t),
                    subsequence(X,Y, m-1, n,t)
                    );
        }
       // return t[m][n];
    }
}
