package com.sociopath.neo4jdb;

import com.sociopath.events.Student;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/11 22:15, in project libs
 */
public class StudentGraph {
    private static ArrayList<Student> students = new ArrayList<>();

    public void init() {
        for (int i = 0; i < 10; i++) {
            students.add(new Student());
        }
        students.get(0).makeFriends(students.get(1),5,8);
        students.get(0).makeFriends(students.get(6),4,3);
        students.get(1).makeFriends(students.get(2),5,4);
        students.get(1).makeFriends(students.get(5),9,7);
        students.get(3).makeFriends(students.get(7),7,10);
        students.get(3).makeFriends(students.get(9),5,6);
        students.get(8).makeFriends(students.get(9),7,7);
    }

    public static void main(String[] args) {
        new StudentGraph().init();
        Student.printStudents(students);
        System.out.println(Student.isFriendWith(students,1,6));
    }

}

