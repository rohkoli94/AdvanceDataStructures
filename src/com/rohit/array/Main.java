package com.rohit.array;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

    }

    // kadane algorithm
    public static int maxSumSubArray(int[] arr){
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < arr.length; i++) {
            currSum = currSum + arr[i];
            if(currSum > maxSum){
                maxSum = currSum;
            }
            if(currSum < 0){
                currSum = 0;
            }
        }
        return maxSum;
    }

    // subarray sum equals k (start and end index)
    public static void subArrayIndex(int[] arr, int k){

        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int start = 0;
        int end = -1;

        for (int i = 0; i < arr.length; i++) {
            prefixSum = prefixSum + arr[i];
            if(prefixSum - k == 0){ //  handle first number
                start = 0;
                end = i;
                break;
            }
            if(map.containsKey(prefixSum - k)){
                start = map.get(prefixSum - k) + 1;
                end = i;
                break;
            }
            map.put(prefixSum,i);
        }

        if(end == -1){
            System.out.println("sum not found");
        }else{
            System.out.println(start + " " + end);
        }
    }


    // subarray sum equals k count
    public static int subArrayCount(int[] arr, int k){
        int prefixSum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum = prefixSum + arr[i];
            if(map.containsKey(prefixSum -k)){
                ans = ans + map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum,0) + 1);
        }
        return ans;
    }

    // subarray sum equals k longest length and start and end index
    public static void subArrayLongest(int[] arr, int k){
        int prefixSum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int length = 0;
        int end_index = -1;

        for (int i = 0; i < arr.length; i++) {
            prefixSum = prefixSum + arr[i];

            map.putIfAbsent(prefixSum,i);

            if(map.containsKey(prefixSum - k) && length < i - map.get(prefixSum - k)){
                length = i - map.get(prefixSum - k);
                end_index = i;
            }
        }

        System.out.println("length is "+length);
        System.out.println("start index is "+(end_index - length + 1 )+" endindex is "+end_index);
    }
}
