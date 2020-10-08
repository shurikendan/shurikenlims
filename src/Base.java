import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Base {

    private static String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/lims.db";

    private static void connect() {
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
    private static void createTable() {
        String[] sql = {"CREATE TABLE IF NOT EXISTS privLevels (\n"
                + "user STRING,\n"
                + "level INTEGER\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS hashes (\n"
                + "user STRING,\n"
                + "hash STRING\n"
                + ");\n"};
        executeStatement(sql);
    }

    public static void fetchTasks(String user) {

    }

    /**
     * Deserialises a <String, String> Map to the database, specifying the seperated key and value as well as the
     * relevant columns.
     * @param key String data to be written into keyColumn column
     * @param value String data to be written into valueColumn column
     * @param table String name of table to be written to
     * @param keyColumn String name of column for key to be inserted into
     * @param valueColumn String name of column for value to be insterted into
     */
    public static void writeStringMapToDatabase(String key, String value, String table, String keyColumn,
                                                String valueColumn) {
        //String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/lims.db";
        String[] sql =
                {"INSERT INTO " + table + " (" + keyColumn + ", " + valueColumn + ")\n"
                        + "VALUES (" + "'" + key + "'" + ", " + "'" + value + "'"
                        + ");"};
        executeStatement(sql);
        System.out.println("[DEBUG] [MAP DESERIALISATION] Success in writing to database");
    }

    public static HashMap<String, String> getStringMapFromDatabase(String table, String keyColumn, String valueColumn) {
        String sql = "SELECT " + keyColumn + ", " + valueColumn + " FROM " + table + ";";
        Map<String, String> returnMap  = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Map<String, String> resultsMap = new HashMap<>();         //TODO good one
            while(rs.next()) {
                resultsMap.put(rs.getString(keyColumn), rs.getString(valueColumn));
                returnMap = resultsMap;
            }
        }
        catch (SQLException e) {
            e.getStackTrace();
        }

        return (HashMap<String, String>) returnMap;
    }


    public static void main(String[] args) {
        connect();
        createTable();
    }
    //TODO could move this

    private static void executeStatement(String[] sql) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            for (String i : sql) {
                statement.execute(i);
            }
        } catch (SQLException exception) {
            System.out.println("[EXCEPTION] " + exception.getMessage());
        }
    }



    public static ResultSet executeQuery(String sql) throws SQLException {
        ResultSet returnSet = null;
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(sql);
            returnSet = results;
        }
        catch (SQLException exception) {
            System.out.println("[EXCEPTION] " + exception.getMessage());
        }
        return returnSet;
    }

}

