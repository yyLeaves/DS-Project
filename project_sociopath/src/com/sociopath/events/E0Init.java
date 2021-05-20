package com.sociopath.events;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/21 0:10, in project PACKAGE_NAME
 */
public class E0Init {
    private static ArrayList<Student> students = new ArrayList<>();

    public static ArrayList<Student> init() {
        for (int i = 0; i < 10; i++) {
            students.add(new Student());
        }
        students.get(0).makeFriends(students.get(1),5,8);
        students.get(0).makeFriends(students.get(6),4,3);
        students.get(1).makeFriends(students.get(2),5,4);
        students.get(1).makeFriends(students.get(4),6,2);
        students.get(1).makeFriends(students.get(5),9,7);
        students.get(3).makeFriends(students.get(7),7,10);
        students.get(3).makeFriends(students.get(9),5,6);
        students.get(8).makeFriends(students.get(9),7,7);
        return students;
    }

    public static void main(String[] args) {
        students = E0Init.init();
        Student.printStudents(students);
        System.out.println(Student.isFriendWith(students,1,6));
    }
}
