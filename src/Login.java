
import javax.swing.*;
import java.net.URL;

public class Login {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Shuriken LIMS Login");
        frame.setVisible(true);
        frame.setBounds(10,10,350,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        URL iconURL = Login.class.getResource("ico.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
    }
}
