package com.rohit.stacksAndQueues;

import java.util.Stack;

public class InterviewQuestions {

    public static void main(String[] args) {

        //  https://leetcode.com/problems/backspace-string-compare
        //Q.3 backspace character
        // time O (N+M)
        // space O (N+M)
        q3_backspaceCharacter();
        q3_backspaceCharacter2();

        // Next greatest right (foundation logic)
        q4_nextGreatestRight();
        // Next greatest left (foundation logic)
        q4_nextGreatestLeft();


    }

    public static void q3_backspaceCharacter(){
        String s = "ab#c";
        String t = "ad#c";

        System.out.println("ans is "+ backspaceCompare(s, t));
    }
    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = stackOperation(s);
        Stack<Character> stack2 = stackOperation(t);

        return stack1.equals(stack2);
    }
    public static Stack<Character> stackOperation(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '#' && !stack.isEmpty()){
                stack.pop();
            } else if (ch != '#') {
                stack.push(ch);
            }
        }
        return stack;
    }

    public static boolean q3_backspaceCharacter2(){
        String s = "ab#c";
        String t = "ad#c";

       int i = s.length() - 1;
       int j = t.length() - 1;

       int sSkip = 0;
       int tSkip = 0;

       while(i >= 0 || j >= 0){

           // check s
           while(i >= 0){
               if(s.charAt(i) == '#'){
                   sSkip++;
                   i--;
               }else if(sSkip > 0){
                   sSkip --;
                   i--;
               }else{
                   break;
               }
           }
           // check t
           while(j >= 0){
               if(t.charAt(j) == '#'){
                   tSkip++;
                   j--;
               }else if(tSkip > 0){
                   tSkip --;
                   j--;
               }else{
                   break;
               }
           }

           if(i >= 0 && j>= 0 && s.charAt(i) != t.charAt(j)){
               return false;
           }
           // if one is empty and other  has some values
          // -1 and -1 is proper means both are empty
           // -1 and 3 is improper

           if((i>=0) != (j>=0)){
               return false;
           }
           i--;
           j--;
       }
       return true;
    }

    public int stockRatta(int price){
        Stack<int[]> stack = new Stack<>();
            int res = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price)
                res += stack.pop()[1];
            stack.push(new int[]{price, res});
            return res;
    }


    public static void q4_nextGreatestRight(){
        int[] arr = {3,4,0,0,2,1};

        int[] ans = new int[arr.length];
        int index  = arr.length -1;

        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0 ; i--) {
                while(!stack.isEmpty() && stack.peek() <= arr[i]){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    ans[index] = -1;
                }else{
                    ans[index] = stack.peek();
                }
                stack.push(arr[i]);
            index--;
        }

        System.out.println("next greatest right ans is");
        for (int i = ans.length -1; i >=0 ; i--) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void q4_nextGreatestLeft(){
        int[] arr = {3,4,0,0,2,1};

        int[] ans = new int[arr.length];
        int index  = arr.length -1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <arr.length  ; i++) {
                while(!stack.isEmpty() && stack.peek() <= arr[i]){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    ans[index] = -1;
                }else{
                    ans[index] = stack.peek();
                }
            stack.push(arr[i]);
            index--;
        }

        System.out.println();
        System.out.println("next greatest left ans is");
        for (int i = 0; i < ans.length ; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}

    //https://leetcode.com/problems/online-stock-span/
    //Q.5 Online stock span

class StockSpanner {
    int currentIndex; // used as for loop
    Stack<int[]> stack; // to store {price and index}

    public StockSpanner() {
        stack = new Stack<>();
        currentIndex = 0;
    }

    public int next(int price) {

        int ans;

        // formula derived = current index -  Next Greatest element on left
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            stack.pop();
        }
        if(stack.isEmpty()){
            ans  = currentIndex - (-1);
        }else{
            ans = currentIndex - stack.peek()[1];
        }

        stack.push(new int[]{price, currentIndex});
        currentIndex++;
        return ans;
    }
}

// https://leetcode.com/problems/largest-rectangle-in-histogram
// largest area in histogram

class Solution {

    /*
        1.calculate NSR (pseudo index as arr.length and reverse ans )
        and NSL (pseudo index as -1 ) for each element  (stock span method of indexing)
        2.width of each element = (NSR - NSL ) -1;
        3. rectangle (area) = current element * width of each element
        4. find maximum inside rectangle

    */

    public int largestRectangleArea(int[] heights) {

        // NSR
        Stack<int[]> stackRight = new Stack<>();
        int[] rightans = new int[heights.length];
        int psedoIndexRight = heights.length;

        for(int i = heights.length - 1; i >= 0; i --){
            while(!stackRight.isEmpty() && stackRight.peek()[0] >= heights[i]){
                stackRight.pop();
            }
            if(stackRight.isEmpty()){
                // v.v.v.imp (don't do currentIndex - psedoIndexRight as we do i stock span)
                rightans[i] =  psedoIndexRight;
            }else{
                // v.v.v.imp (don't do currentIndex - stackRight.peek()[1] as we do i stock span)
                rightans[i] = stackRight.peek()[1];
            }
            // push data in stack
            stackRight.push(new int[]{heights[i],i});
        }
        // reverse rightans array
        int[] temp = new int[rightans.length];
        for(int i = rightans.length -1 ; i >= 0 ; i -- ){
            temp[i] = rightans[i];
        }
        rightans = temp;

        // NSL
        Stack<int[]> stackLeft = new Stack<>();
        int[] leftans = new int[heights.length];
        int psedoIndexLeft = -1;

        for(int i = 0 ; i < heights.length; i++){
            while(!stackLeft.isEmpty() && stackLeft.peek()[0] >= heights[i]){
                stackLeft.pop();
            }
            if(stackLeft.isEmpty()){
                // v.v.v.imp (don't do currentIndex - psedoIndexLeft as we do i stock span)
                leftans[i] = psedoIndexLeft;
            }else{
                // v.v.v.imp (don't do currentIndex - stackRight.peek()[1] as we do i stock span)
                leftans[i] = stackLeft.peek()[1];
            }
            // push data in stack
            stackLeft.push(new int[]{heights[i],i});
        }

        // calculate width, then reactangle and then maximum inside that
        int max = -1;
        int[] rectangle = new int[leftans.length];
        for(int i = 0 ; i < leftans.length; i ++){
            // width = (rightans[i] - leftans[i] ) - 1
            // reactangle  = height * width
            rectangle[i] =  heights[i] * ( rightans[i] - leftans[i]  - 1);
            if(rectangle[i] > max){
                max = rectangle[i];
            }
        }

        return max;

    }
}

// 6) https://leetcode.com/problems/maximal-rectangle

class SolutionMaxRectangle {

    /*
    if matrix length is n * m = 4(rows) * 5(columns)
    find (MAH) maxium area histogram for
    MAH 1 - take all values (wrote combined code as initially for MAH1 arr is int array means all 0)

    MAH 2,MAH 3,MAH 4 : if 0 then make element as 0 else add 1 + previous)

    */

    public int maximalRectangle(char[][] matrix) {

        int max = Integer.MIN_VALUE;
        int newMax;

        int[] arr = new int[matrix[0].length]; // passed in mah function

        // MAH 1 2,3,4

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0 ; j < matrix[i].length; j++){
                if(matrix[i][j] == '0'){
                    arr[j] = 0;
                }else{
                    arr[j] = arr[j] + 1;
                }
            }
            newMax = mah(arr);
            System.out.println("max " +newMax);
            if(newMax >= max){
                max =  newMax;
            }
        }
        return max;
    }

    public int mah(int[] arr){
        // find NSR, NSL , width = NSR- NSL - 1, area = height * width , then max from area

        // NSR
        Stack<int[]> stackL =  new Stack<>();
        Stack<int[]> stackR =  new Stack<>();
        int pseduoIndexL = -1;
        int pseduoIndexR = arr.length;
        int[] leftAns = new int[arr.length];
        int[] rightAns = new int[arr.length];

        // NSR
        for(int i = arr.length - 1; i >= 0 ; i--){
            while(!stackR.isEmpty() && stackR.peek()[0] >= arr[i]){
                stackR.pop();
            }
            if(stackR.isEmpty()){
                rightAns[i] = pseduoIndexR;
            }else{
                rightAns[i] = stackR.peek()[1];
            }
            // push into stack with element and index
            stackR.push(new int[]{arr[i],i});
        }

        // reverse
        int[]temp = new int[arr.length];
        for(int i = rightAns.length - 1; i >= 0 ; i--){
            temp[i] = rightAns[i];
        }
        rightAns = temp;

        // NSL
        for(int i = 0 ; i < arr.length ; i++){
            while(!stackL.isEmpty() && stackL.peek()[0] >= arr[i]){
                stackL.pop();
            }
            if(stackL.isEmpty()){
                leftAns[i] = pseduoIndexL;
            }else{
                leftAns[i] = stackL.peek()[1];
            }
            // push into stack with element and index
            stackL.push(new int[]{arr[i],i});
        }

        // find width (NSR - NSL - 1), then rectangle = height * width . then max
        int max = Integer.MIN_VALUE;
        int[] rectangle = new int[leftAns.length];
        for(int i = 0; i < leftAns.length; i++){
            rectangle[i] = arr[i] * (rightAns[i] - leftAns[i] - 1);

            if(rectangle[i] >= max){
                max = rectangle[i];
            }
        }
        return max;
    }
}


