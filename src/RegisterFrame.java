import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    Container regContainer = getContentPane();
    JButton backButton = new JButton("< Back");
    JLabel regInfoLabel = new JLabel("Register new user");
    JLabel regUserLabel = new JLabel("New Username");
    JLabel regPassLabel = new JLabel("New Password");
    JTextField regUserTextField = new JTextField();
    JPasswordField regPassField = new JPasswordField();
    JButton regLoginButton = new JButton("Confirm");
    JButton regResetButton = new JButton("Reset");
    JCheckBox regShowPassword = new JCheckBox("Show Password");
    RegisterFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setLabelFont();
    }

    //Ignore the use of any layout manager
    public void setLayoutManager() {
        regContainer.setLayout(null);
    }

    //Sets the location and size of the elements
    public void setLocationAndSize() {
        backButton.setBounds(10, 10, 75, 25);
        regInfoLabel.setBounds(350,0,100,25);
        //Username elements
        regUserLabel.setBounds(50,50,100,30);
        regUserTextField.setBounds(150,50,150,30);
        //Password elements
        regPassLabel.setBounds(50,100,100,30);
        regPassField.setBounds(150,100,150,30);
        regPassField.setEchoChar('*');
        //Other elements
        regShowPassword.setBounds(150,130,150,30);
        regLoginButton.setBounds(50,180,100,30);
        regResetButton.setBounds(200,180,100,30);
    }

    //Adds all the components to the container
    public void addComponentsToContainer() {
        regContainer.add(backButton);
        regContainer.add(regInfoLabel);
        regContainer.add(regUserLabel);
        regContainer.add(regUserTextField);
        regContainer.add(regPassLabel);
        regContainer.add(regPassField);
        regContainer.add(regShowPassword);
        regContainer.add(regLoginButton);
        regContainer.add(regResetButton);
    }

    private void setLabelFont() {
        regInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    }


    public void addActionEvent() {

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
