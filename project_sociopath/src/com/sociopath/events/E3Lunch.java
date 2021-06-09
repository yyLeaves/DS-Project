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
        int myStart = you.getLunchStart();
        int myEnd = getEndTIme(you);
        for(Student i : students){
            int otherStart = i.getLunchStart();
            int otherEnd = getEndTIme(i);
            if ((myStart>=otherStart&&myStart<=otherEnd) || (myEnd>=otherStart&&myEnd<=otherEnd) || (otherStart<=otherEnd&&otherStart>=myStart&&otherEnd<=myEnd)){
               ans.add(i);
           }
        }
        return ans;
    }

    // Tester
    public static void main(String[] args) {
/*        for (int i = 0; i < 9; i++) {
            s.add(new Student());
        }

        s.get(0).setLunchStart(1303);
        s.get(0).setLunchPeriod(9);

        s.get(1).setLunchStart(1106);
        s.get(1).setLunchPeriod(25);

        s.get(2).setLunchStart(1138);
        s.get(2).setLunchPeriod(22);

        s.get(3).setLunchStart(1158);
        s.get(3).setLunchPeriod(1232-1158);

        s.get(7).setLunchStart(1247);
        s.get(7).setLunchPeriod(1254-1247);

        E3Lunch e= new E3Lunch(s, s.get(0));
        Student.printLunchtime(s);
        System.out.println(getLunchList(findMaxSolution()));;
        Student.printStudents(s);*/
        ArrayList<Student> s = E0Init.init();

        s.get(0).setLunchPeriod(0);
        s.get(0).setLunchStart(1100);
        Student.printLunchtime(s);
        E3Lunch e= new E3Lunch(s, s.get(0));
        System.out.println(getLunchList(findMaxSolution()));
        Student.printLunchtime(students);
    }
}
