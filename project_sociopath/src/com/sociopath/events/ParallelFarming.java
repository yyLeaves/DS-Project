package com.sociopath.events;

import java.util.ArrayList;

/*
Start time : 1100 -> 1400
Duration   : 0006 -> 0059
End time   : 1106 -> 1459
 */

public class ParallelFarming {
    private static ArrayList<Student> students;

    public ParallelFarming(ArrayList<Student> deStudents){
        students = (ArrayList<Student>) deStudents.clone();

    }

    public static ArrayList<Student> findMaxSolution(){
        ArrayList<Student> ans = new ArrayList<>();
        int spareTimeChecker = 0; // to avoid get nothing later
        ascendingByEndTime(students);
        ans.add(students.get(0));
        spareTimeChecker = getEndTIme(students.get(0)); // to get the first student's end time. The end time must be the earliest since we have sorted them
        for(int i = 1; i < students.size(); i++){
            if (students.get(i).getLunchStart() > spareTimeChecker){
                ans.add(students.get(i));
                spareTimeChecker = getEndTIme(students.get(i)); // set a new checker for the for loop
            }
        }
        return ans;
    }

    // This is the method need to be called
    public static ArrayList<Student> receiver(Student you){
        students.remove(you);
        return findMaxSolution();
    }

    // To get the end time of a student's lunch time.
    public static int getEndTIme(Student s){
        int end = s.getLunchPeriod()+s.getLunchStart();
        if(s.getLunchPeriod() + Integer.parseInt(String.valueOf(s.getLunchStart()).substring(2)) >= 60){
            end = end + 40; // to make sure minutes part won't exceed or equal to 60
        }
        return end;
    }

    // To return a description on the lunch arrangement
    public static String getLunchList(ArrayList<Student> ans, Student you){
        String temp = "";
        String temp2= "CRAZY BIG EATER(PARALLEL FARMING)\nI can have lunch with Student ";
        for (Student an : ans) {
            Student.incRep(you, an, 1, 2);
            temp2 = temp2 + an.getId() + ",";
            temp = temp + "Student ID:" + an.getId() + "\nLunch Period: " + String.valueOf(an.getLunchStart()).substring(0, String.valueOf(an.getLunchStart()).length()-2) + ":" + String.valueOf(an.getLunchStart()).substring(2) + "-->" + String.valueOf(getEndTIme(an)).substring(0, String.valueOf(getEndTIme(an)).length()-2) + ":" + String.valueOf(getEndTIme(an)).substring(2) + "\n";
        }
        return "-".repeat(38) + "\n" + temp2.substring(0,temp2.length()-1) + ".\n\n" + temp + "-".repeat(38);
    }

    // To sort the end time in ascending order.
    public static void ascendingByEndTime(ArrayList<Student> stu){
        for (int n=0; n < stu.size()-1; n++) {
            for (int i = 0; i < stu.size() - 1 - n; i++) {
                if (getEndTIme(stu.get(i)) > getEndTIme(stu.get(i+1))) {
                    Student tmp = stu.get(i);
                    stu.set(i, stu.get(i+1));
                    stu.set(i+1, tmp);
                }
            }
        }
    }

    // A normal tester
    public static void main(String[] args) {
        ArrayList<Student> s = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            s.add(new Student());
        }
        s.get(0).setLunchStart(1100);
        s.get(0).setLunchPeriod(20);
        s.get(1).setLunchStart(1110);
        s.get(1).setLunchPeriod(20);
        s.get(2).setLunchStart(1130);
        s.get(2).setLunchPeriod(20);
        s.get(3).setLunchStart(1330);
        s.get(3).setLunchPeriod(20);
        s.get(7).setLunchStart(1150);
        s.get(7).setLunchPeriod(20);
        ParallelFarming e= new ParallelFarming(s);
        ArrayList<Student> ans = e.receiver(s.get(0));
        System.out.println(e.getLunchList(ans, s.get(0)));
        Student.printStudents(s);
    }
}
