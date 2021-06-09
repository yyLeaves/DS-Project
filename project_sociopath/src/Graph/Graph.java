package Graph;

// Java program to print all paths of source to
// destination in given graph

import java.io.*;
import java.util.LinkedList;
import java.util.*;


//undirected graph represented using adjacency list.  
class Graph {
    int convince = 0, KnowSize = 1;
    private int Vertices;   // No. of vertices 
    private LinkedList<Integer> adj_list[]; //Adjacency Lists 
    LinkedList<Integer> queue = new LinkedList<Integer>();

    boolean visited[] = new boolean[Vertices];


    // graph Constructor:number of vertices in graph are passed 
    Graph(int v) {
        Vertices = v;
        adj_list = new LinkedList[v];
        for (int i = 0; i < v; ++i)         //create adjacency lists
            adj_list[i] = new LinkedList();
    }

    // add an edge to the graph 
    void addEdge(int v, int w) {
        adj_list[v].add(w);
        adj_list[w].add(v);
    }


    // BFS traversal from the root_node 
    void BFS(int root_node) {
        //int KnowSize=1; //know size=1 include the spreader
        int day = 1;
        // initially all vertices are not visited 
/*        boolean*/ visited = new boolean[Vertices];
        System.out.println("Stranger " + root_node + " starts to spread rumors about you\n");
        // BFS queue 
        //LinkedList<Integer> queue = new LinkedList<Integer>(); 

        // current node = visited, insert into queue 
        visited[root_node] = true;
        queue.add(root_node);


        while (queue.size() != 0) {
            // deque an entry from queue and process it  
            root_node = queue.poll();
            System.out.println("****Day " + day + "****");  //+spread to:


            // get all adjacent nodes of current node and process
            Iterator<Integer> i = adj_list[root_node].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);

                    KnowSize++;
                    //System.out.print("spreaded to student: ");
                    System.out.print(n + " ");
                    System.out.println(queue);

                }
            }
            if (queue.contains(7)) {
                System.out.println("\nOh no, your crush(7) knows!\n");
                end();
            }

            day++;
            convinceOne();
        }
        end();
    }


    public void convinceOne() {
        System.out.println("\nWho do you want to convience? Press 0 if none");
        System.out.print("Convince: ");
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        if (input == 0) {
            System.out.println("You did not convience anyone, rumors continue to spread\n");
        } else if (!queue.contains(input)) {
            convinceOne();
        } else {

            convince++;
            // TODO
            if (!queue.contains(input)) {
                queue.add(input);
            }
            while (queue.size() != 0) {
                // deque an entry from queue and process it  
                input = queue.poll();


                // get all adjacent nodes of current node and process
                Iterator<Integer> i = adj_list[input].listIterator();
                while (i.hasNext()) {
//                    System.out.println("i=");
                    int n = i.next();
                    System.out.println("n=" + n);
                    if (!visited[n]) {
                        visited[n] = true;
                        System.out.println(queue);
                        queue.poll();   //*****same level all deleted
                    }
                }
            }
            System.out.println(queue);
            System.out.println("\nHe stops spreading and no one else will know");
            System.out.println("Goodluck with your crush");
        }

    }

    public void end() {
        System.out.println(":: " + KnowSize + " person knows about your rumors");
        System.out.println(":: " + convince + " person has been convinced");
        System.exit(0);
    }

    public static void main(String args[]) {
        //vertices
        Graph g = new Graph(11);
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
        g.BFS(4);
    }
}



/*
generate random spreader    != crush + me
generate crush              != me
(!) Same level all stops rumors together
*/
 
    
