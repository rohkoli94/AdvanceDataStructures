package com.rohit.hashmap;

public class Main {

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1,100);
        map.put(2,200);
        map.put(2,300);
        System.out.println(map.get(2));
    }
}
