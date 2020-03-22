import javax.swing.*;

public class Login {
    public static void main() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Shuriken LIMS");
        loginFrame.setVisible(true);
        loginFrame.setBounds(10,10,350,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }
}
