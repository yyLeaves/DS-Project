package com.sociopath.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E6Friendship {
    private int vertex;
    private ArrayList<Integer>[] adjacencyList;

    public E6Friendship(int vertices) {

        this.vertex = vertices;

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
            for(Integer integer:pathList)
                System.out.print(integer + 1 + " ");
            System.out.println();
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

    public static void callE6(int amountOfVertices, int[][] vertices) {
//        Scanner x = new Scanner(System.in);
//        System.out.println("Input:");
//        int amountOfVertices = x.nextInt();
        E6Friendship g = new E6Friendship(amountOfVertices);
/*        for (int i=0; i<amountOfVertices; i++){
            g.addEdge(x.nextInt()-1, x.nextInt()-1);
        }*/

        for (int[] vertex : vertices) {
            g.addEdge(vertex[0]-1,vertex[1]-1);
        }


        System.out.println("Output:");
        for (int i=0; i<amountOfVertices;i++){
            for(int n=0; n<amountOfVertices; n++){
                if(i<n){
                    g.findPathList(i, n);
                }
            }
        }
    }
// Tester
     public static void main(String[] args) {
//         callE6();
         int amount = 3;
         int[][] v = new int[][]{{1,3},{2,3},{1,2}};
         callE6(amount,v);


     }
}
