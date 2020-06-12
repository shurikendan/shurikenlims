import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

//Creates custom frame class
public class LoginFrame extends JFrame implements ActionListener {
    //Container to hold elements
    Container container = getContentPane();
    //Creating instances of elements
    JLabel userLabel = new JLabel("Username");
    JLabel passLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton = new JButton("Log in");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JButton optionButton = new JButton("Options");
    //Constructor (calling all the other methods)
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    //Setting bounds manually, so don't need a layout manager
    public void setLayoutManager() {
        container.setLayout(null);
    }

    //Setting the bounds for all the elements
    public void setLocationAndSize() {
        //Username elements
        userLabel.setBounds(50,50,100,30);
        userTextField.setBounds(150,50,150,30);
        //Password elements
        passLabel.setBounds(50,100,100,30);
        passField.setBounds(150,100,150,30);
        passField.setEchoChar('*');
        //Other elements
        showPassword.setBounds(150,130,150,30);
        loginButton.setBounds(50,180,100,30);
        resetButton.setBounds(200,180,100,30);
        optionButton.setBounds(200, 220, 100, 20);
    }

    //Adds all the components to the container
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passLabel);
        container.add(userTextField);
        container.add(passField);
        container.add(loginButton);
        container.add(resetButton);
        container.add(showPassword);
        container.add(optionButton);
    }

    //Adds event listeners to the necessary elements
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        optionButton.addActionListener(this);
    }

    //Controls the actions of the login, reset and show password buttons
    @Override
    public void actionPerformed(@NotNull ActionEvent actionEvent) {
        //Login button actions
        if (actionEvent.getSource() == loginButton) {
            String userText;
            char[] pwdText;
            //Get whatever's inside the username and password fields
            userText = userTextField.getText();
            pwdText = passField.getPassword();
            try {
                //Checks if the login is correct
                if (UserData.getInstance().isLoginCorrect(userText, pwdText)) {
                    if (UserData.getInstance().getPriv(userText).equals("2")) {
                        LandingTR.main(null);
                    }
                    else {
                        if (UserData.getInstance().getPriv(userText).equals("1")) {
                            System.out.println("Technician Page");
                        }
                        else {
                            if (UserData.getInstance().getPriv(userText).equals("0")) {
                                System.out.println("Admin Page");
                            }
                        }
                    }
                }
                else {
                    //If it's not correct, show error message.
                    JOptionPane.showMessageDialog(this, "Incorrect Login", "Authentication Error", JOptionPane.ERROR_MESSAGE);
                    //Sets both fields to be blank again
                    passField.setText("");
                }
            }
            catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }
        //Reset button actions
        if (actionEvent.getSource() == resetButton) {
            //Set the text inside the fields to be blank
            userTextField.setText("");
            passField.setText("");
        }
        //Show password button actions
        if (actionEvent.getSource() == showPassword) {
            //If the button is selected...
            if (showPassword.isSelected()) {
                //Change the characters to be readable
                passField.setEchoChar((char) 0);
            }
            else {
                //Change the characters to unreadable
                passField.setEchoChar('*');
            }
        }
        if (actionEvent.getSource() == optionButton) {
            System.out.println("options");
            LoginOptions.main(null);
            super.dispose();

        }
    }
}
