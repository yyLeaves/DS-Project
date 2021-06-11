package com.sociopath.events;

import java.util.ArrayList;

/**
 * Description: Teaching event
 *
 */
public class E1Teaching {
    private ArrayList<Student> students;
    public E1Teaching(ArrayList<Student> theStudents) {
        students = theStudents;
    }

    public Student randomStrangerToHelp(Student currentStudent) {
        ArrayList<Student> strangers = Student.getStrangers(students,currentStudent);
        if(strangers.isEmpty()) return null;
        int randIndex = (int)(Math.random()*(strangers.size()));
        return strangers.get(randIndex);
    }

    public Student randomStrangerToHelp(int currentStudentId) {
        Student current = students.get(currentStudentId-1);
        return randomStrangerToHelp(current);
    }

    public String teachAStranger(int currentStudent) {
        Student current = students.get(currentStudent-1);
        Student studentToHelp = randomStrangerToHelp(currentStudent);
        if(studentToHelp==null) {
            return "You've made friends with everyone!";
        }
        boolean success = Math.random()*100 < 100-students.get(currentStudent-1).getDive();
        int diveOfStudentToHelp = studentToHelp.getDive();
        String returnStr = "Student " + studentToHelp.getId()+" needs your help!\n";

        if(success) {
            current.makeFriends(studentToHelp,(100-diveOfStudentToHelp)*10/100,10);
            returnStr+=String.format("Congratulations! You helped %d solved the problem!\nYour rep to him increases by 10\n" +
                    "His reputation to you increases by %d\n",studentToHelp.getId(),(100-diveOfStudentToHelp)*10/100);
            returnStr+=E2ChitChat.chitChat(students,studentToHelp,current);

            return returnStr;
        } else {
            //you will still be friends with him,
            //your rep points relative to that person will be 2 instead
            current.makeFriends(studentToHelp,(100-diveOfStudentToHelp)*10/200,2);
            returnStr+=String.format("Oops, You didn't solve the problem for %d... \nYour rep to him increases by 2" +
                    "\nhis reputation to you increases by %d\n",studentToHelp.getId(),(100-diveOfStudentToHelp)*10/200);
            returnStr+=E2ChitChat.chitChat(students,studentToHelp,current);
            return returnStr;
        }

    }

    public static void main(String[] args) {
        ArrayList<Student> students = E0Init.init();
        Student.printStudents(students);
        E1Teaching teach = new E1Teaching(students);
        String s = teach.teachAStranger(1);
        System.out.println(s);

    }

}
