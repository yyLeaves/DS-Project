package com.sociopath.events;

import java.util.ArrayList;

/**
 * Description:
 *
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

    public String teachAStranger(int currentStudent) {
        Student current = students.get(currentStudent-1);
        Student studentToHelp = randomStrangerToHelp(currentStudent);
        if(studentToHelp==null) {
//            System.out.println("You've made friends with everyone!");
            return "You've made friends with everyone!";
        }
//        System.out.println(studentToHelp.getId()+" Needs your help!");
        boolean success = Math.random()*100 < students.get(currentStudent-1).getDive();
//        System.out.println(students.get(currentStudent).getDive());
        int diveOfStudentToHelp = studentToHelp.getDive();
        String returnStr = "Student " + studentToHelp.getId()+" needs your help!\n";
        if(success) {
//            System.out.printf("Congratulations! You helped %d solved the problem!, Your rep to him increases by 10, " +
//                    "his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/100);
            current.makeFriends(studentToHelp,diveOfStudentToHelp*10/100,10);
            returnStr+=String.format("Congratulations! You helped %d solved the problem!, Your rep to him increases by 10, " +
                    "his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/100);
            returnStr+=E2ChitChat.chitChat(students,studentToHelp,current);

            return returnStr;
        } else {
            //you will still be friends with him,
            //your rep points relative to that person will be 2 instead
//            System.out.printf("Oops, You didn't solve the problem for %d... Your rep to him increases by 2, " +
//                    "his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/200);
            current.makeFriends(studentToHelp,diveOfStudentToHelp*10/200,2);
            returnStr+=String.format("Oops, You didn't solve the problem for %d... Your rep to him increases by 2, " +
                    "his reputation to you increases by %d\n",studentToHelp.getId(),diveOfStudentToHelp*10/200);
            returnStr+=E2ChitChat.chitChat(students,studentToHelp,current);
            return returnStr;
        }

    }

    public static void main(String[] args) {
        ArrayList<Student> students = E0Init.init();
        E1Teaching teach = new E1Teaching(students);
        String s = teach.teachAStranger(1);
        System.out.println(s);

    }

}
