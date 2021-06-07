package com.sociopath.events;

import java.util.ArrayList;
import java.util.List;

public class E6Friendship {
    private int vertex;
    private ArrayList<Integer>[] adjacencyList;
    private static ArrayList<ArrayList<Integer>> ans;

    public E6Friendship(int vertices) {

        this.vertex = vertices;
        ans = new ArrayList<>();
        initAdjList();
    }

    @SuppressWarnings("unchecked")
    private void initAdjList() {
        adjacencyList = new ArrayList[vertex];

        for (int i = 0; i < vertex; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        adjacencyList[to].add(from);
    }

    public void findPathList(int source, int destination) {
        boolean[] isVisited = new boolean[vertex];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(source);
        findAllPaths(source, destination, isVisited, pathList);
    }

    private void findAllPaths(Integer u, Integer d, boolean[] isVisited, List<Integer> pathList) {
        if (u.equals(d)) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(Integer integer:pathList)
                temp.add(integer + 1);
            ans.add(temp);
            return;
        }
        isVisited[u] = true;
        for (Integer i : adjacencyList[u]) {
            if (!isVisited[i]) {
                pathList.add(i);
                findAllPaths(i, d, isVisited, pathList);
                pathList.remove(i);
            }
        }
        isVisited[u] = false;
    }

    public static void sorting(){
        for (int pass=0; pass<ans.size()-1;pass++){
            for (int i=0; i<ans.size()-1-pass; i++){
                ArrayList<Integer> tmp;
                if (ans.get(i).size() > ans.get(i+1).size()){
                    tmp = ans.get(i);
                    ans.set(i, ans.get(i+1));
                    ans.set(i+1, tmp);
                }
            }
        }
    }

    public static String callE6(int[][] vertices) {
        int temp = 0;
        for (int[] i : vertices){
            for(int n : i){
                if(n>temp){
                    temp = n;
                }
            }
        }
        E6Friendship g = new E6Friendship(temp);
        for (int[] vertex : vertices) {
            g.addEdge(vertex[0]-1,vertex[1]-1);
        }
        for (int i=0; i<temp;i++){
            for(int n=0; n<temp; n++){
                if(i<n){
                    g.findPathList(i, n);
                }
            }
        }
        sorting();
        String output = ans.size() + "\nOutput: \n";
        for (int i=0; i<ans.size(); i++){
            output = output + (i + 1) + ". " + ans.get(i) + "\n";
        }
        return output;
    }

    public static void main(String[] args) {
        int[][] v = new int[][]{{2,1},{2,3},{3,4}};
        callE6(v);
    }
}
