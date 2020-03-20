//Package imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Username");
    JLabel passLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton = new JButton("Log in");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

    }
    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void setLocationAndSize() {
        //Username element
        userLabel.setBounds(50,50,100,30);
        userTextField.setBounds(150,50,150,30);
        //Password element
        passLabel.setBounds(50,100,100,30);
        passField.setBounds(150,100,150,30);
        //Other elements
        showPassword.setBounds(150,130,150,30);
        loginButton.setBounds(50,180,100,30);
        resetButton.setBounds(200,180,100,30);
    }
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passLabel);
        container.add(userTextField);
        container.add(passField);
        container.add(loginButton);
        container.add(resetButton);
        container.add(showPassword);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}
