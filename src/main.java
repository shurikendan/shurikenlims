//Package imports
import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class main {
    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        //Loads data from file into HashMap Object
        UserData.getInstance().fileToMap();
        Login.main(null);
        /*
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Shuriken LIMS");
        loginFrame.setVisible(true);
        loginFrame.setBounds(10,10,350,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
         */

    }
}
