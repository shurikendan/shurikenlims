
/*
//STEP 1. Import required packages
import java.sql.*;

public class Base {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample

 */

import java.sql.*;

public class Base {
    public static void connect(String fileName) {
        String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/" + fileName;
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("[DEBUG] [DB] Driver name: " + meta.getDriverName());
                System.out.println("[DEBUG] [DB] Database created.");
            }
        }
        catch (SQLException exception) {
            System.out.println("[EXCEPTION] [SQL] " + exception.getMessage());
        }
    }
    public static void newTable() {
        String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/lims.db";
        String sql = "CREATE TABLE IF NOT EXISTS test (\n"
                + "id integer PRIMARY KEY,\n"
                + "name text NOT NULL,\n"
                + "capacity real\n"
                + ");";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException exception) {
            System.out.println("[EXCEPTION] [SQL] " + exception.getMessage());
        }
    }
    public static void main(String[] args) {
        connect("lims.db");
        newTable();
    }
}
