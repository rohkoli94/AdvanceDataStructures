package com.rohit.hashmap;

public class CustomHashMap {

    private static final int SIZE = 100;
    private Node[] table;

    public CustomHashMap(){
        this.table = new Node[SIZE];
    }

    public int getHashIndex(int key){
        return key % SIZE;
    }

    // get
    public int get(int key){
        int hashIndex = getHashIndex(key);
        Node node = table[hashIndex];

        while(node != null){
            if(node.key == key){
                return node.value;
            }
            node = node.next;
        }
        return -1;
    }

    // put
    public void put(int key, int value){
        int hashIndex = getHashIndex(key);
        Node node = table[hashIndex];

        if(node == null){
            table[hashIndex] = new Node(key, value);
            return;
        }
        if(node.key == key){
            node.value = value;
            return;
        }
        while(node.next != null){
            if(node.next.key == key){
                node.next.value = value;
                return;
            }
            node = node.next;
        }
        node.next = new Node(key, value);
    }

    // remove
    public void remove(int key){
        int hashIndex = getHashIndex(key);
        Node node = table[hashIndex];

        if(node == null){
            return;
        }
        if(node.key == key){
            table[hashIndex] = node.next;
        }
        while(node.next != null){
            if(node.next.key == key){
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

}

class Node{
    int key;
    int value;
    Node next;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
