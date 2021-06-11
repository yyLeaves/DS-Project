package com.sociopath.events;

import java.io.*;
import java.util.LinkedList;
import java.util.*; 


public class GraphVisted {

    int convince=0, KnowSize=1;
    private int Vertices;   // No. of vertices 
    private LinkedList<Integer> adj_list[]; //Adjacency Lists 
    LinkedList<Integer> queue = new LinkedList<Integer>();
    LinkedList<Boolean> visit= new LinkedList<>();
    
    GraphVisted(int v) { 
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
    

  void BFS(int spreader,int crush)   {

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
                if(nextQueue.contains(5)){      //node5= YOU
                    System.out.println("\nRumors spread to your ear,you stop spreading");
                    int y=nextQueue.indexOf(5);        //remove object
                    nextQueue.remove(y);
                }
                
                if(nextQueue.contains(crush)){
                    System.out.println("\nOh no, your crush(7) knows!\n");
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
        
  
    public void convinceOne(){  
             
            System.out.println("\nWho do you want to convience? Press 0 if none");
            System.out.print("Convince: ");
            Scanner s=new Scanner(System.in);
            int input= s.nextInt();
            
            if(input==0){
                System.out.println("You did not convience anyone\n");
            }
            
            else if(!queue.contains(input)){
                 convinceOne(); 
            }
            
            else 
            {
               convince++;

                // get all adjacent nodes of current node and process
                Iterator<Integer> i = adj_list[input].listIterator(); 
                while (i.hasNext()){  
                    int n = i.next(); 
                    if (visit.get(n).booleanValue()==Boolean.FALSE) { 
                        visit.set(input, Boolean.TRUE); 
                        }
                    }
                int y=queue.indexOf(input);        //remove object
                queue.remove(y);

            System.out.println("This student stops spreading to his friends\n");
            }         
    }        
    
     public void end(){
      System.out.println(":: "+KnowSize+" person knows about your rumors");
      System.out.println(":: "+convince+" person has been convinced"); 
      System.exit(0);
     }   

     public static void t() {
         //vertices
         GraphVisted g = new GraphVisted(11);
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
         //start from rumor spreader


//        Random r=new Random();
//        int spreader= r.nextInt(10)+1;
//        int crush= r.nextInt(10)+1;
//
//        if (spreader==crush || spreader==YOU){
//            spreader= r.nextInt(10)+1;
//        }
//
//        if (crush==spreader || crush==YOU){
//            spreader= r.nextInt(10)+1;
//        }

         g.BFS(3,7);
     }

    public static void main(String args[]) 
    { 
        //vertices
        GraphVisted g = new GraphVisted(11); 
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
        //start from rumor spreader
        
 
//        Random r=new Random();
//        int spreader= r.nextInt(10)+1;
//        int crush= r.nextInt(10)+1;
//        
//        if (spreader==crush || spreader==YOU){
//            spreader= r.nextInt(10)+1;
//        }
//        
//        if (crush==spreader || crush==YOU){
//            spreader= r.nextInt(10)+1;
//        }
        
        g.BFS(3,7);
    } 
}
