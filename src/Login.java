
import javax.swing.*;
import java.net.URL;

public class Login {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Shuriken LIMS Login");
        loginFrame.setVisible(true);
        loginFrame.setBounds(10,10,350,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        URL iconURL = Login.class.getResource("ico.png");
        ImageIcon icon = new ImageIcon(iconURL);
        loginFrame.setIconImage(icon.getImage());
    }
}
