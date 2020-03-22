import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;


public class UserData {
    private static UserData instance = new UserData();
    private Map<String, String> userMap = new HashMap<>();
    public static UserData getInstance() {
        return instance;
    }
    /*
    public static void main() {
        //userMap = fileToMap((HashMap<String, String>) userMap);
    }
    */
    //Checks if username is taken by referencing UserMap
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    //Adds a username and password to the UserMap
    public void registerUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String passwordHash = Ada.main(password);
        userMap.put(username, passwordHash);
        //mapToFile((HashMap<String, String>) userMap);
    }

    //Checks if username and password match
    public boolean isLoginCorrect(String usernameInput, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //If the username isn't found in the map...
        if (!userMap.containsKey(usernameInput)) {
            return false;
        }
        String storedPassword = userMap.get(usernameInput);
        return Ada.validatePassword(password, storedPassword);
    }
/*
    public void mapToFile(HashMap<String, String> map) {
        try {
            File toWrite = new File("dat/map.txt");
            FileOutputStream fos = new FileOutputStream(toWrite);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> fileToMap(HashMap<String, String> map) {
        try {
            File toRead = new File("dat/map.txt");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, String> hashMap = (HashMap<String, String>)ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return HashMap<String, String> hashMap;
    }
    */
}
