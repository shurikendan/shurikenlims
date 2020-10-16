import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Base {

    private static String url = "jdbc:sqlite:C:/Users/Daniel/IdeaProjects/shurikenlims/sqlite/db/lims.db";

    public static void main(String[] args) {
        connect();
        createTable();
    }

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

    /**
     * Creates database tables
     */
    private static void createTable() {
        String[] sql = {"CREATE TABLE IF NOT EXISTS privLevels (\n"
                + "user STRING,\n"
                + "level INTEGER\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS hashes (\n"
                + "user STRING,\n"
                + "hash STRING\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS tasks (\n"
                + "user STRING, \n"
                + "message STRING, \n"
                + "due DATE\n"
                + ");\n",

                "CREATE TABLE IF NOT EXISTS practicals (\n"
                + "date STRING, \n"
                + "time STRING, \n"
                + "class STRING, \n"
                + "code STRING, \n"
                + "message STRING\n"
                + ");"};
        executeStatement(sql);
    }


    //Doesn't work

    public static HashMap<String, Date> fetchTasks() {
        String user = UserData.getInstance().getUser();
        String sql = "SELECT message, due FROM tasks WHERE user= '" + user + "';";
        Map<String, Date> tasksMap = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Map<String, Date> resultsMap = new HashMap<>();
            while(rs.next()) {
                resultsMap.put(rs.getString("message"), rs.getDate("due"));
                tasksMap = resultsMap;
            }
        }
        catch (SQLException e) {
            e.getStackTrace();
        }

        return (HashMap<String, Date>) tasksMap;

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
        String[] sql =
                {"INSERT INTO " + table + " (" + keyColumn + ", " + valueColumn + ")\n"
                        + "VALUES (" + "'" + key + "'" + ", " + "'" + value + "'"
                        + ");"};
        executeStatement(sql);
        System.out.println("[DEBUG] [MAP DESERIALISATION] Success in writing to database");
    }

    /**
     * Retrieves map data from the database and puts it into a map to be passed between classes.
     * @param table String name of table to get data from
     * @param keyColumn String name of column corresponding to map key
     * @param valueColumn String name of column corresponding to value key
     * @return HashMap<String, String> containing results
     */
    public static HashMap<String, String> getStringMapFromDatabase(String table, String keyColumn, String valueColumn) {
        String sql = "SELECT " + keyColumn + ", " + valueColumn + " FROM " + table + ";";
        Map<String, String> returnMap  = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Map<String, String> resultsMap = new HashMap<>();
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

    public static void writePracticalToDatabase(String date, String time, String _class, String code, String message) {
        String[] sql = {
                "INSERT INTO practicals (date, time, class, code, message) VALUES (" + "'" + date + "'" + ", "
                        + "'" + time + "'" + ", "
                        + "'" + _class + "'" + ", "
                        + "'" + code + "'" + ", "
                        + "'" + message + "'" + ");"
        };
        System.out.println(sql);
        executeStatement(sql);
        System.out.println("[DEBUG] [DATABASE WRITE] Practical written to database");
    }

    public static void getPracticalFromDatabase() {
        String sql = "SELECT date, time, class, code, message FROM practicals;";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("date"));
            }
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
    }


    /**
     * Executes sql statement passed in
     * @param sql String results of statement, if any
     */
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


    /**
     * Executes query on database
     * @param sql String query to be run on database
     * @return ResultSet of results
     */
    public static ResultSet executeQuery(String sql) {
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

