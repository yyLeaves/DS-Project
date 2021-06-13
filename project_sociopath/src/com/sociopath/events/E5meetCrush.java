package com.sociopath.events;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.*;

public class E5meetCrush {

    private int currentStudentId;
    private int crush;
    private int spreader;
    private boolean first = true;


    int convince=0, KnowSize=1;
    private int Vertices;   // No. of vertices
    private LinkedList<Integer> adj_list[]; //Adjacency Lists
    LinkedList<Integer> queue = new LinkedList<Integer>();
    LinkedList<Boolean> visit= new LinkedList<>();


    public E5meetCrush(int v) {
        Vertices = v;
        adj_list = new LinkedList[v];
        for (int i=0; i<v; ++i){         //create adjacency lists
            adj_list[i] = new LinkedList();
            visit.add(i, Boolean.FALSE);
        }
    }


    // add an edge to the graph
    void addEdge(int v,int w) {
        adj_list[v].add(w);
        adj_list[w].add(v);
    }


    void BFS()   {

        this.crush= crush();
        this.spreader =spreader(crush);
        System.out.println("Your have a crush on Student "+ crush);


        int day=1;
        System.out.println("Stanger "+ spreader +" starts to spread rumors about you\n" );
        // BFS queue

        // current node = visited, insert into queue
        visit.set(spreader, Boolean.TRUE);
        queue.add(spreader);


        while (queue.size() != 0)  {

            LinkedList<Integer> nextQueue = new LinkedList<Integer>();
            boolean dayPrinted = false;

            // traversed ALL the nodes in this layer
            // and put them into the nextLayer queue
            while(queue.size() != 0) {
                // deque an entry from queue and process it
                spreader = queue.poll();


                // get all adjacent nodes of current node and process
                Iterator<Integer> i = adj_list[spreader].listIterator();
                while (i.hasNext()){
                    int n = i.next();

                    if ( visit.get(n).booleanValue()==Boolean.FALSE)
                    {
                        if(!dayPrinted){
                            System.out.println("**** Day "+day+" ****");
                            dayPrinted=true;
                        }
                        visit.set(n, Boolean.TRUE);
                        nextQueue.add(n);
                        KnowSize++;
                        //System.out.print("spreaded to student: ");
                        System.out.print(n+ " ");

                    }
                }
                if(nextQueue.contains(currentStudentId)){      //YOU
                    System.out.println("\nRumors spread to your ear,you stop spreading");
                    int y=nextQueue.indexOf(5);        //remove object
                    nextQueue.remove(y);
                }

                if(nextQueue.contains(crush)){
                    System.out.println("\nOh no, your crush("+crush+")"+" knows!\n");
                    end();
                }
            }
            if(dayPrinted){
                queue = nextQueue;
                convinceOne();
                day++;
            }
        }
        System.out.println("Rumors will not reach to your crush, GOODLUCK!");
        end();
    }

    public int crush(){
        Random r=new Random();
        int crush= r.nextInt(10)+1;

        if (crush!=currentStudentId){
            crush=crush;
            spreader(crush);
        }
        else{
            crush();
        }
        return crush;
    }

    public int spreader(int crush){
        Random r=new Random();
        int spreader= r.nextInt(10)+1;
        if(spreader!= currentStudentId && spreader!= crush){
            spreader=spreader;
        }
        else{
            spreader(crush);
        }
        return spreader;
    }



    public void convinceOne(){

        if(first) {
            System.out.println("You can't convince anyone in the first day!");
            first = false;
        } else {
        System.out.println("\nWho do you want to convience? Press 0 if none");
        System.out.print("Convince: ");
/*        Scanner s=new Scanner(System.in);
        int input= s.nextInt();*/

            int input;
        String str = JOptionPane.showInputDialog(null,"who?","convince",JOptionPane.PLAIN_MESSAGE);
        if(str==null) {
            input = 0;
        } else {
            input = Integer.parseInt(str);
        }

        if(input==0){
            System.out.println("You did not convience anyone\n");
        }

        else if(!queue.contains(input)){
            convinceOne();
        }

        else {
            convince++;

            // get all adjacent nodes of current node and process
            Iterator<Integer> i = adj_list[input].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (visit.get(n).booleanValue() == Boolean.FALSE) {
                    visit.set(input, Boolean.TRUE);
                }
            }
            int y = queue.indexOf(input);        //remove object
            queue.remove(y);

            System.out.println("This student stops spreading to his friends\n");
        }
        }
    }

    public void end(){
        System.out.println(":: "+KnowSize+" person knows about your rumors");
        System.out.println(":: "+convince+" person has been convinced");
        System.exit(0);
    }

    public void meet(int currentStudentId) {
        this.currentStudentId = currentStudentId;
        E5meetCrush g = new E5meetCrush(11);
        //add edges to the graph
        g.addEdge(1, 2);
        g.addEdge(1, 7);
        g.addEdge(2, 3);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(4, 8);
        g.addEdge(4, 10);
        g.addEdge(9, 10);

        //print BFS sequence
        g.BFS();
    }

    public static void main(String args[])
    {
        //vertices

        E5meetCrush e5meetCrush = new E5meetCrush(11);
        e5meetCrush.meet(5);
    }

}

