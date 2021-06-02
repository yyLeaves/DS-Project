package com.sociopath.events;

import java.util.ArrayList;

/*
Start time : 1100 -> 1400
Duration   : 0006 -> 0059
End time   : 1106 -> 1459
 */

public class E3Lunch {
    private static ArrayList<Student> students;

    public E3Lunch(ArrayList<Student> deStudents){
        students = deStudents;
    }

    public ArrayList<Student> findMaxSolution(){
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

    // To get the end time of a student's lunch time.
    public int getEndTIme(Student s){
        int end = s.getLunchPeriod()+s.getLunchStart();
        if(s.getLunchPeriod() + Integer.parseInt(String.valueOf(s.getLunchStart()).substring(2)) >= 60){
            end = end + 40; // to make sure minutes part won't exceed or equal to 60
        }
        return end;
    }

    // To sort the end time in ascending order.
    public void ascendingByEndTime(ArrayList<Student> stu){
        for (int n=0; n < stu.size()-1; n++) {
            for (int i = 0; i < stu.size() - 1 - n; i++) {
                if (getEndTIme(stu.get(i)) > getEndTIme(stu.get(i+1))) {
                    Student tmp = stu.get(i);
                    stu.set(i, stu.get(i+1));
                    stu.set(i+1, tmp);
                }
            }
        }

// This is to output the ArrayList after sorting for testing purpose.
//
/*
        for (Student s : students){
            System.out.print(s.getLunchStart() + "/" + getEndTIme(s) + "\n");
        }
 */
    }

    // A normal tester
/*
    public static void main(String[] args) {
        Student a = new Student();
        Student b = new Student();
        Student c = new Student();
        Student d = new Student();
        ArrayList<Student> s = new ArrayList<>();
        s.add(a);
        s.add(b);
        s.add(c);
        s.add(d);
        E3Lunch e= new E3Lunch(s);
        ArrayList<Student> ans = e.findMaxSolution();
        for (Student an : ans) System.out.print(an.getLunchStart() + " ");
    }
*/
}
