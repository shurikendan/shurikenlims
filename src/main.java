import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class main {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //This is a test - I'm not stupid enough to hardcode a master password
        UserData.getInstance().registerUser("admin", "password");
        /*
        if (Ada.validatePassword("password", Ada.main("password"))) {
            System.out.println("Passwords match");
        }
        */
        Login.main();
    }
}
