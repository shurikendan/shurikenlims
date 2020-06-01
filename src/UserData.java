/*
This class contains all the methods that are related to the user's data.
 */

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.String;


public class UserData {
    private static UserData instance = new UserData();

    //Defines userMap as a new HashMap
    private Map<String, String> userMap = new HashMap<>();

    //This keeps UserData a singleton
    public static UserData getInstance() {
        return instance;
    }

    //Checks whether a username is taken
    public boolean isUsernameTaken(String username) {
        String key = username + "=1000"; //Bad programming, should change when iterations change
        return userMap.containsKey(key);
    }

    //Registers a new user
    public void registerUser(String username, char[] password) throws InvalidKeySpecException,
            NoSuchAlgorithmException {
        String passwordHash = Ada.main(password);
        userMap.put(username, passwordHash);
        mapToFile((HashMap<String, String>) userMap);
        //aliasToFile(username, forename, surname);
    }

    //Write the HashMap to the file map.txt
    public void mapToFile(@NotNull HashMap<String, String> map) {
        //Write to file "map.txt"
        try {
            File mapFile = new File("dat/map.txt");
            FileOutputStream fos = new FileOutputStream(mapFile);
            PrintWriter pw = new PrintWriter(fos);
            for (Map.Entry<String, String> m : map.entrySet()) {
                pw.println(m.getKey() + "=" + m.getValue());
            }
            pw.flush();
            pw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Writes the contents of the file to the HashMap object
    public void fileToMap() throws IOException {
        {
            String filePath = "dat/map.txt";
            //HashMap<String, String> map = new HashMap<String, String>();

            String line;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    userMap.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }

            for (String key : userMap.keySet()) {
                System.out.println(key + ":" + userMap.get(key));
            }
            reader.close();
        }
    }

    public void aliasToFile(String username, String forename, String surname) throws IOException {
        File aliasFile = new File("dat/alias.txt");
        FileOutputStream fos = new FileOutputStream(aliasFile);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(username + ":" + forename + ":" + surname);
        pw.flush();
        pw.close();
        fos.close();
    }
    //Checks if username and password match
    public boolean isLoginCorrect(String usernameInput, char[] password) throws InvalidKeySpecException,
            NoSuchAlgorithmException {
        //Loads the hashmap from map.txt
        String storedPassword = null;
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
            storedPassword = userMap.get(usernameInput);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //If the username isn't found in the map...

        if (storedPassword == null) {
            return false;
        }
        else {
            return Ada.validatePassword(password, storedPassword);
        }


    }
}
