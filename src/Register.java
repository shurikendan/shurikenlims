import javax.swing.*;

public class Register {
    public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Register New User");
        frame.setVisible(true);
        frame.setBounds(10,10,350,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        System.out.println("register");
    }
}
