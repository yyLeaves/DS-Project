package com.sociopath.events;

import java.util.ArrayList;

/**
 * Description:
 *
 */
public class Events {
    private int currentStudent = 1;

    public void setCurrentStudent(int currentStudent) {
        this.currentStudent = currentStudent;
    }
    private static ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void init() {
       students = E0Init.init();
    }

    public void teach() {
       new E1Teaching(students).teachAStranger(currentStudent);
    }

    public static void main(String[] args) {
        new Events().init();
        Student.printStudents(students);
    }
}
