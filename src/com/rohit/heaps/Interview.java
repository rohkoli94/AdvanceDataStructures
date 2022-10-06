package com.rohit.heaps;

import java.util.*;

public class Interview {

    public static void main(String[] args) {

        //kth largest element
        kthLargestElement();

        System.out.println();
        //k smallest element
        kSmallestElement();

        System.out.println();
        // sort a k sorted array
        sortKSortedArray();

        System.out.println();
        // k closet elements to x are
        kClosetElementToX();

        System.out.println();
        // Top k frequent numbers
        topKFrequenctNumbers();

        System.out.println();
        // frequency sort
        frequencySort();

        System.out.println();
        // k closet pont to origin
        kClosetPointsToOrigin();

        System.out.println();
        // combine ropes to minimise the cost
        combineRopeToMinimiseCost();

        System.out.println();
        // sum of elements between k1th and k2th elements
        sumOfElementsBetweenK1AndK2();

      //  test();

    }



    public static void  kthLargestElement(){
        //kth largest element
        int[] arr = {8,6,5,4,9,2};
        int k = 3;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap by default

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if(pq.size() > k){
                pq.remove();
            }
        }
        System.out.println("kth largest element is "+pq.peek());
    }

    public static void  kSmallestElement(){
        //kth largest element
        int[] arr = {8,6,5,4,9,2};
        int k = 3;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap by default

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if(pq.size() > k){
                pq.remove();
            }
        }

        while(pq.size() > 0){
            System.out.println("smallest element is "+pq.peek());
            pq.remove();
            // or use pq.pool()  // it fetches last and also removed it
        }
    }
    public static void sortKSortedArray(){
        /* k sorted array means, array index 0,1,2,3,4....
        so for 0 element -> ans will be found out on left(k) or right (k) that is  0 + k
         */

        int[] arr = {6,5,3,2,8,10,9};
        int k = 3;

        int[] ans = new int[arr.length];
        int index = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min heap be default

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if(pq.size() > k){
                // peek is the smallest element as we have min heap;
                //store in ans and remove it
                ans[index] = pq.peek();
                pq.remove();
                index++;
            }
        }

        // some elements will be remaining so empty
        // and top will be minimum, so when we remove, automatically smalleer will comm at top

        while(pq.size() > 0){
            ans[index] = pq.peek();
            pq.remove();
            index++;
        }

        System.out.println(Arrays.toString(ans));

    }

    public static void kClosetElementToX(){
        int[] arr = {2,4,6,7,8,9};
        int k = 3;
        int x = 7;

        // closet can be found by subtracting ans taking absolute values
        // and then using max heap - as we want k smallest elements
        // but we will need track of element along with absolute value

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap

        for (int i = 0; i < arr.length; i++) {
            // we will need to store first as absolute values (pq will use it internally) and second as element
          int tmpVal = arr[i] - x;
            int absValue = tmpVal > 0 ? tmpVal : 0 - (tmpVal);
            // or int a = Math.abs(tmpVal);
            Pair pair = new Pair(absValue,arr[i]);
            pq.add(pair);

            if(pq.size() > k){
                pq.remove();
            }
        }

        // we have k smaller elements in heap
        System.out.println("k closet element are");
        while(pq.size()  > 0){
            System.out.print (pq.peek().element + " ");
            pq.remove();
        }
    }

    public static void topKFrequenctNumbers(){
        int[]arr = {1,1,1,3,2,2,4,4};
        int k = 3;

        // get frequencey count along with key
        /*
        1 = 3 times;
        2  = 2 times;
        3  = 1 time;
        4  = 1 time
         */

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], + map.getOrDefault(arr[i],0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(); // min heap

        for (Map.Entry<Integer, Integer> e : map.entrySet()){
            Pair pair = new Pair(e.getValue(),e.getKey());
            pq.add(pair);
            if(pq.size() > k){
                pq.remove();
            }
        }
        // remaining items are present inside the heap
        System.out.println("K top elements are ");
        while(pq.size() > 0){
            System.out.print(pq.peek().element + " ");
            pq.remove();
        }

    }

    public static void frequencySort(){
        int[]arr = {3,2,9,9,9,9,5,5,5,2,4};

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], + map.getOrDefault(arr[i],0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap

        // maximum times wil be up
        for (Map.Entry<Integer, Integer> e : map.entrySet()){
            Pair pair = new Pair(e.getValue(),e.getKey());
            pq.add(pair);
        }

        int index = 0;
        int[] ans = new int[arr.length];

        while (pq.size() > 0){
            int num = pq.peek().element;
            int frequency = pq.peek().value;

            for (int i = 1; i <= frequency ; i++) {
                ans[index] = num;
                index++;
            }
            pq.remove();
        }

        System.out.println("sorted array is "+Arrays.toString(ans));

    }

    public static void kClosetPointsToOrigin(){
        int[][] matrix = new int[][]{{1,2},{-3,-2},{5,5},{3,5}};
        int k = 2;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        for (int i = 0; i < matrix.length; i++) {
            // formula sq.rt of (x1-x2)^2 + (y1-y2)^2
            // origin is (0,0) i.e x2 = 0 and y2 = 0;
            // we can neglect sqrt for easy calculation as no difference in relation (greater/ smaller) of values
            //  distance = x^2 + y^2
            int distance = matrix[i][0] * matrix[i][0] + matrix[i][1] + matrix[i][1];
            Pair pair = new Pair(distance, matrix[i][0] , matrix[i][1] ); // distance,x,y
            pq.add(pair);
            if(pq.size() > k){
                pq.remove();
            }
        }

        System.out.println("closet point to origin is ");
        while(pq.size() > 0){
            System.out.println("[" + pq.peek().x + " " +pq.peek().y+"]");
            pq.remove();
        }
    }

    public static void combineRopeToMinimiseCost(){
        int[] arr = {1,4,5,7,8,3,5};

        PriorityQueue<Integer> pq= new PriorityQueue<>(); // min heap

        // store all minimum values at top
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        int total = 0;
        while(pq.size() >= 2){
            int first  = pq.peek();
            pq.remove();
            int second  = pq.peek();
            pq.remove();
            int combineValue = first + second;
            total = total + combineValue;

            pq.add(combineValue);
        }

        System.out.println("minimum cost is "+total);
    }

    public static void sumOfElementsBetweenK1AndK2(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10,12,13,14};
        int k1 = 2; // 2
        int k2 = 10; // 10

        int first  = kthsmallestElement(arr, k1);
        int second  = kthsmallestElement(arr, k2);

        System.out.println("sum of elements is");
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
         if(arr[i] > first && arr[i] < second){
           //  System.out.println(arr[i]);
             sum = sum + arr[i];
         }
        }
        System.out.println("sum is "+sum);
    }

    public static int  kthsmallestElement(int[] arr, int k){
        //kth largest element
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // min heap by default

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if(pq.size() > k){
                pq.remove();
            }
        }
        return pq.peek();
    }

    public static void test(){
        Map<Integer, Integer> hashmap = new HashMap<>();

        int[] arr = {1,1,1,2,2,3,3,4,4,4,4,4,5,5,5,5,5,6};

        for (int i = 0; i < arr.length; i++) {
           hashmap.put(arr[i],hashmap.getOrDefault(arr[i],0) + 1);

        }
                System.out.println();
        for (Map.Entry<Integer,Integer> e : hashmap.entrySet()){
            System.out.print(e.getKey() + " "+ e.getValue());
            System.out.println();
        }
    }

}
class Pair implements Comparable<Pair>{
    int value; // absolute value
    int element;

    int x;
    int y;

    public Pair(int value, int element) {
        this.value = value;
        this.element = element;
    }

    public Pair(int value,int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair o){
        return this.value - o.value;
    }
}



