package com.sociopath.events;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static String callE6(int amountOfVertices, int[][] vertices) {
        String ret = "Output: ";
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


        for (int i=0; i<amountOfVertices;i++){
            for(int n=0; n<amountOfVertices; n++){
                if(i<n){
                    g.findPathList(i, n);
                }
            }
        }
        sorting();
//        System.out.println(ans.size());
        ret+=ans.size()+"\n";
        for (int i=0; i<ans.size(); i++){
            ret+=(i + 1 + ". " + ans.get(i)+"\n");
//            System.out.println(i + 1 + ". " + ans.get(i));
        }
        return ret;
    }
    // Tester
    public static void main(String[] args) {
//         callE6();
//        int amount = 4;
//        int[][] v = new int[][]{{1,3},{2,3},{1,2},{3,4}};

//        int amount = 1;
//        int[][] v = new int[][]{{1,3}};
        int amount = 3;
        int[][] v = new int[][]{{1,2},{2,3},{3,4}};
        System.out.println(callE6(amount, v));


    }
}
