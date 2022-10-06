package com.rohit.graphs;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int v = 7;
        int e = 4;

        // adjacency matrix  O(v*v)
       // int[][] m = new int[v][v];
       // addEdge(m,1, 2);

        //-----------------------------------------------------
        // adjacency list O(v+n)
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <v  ; i++) {
            list.add(new ArrayList<Integer>());
        }
        addEdge(list,0,1);
        addEdge(list,0,2);
        addEdge(list,1,2);
        addEdge(list,1,3);
        addEdge(list,2,3);
        addEdge(list,2,4);
        addEdge(list,3,4);
        printList(list);
        System.out.println();
        System.out.println("printing bfs");
        bfs(list,v,0);
        System.out.println();
        System.out.print("bfs count if different components "+bfsDis(list,v));
        System.out.println();
        System.out.println("printing dfs");
        dfs(list,v);
        System.out.println();
        System.out.println("cycle detection on undirected graph "+detectCyleUndirected(list,v));

        System.out.println();
        System.out.println("topological sort using dfs");
        System.out.println(Arrays.toString(topologicalSortDfs(list,v)));

        System.out.println();
        System.out.println("detect cycle in directed graph "+isCyclic(list,v));
    }

    private static void addEdge(int[][] m ,int source, int destination) {
        m[source][destination] = 1;
        m[destination][source] = 1;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> list ,int source, int destination) {
        list.get(source).add(destination);
        list.get(destination).add(source);
    }

    public static void printList(ArrayList<ArrayList<Integer>> list){

        for (int i = 0; i < list.size(); i++) {
                System.out.println(i+" --> "+list.get(i));
        }
    }
    public static void bfs(ArrayList<ArrayList<Integer>> list, int v, int s){
        boolean[] visited = new boolean[v];
        Queue <Integer> q = new ArrayDeque<>();

        visited[s] = true;
        q.add(s);

        while(!q.isEmpty()){
            int u = q.poll();
            System.out.print(u+" ");

            for(int num : list.get(u)){
                if(!visited[num]){
                    visited[num] = true;
                    q.add(num);
                }
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> list,int s,  boolean[] visited){
        Queue <Integer> q = new ArrayDeque<>();

        visited[s] = true;
        q.add(s);

        while(!q.isEmpty()){
            int u = q.poll();
            System.out.print(u+" ");

            for(int num : list.get(u)){
                if(!visited[num]){
                    visited[num] = true;
                    q.add(num);
                }
            }
        }
    }

    // bfs discontinous (count number of different components)
    public static int bfsDis(ArrayList<ArrayList<Integer>> list, int v){
        int count = 0;
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                count++;
                bfs(list,i,visited);
            }
        }

        return count;
    }

    //dfs

    public static void dfs(ArrayList<ArrayList<Integer>> list, int v){
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                dfsRec(list, i,visited);
            }
        }
    }
    public static void dfsRec(ArrayList<ArrayList<Integer>> list, int s, boolean[] visited ){
        visited[s] = true;
        System.out.print(s + " ");
        for(int num : list.get(s)){
            if(!visited[num]){
                dfsRec(list,num,visited);
            }
        }
    }


    // detect cycle in undirected graph

    public static boolean detectCyleUndirected(ArrayList<ArrayList<Integer>> list, int v){
        boolean [] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                if(dfscycleRec(list,i , -1,visited)){ // list, starting ppint, parent intial as -1
                    return true;
                }
            }
        }
        return  false;
    }

    public static boolean dfscycleRec(ArrayList<ArrayList<Integer>> list, int s, int parent,boolean[] visited){
        visited[s] = true;

        for (int neighbour : list.get(s)){
            if(!visited[neighbour]){
                if(dfscycleRec(list,neighbour,s,visited)){
                    return true;
                }
            }else if(parent != neighbour){
                return true;
            }
        }
        return false;
    }

    // topological sort

    public ArrayList<Integer> topologicalSortBfs(ArrayList<ArrayList<Integer>> adj, int v){
        // find indegree
        // bfs
        // put all 0 inside quque
        // check untill empty and find neighbours and make them --  and check if 0 then put in quque

        ArrayList<Integer> ans = new ArrayList<>();
        int[] inDegree = new int[v];
        Queue<Integer> q = new ArrayDeque<>();

        // find indegree
        for(ArrayList<Integer> list : adj){
            for(int num : list){
                inDegree[num]++;
            }
        }

        // put all 0 in quque
        for(int num : inDegree){
            q.add(num);
        }

        while(!q.isEmpty()){
            int curr= q.poll();
            ans.add(curr); // printing

            for(int neighbour : adj.get(curr)){
                inDegree[neighbour]--; // reduce in degree
                if(inDegree[neighbour] == 0){
                    q.add(neighbour);
                }
            }
        }

        return ans;
    }

    // topological sort using dfs
    public static int[] topologicalSortDfs(ArrayList<ArrayList<Integer>> adj, int v){
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                dfsRecTopological(adj,i,visited,stack);
            }
        }

        int[] ans = new int[v];
        int index = 0;
        while(!stack.isEmpty()){
            ans[index++] = stack.pop();
        }
        return ans;
    }

    private static void dfsRecTopological(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, Stack<Integer> stack) {
    visited[s] = true;

    for(int neighbour : adj.get(s)){
        if(!visited[neighbour]){
            dfsRecTopological(adj,neighbour,visited,stack);
        }
    }
    stack.push(s);
    }

    // detect cycle directed graph
    public static boolean isCyclic(ArrayList<ArrayList<Integer>> adj , int v) {
        // code here

        boolean[] visited = new boolean[v];
        boolean[] currVisited = new boolean[v];

        for(int i = 0 ; i < v ; i++){
            if(!visited[i]){
                if(dfsRec(adj,i, visited,currVisited)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean  dfsRec(ArrayList<ArrayList<Integer>>  adj, int s,  boolean[] visited, boolean[] currVisited){
        visited[s] =  true;
        currVisited[s] = true;

        for(int neighbour : adj.get(s)){
            if(!visited[neighbour]){
                if(dfsRec(adj,neighbour,visited,currVisited)){
                    return true;
                }
            }else if(currVisited[neighbour]){
                return true;
            }
        }
        currVisited[s] = false; // backtracking
        return false;
    }

    // minimum spanning tree (Prim's algorithm)

    public static int prims(ArrayList<ArrayList<ArrayList<Integer>>>  adj, int vertex){
        boolean[] visited = new boolean[vertex];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int ans= 0;
        pq.add(new Pair(0,0)); // vertex, weight

        while(pq.size() != 0){
            Pair p = pq.poll();
            int v = p.vertex;
            int weight = p.weight;

            if(!visited[v]){
                visited[v] = true;
                ans = ans + weight;

                /// find neighbours
                ArrayList<ArrayList<Integer>>  list   = adj.get(v);
                for(ArrayList<Integer>  neigh: list){
                    int u = neigh.get(0);
                    int wt = neigh.get(1);

                    if(!visited[u]){
                        pq.add(new Pair(u,wt));
                    }
                }
            }
        }
        return ans;
    }

    //  (Dihkstaras's algorithm)

    public static  int[] djKstaras(ArrayList<ArrayList<ArrayList<Integer>>>  adj, int vertex){
        boolean[] visited = new boolean[vertex];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] direction = new int[vertex];
        Arrays.fill(direction,Integer.MAX_VALUE);

        pq.add(new Pair(0,0)); // vertex, weight
        direction[0] = 0;

        while(pq.size() != 0){
            Pair p = pq.poll();
            int v = p.vertex;

            if(!visited[v]){
                visited[v] = true;

                /// find neighbours
                ArrayList<ArrayList<Integer>>  list   = adj.get(v);
                for(ArrayList<Integer>  neigh: list){
                    int u = neigh.get(0);
                    int wt = neigh.get(1);

                    if(direction[v] + wt < direction[u] ){
                        direction[u] = direction[v] + wt;
                        pq.add(new Pair(u,wt));
                    }
                }
            }
        }
        return direction;
    }

    // kruskals algorithm

    public static int[] parent;

    public static int kruskals(ArrayList<ArrayList<ArrayList<Integer>>> adj , int n){

        // fill union parent array
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // maintain arrayList of Edge(src, dst, weight) in asc order
        // for loop and keep counter, if counter is n-1 then break, you got the minimum spanning tree
        // use union find method to check if path is already been added or not

        ArrayList<Edge> edgesList = new ArrayList<>();
        // extract edges from data
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                ArrayList<Integer> list = adj.get(i).get(j);
                Edge edge = new Edge(i,list.get(0),list.get(1));
                edgesList.add(edge);
            }
        }

        // sort in asc order
        Collections.sort(edgesList);

        // loop and check if counter is n-1, that is MST
        int count = 0 ;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            Edge edge = edgesList.get(i);
            int x = find(edge.src);
            int y = find(edge.dst);
            // union
            if(x != y){
                count = count + 1;
                ans = ans + edge.weight;
            }
            if(count == n- 1){
                break;
            }
        }

        return ans;
    }


    public static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return find(parent[x]);
    }

   // DAG find all path from source destination
   public static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public static ArrayList<Integer> path =  new ArrayList<>();
    public static int indexAdded = 0;

    public static ArrayList<ArrayList<Integer>> shortestPath(ArrayList<ArrayList<Integer>>  adj, int s ,  int d){
        // visited not required as acyclic
        dfsRecc(adj,s,d);
        return ans;
    }

    public static void dfsRecc(ArrayList<ArrayList<Integer>>  adj , int s ,  int d){
        path.add(s);
        indexAdded++;
        if(s == d){
        ans.add(path);
        }else{
            // fibnd neigh
            for(int neigh : adj.get(s)){
                dfsRecc(adj,neigh,d);
            }
        }
        // while returning remove item from arraylist
        path.remove(--indexAdded);
    }

}

// https://leetcode.com/problems/find-if-path-exists-in-graph/submissions/
//1971. Find if Path Exists in Graph

class Solution1 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // adjacency list :create arraylist of arraylist
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            list.add(new ArrayList<>());
        }

        // add edge
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            list.get(u).add(v);
            list.get(v).add(u);
        }

        // bfs

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        visited[source] = true;
        q.add(source);

        while(!q.isEmpty()){
            int num = q.poll();
            if(num == destination){ //
                return true;
            }

            for(int m : list.get(num)){
                if(!visited[m]){
                    visited[m] = true;
                    q.add(m);
                }
            }
        }
        return false;
    }
}
// Find the Town Judge
// https://leetcode.com/problems/find-the-town-judge/submissions/
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n+1];

        for(int[] numArr : trust){
            count[numArr[0]]--; // outwards so negative point
            count[numArr[1]]++;// outwards so postive point
        }

// check if any one has n-1 , as that is the answer as everyone except judge truste the judge
        for(int i = 1; i <= n ; i++){
            if (count[i] == n-1){
                return i;
            }
        }
        return -1;
    }
}


//https://leetcode.com/problems/number-of-islands/submissions/

//  Number of Islands

class Solution2 {
    public int numIslands(char[][] grid) {
        int ans = 0;

        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                if(grid[i][j] == '1'){
                    ans = ans + 1;
                    dfsRec(grid,m,n,i,j);
                }
            }
        }
        return ans;
    }


    public static void dfsRec(char[][] grid,int m,int n,int i,int j){
        grid[i][j] = '0';

        // check for adjacent with end cases
        //left right, top,down

        if(isValid(grid,m,n ,i, j-1)){ // left
            dfsRec(grid,m,n,i,j -1);
        }

        if(isValid(grid,m,n ,i, j+1)){ // right
            dfsRec(grid,m,n,i,j +1);
        }

        if(isValid(grid,m,n ,i+1, j)){ // top
            dfsRec(grid,m,n,i +1,j);
        }

        if(isValid(grid,m,n ,i-1, j)){ // down
            dfsRec(grid,m,n,i-1,j);
        }

    }

    public static boolean isValid(char[][] grid,int m,int n,int i,int j){
        if(i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1'){
            return true;
        }
        return false;
    }
}


// https://leetcode.com/problems/flood-fill/submissions/

// Flood Fill
class Solution3 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int initialcolor = image[sr][sc];

        if(initialcolor != color){ //  if both are same return same image else dfs
            dfsRec(image,sr,sc,color,initialcolor);
        }
        return image;
    }

    public void dfsRec(int[][] image, int i, int j, int color, int initialcolor){
        image[i][j] = color;// visited means change color

        // check 4 directions with valid case
        if(isValid(image,i, j-1, color,initialcolor)){
            dfsRec(image,i,j-1,color,initialcolor);
        }
        if(isValid(image,i, j+1, color,initialcolor)){
            dfsRec(image,i,j+1,color,initialcolor);
        }
        if(isValid(image, i -1,j, color,initialcolor)){
            dfsRec(image,i-1,j,color,initialcolor);
        }
        if(isValid(image, i +1,j, color,initialcolor)){
            dfsRec(image,i+1,j,color,initialcolor);
        }
    }


    public boolean isValid(int[][] image, int i, int j, int color, int initialcolor){
        int m = image.length;
        int n = image[0].length;
        if(i>=0 && i < m && j >= 0 && j <n && image[i][j] == initialcolor){
            return true;
        }
        return false;
    }
}


//https://leetcode.com/problems/find-eventual-safe-states/
// Find Eventual Safe States
class Solution4 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // graph[][] is adjacency list
        // graph size i.e lenth is n i.e number of vertex

        // directed graph cycle detection logic

        int v = graph.length;
        boolean[] visited = new boolean[v];
        boolean[] currvisited = new boolean[v];

        boolean[] cyclePresent = new boolean[v];

        for(int i = 0 ; i < v; i++){
            if(!visited[i]){
                dfsRec(graph, i, visited, currvisited,cyclePresent);
            }
        }

        // iterate over cyclePresent
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < cyclePresent.length; i++){
            if(!cyclePresent[i]){
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean dfsRec(int[][] graph,int s, boolean[] visited, boolean[] currvisited,boolean[] cyclePresent){

        visited[s] = true;
        currvisited[s] = true;
        // as we are given int[][] instead of arraylist of araylist
        for(int neighbour : graph[s]){
            if(!visited[neighbour]){
                if(dfsRec(graph, neighbour, visited, currvisited,cyclePresent)){
                    cyclePresent[s] = true;
                    return true;
                }
            }else if(currvisited[neighbour]){
                cyclePresent[s] = true;
                return true;
            }
        }
        currvisited[s] = false; // backtracking
        return false;
    }
}


//https://leetcode.com/problems/course-schedule/submissions/
// Course Schedule

class Solution5 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // cycle present means return false;

        int v = numCourses;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < v; i ++){
            adj.add(new ArrayList<>());
        }

        for(int[] data : prerequisites){
            int u1 = data[0];
            int u2 = data[1];
            adj.get(u1).add(u2);
        }

        boolean[] visited = new boolean[v];
        boolean[] currvisited = new boolean[v];

        for(int i = 0 ; i < v; i++){
            if(!visited[i]){
                if(dfsRec(adj, i, visited, currvisited)){
                    return false; // as ans wants false if cycle present
                }
            }
        }
        return true; //// as ans wants true if cycle not present
    }

    public boolean dfsRec(ArrayList<ArrayList<Integer>> adj,int s, boolean[] visited, boolean[] currvisited){

        visited[s] = true;
        currvisited[s] = true;

        for(int neighbour : adj.get(s)){
            if(!visited[neighbour]){
                if(dfsRec(adj, neighbour, visited, currvisited)){

                    return true;
                }
            }else if(currvisited[neighbour]){

                return true;
            }
        }
        currvisited[s] = false; // backtracking
        return false;
    }
}

// https://leetcode.com/problems/max-area-of-island/submissions/
// Max Area of Island

class Solution6 {
    public static int ans;
    public static int area;

    public int maxAreaOfIsland(int[][] grid) {

        ans = 0;
        area = 0;

        int m = grid.length;
        int n = grid[0].length;

        // dfs
        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 1){

                    area = 0;
                    dfs(grid,i,j,m,n);
                    // check max value here
                    if(area > ans){
                        ans = area;
                    }
                }
            }
        }

        return ans;
    }

    public boolean isValid(int[][] grid,int i,int j,int m,int n){

        if(i >= 0 && i < m && j >= 0 && j< n && grid[i][j] == 1){
            return true;
        }
        return false;
    }

    public void dfs(int[][] grid,int i,int j,int m,int n){
        grid[i][j] = 0; // visited
        area = area + 1;


        // check 4 directions
        if(isValid(grid,i+1,j,m,n)){

            dfs(grid,i+1,j,m,n);
        }
        if(isValid(grid,i-1,j,m,n)){
            dfs(grid,i-1,j,m,n);
        }
        if(isValid(grid,i,j+1,m,n)){
            dfs(grid,i,j+1,m,n);
        }
        if(isValid(grid,i,j-1,m,n)){
            dfs(grid,i,j-1,m,n);
        }

    }

}

// https://leetcode.com/problems/rotting-oranges/submissions/

// Rotting Oranges

class Solution7 {

    public int orangesRotting(int[][] grid) {
        // bfs logic
        // put all rotten oranges in queue at once
        // maintian fresh oranges count
        // if fresh count is 0  return 0;
        // else do bfs with valid 4 directions and maintain temp count of convertinf fresh orange to rottn
        // after bfs if traverse and if you found fresh then return -1

        int m = grid.length;
        int n = grid[0].length;
        // pair of i, j
        Queue<int[]> q = new ArrayDeque<>();

        int freshOrangecount =  0;
        int timeTaken = 0;

        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n ; j ++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i,j});
                }else if(grid[i][j] == 1){
                    freshOrangecount++;
                }
            }
        }


        if(freshOrangecount == 0){
            return 0 ; // case 1 handled
        }

        //do bfs
        while(!q.isEmpty()){
            int qSize = q.size();
            int tempCountOfFreshToRotten = 0;
            // find  ADJACENT ELEMENTS LOGIC BUT POPPING INSIDE
            // find FRESH AND MAKE IT ROTEN
            for(int i = 0 ; i < qSize; i++){
                // pop element
                int[] currElemeent = q.poll();
                // find l r t d with valid condition
                // advance method
                int[] direcX = new int[]{1,-1,0,0};
                int[] direcY = new int[]{0,0,1,-1};

                for(int iLoop = 0 ; iLoop < direcX.length; iLoop++){
                    int newRow = currElemeent[0] + direcX[iLoop];
                    int newCol = currElemeent[1] + direcY[iLoop];

                    if(isValid(grid,newRow,newCol)){
                        tempCountOfFreshToRotten++;
                        grid[newRow][newCol] = 2; //  make rotten
                        q.add(new int[]{newRow,newCol});
                    }
                }
            }
            if(tempCountOfFreshToRotten != 0){
                timeTaken++;
            }
        }

        // find fresh orange still pending, return -1


        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n ; j ++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }

        // else return proper answer
        return timeTaken;
    }


    public boolean isValid(int[][] grid,int i,int j){
        int m = grid.length;
        int n = grid[0].length;

        if(i >= 0 && i < m && j>=0 && j < n && grid[i][j] == 1){
            return true;
        }
        return false;
    }
}

// https://leetcode.com/problems/surrounded-regions/submissions/
// Surrounded Regions
class Solution8 {

    public void solve(char[][] board) {
        // boundary dfs (ulta approach)

        // find 4 corners and if 0 found dfs and change to 'b'
        // at last replace 0 with X and b with 0;

        int m = board.length;
        int n = board[0].length;


        for(int  i = 0 ; i < board.length; i++){
            int j = 0 ;
            int jLast = board[0].length - 1;

            if(board[i][j] == 'O'){
                dfsRec(board,m,n,i,j);
            }
            if(board[i][jLast] == 'O'){
                dfsRec(board,m,n,i,jLast);
            }
        }

        for(int  j = 0 ; j < board[0].length; j++){
            int i = 0 ;
            int iLast = board.length -1;

            if(board[i][j] == 'O'){
                dfsRec(board,m,n,i,j);
            }
            if(board[iLast][j] == 'O'){
                dfsRec(board,m,n,iLast,j);
            }
        }

        // replace o with X and  b with 0
        for(int  i = 0 ; i < board.length; i++){
            for(int  j = 0 ; j < board[0].length; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'B'){
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static boolean isValid(char[][] board,int m,int n,int i,int j){
        if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'O'){
            return true;
        }
        return false;
    }

    // dfs
    public void dfsRec(char[][] board,int m, int n, int i , int j){
        board[i][j] = 'B';

        // find neightbours
        if(isValid(board,m,n ,i, j-1)){ // left
            dfsRec(board,m,n,i,j -1);
        }

        if(isValid(board,m,n ,i, j+1)){ // right
            dfsRec(board,m,n,i,j +1);
        }

        if(isValid(board,m,n ,i+1, j)){ // top
            dfsRec(board,m,n,i +1,j);
        }

        if(isValid(board,m,n ,i-1, j)){ // down
            dfsRec(board,m,n,i-1,j);
        }
    }



}

class Pair implements  Comparable<Pair>{
    int vertex;
    int weight;

    public Pair(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair ot){
        return this.weight = ot.weight;
    }
}

class Edge implements Comparable<Edge>{
    int src;
    int dst;
    int weight;

    public Edge(int src, int dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge ot){
        return this.weight = ot.weight;
    }
}