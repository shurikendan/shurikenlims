
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
        //URL iconURL = Login.class.getResource("../res/beaker_white.png");
        //ImageIcon icon = new ImageIcon(iconURL);
        //frame.setIconImage(icon.getImage());
        try {
            frame.setIconImage(ImageIO.read(new File("res/beaker_white.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
