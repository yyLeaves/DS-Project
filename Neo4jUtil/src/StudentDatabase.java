import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            String query = "MATCH(n) DELETE n";
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
            String query = "MATCH(n) DELETE n";
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


    public static void main(String[] args) {
        init();
//        clear();


        Neo4jUtil.close(conn);
    }
}
