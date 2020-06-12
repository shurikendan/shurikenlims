//Package imports
import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class main {
    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //TODO Change LookAndFeel
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //Loads data from file into HashMap Object
        UserData.getInstance().fileToMap();
        UserData.getInstance().privToMap();
        Login.main(null);
        //System.out.println(UserData.getInstance().getPriv("test1"));
        //char[] ps;
        //String str = "password123!";
        //ps = str.toCharArray();
        //UserData.getInstance().registerUser("admin", ps, "0");
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
