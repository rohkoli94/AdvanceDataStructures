package com.rohit.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayDeque;



public class CustomTree {

    public static int maxLeft = 0;

    public static int maxRight = 0;
    public static Scanner sc;

    // create tree
    public static Node createTree(){
        int data = sc.nextInt();

        // base condition
        if(data == -1){
            return null;
        }

        Node root = new Node(data);
        System.out.println("enter left data for "+data);
        root.left = createTree();
        System.out.println("enter right data for "+data);
        root.right = createTree();

        return root;
    }

    // inOrder traversal
    public static void inOrderTraversal(Node root){
        // base condition
        if(root == null){
            return;
        }
        // LNR
        inOrderTraversal(root.left); // L
        System.out.print(root.data +" "); //printing node N
        inOrderTraversal(root.right); // R
    }

    // preOrder traversal
    public static void preOrderTraversal(Node root){
        // base condition
        if(root == null){
            return;
        }
        // NLR
        System.out.print(root.data +" "); //printing node N
        preOrderTraversal(root.left); // L
        preOrderTraversal(root.right); // R
    }

    // postOrder Traversal
    public static void postOrderTraversal(Node root){
        // base condition
        if(root == null){
            return;
        }
        // LRN
        postOrderTraversal(root.left); // L
        postOrderTraversal(root.right); // R
        System.out.print(root.data +" "); //printing node N
    }

    // height of binary tree
    public static int heightOfTree(Node root){
        // base condition
        if(root == null){
            return 0;
        }
        // suppose i am root node, i will tell solve left and right and take max of it  and add 1 (of mine as well) to the max
        return Math.max(
                heightOfTree(root.left),
                heightOfTree(root.right)
        ) + 1;
    }

    // size of binary tree (how many nodes)
    public static int size(Node root){
        // base condition
        if(root == null){
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    // maximum value in binary tree
    public static int maxValue(Node root){
        // base condition
        if(root == null){
            return Integer.MIN_VALUE;
        }
        return Math.max(
                Math.max(maxValue(root.left),maxValue(root.right)),
                root.data
        );
    }

    // min value in binary tree
    public static int minValue(Node root){
        // base condition
        if(root == null){
            return Integer.MAX_VALUE;
        }
        return Math.min(
                Math.min(minValue(root.left),minValue(root.right)),
                root.data
        );
    }

    // level order traversal  O(n^2)

    public static  void levelOrderTraversal(Node root){
        for (int i = 1; i <= heightOfTree(root) ; i++) {
            printCurrentLevel(root,i);
        }
    }
    public static void printCurrentLevel(Node root, int level){
        // base condition
        if(root == null){
            return;
        }
        if(level == 1){
            System.out.print(root.data + " ");
        }else {
            printCurrentLevel(root.left,level-1);
            printCurrentLevel(root.right,level-1);
        }
    }

    // level order traversal using queue 0(n)

    public static void levelOrderTraversalQueue(Node root){
        if(root == null){
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node curr = q.poll();
            System.out.print(curr.data+" ");

            if(curr.left != null){
                q.add(curr.left);
            }
            if(curr.right != null){
                q.add(curr.right);
            }
        }
    }

    // level order traversal using queue 0(n) printing on each line

    public static void levelOrderTraversalQueueNewLine(Node root) {
        if(root == null){
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node curr = q.poll();
            if(curr == null){
                if(q.isEmpty()){
                    return;
                }
                q.add(null); // it means left and right has been added
                System.out.println();
            }else{
                System.out.print(curr.data+" ");

                if(curr.left!= null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }
    }

    //left view of tree


    public static  void leftView(Node root,int level){
        // bc
        if(root == null){
            return;
        }

        //print node
        if(maxLeft < level){
            System.out.print(root.data +" ");
            maxLeft = level;
        }
        leftView(root.left,level+1); // L
        leftView(root.right,level+1); // R
    }

    //right view of tree

    public static void rightView(Node root,int level){
        // bc
        if(root == null){
            return;
        }

        //print node
        if(maxRight < level){
            System.out.print(root.data +" ");
            maxRight = level;
        }
        rightView(root.right,level+1); // R
        rightView(root.left,level+1); // L
    }


    // top view of binary tree

    public static ArrayList<Integer> topView(Node root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new ArrayDeque<>();

        // list order traversal with check
        if(root == null){
            return list;
        }
        q.add(new Pair(0,root));

        while(!q.isEmpty()){
            Pair pair = q.poll();
            if(!map.containsKey(pair.hd)){ // only add once , as top view needs unique hd values
                map.put(pair.hd,pair.node.data);
            }
            if(pair.node.left != null){
                q.add(new Pair(pair.hd-1, pair.node.left));
            }
            if(pair.node.right != null){
                q.add(new Pair(pair.hd+1, pair.node.right));
            }
        }


        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    // bottom view of binary tree

    public static ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new ArrayDeque<>();

        // list order traversal with check
        if(root == null){
            return list;
        }
        q.add(new Pair(0,root));

        while(!q.isEmpty()){
            Pair pair = q.poll();
                map.put(pair.hd,pair.node.data);
            if(pair.node.left != null){
                q.add(new Pair(pair.hd-1, pair.node.left));
            }
            if(pair.node.right != null){
                q.add(new Pair(pair.hd+1, pair.node.right));
            }
        }


        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    //flatten binary tree to double linked list using inoder traversal

    public static Node prev;
    public static Node head;

    public static void dll(Node root){
        // base condition
        if(root == null){
            return;
        }
        // inorder traversal
        dll(root.left); // L
        // node
        if(prev== null){
            head = root;
        }else{
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        dll(root.right); // R

    }


    //diameter of a binary tree
    public static int res_diameter = Integer.MIN_VALUE;
    public static int diameter(Node root){
        // base condition
        if(root == null){
            return 0;
        }
        // hypothesis
        int l = diameter(root.left);
        int r = diameter(root.right);
        // induction

        int temp = Math.max(l,r) + 1; // want to pass to above
        int ans = Math.max(temp,l+r+1);  // relation -- possibly answer
        res_diameter = Math.max(ans,res_diameter);

        return temp;
    }

    // maximum path sum from any nide to any node;
    public static int res_maxSumAnyNodes = Integer.MIN_VALUE;
    public static int maxPathSumAnyNode(Node root){
        // base condition
        if(root == null){
            return 0;
        }
        // hypothesis
        int l = maxPathSumAnyNode(root.left);
        int r = maxPathSumAnyNode(root.right);
        // induction
        int temp = Math.max(Math.max(l,r) + root.data,root.data); //  if  l + r + currrent is less than current , then pass current data value
        int ans = Math.max(temp, l + r + root.data); // relation
        res_maxSumAnyNodes = Math.max(ans,res_maxSumAnyNodes);

        return temp;
    }

    // maximum path sum form any leaf to any leaf nodes
    public static int res_pathToPath = Integer.MIN_VALUE;
    public static int maxPathSumLeafToLeaf(Node root){
        // base condition
        if(root == null){
            return 0;
        }
        // hypothesis
        int l = maxPathSumLeafToLeaf(root.left);
        int r = maxPathSumLeafToLeaf(root.left);
        // induction
        int temp;
        if(root.left != null && root.right != null){
            temp = Math.max(l,r) + root.data;
            res_pathToPath = Math.max(res_pathToPath,l+r+root.data); // relation
        }else if(root.left != null){
            temp = l + root.data;
        }else if(root.right != null){
            temp = r + root.data;
        }else{
            temp = root.data;
        }
        return temp;
    }

    // lowest common ancestors
    public static Node lca(Node root, int val1, int val2){
        // base condition
        if(root == null){
            return null;
        }

        // case 1
        if(root.data == val1 || root.data == val2){
            return root;
        }

        Node left = lca(root.left, val1, val2);
        Node right = lca(root.left,val1,val2);

        /// case 2 and 3
        if(left != null){
            return right;
        }
        if(right != null){
            return left;
        }
        return root;
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        System.out.println("enter data");
        Node root = createTree();

        System.out.println("----------");
        inOrderTraversal(root);
        System.out.println();
        preOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);

        System.out.println();
        System.out.println("height of tree is "+heightOfTree(root));

        System.out.println();
        System.out.println("size of binary tree is "+size(root));

        System.out.println();
        System.out.println("max value is "+maxValue(root));

        System.out.println();
        System.out.println("min value is "+minValue(root));

        System.out.println("level order traversal O(n^2) ");
        levelOrderTraversal(root);

        System.out.println();
        System.out.println("level order traversal using queue O(n) ");
        // levelOrderTraversalQueue(root);

        System.out.println("level order traversal using queue O(n) with new line");
        // levelOrderTraversalQueueNewLine(root);

        System.out.println();
        System.out.println("left view of tree");
        // level 1 , maxleft se as static as 0
        leftView(root,1);

        System.out.println();
        System.out.println("right view of tree");
        // level 1 , maxright set as static  as 0
        rightView(root,1);

        System.out.println();
        System.out.println("top view");
        ArrayList<Integer> ans = topView(root);
        System.out.println("ans is "+ans.toString());


        System.out.println();
        System.out.println("bottom view");
        ArrayList<Integer> ans1= bottomView(root);
        System.out.println("ans is "+ans1.toString());

        System.out.println();
        System.out.println("diameter "+diameter(root));

        System.out.println();
        System.out.println("max sum any node to any node "+maxPathSumAnyNode(root));

        System.out.println();
        System.out.println("max sum any leaf to any leaf "+maxPathSumLeafToLeaf(root));


        System.out.println();
        System.out.println("lowest common ancestor "+lca(root,3,4));

        System.out.println();
        System.out.println("flattern binary tree to doubly linkedList");
        dll(root);
    }

}

class Node{
    public int data;
    public Node left;
    public Node right;

    public Node(int data){
        this.data = data;
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

