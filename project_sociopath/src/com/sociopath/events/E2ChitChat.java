package com.sociopath.events;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/21 1:31, in project PACKAGE_NAME
 */
public class E2ChitChat {
    public static void chitChat(ArrayList<Student> students, Student studentChitChat, Student you) {
        int repAboutYou = studentChitChat.getRelationships().get(you);
        ArrayList<Student> friends = Student.getFriends(students, studentChitChat);
        for (Student friend : friends) {
            if (friend == you) {

            } else {
                int oldRep = 0;
                if (friend.getRelationships().containsKey(you)) {
                    oldRep = friend.getRelationships().get(you);
                }
                if (repAboutYou >= 0) {
                    friend.getRelationships().put(you, oldRep + repAboutYou / 2);
                    System.out.printf("Student %d has a chitchat with his friend %d, you reputation to them increases by %d\n", studentChitChat.getId(), friend.getId(), repAboutYou / 2);
                } else {
                    friend.getRelationships().put(you, repAboutYou);
                    System.out.printf("Student %d has a chitchat with his friends, you reputation to them decreases by %d\n", studentChitChat.getId(), -repAboutYou);
                }
            }
        }
    }
}
