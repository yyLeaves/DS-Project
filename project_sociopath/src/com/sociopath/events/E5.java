package com.sociopath.events;

import java.util.*;
import java.util.LinkedList;


public class E5 {

    private ArrayList<Student> students;

    private ArrayList<Student> newSpreaders;
    private ArrayList<Student> visited;

    private int currentStudentId;
    private Student crushStudent;
    private Student rumorSpreader;

    private boolean stop = false;
    private boolean fail = false;


    /**
     * constructor
     *
     * @param students         the ArrayList of students in the graph
     * @param currentStudentId "you"'s id
     */
    public E5(ArrayList<Student> students, int currentStudentId) {
        this.students = students;
        this.currentStudentId = currentStudentId;
    }

    public void meetCrush() {

        System.out.print("Strangers: ");
        for (Student stranger : Student.getStrangers(students, currentStudentId)) {
            System.out.print(stranger.getId() + " ");
        }

        // randomly generate a crush of current student
        this.crushStudent = getCrush();

        if (crushStudent == null) {
            System.out.println("Seems like you've made friends with everyone. There isn't any stranger can be your crush");
        } else {

            // randomly generate a rumor spreader
            this.rumorSpreader = getSpreader();

            // if no one can be the rumor generator
            if (rumorSpreader == null) {
                System.out.println("Seems like no one will spread rumor about you! Good Luck with your crush!");
            } else {
                System.out.println("\nStudent "+currentStudentId+" has a crush on " + getCrushStudent().getId());
                System.out.println("Student " + rumorSpreader.getId() + " is the initial rumor spreader");

                // spread, convince, stop
                stopRumor();


            }
        }


    }

    public static void main(String[] args) {

        //may show in GUI
/*        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Your crush: Student" + n);*/

//       ----- find out random stanger from different cluster ----
//
//        The rumor will start in the stranger’s cluster and your crush is in another cluster. You
//        might identify someone connected between these 2 clusters??? there is only 2 cluster, where am I
//       ###### Here I take it as I'm in the diff cluster from stranger#####
        new E5(E0Init.init(), 1).meetCrush();
        //call rumorSpreader x crush connection

    }



    /**
     * randomly generate and return "you"'s crush
     *
     * @return a Student if can randomize a crush Student from the strangers list, return null if cannot do so
     */
    public Student getCrush() {
        ArrayList<Student> strangers = Student.getStrangers(students, currentStudentId);

        // If you have strangers
        if (strangers.size() > 0) {
            // randomly generate one stranger as your crush
            int randomIndex = (int) (Math.random() * strangers.size());
            return strangers.get(randomIndex);
        } else {
            // If there isn't a stranger can be your crush, return null
            return null;
        }

    }

    /**
     * randomly generate and return "you"'s first rumor spreader
     *
     * @return a Student if can randomize a rumor spreader Student from the strangers list, return null if cannot do so
     */
    public Student getSpreader() {
        // rumor spreader - a stranger of you
        ArrayList<Student> strangers = Student.getStrangers(students, currentStudentId);

        // rumor spreader - not your crush
        strangers.remove(crushStudent);

        // the stranger will be removed out of possible rumor spreader list if he is friend with crush
        ArrayList<Student> clusterWithCrush = new ArrayList<>();
        for (Student stranger : strangers) {
            if (stranger.isFriendWith(crushStudent)) {
                clusterWithCrush.add(stranger);
            }
        }
        for (Student withCrush : clusterWithCrush) {
            strangers.remove(withCrush);
        }

//        Student.printStudents(strangers);

        int index = (int) (Math.random() * strangers.size());

        Student rumor = strangers.get(index);
        return rumor;
    }

    public void stopRumor() {
        visited = new ArrayList<>();

        // new spreaders will spread rumor for another round
        newSpreaders = new ArrayList<>();

        // add first rumor spreader
        newSpreaders.add(rumorSpreader);

        while (!stop) {
            spreadRumor();

        }

    }

    // get the distance to crush of each student among the newSpreaders and convince the one with the shortest path distance
    public void convinceOne() {
        // new Spreader
        if (newSpreaders.size() == 1) {
//            System.out.println("Convinced Student " + newSpreaders.get(0).getId());
//            stop = true;
        }

        for (Student newSpreader : newSpreaders) {

        }

    }

    // bfs for the shortest path distance
    public int distanceToCrush(Student student) {
        List<Student> visitedStudents = new ArrayList<>();
        Queue<Student> toVisit = new LinkedList<>(Student.getFriends(students, student));
        while (!toVisit.isEmpty()) {

        }
        return -1;
    }

    public void spreadRumor() {
        ArrayList<Student> spreadTo = new ArrayList<>();
        // for each new spreader, spread rumor to his friends who haven't heard the rumor
        for (Student newSpreader : newSpreaders) {
            // for each friend of new spreader
            for (Student friend : Student.getFriends(students, newSpreader)) {
                // if the friend haven't heard the rumor
                if (!visited.contains(friend)) {
                    // spread rumor and add him to newSpreader list
                    if (!spreadTo.contains(friend)) {
                        spreadTo.add(friend);
                    }
                }
            }

            // add the student to visited list
            visited.add(newSpreader);
        }

        if (!spreadTo.isEmpty()) {
            System.out.print("Student ");
            for (Student spreader : newSpreaders) {
                System.out.print(spreader.getId() + " ");
            }
            System.out.print("spread rumors to Student ");
            for (Student student : spreadTo) {
                System.out.print(student.getId() + " ");
            }
            System.out.println();
        }

        // remove those spreaders that have spread the rumor from newSpreader list
        // replace newSpreaders
        newSpreaders = spreadTo;

        if(newSpreaders.contains(crushStudent)) {
            System.out.println("Your crush knows your rumor");
            stop = true;
            fail=true;
        }

        else if (newSpreaders.isEmpty()) {
            System.out.println("There's no one to spread the rumor");
            stop = true;
        }
    }

/*
    //printing out the paths from rumor spreader to crush
    public static void connection(ArrayList<Student> students, Student rumorSpreader, Student crush) {

        int size = students.size();
        boolean[] isVisited = new boolean[size];
        ArrayList<Student> path = new ArrayList<>();
        int path_index = 0;
        printConnection(students, rumorSpreader, crush, isVisited, path);

    }

    public static void printConnection(ArrayList<Student> students, Student rumorSpreader, Student crush, boolean[] isVisited, ArrayList<Student> path) {
        if (isVisited[rumorSpreader.getId()] == true) {
            path.add(rumorSpreader);
        }

        if (rumorSpreader == crush) {
            for (int i = 0; i < path.size(); i++) {
                System.out.println(path.get(i));
            }
        } */
 /*else {
            for (int i : students.get(sourceNode))
                if (!isVisited[i])
                    printConnection(students, rumorSpreader, crush, isVisited, path);
        }

        path.remove(path.size() - 1);
        isVisited[sourceNode] = false;*//*

    }
*/


    //list of student in different cluster
/*    public static int rumorSpreader() {
        int[] cluster1 = new int[]{1, 2, 3, 5, 6, 7};
        int[] cluster2 = new int[]{4, 8, 9, 10};

        Random r = new Random();
        if (Student.getCurrentStudentId() == 4 || Student.getCurrentStudentId() == 8 ||
                Student.getCurrentStudentId() == 9 || Student.getCurrentStudentId() == 10) {

            int index = r.nextInt(cluster1.length);
            int rumor = cluster1[index];
            return rumor;
        } else {
            int index = r.nextInt(cluster2.length);
            int rumor = cluster2[index];
            return rumor;
        }
    }*/

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getCrushStudent() {
        return crushStudent;
    }

    public Student getRumorSpreader() {
        return rumorSpreader;
    }
}
            



