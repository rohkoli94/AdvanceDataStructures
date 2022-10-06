package com.rohit.bst;

import java.util.*;

public class CustomBinarySearchTree {

    // search in BST
    public static boolean search(Node root, int key){
        // base condition
        if(root == null){
            return false;
        }
        if(key == root.data ){                 // key equal
            return true;
        }
        if(key < root.data ){                 // key is smaller ; search in left
            return search(root.left,key);
        }

        return search(root.right, key);      // key is bigger ; search in right
    }


    // insert in bst using recursion
    // time O(h), space O(h) ; h is height

    public static Node insertRec(Node root, int key){
        // bc
        if(root == null){
            return new Node(key);
        }
        if(key == root.data){
            return root;
        }
        if(key < root.data){
            root.left = insertRec(root.left, key); // we are inserting at last so point parent to newly inserted node
        }else{
            root.right = insertRec(root.right, key); // we are inserting at last so point parent to newly inserted node
        }
        return root;
    }

    // insert in bst using iterative
    // time O(h), space O(1) ; h is height

    public Node insertIterative(Node root, int key){

        if(root == null){
            Node node = new Node(key);
        };

        // we need to go till last
        // maintain 2 pointers curr and parent
        // cur will go till null to find last element
        // parent will be used to point left. right

        Node curr = root;
        Node parent= null;
        while(curr != null){ // traverse till last till null
            if(curr.data == key){ // if data is already present
                return root; // as leetcode or any compiler needs root internally
            }
            parent = curr; // parent is one step behind
            if(key < curr.data){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }
        // here check where to point parent to (left / right)
        if(key < parent.data){
            parent.left = new Node(key);
        }else{
            parent.right = new Node(key);
        }
        return root;
    }

    // inorder traversal
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        // LNR
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    // delete a node in BST

    public static  Node delete(Node root, int key){
        // base condition
        if(root == null){
            return root;
        }
        // binary search
        if(key < root.data){ // search left
            root.left = delete(root.left,key);
        }else if(key > root.data){ // search right
            root.right = delete(root.right, key);
        }else{ // ans found
            // here it can be 3 cases

            //case 1 : leaf node is to be deleted
            if(root.left == null && root.right == null){
                return null;
            }else if(root.left == null){ //case 2 : node  has only one child is to be deleted
                return root.right;
            }else if(root.right == null){ // case 2: same as abovwe
                return root.left;
            }else{ // case 3: node has both left and right
                // replace value of IS with current node and later delete it
                // find inorder successor i.e leftmost element in right sub tree
                Node IS = inordersuccessor(root.right);
                root.data = IS.data; // replace IS value with current node
                root.right = delete(root.right, IS.data);// delete IS value

            }
        }
        return root;
    }

    private static Node inordersuccessor(Node root) {
        // find left most element
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    // validate a binary tree
    public static Node prev = null;
    public static boolean validate(Node root){
        // bc , one of its kind
        if(root != null){
            // inorder LNR and check for false cases,as last we r returning true
            // check usong prev and current logic, curr is greater than previous
            if(!validate(root.left)){
                return false;
            }
            if(prev != null && root.data <= prev.data){
                return false;
            }
            return validate(root.right);
        }
        return true;
    }

    // floor
    public static int floor(Node root, int key){
        int ans = -1;
        while(root != null){
            if(root.data == key){
                return root.data;
            }
            if(key > root.data){
                ans = root.data;
                root = root.right;
            }else{
                root = root.left;
            }
        }
        return ans;
    }

    // ceil
    public static int ceil(Node root, int key){
        int ans = -1;
        while(root != null){
            if(root.data == key){
                return root.data;
            }
            if(key > root.data){
                root = root.right;
            }else{
                ans = root.data;
                root = root.left;
            }
        }
        return ans;
    }

    // two sum  (pair maks sum)
    public static Set<Integer> set = new HashSet<>();
    public static boolean twoSum(Node root, int sum){
        // bc
        if(root == null){
            return false;
        }
        // inorder LNR
        if(twoSum(root.left,sum)){
            return true;
        }
        // check complementary value is present
        if(set.contains(sum - root.data)){
            return true;
        }else{
            set.add(root.data);
        }
        return twoSum(root.right, sum);
    }

    // vertical order traversal in binary tree
    public static ArrayList<Integer> vertical(Node root){
        if(root == null){
            return null;
        }
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new ArrayDeque<>();

        q.add(new Pair(0, root));
        while(!q.isEmpty()){
            Pair pair = q.poll();
            if(map.containsKey(pair.hd)){
                map.get(pair.hd).add(pair.node.data);
            }else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(pair.node.data);
                map.put(pair.hd,temp);
            }

            if(pair.node.left != null){
                q.add(new Pair(pair.hd-1,pair.node.left));
            }
            if(pair.node.right != null){
                q.add(new Pair(pair.hd+1,pair.node.right));
            }
        }

        //
        ArrayList<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
            ans.addAll(entry.getValue());
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {8,5,3,1,4,6,10,11,14};
        Node root = null;

        for (int i = 0; i < arr.length; i++) {
            root = insertRec(root,arr[i]);
        }

        inorder(root);
        System.out.println();
        delete(root,11);
        System.out.println();
        inorder(root);

        System.out.println();
        System.out.println("validate "+validate(root));

        System.out.println();
        System.out.println("floor "+floor(root,7));

        System.out.println();
        System.out.println("ceil "+ceil(root,7));

        System.out.println();
        System.out.println("twoSum "+twoSum(root, 20));

        System.out.println();
        System.out.println("vertical traversal "+vertical(root));
    }
}

class Node{
    int data;
    Node left;
    Node right;


    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}


class Pair{
    int hd;
    Node node;

    public Pair(int hd, Node node) {
        this.hd = hd;
        this.node = node;
    }
}