/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/11 22:15, in project libs
 */
public class StudentGraph {
    private Student[] studentsGraph = new Student[10];

    public StudentGraph() {
    }

    private class StudentNode {
        Student student;
        Relationship inRelationship;
        Relationship outRelationship;
        StudentNode nextStudent;
    }
}

