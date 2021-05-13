import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Description: A Utility class for graph database neo4j
 *
 * @author Yeyang Liu, S2000549
 */
public class Neo4jUtil {

    private static String url;
    private static String name;
    private static String password;

    /*
     * load properties of the neo4j database
     * The properties of the neo4j should be written in the "neo4j.properties" under /src folder
     */
    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader("neo4j.properties"));

            url = prop.getProperty("url");
            name = prop.getProperty("name");
            password = prop.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * get connection to neo4j database
     * @return a connection to the neo4j database
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,password);
    }

    public static void close(Connection conn) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     *
     * @param conn the connection to the database to be closed
     * @param stmt the statement to be closed
     */
    public static void close(Connection conn, Statement stmt) {
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt,ResultSet rs) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = Neo4jUtil.getConnection();
            String query = "Create (stu:stu1)";
            Statement statement = conn.createStatement();

            statement.executeQuery(query);
            Neo4jUtil.close(conn,statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*
//        try {
////            Connection con = DriverManager.getConnection("jdbc:neo4j:http://localhost", "neo4j","root");
            String query = "Create (stu:stu1)";
            Statement statement = con.createStatement();
            statement.executeQuery(query);
//
            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("Friend: "+rs.getString("f.name")+" is "+rs.getInt("f.age"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
//        }*/
    }
}
