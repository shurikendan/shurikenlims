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
    public static void createTable() {
        String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/lims.db";
        String[] sql = {"CREATE TABLE IF NOT EXISTS test (\n"
                + "id INTEGER PRIMARY KEY,\n"
                + "name TEXT,\n"
                + "capacity REAL\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS alias (\n"
                + "id INTEGER PRIMARY KEY,\n"
                + "fore STRING,\n"
                + "sur STRING,\n"
                + "code STRING\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS task (\n"
                + "user STRING,\n"
                + "created DATE,\n"
                + "due DATE,\n"
                + "message TEXT\n"
                + ");\n"};
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            for(String i : sql) {
                statement.execute(i);
            }
        }
        catch (SQLException exception) {
            System.out.println("[EXCEPTION] [SQL] " + exception.getMessage());
        }
    }
    public static void fetchTasks(String user) {

    }
    public static void main(String[] args) {
        connect("lims.db");
        createTable();
    }
}
