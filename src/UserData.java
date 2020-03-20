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
    private UserData(){}
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }
    public void registerUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String passwordHash = password;
        userMap.put(username, passwordHash);
    }
    public boolean isLoginCorrect(String usernameInput, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (!userMap.containsKey(usernameInput)) {
            return false;
        }
        String storedPassword = userMap.get(usernameInput);
        return password.equals(storedPassword);
    }
}
