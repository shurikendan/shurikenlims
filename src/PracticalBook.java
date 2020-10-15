import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PracticalBook {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("[EXCEPTION] [UIMANAGER] " + e.getMessage());
        }
        PracticalBookFrame frame = new PracticalBookFrame();
        frame.setTitle("Shuriken LIMS");
        frame.setVisible(true);
        frame.setSize(300, 378);
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
