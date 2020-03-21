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
    private UserData() {
        //TODO get this to save as file (JSON)?
    }

    //Checks if username is taken by referencing UserMap
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    //Adds a username and password to the UserMap
    public void registerUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String passwordHash = password;
        userMap.put(username, passwordHash); //TODO change this to hashed password
    }

    //Checks if username and password match
    public boolean isLoginCorrect(String usernameInput, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //If the username isn't found in the map...
        if (!userMap.containsKey(usernameInput)) {
            return false;
        }
        String storedPassword = userMap.get(usernameInput);
        return password.equals(storedPassword);
    }
}
