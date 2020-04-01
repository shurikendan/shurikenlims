import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class main {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //This is a test - I'm not stupid enough to hardcode a master password //TODO Remove hard coded password
        UserData.getInstance().registerUser("admin", "password");
        //UserData.getInstance().registerUser("username", "password");
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Shuriken LIMS");
        loginFrame.setVisible(true);
        loginFrame.setBounds(10,10,350,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }
}
