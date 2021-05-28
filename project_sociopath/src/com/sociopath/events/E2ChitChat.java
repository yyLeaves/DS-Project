package com.sociopath.events;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description:
 *
 */
public class E2ChitChat {
    private static ArrayList<String> chitChats;

    public static ArrayList<String> getChitChats() {
        return chitChats;
    }

    public static String chitChat(ArrayList<Student> students, Student studentChitChat, Student you) {
        int repAboutYou = studentChitChat.getRelationships().get(you);
        ArrayList<Student> friends = Student.getFriends(students, studentChitChat);
        String returnStr = "";
        for (Student friend : friends) {
            if (friend == you) {

            } else {
                int oldRep = 0;
                if (friend.getRelationships().containsKey(you)) {
                    oldRep = friend.getRelationships().get(you);
                }
                if (repAboutYou >= 0) {
                    friend.getRelationships().put(you, oldRep + repAboutYou / 2);
                    returnStr+=(String.format("Student %d has a chitchat with his friend %d, you reputation to he/she increases by %d\n", studentChitChat.getId(), friend.getId(), repAboutYou / 2));
                } else {
                    friend.getRelationships().put(you, repAboutYou);
                    returnStr+=(String.format("Student %d has a chitchat with his friends, you reputation to he/she decreases by %d\n", studentChitChat.getId(), -repAboutYou));
                }
            }
        }
        return returnStr;

    }
}
