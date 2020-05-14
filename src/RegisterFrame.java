import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterFrame extends JFrame implements ActionListener {
    Container regContainer = getContentPane();
    JButton backButton = new JButton("< Back");
    JLabel regUserLabel = new JLabel("New Username");
    JLabel regPassLabel = new JLabel("New Password");
    JLabel regConfirmPassLabel = new JLabel("Confirm Password");
    JTextField regUserTextField = new JTextField();
    JPasswordField regPassField = new JPasswordField();
    JPasswordField regConfirmPassField = new JPasswordField();
    JButton regLoginButton = new JButton("Confirm");
    JButton regResetButton = new JButton("Reset");
    JCheckBox regShowPassword = new JCheckBox("Show Password");
    JLabel adminUserLabel = new JLabel("Admin Username");
    JLabel adminPassLabel = new JLabel("Admin Password");
    JTextField adminUserTextField = new JTextField();
    JPasswordField adminPassField = new JPasswordField();
    JButton adminLoginButton = new JButton("Confirm");
    JButton adminResetButton = new JButton("Reset");
    JCheckBox adminShowPassword = new JCheckBox("Show Password");

    RegisterFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    //Ignore the use of any layout manager
    public void setLayoutManager() {
        regContainer.setLayout(null);
    }

    //Sets the location and size of the elements
    public void setLocationAndSize() {
        backButton.setBounds(10, 10, 75, 25);
        //Username elements
        regUserLabel.setBounds(50,50,100,30);
        regUserTextField.setBounds(150,50,150,30);
        adminUserLabel.setBounds(50, 300, 100, 30);
        adminUserTextField.setBounds(150, 300, 150, 30);
        //Password elements
        regPassLabel.setBounds(50,100,100,30);
        regPassField.setBounds(150,100,150,30);
        regPassField.setEchoChar('*');
        regConfirmPassLabel.setBounds(50, 150, 100, 30);
        regConfirmPassField.setBounds(150, 150, 150, 30);
        adminPassLabel.setBounds(50, 350, 100, 30);
        adminPassField.setBounds(150, 350, 150, 30);
        adminPassField.setEchoChar('*');
        //Other elements
        regShowPassword.setBounds(150,180,150,30);
        regLoginButton.setBounds(50,230,100,30);
        regResetButton.setBounds(200,230,100,30);
        adminShowPassword.setBounds(150, 380, 150, 30);
        adminLoginButton.setBounds(50, 430, 100, 30);
        adminResetButton.setBounds(200, 430, 100, 30);
    }

    //Adds all the components to the container
    public void addComponentsToContainer() {
        regContainer.add(backButton);
        //New user's details elements
        regContainer.add(regUserLabel);
        regContainer.add(regUserTextField);
        regContainer.add(regPassLabel);
        regContainer.add(regPassField);
        regContainer.add(regConfirmPassLabel);
        regContainer.add(regConfirmPassField);
        regContainer.add(regShowPassword);
        regContainer.add(regLoginButton);
        regContainer.add(regResetButton);
        //Admin's details elements
        regContainer.add(adminUserLabel);
        regContainer.add(adminUserTextField);
        regContainer.add(adminPassLabel);
        regContainer.add(adminPassField);
        regContainer.add(adminShowPassword);
        regContainer.add(adminLoginButton);
        regContainer.add(adminResetButton);
    }


    public void addActionEvent() {
        backButton.addActionListener(this);
        regLoginButton.addActionListener(this);
        regResetButton.addActionListener(this);
        regShowPassword.addActionListener(this);
        adminLoginButton.addActionListener(this);
        adminResetButton.addActionListener(this);
        adminShowPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            LoginOptions.main(null);
            super.dispose();
        }
        if (actionEvent.getSource() == regLoginButton) {
            String userText;
            String pwdText;
            userText = regUserTextField.getText();
            pwdText = regPassField.getText();
            try {
                if (UserData.getInstance().isLoginCorrect(userText, pwdText) != false) {
                    JOptionPane.showMessageDialog(this, "Correct Login");
                    regUserTextField.setEditable(false);
                    regPassField.setEditable(false);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Incorrect Login");
                    regPassField.setText("");
                }
            }
            catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
