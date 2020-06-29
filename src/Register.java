import javax.swing.*;

public class Register {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Register New User");
        frame.setVisible(true);
        frame.setBounds(10,10,350,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        System.out.println("register");
    }
}
