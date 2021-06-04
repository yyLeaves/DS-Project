/*
package com.sociopath.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class ProjectDS {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        
        //may show in GUI
        Scanner s= new Scanner(System.in);
        int n= s.nextInt();
        System.out.println("Your crush: Student" +n);
        
//       ----- find out random stanger from different cluster ----
//
//        The rumor will start in the stranger’s cluster and your crush is in another cluster. You
//        might identify someone connected between these 2 clusters??? there is only 2 cluster, where am I
//       ###### Here I take it as I'm in the diff cluster from stranger#####

        
        System.out.println("Student "+rumorSpreader()+" is spreading rumors about you");
        //call rumorSpreader x crush connection
            
        }
    
    
    //printing out the paths from rumor spreader to crush
    public static void connection(ArrayList<Student>students,Student rumorSpreader,Student crush){
        
        int size= students.size();
        boolean[] isVisited= new boolean[size];
        ArrayList<Student> path= new ArrayList<>();
        int path_index=0;
        printConnection(students, rumorSpreader, crush, isVisited, path);
        
    }
    
     public static void printConnection(ArrayList<Student>students,Student rumorSpreader,Student crush,boolean[] isVisited,ArrayList<Student> path){
         isVisited[rumorSpreader.getId()]= true;
         path.add(rumorSpreader);
         
         if (rumorSpreader== crush){
             for (int i=0; i<path.size();i++){
                 System.out.println(path.get(i));
             }
         }
         else{
             for (int i: students.get(sourceNode))
                 if(!isVisited[i])
                     printConnection(students, rumorSpreader, crush,isVisited,path);
         }
     
     path.remove(path.size() - 1);
     isVisited[sourceNode] = false;
     }
    
    
    

    //list of student in different cluster
    public static int rumorSpreader() {
        int[] cluster1= new int[]{1,2,3,5,6,7};
        int[] cluster2= new int[]{4,8,9,10};
        
        Random r= new Random();
        if (Student.getCurrentStudentId()== 4||Student.getCurrentStudentId()== 8 ||
            Student.getCurrentStudentId()== 9 ||Student.getCurrentStudentId()== 10){
        
            int index =r.nextInt(cluster1.length);
            int rumor=cluster1[index];
            return rumor;
        }
        else{
           int index =r.nextInt(cluster2.length);
           int rumor= cluster2[index]; 
           return rumor;
        }
}

    
    
    
}
            
            
            
            
            
            
            
        
        
        

    
    
   



*/
