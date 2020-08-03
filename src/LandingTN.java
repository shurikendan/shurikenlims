import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LandingTN {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("[EXCEPTION] [UIMANAGER] " + e.getMessage());
        }
        LandingTNFrame frame = new LandingTNFrame();
        frame.setTitle("Shuriken LIMS");
        frame.setVisible(true);
        frame.setSize(1024, 755);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        try {
            frame.setIconImage(ImageIO.read(new File("res/beaker_white.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
