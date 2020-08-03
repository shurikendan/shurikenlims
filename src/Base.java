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
