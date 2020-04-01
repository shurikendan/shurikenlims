import javax.print.DocFlavor;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


public class UserData {

    private static UserData instance = new UserData();

    //Defines userMap as a new HashMap
    private Map<String, String> userMap = new HashMap<>();

    //This keeps UserData a singleton
    public static UserData getInstance() {
        return instance;
    }

    //Checks if username is taken by referencing UserMap
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    //Adds a username and password to the UserMap
    public void registerUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String passwordHash = Ada.main(password);
        userMap.put(username, passwordHash);
        mapToFile((HashMap<String, String>) userMap);
    }

    //Write the hashmap to the file map.txt
    public void mapToFile(HashMap<String, String> map) {
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
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
/* //TODO Get read to and write from file working
    public void Map<String, String> fileToMap() {
        try {
            File toRead = new File("dat/map.txt");
            FileInputStream fis = new FileInputStream(toRead);
            Scanner sc = new Scanner(fis);
            HashMap<String, String> userMap = new HashMap<String, String>();

            //Read from file line by line
            while(sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer st = new StringTokenizer(currentLine, "=", false);
                userMap.put(st.nextToken(), st.nextToken());
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
    //Checks if username and password match
    public boolean isLoginCorrect(String usernameInput, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //If the username isn't found in the map...
        //if (!userMap.containsKey(usernameInput)) {
        //    return false;
        //}


        return Ada.validatePassword(password, storedPassword);

    }
}
