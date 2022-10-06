package com.rohit.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Max Sum SubArray With K Window
        System.out.println(maxSumSubArrayWithKWindow(new int[]{1,2,3,4,5},9));

        // find occurance of anagram
        System.out.println(findOccuranceOfAnagram("abcfoxcdoxf","fox"));
    }

    public static int maxSumSubArrayWithKWindow(int[] arr, int k){
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        int i = 0, j = 0;

        while(j < arr.length){
            sum = sum + arr[j];

            if(j - i + 1 < k){ // proceed until uh find window
                j++;
            }else if( j - i + 1 == k){ // window is found
                maxSum = Math.max(maxSum,sum);
                sum = sum - arr[i]; // before moving make changes for ith element
                // moive window ahead
                i++;
                j++;
            }
        }

        return maxSum;
    }

    public static int findOccuranceOfAnagram(String s1, String pattern){
        int ans = 0;

        // maintain map and counter, so that no need to traverse map evertytime

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            map.put(ch, map.getOrDefault(ch,0) + 1);
        }
        int count = map.size();

        int k = pattern.length();
        int i=0,j=0;

        while(j < s1.length()){
            // calculation
            char ch = s1.charAt(j);
            if(map.containsKey(ch)){
                // we found one letter so minus the count and if 0 the decrement the count variable
                map.put(ch,map.get(ch) - 1);
                if(map.get(ch) == 0){
                    count--;
                }
            }
            if( j - i + 1 < k){
                j++;
            }else if( j - i + 1 == k){
                // find ans
                if(count == 0){
                    ans = ans + 1; // occurance found
                }
                // make changes for ith element
                char ithChar = s1.charAt(i);
                if(map.containsKey(ithChar)){
                    map.put(ithChar,map.get(ithChar) + 1);  // increment
                    if(map.get(ithChar) == 1){ // ratta
                        count++;
                    }
                }
                // slide window
                i++;
                j++;
            }
        }
        return ans;
    }
}
