import javax.swing.*;

public class Register {
    public static void main() {
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Shuriken LIMS");
        frame.setVisible(true);
        frame.setBounds(10,10,350,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
