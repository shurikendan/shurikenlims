//Package imports
import javax.swing.*;
import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException {
        //Loads data from file into HashMap Object
        UserData.getInstance().fileToMap();
        Login.main();
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
