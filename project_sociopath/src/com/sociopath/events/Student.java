package com.sociopath.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Description: This is the Student Class
 */
public class Student {
    private static int inInc;
    private static int currentStudentId = 1;
    private final int id;
    private int dive;
    private int startTimeInMinute;
    private int lunchStart;
    private int lunchPeriod;
    private HashMap<Student, Integer> relationships;

    public HashMap<Student, Integer> getRelationships() {
        return relationships;
    }

    public Student() {
        id = ++inInc;
        // 0 < dive < 100 (%) || [1,99]
        dive = (int) (Math.random() * 99 + 1);
        // 5 < lunch period < 60 || [6,59]
        lunchPeriod = (int) (Math.random() * 54 + 6);
        // 0 <= startTimeInMinute <= 180-lunchPeriod || [0,180-lunchPeriod]
        startTimeInMinute = (int) (Math.random() * (181 - lunchPeriod));
        // 1100 <= lunchStart <=1400
        lunchStart = startTimeInMinute / 60 * 100 + startTimeInMinute % 60 + 1100;
        relationships = new HashMap<>();
    }

    /**
     * @param o the students to be compared
     * @return true if they are the same student
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    /**
     * Check if the student is friend with another student
     * @param student another student
     * @return true if this student is friend with another student
     */
    public boolean isFriendWith(Student student) {
        if (student.relationships.containsKey(this)) {
            if (student.relationships.get(this) <= 0) {
                return false;
            }
        } else {
            return false;
        }
        if (this.relationships.containsKey(student)) {
            return this.relationships.get(student) >= 0;
        } else {
            return false;
        }
    }

    /**
     * Get one student's reputation from another student
     * @param stu1 get the reputation from who
     * @param stu2 get who's reputation
     * @return the stu2's reputation in stu1
     */
    public static Integer getReputation(Student stu1, Student stu2) {
        if (stu1.getRelationships().containsKey(stu2)) {
            return stu1.getRelationships().get(stu2);
        }
        return null;
    }

    /**
     * check if two students are friends
     * @param students the list that two students are in
     * @param stu1Id 1st student's id
     * @param stu2Id 2nd student's id
     * @return true if 1st and 2nd students are friends
     */
    public static boolean isFriendWith(ArrayList<Student> students, int stu1Id, int stu2Id) {
        stu1Id--;
        stu2Id--;
        if (students.get(stu1Id).relationships.containsKey(students.get(stu2Id))) {
            if (students.get(stu1Id).relationships.get(students.get(stu2Id)) <= 0) {
                return false;
            }
        } else {
            return false;
        }
        if (students.get(stu2Id).relationships.containsKey(students.get(stu1Id))) {
            return students.get(stu2Id).relationships.get(students.get(stu1Id)) >= 0;
        } else {
            return false;
        }
    }

    public void makeFriends(Student student, int inRep, int outRep) {
        if (isFriendWith(student)) {
            return;
        }
        relationships.put(student, inRep);
        student.relationships.put(this, outRep);
    }

    public static ArrayList<Student> getStrangers(ArrayList<Student> students, Student currentStudent) {
        ArrayList<Student> strangers = new ArrayList<>();
        for (Student student : students) {
            if (!student.isFriendWith(currentStudent)) {
                if (!student.equals(currentStudent)) {
                    strangers.add(student);
                }
            }
        }
        return strangers;
    }

    public static ArrayList<Student> getStrangers(ArrayList<Student> students, int currentStudentId) {
        ArrayList<Student> strangers = new ArrayList<>();
        Student currentStudent = students.get(currentStudentId - 1);
        for (Student student : students) {
            if (!student.isFriendWith(currentStudent)) {
                if (!student.equals(currentStudent)) {
                    strangers.add(student);
                }
            }
        }
        return strangers;
    }

    public static void incRep(Student student1, Student student2, int n1, int n2) {
        if (student1.getRelationships().containsKey(student2)) {
            student1.getRelationships().put(student2, student1.getRelationships().get(student2) + n1);
        } else {
            student1.getRelationships().put(student2, n1);
        }

        if (student2.getRelationships().containsKey(student1)) {
            student2.getRelationships().put(student1, student2.getRelationships().get(student1) + n2);
        } else {
            student2.getRelationships().put(student1, n2);
        }
    }

    public static ArrayList<Student> getFriends(ArrayList<Student> students, Student currentStudent) {
        ArrayList<Student> friends = new ArrayList<>();
        for (Student student : students) {
            if (student.isFriendWith(currentStudent)) {
                friends.add(student);
            }
        }
        return friends;
    }

    public static void updateLunchtime(ArrayList<Student> students) {
        for (Student student : students) {
            student.lunchPeriod = (int) (Math.random() * 54 + 6);
            // 0 <= startTimeInMinute <= 180-lunchPeriod || [0,180-lunchPeriod]
            student.startTimeInMinute = (int) (Math.random() * (181 - student.lunchPeriod));
            // 1100 <= lunchStart <=1400
            student.lunchStart = student.startTimeInMinute / 60 * 100 + student.startTimeInMinute % 60 + 1100;
        }
    }

    public static void printLunchtime(ArrayList<Student> students) {
        StringBuilder temp = new StringBuilder();
        for (Student an : students) {
            temp.append("Student ").append(an.getId()).append(": ").append(String.valueOf(an.getLunchStart()).substring
                    (0, String.valueOf(an.getLunchStart()).length() - 2)).append(":").append(
                    String.valueOf(an.getLunchStart()).substring(2)).append("-->").append(String.valueOf(
                    getEndTIme(an)).substring(0, String.valueOf(getEndTIme(an)).length() - 2))
                    .append(":").append(String.valueOf(getEndTIme(an)).substring(2)).append("\n");
        }
        System.out.println(temp);
    }

    public static int getEndTIme(Student s) {
        int end = s.getLunchPeriod() + s.getLunchStart();
        if (s.getLunchPeriod() + Integer.parseInt(String.valueOf(s.getLunchStart()).substring(2)) >= 60) {
            end = end + 40; // to make sure minutes part won't exceed or equal to 60
        }
        return end;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret = new StringBuilder("Student " + id +
                ": dive=" + dive +
                "%, lunchStart=" + lunchStart / 100 + ":" + lunchStart % 100 +
                ", lunchPeriod=" + lunchPeriod + "min");
        ret.append(", relationships[");
        int cnt = 0;
        for (Map.Entry<Student, Integer> studentRelationshipEntry : relationships.entrySet()) {
            if (++cnt < relationships.size()) {
                ret.append(studentRelationshipEntry.getKey().getId()).append(" with rep ").append(studentRelationshipEntry.getValue()).append("; ");
            } else {
                ret.append(studentRelationshipEntry.getKey().getId()).append(" with rep ").append(studentRelationshipEntry.getValue());
            }
        }
        ret.append("] ");
        return ret.toString();
    }

    public int getId() {
        return id;
    }

    public int getDive() {
        return dive;
    }

    public int getLunchStart() {
        return lunchStart;
    }

    public int getLunchPeriod() {
        return lunchPeriod;
    }

    public static int getCurrentStudentId() {
        return currentStudentId;
    }


    public void setDive(int dive) {
        this.dive = dive;
    }

    public void setLunchStart(int lunchStart) {
        this.lunchStart = lunchStart;
    }

    public void setLunchPeriod(int lunchPeriod) {
        this.lunchPeriod = lunchPeriod;
    }

    public static void setCurrentStudentId(int currentStudentId) {
        if (currentStudentId > 0 && currentStudentId <= 10) {
            Student.currentStudentId = currentStudentId;
        }
    }

    public static void resetCount() {
        inInc = 0;
    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void printStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Test program
     */
    public static void main(String[] args) {
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new Student();
        }
        students[0].makeFriends(students[1], 5, 8);
        students[0].makeFriends(students[6], 4, 3);

        students[1].makeFriends(students[2], 5, 4);
        students[1].makeFriends(students[4], 6, 2);
        students[1].makeFriends(students[5], 9, 7);

        students[3].makeFriends(students[7], 7, 10);
        students[3].makeFriends(students[9], 7, 7);
        students[8].makeFriends(students[9], 5, 6);
        Student.printStudents(students);
        Student.incRep(students[0],students[1],1,1 );
        System.out.println(students[1].isFriendWith(students[2]));

        Student.printLunchtime(E0Init.init());
    }
}
