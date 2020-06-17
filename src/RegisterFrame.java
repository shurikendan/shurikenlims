import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


public class RegisterFrame extends JFrame implements ActionListener {

    //Container
    Container regContainer = getContentPane();

    //Back button
    JButton backButton = new JButton("< Back");

    //New user elements
    JLabel regUserLabel = new JLabel("New Username");
    JLabel regPassLabel = new JLabel("New Password");
    JLabel regConfirmPassLabel = new JLabel("Confirm Password");

    JTextField regUserTextField = new JTextField();
    JPasswordField regPassField = new JPasswordField();
    JPasswordField regConfirmPassField = new JPasswordField();

    String[] priveligeOptions = {"Teacher", "Technician", "Admin"};
    JComboBox priveligesComboBox = new JComboBox(priveligeOptions);

    JButton regLoginButton = new JButton("Confirm");
    JButton regResetButton = new JButton("Reset");

    JCheckBox regShowPassword = new JCheckBox("Show Password");

    //Admin elements
    JLabel adminUserLabel = new JLabel("Admin Username");
    JLabel adminPassLabel = new JLabel("Admin Password");

    JTextField adminUserTextField = new JTextField();
    JPasswordField adminPassField = new JPasswordField();

    JButton adminLoginButton = new JButton("Create User");
    JButton adminResetButton = new JButton("Reset");

    JCheckBox adminShowPassword = new JCheckBox("Show Password");

    JSeparator sep = new JSeparator();

    RegisterFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        adminLoginButton.setEnabled(false);
        adminUserTextField.setEnabled(false);
        adminPassField.setEnabled(false);
        adminShowPassword.setEnabled(false);
        adminResetButton.setEnabled(false);
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

        adminUserLabel.setBounds(50, 350, 100, 30);
        adminUserTextField.setBounds(150, 350, 150, 30);

        //Password elements
        regPassLabel.setBounds(50,100,100,30);
        regPassField.setBounds(150,100,150,30);
        regPassField.setEchoChar('*');
        regConfirmPassLabel.setBounds(50, 150, 100, 30);
        regConfirmPassField.setBounds(150, 150, 150, 30);
        regConfirmPassField.setEchoChar('*');

        adminPassLabel.setBounds(50, 400, 100, 30);
        adminPassField.setBounds(150, 400, 150, 30);
        adminPassField.setEchoChar('*');

        //Other elements
        regShowPassword.setBounds(150,180,150,30);
        regLoginButton.setBounds(50,265,100,30);
        regResetButton.setBounds(200,265,100,30);

        priveligesComboBox.setBounds(50,215,250,30);

        adminShowPassword.setBounds(150, 430, 150, 30);
        adminLoginButton.setBounds(50, 480, 100, 30);
        adminResetButton.setBounds(200, 480, 100, 30);

        sep.setBounds(0,320,350,10);

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
        regContainer.add(priveligesComboBox);

        //Admin's details elements
        regContainer.add(adminUserLabel);
        regContainer.add(adminUserTextField);
        regContainer.add(adminPassLabel);
        regContainer.add(adminPassField);
        regContainer.add(adminShowPassword);
        regContainer.add(adminLoginButton);
        regContainer.add(adminResetButton);

        regContainer.add(sep);

    }


    public void addActionEvent() {

        backButton.addActionListener(this);

        //New user elements
        regLoginButton.addActionListener(this);
        regResetButton.addActionListener(this);
        regShowPassword.addActionListener(this);

        //Admin elements
        adminLoginButton.addActionListener(this);
        adminResetButton.addActionListener(this);
        adminShowPassword.addActionListener(this);

    }


    //Action events
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Back button
        if (actionEvent.getSource() == backButton) {
            LoginOptions.main(null);
            super.dispose();
        }

        //Admin Login button
        if (actionEvent.getSource() == adminLoginButton) {
            String adminUserText;
            char[] adminPwdText;
            String regUserText;
            char[] regPwdText;
            adminUserText = adminUserTextField.getText();
            adminPwdText = adminPassField.getPassword();
            regUserText = regUserTextField.getText();
            regPwdText = regPassField.getPassword();
            int privIndex;
            String priv = "";
            privIndex = priveligesComboBox.getSelectedIndex();
            if (privIndex == 0) {
                priv = "2"; //Teacher
            }
            else {
                if (privIndex == 1) {
                    priv = "1"; //Technician
                }
                else {
                    if (privIndex == 2) {
                        priv = "0"; //Admin
                    }
                }
            }
            try {
                if (UserData.getInstance().isLoginCorrect(adminUserText, adminPwdText)) {
                    JOptionPane.showMessageDialog(this, "Correct Admin Credentials");
                    adminUserTextField.setEnabled(false);
                    adminPassField.setEnabled(false);
                    adminShowPassword.setEnabled(false);
                    adminLoginButton.setEnabled(false);
                    System.out.println("[DEBUG] [REGISTER] Admin Approved");
                    try {
                        UserData.getInstance().registerUser(regUserText, regPwdText, priv);
                        System.out.println("[DEBUG] [REGISTER] User Created");
                        JOptionPane.showMessageDialog(this, "User created successfully");
                    }
                    catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect Admin Credentials");
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }

        //Events for new user login button
        if (actionEvent.getSource() == regLoginButton) {
            char[] p1 = regPassField.getPassword();
            char[] p2 = regConfirmPassField.getPassword();

            //Checks if username isn't blank
            String username = regUserTextField.getText();
            if (username.isEmpty()) JOptionPane.showMessageDialog(this, "Please enter username");
            else {
                //Checks that the username is available
                if (UserData.getInstance().isUsernameTaken(regUserTextField.getText())) {
                    JOptionPane.showMessageDialog(this, "Username is already taken");
                } else {
                    if (!regPassField.getPassword().equals("")) {

                        //Checks that the passwords match
                        if (Arrays.equals(p1, p2)) {

                            //Checks the complexity of the password
                            char[] p = regPassField.getPassword();
                            int caps = 0;
                            int numer = 0;
                            int speci = 0;

                            int capsReq = 1;
                            int numerReq = 1;
                            int speciReq = 1;

                            if (p.length > 7) {
                                for (int i = 0; i < p.length; i++) {
                                    char c = p[i];                  //Separates the string into chars
                                    String cs = String.valueOf(c);

                                    //Counts alphabetical characters
                                    if (cs.matches("[a-zA-Z]")) caps = caps + 1;
                                    else {
                                        //Counts numerical characters
                                        if (cs.matches("[0-9]")) numer = numer + 1;
                                        else {
                                            //Counts special characters
                                            if (!cs.matches("[a-zA-Z0-9]")) speci = speci + 1;
                                        }
                                    }
                                }
                                if (caps >= capsReq && numer >= numerReq && speci >= speciReq) {
                                    System.out.println("[DEBUG] [REGISTER] Password Meets Complexity Requirements");
                                    regUserTextField.setEnabled(false);
                                    regPassField.setEnabled(false);
                                    regConfirmPassField.setEnabled(false);
                                    regLoginButton.setEnabled(false);
                                    regShowPassword.setEnabled(false);

                                    adminUserTextField.setEnabled(true);
                                    adminPassField.setEnabled(true);
                                    adminLoginButton.setEnabled(true);
                                    adminShowPassword.setEnabled(true);
                                    adminResetButton.setEnabled(true);

                                } else {
                                    if (caps < capsReq) {
                                        JOptionPane.showMessageDialog(this, "Insufficient password capitalisation");
                                    }
                                    if (numer < numerReq) {
                                        JOptionPane.showMessageDialog(this, "Insufficient password numerals");
                                    }
                                    if (speci < speciReq) {
                                        JOptionPane.showMessageDialog(this, "Insufficient password speciality");
                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(this, "Password too short");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Passwords do not match");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Password is blank");
                    }
                }
            }
        }

        //Events for admin reset button
        if (actionEvent.getSource() == adminResetButton) {
            adminUserTextField.setText("");
            adminPassField.setText("");
            adminUserTextField.setEnabled(true);
            adminPassField.setEnabled(true);
        }

        //Events for new user reset button
        if (actionEvent.getSource() == regResetButton) {
            regUserTextField.setText("");
            regPassField.setText("");
            regConfirmPassField.setText("");
            regUserTextField.setEnabled(true);
            regPassField.setEnabled(true);
            regConfirmPassField.setEnabled(true);
            regLoginButton.setEnabled(true);
            regShowPassword.setEnabled(true);
        }

        //Events for new user show password button
        if (actionEvent.getSource() == regShowPassword) {
            if (regShowPassword.isSelected()) {
                //Change the characters to be readable
                regPassField.setEchoChar((char) 0);
                regConfirmPassField.setEchoChar((char) 0);
            } else {
                //Change the characters to unreadable
                regPassField.setEchoChar('*');
                regConfirmPassField.setEchoChar('*');
            }
        }

        //Events for admin show password button
        if (actionEvent.getSource() == adminShowPassword) {
            if (adminShowPassword.isSelected()) {
                //Change the characters to be readable
                adminPassField.setEchoChar((char) 0);
            } else {
                //Change the characters to unreadable
                adminPassField.setEchoChar('*');
            }
        }
    }
}
