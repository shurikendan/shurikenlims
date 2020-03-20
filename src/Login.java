import javax.swing.*;

public class Login {
    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Shuriken LIMS");
        frame.setVisible(true);
        frame.setBounds(10,10,350,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
