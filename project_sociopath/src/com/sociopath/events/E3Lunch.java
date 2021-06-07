package com.sociopath.events;

import java.util.ArrayList;

/*
Start time : 1100 -> 1400
Duration   : 0006 -> 0059
End time   : 1106 -> 1459
 */

public class E3Lunch {
    private static ArrayList<Student> students;
    private static Student you;
    public E3Lunch(ArrayList<Student> deStudents, Student me){
        students = (ArrayList<Student>) deStudents.clone();
        you = me;
    }


    // To get the end time of a student's lunch time.
    public static int getEndTIme(Student s) {
        int end = s.getLunchPeriod() + s.getLunchStart();
        if (s.getLunchPeriod() + Integer.parseInt(String.valueOf(s.getLunchStart()).substring(2)) >= 60) {
            end = end + 40; // to make sure minutes part won't exceed or equal to 60
        }
        return end;
    }

    // To return a description on the lunch arrangement
    public static String getLunchList(ArrayList<Student> ans) {
        String temp = "";
        String temp2 = "I can have lunch with Student ";
        for (Student an : ans) {
            temp2 = temp2 + an.getId() + ",";
            temp = temp + "Student ID:" + an.getId() + "\nLunch Period: " + String.valueOf(an.getLunchStart()).substring(0, String.valueOf(an.getLunchStart()).length() - 2) + ":" + String.valueOf(an.getLunchStart()).substring(2) + "-->" + String.valueOf(getEndTIme(an)).substring(0, String.valueOf(getEndTIme(an)).length() - 2) + ":" + String.valueOf(getEndTIme(an)).substring(2) + "\n";
        }
        return "-".repeat(38) + "\n" + "My lunchtime is " + String.valueOf(you.getLunchStart()).substring(0, String.valueOf(you.getLunchStart()).length() - 2) + ":" + String.valueOf(you.getLunchStart()).substring(2) + "-->" + String.valueOf(getEndTIme(you)).substring(0, String.valueOf(getEndTIme(you)).length() - 2) + ":" + String.valueOf(getEndTIme(you)).substring(2) + "\n" + temp2.substring(0, temp2.length() - 1) + ".\n\n" + temp + "-".repeat(38);
    }

    // To sort the end time in ascending order.
    public static void ascendingByEndTime(ArrayList<Student> stu) {
        for (int n = 0; n < stu.size() - 1; n++) {
            for (int i = 0; i < stu.size() - 1 - n; i++) {
                if (getEndTIme(stu.get(i)) > getEndTIme(stu.get(i + 1))) {
                    Student tmp = stu.get(i);
                    stu.set(i, stu.get(i + 1));
                    stu.set(i + 1, tmp);
                }
            }
        }
    }

    // This is the method to find intersection of students
    public static ArrayList<Student> findMaxSolution(){
        ascendingByEndTime(students);
        students.remove(you);
        ArrayList<Student> ans = new ArrayList<>();
        for(Student i : students){
            if (getEndTIme(you) >= i.getLunchStart()){
               ans.add(i);
           }
        }
        return ans;
    }

    // Tester
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

        E3Lunch e= new E3Lunch(s, s.get(0));
        System.out.println(getLunchList(findMaxSolution()));;
        Student.printStudents(s);
    }
}
