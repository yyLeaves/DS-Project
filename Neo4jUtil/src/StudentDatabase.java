import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 */
public class StudentDatabase {
    private static Connection conn;

    static {
        try {
            conn = Neo4jUtil.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * initialize the database as the requirement
     */
    public static void init() {
        try {
            String query = "MATCH(n) DETACH DELETE n";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            Student[] students = new Student[10];
            for (int i = 0; i < 10; i++) {
                students[i] = new Student();
                query = String.format("CREATE(stu" +
                                ":Student{id:%d,dive:%d,lunchStart:%d,lunchPeriod:%d})",
                        /*i + 1,*/ i + 1, students[i].getDive(), students[i].getLunchStart(), students[i].getLunchPeriod());
                System.out.println(query);
                stmt.executeQuery(query);
            }
            stmt.close();

//            stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * clear all records in the database
     */
    public static void clear() {
        try {
            String query = "MATCH(n) DETACH DELETE n";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            Neo4jUtil.close(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Close the connection
     */
    public static void close() {
        Neo4jUtil.close(conn);
    }

    public static void createExample() {
        try {
            Statement stmt = conn.createStatement();
            // create a student
            String query = "CREATE (stu:Student{id:11,dive:99})";
            stmt.executeQuery(query);
            // match student 1 and student 11, create a one-way relationship
            query = "MATCH(s1:Student{id:11}),(s2:Student{id:1}) CREATE (s1)-[r:KNOW{rep:10,isFriend:0}]->(s2) ";
            stmt.executeQuery(query);
            Neo4jUtil.close(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
/*
    public static void findExample() {
        try {
            Statement stmt = conn.createStatement();
            // create a student
            String
                    query = "MATCH(s1:Student{id:11}),(s2:Student{id:1}) DELETE (s1)->(s2) ";
            stmt.executeQuery(query);
            // match student 1 and student 11
            Neo4jUtil.close(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
*/

    public static void main(String[] args) {
        init();
        createExample();
//        findExample();
//        clear();



        Neo4jUtil.close(conn);
    }
}
