import javax.swing.*;
import java.net.URL;

public class LandingTR {
    public static void main(String args[]) {
        LandingTRFrame frame = new LandingTRFrame();
        frame.setTitle("Shuriken LIMS");
        frame.setVisible(true);
        frame.setBounds(10,10,1024,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        URL iconURL = LandingTR.class.getResource("ico.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
    }
}
