package com.sociopath.events;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/21 0:54, in project PACKAGE_NAME
 */
public class Main {
    private static int currentStudent=1;
    public static void main(String[] args) {
        Events events = new Events();

        // init
        events.init();
        Student.printStudents(events.getStudents());
        System.out.println("---------------------");

        // 1 teach
        for (int i = 0; i < 10; i++) {
            events.teach();
        }
        Student.printStudents(events.getStudents());
        System.out.println("---------------------");

        // 2 teach
        events.setCurrentStudent(2);
        for (int i = 0; i < 10; i++) {
            events.teach();
        }
        Student.printStudents(events.getStudents());
        System.out.println("---------------------");

        // 3 ChitChat
    }
}
