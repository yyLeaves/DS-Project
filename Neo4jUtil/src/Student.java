import java.util.*;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/11 14:05, in project PACKAGE_NAME
 */
public class Student{
    private static int inInc;
    private int id;
    private int dive;
    private int startTimeInMinute;
    private int lunchStart;
    private int lunchPeriod;
    private HashMap<Student,Relationship> friends;

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
        friends = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    public boolean isFriendWith(Student student) {
        for (int i = 0; i < friends.size(); i++) {
            if(friends.containsKey(student)) {
                return true;
            }
        }
        return false;
    }

    public void makeFriends(Student student, int inRep, int outRep) {
        if(isFriendWith(student)) {
            return;
        }
        friends.put(student,new Relationship(inRep));
        student.friends.put(this,new Relationship(outRep));
    }

    private class Friend {
        Student friend;
        int rep;

        public int getRep() {
            return rep;
        }

        public void setRep(int rep) {
            this.rep = rep;
        }

    }

    @Override
    public String toString() {
        String ret = "";
        ret =  "Student " + id +
                ":dive=" + dive +
                "%, lunchStart=" + lunchStart/100 + ":" + lunchStart%100 +
                ", lunchPeriod=" + lunchPeriod;
        ret+=",friends[";
        for (Map.Entry<Student, Relationship> studentRelationshipEntry : friends.entrySet()) {
            ret=ret+ studentRelationshipEntry.getKey().getId()+":"+studentRelationshipEntry.getValue()+";";
        }
        ret+="] ";
        return ret;
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



    public static void main(String[] args) {
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new Student();
        }
        students[1].makeFriends(students[2],2,2);
        students[1].makeFriends(students[3],2,3);
        students[1].makeFriends(students[4],2,4);
        System.out.println(Arrays.toString(students));

    }
}


class Relationship{
    int reputation;

    public Relationship(int reputation) {
        this.reputation = reputation;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void addReputation(int increment) {
        if(reputation+increment>=10) {
            reputation=10;
        }else {
            reputation+=increment;
        }
    }

    @Override
    public String toString() {
        return "rep="+reputation;
    }
}
