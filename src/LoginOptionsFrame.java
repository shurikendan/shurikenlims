import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOptionsFrame extends JFrame implements ActionListener {
    Container backContainer = getContentPane();
    Container buttonContainer = getContentPane();
    JButton backButton = new JButton("< Back");
    JButton registerButton = new JButton("Register user");
    //JButton userOptionsButton = new JButton("User options");
    LoginOptionsFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        backContainer.setLayout(null);
        buttonContainer.setLayout(null);
    }
    public void setLocationAndSize() {
        backButton.setBounds(10, 10, 75, 25);
        registerButton.setBounds(75, 50, 200, 30);
        //userOptionsButton.setBounds(75, 100, 200, 30);

    }
    public void addComponentsToContainer() {
        buttonContainer.add(backButton);
        buttonContainer.add(registerButton);
        //buttonContainer.add(userOptionsButton);
    }
    public void addActionEvent() {
        backButton.addActionListener(this);
        registerButton.addActionListener(this);
        //userOptionsButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            Login.main(null);
            super.dispose();
        }
        if (actionEvent.getSource() == registerButton) {
            Register.main(null);
            super.dispose();
        }
    }
}

System.out.println("Test");
