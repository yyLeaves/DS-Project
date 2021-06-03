package com.sociopath.events;

import java.util.ArrayList;
import java.util.Scanner;

public class E6Friendship {



    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
            Scanner s =new Scanner(System.in);
            int amount = s.nextInt();
            E6Friendship test = new E6Friendship();
            while(amount != 0){
                int temp1 = s.nextInt();
                int temp2 = s.nextInt();
                ArrayList<Integer> tmpArrayList = new ArrayList<>();
                tmpArrayList.add(temp1);
                tmpArrayList.add(temp2);
                System.out.println(temp1 + " " + temp2);
                graph.addVertex(temp1);
                graph.addVertex(temp2);
                graph.addUndirectedEdge(temp1,temp2);
                graph.printEdges();
                amount--;
            }
    }
}