package com.sociopath.events;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/21 0:14, in project PACKAGE_NAME
 */
public class E1Teaching {
    private static ArrayList<Student> students;
    public E1Teaching(ArrayList<Student> theStudents) {
        students = theStudents;
    }

    public Student randomStrangerToHelp(Student currentStudent) {
        ArrayList<Student> strangers = Student.getStrangers(students,currentStudent);
        if(strangers.isEmpty()) return null;
        int randIndex = (int)(Math.random()*(strangers.size()));
        return strangers.get(randIndex);
    }

    public Student randomStrangerToHelp(int currentStudent) {
        Student current = students.get(currentStudent-1);
        return randomStrangerToHelp(current);
    }

    public void teachAStranger(int currentStudent) {
        Student current = students.get(currentStudent-1);
        Student studentToHelp = randomStrangerToHelp(currentStudent);
        if(studentToHelp==null) {
            System.out.println("You've made friends with everyone!");
            return;
        }
        System.out.println(studentToHelp.getId()+" Needs your help!");
        boolean success = Math.random()*100 < students.get(currentStudent).getDive();
        int diveOfStudentToHelp = studentToHelp.getDive();
        if(success) {
            System.out.printf("Congratulations! You helped %d solved the problem!, Your rep to him increases by 10, his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/100);
            current.makeFriends(studentToHelp,diveOfStudentToHelp*10/100,10);
        } else {
            System.out.printf("Oops, You didn't solve the problem for %d... Your rep to him increases by 2, his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/200);
            current.makeFriends(studentToHelp,diveOfStudentToHelp*10/200,2);
        }

        E2ChitChat.chitChat(students,studentToHelp,current);
    }

    public static void main(String[] args) {


    }
}
