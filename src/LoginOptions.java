import javax.swing.*;


public class LoginOptions {
    public static void main(String[] args) {
        LoginOptionsFrame loginOptionsFrame = new LoginOptionsFrame();
        loginOptionsFrame.setTitle("Shuriken LIMS Login");
        loginOptionsFrame.setVisible(true);
        loginOptionsFrame.setBounds(10,10,350,300);
        loginOptionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginOptionsFrame.setResizable(false);
    }

}
