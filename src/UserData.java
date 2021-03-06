/*
This class handles everything to do with user data.
 */

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.Buffer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.lang.String;

/**
 * Handles everything to do with the user's data
 */

public class UserData {
    /**
     * Makes sure class is always a singleton
     */
    private static UserData instance = new UserData();

    public String currentUser = null;

    //Defines the two hashmaps
    private Map<String, String> userMap = new HashMap<>();
    private Map<String, String> privMap = new HashMap<>();

    public String getUser() {
        return currentUser;
    }

    /**
     * Makes sure class is always singleton
     * @return instance
     */
    public static UserData getInstance() {
        return instance;
    }

    /**
     * Checks whether username exists within the HashMap
     * @param username username to be searched for
     * @return true if username taken
     */
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    /**
     * Registers a new user into the HashMap
     * @param username username
     * @param password password
     * @param priv privilege level
     */
    public void registerUser(String username, char[] password, String priv) {
        //Creates hash
        String passwordHash = Ada.main(password);

        //Puts username and password into hashmap and then stores to file
        userMap.put(username, passwordHash);
        //mapToFile((HashMap<String, String>) userMap);
        addHashMapToDatabase(userMap);

        //Puts username and priv into hashmap then stores in file
        privMap.put(username, priv);
        //privToFile((HashMap<String, String>) privMap);
        addPrivMapToDatabase(privMap);
    }

    /**
     * Removes user from files
     * @param username username to be removed
     */
    public void removeUser(String username) {
        userMap.remove(username);
        //mapToFile((HashMap<String, String>) userMap);
        //TODO put priv here
    }

    /**
     * Writes the hashmap to the txt file
     * @param map map to be read from
     */
    public void mapToFile(@NotNull HashMap<String, String> map) {
        String key = "";
        try {
            File mapFile = new File("dat/map.txt");
            FileOutputStream fos = new FileOutputStream(mapFile);
            PrintWriter pw = new PrintWriter(fos);
            for (Map.Entry<String, String> m : map.entrySet()) {
                pw.println(m.getKey() + "=" + m.getValue());
                System.out.println(m.getKey());
            }
            pw.flush();
            pw.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Writes the contents of the file to the HashMap object
    public void fileToMap() {
        {
            String filePath = "dat/map.txt";
            //HashMap<String, String> map = new HashMap<String, String>();

            String line;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length >= 2) {
                        String key = parts[0];
                        String value = parts[1];
                        userMap.put(key, value);
                    } else {
                        System.out.println("[UserData - fileToMap] Ignoring line: " + line);
                    }
                }
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String key : userMap.keySet()) {
                System.out.println("[DEBUG] [MAP] " + key + ":" + userMap.get(key));
            }

        }
    }




    //Not used
    public void aliasToFile(String username, String forename, String surname) throws IOException {
        File aliasFile = new File("dat/alias.txt");
        FileOutputStream fos = new FileOutputStream(aliasFile);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(username + ":" + forename + ":" + surname);
        pw.flush();
        pw.close();
        fos.close();
    }


    //Adds the user's priv to the file
    public void privToFile(HashMap<String, String> map) {
        try {
            File mapFile = new File("dat/priv.txt");
            FileOutputStream fos = new FileOutputStream(mapFile);
            PrintWriter pw = new PrintWriter(fos);
            for (Map.Entry<String, String> m : map.entrySet()) {
                pw.println(m.getKey() + ":" + m.getValue());
            }
            pw.flush();
            pw.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPrivMapToDatabase(Map<String, String> map) {
        for (Map.Entry<String, String> m : map.entrySet()) {
            Base.writeStringMapToDatabase(m.getKey(), m.getValue(), "privLevels", "user", "level");
        }
    }

    public void getPrivMapFromDatabase() {
        HashMap<String, String> resultsMap = Base.getStringMapFromDatabase("privLevels", "user", "level");
        privMap = resultsMap;
        System.out.println(privMap);
    }

    public void addHashMapToDatabase(Map<String, String> map) {
        for (Map.Entry<String, String> m : map.entrySet()) {
            Base.writeStringMapToDatabase(m.getKey(), m.getValue(), "hashes", "user", "hash");
        }
    }

    public void getHashMapFromDatabase() {
        HashMap<String, String> resultsMap = Base.getStringMapFromDatabase("hashes", "user", "hash");
        userMap = resultsMap;
        System.out.println(userMap);
    }



    public void privToMap() {
        {
            String filePath = "dat/priv.txt";
            String line;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":", 2);
                    if (parts.length >= 2) {
                        String key = parts[0];
                        String value = parts[1];
                        privMap.put(key, value);
                    } else {
                        System.out.println("[UserData - privToMap] Ignoring line: " + line);
                    }
                }
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //This is for debug purposes
            for (String key : privMap.keySet()) {
                System.out.println("[DEBUG] [PRIV] " + key + ":" + privMap.get(key));
            }

        }
    }

    //Finds and returns the privelige level of a user when called.
    public String getPriv(String username) {
        privMap = Base.getStringMapFromDatabase("privLevels", "user", "level");
        String priv;
        priv = privMap.get(username);
        return priv;
        /*
        try {
            File toRead = new File("dat/priv.txt");
            FileInputStream fis = new FileInputStream(toRead);
            Scanner sc = new Scanner(fis);
            HashMap<String, String> privMap = new HashMap<>();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer st = new StringTokenizer(currentLine, ":", false);
                privMap.put(st.nextToken(), st.nextToken());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        */

    }


    //Checks if username and password match
    public boolean isLoginCorrect(String usernameInput, char[] password) {
        userMap = Base.getStringMapFromDatabase("hashes", "user", "hash");
        String storedPassword = null;
        storedPassword = userMap.get(usernameInput);
        if (storedPassword == null) {
            return false;
        }
        else {
            return Ada.validatePassword(password, storedPassword);
        }
        /*
        try {
            File toRead = new File("dat/map.txt");
            FileInputStream fis = new FileInputStream(toRead);
            Scanner sc = new Scanner(fis);
            HashMap<String, String> userMap = new HashMap<String, String>();

            //Read from file line by line
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer st = new StringTokenizer(currentLine, "=", false);
                userMap.put(st.nextToken(), st.nextToken());
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
