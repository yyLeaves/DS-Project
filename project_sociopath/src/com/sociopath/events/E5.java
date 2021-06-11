package com.sociopath.events;

import java.util.*;
import java.util.LinkedList;


public class E5 {

    final private ArrayList<Student> students;

    private ArrayList<Student> newSpreaders;
    private ArrayList<Student> visited;
    private ArrayList<Student> convinced = new ArrayList<>();

    private int currentStudentId;
    private Student crushStudent;
    private Student rumorSpreader;

    private boolean stop = false;
    private boolean fail = false;

    Scanner sc = new Scanner(System.in);


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


        new E5(E0Init.init(), 1).meetCrush();


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
    public void convince(Student toConvince) {
        System.out.println("You can convince ");
        System.out.println(visited);
        System.out.println("Who do you want to convince?");
        int convince = sc.nextInt();
        for (Student student : visited) {
            if(student.getId()==convince) {

            }
        }


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
        // replace with newSpreaders
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
            



