import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandingTRFrame  extends JFrame implements ActionListener {
    Container container = getContentPane();
    JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
    JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
    JButton practicalsButton = new JButton("Practicals ");
    JButton remindersButton = new JButton("Reminders");
    JButton settingsButton = new JButton("Settings");
    JButton logOutButton = new JButton("Log Out");

    LandingTRFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        sep1.setBounds(256, 0, 10, 768);
        sep2.setBounds(256, 369, 768, 10);
        practicalsButton.setBounds(10, 10, 236, 172);
        remindersButton.setBounds(10, 192, 236, 172);
        settingsButton.setBounds(10, 374, 236, 172);
        logOutButton.setBounds(10, 556, 236, 172);
    }

    public void addComponentsToContainer() {
        container.add(sep1);
        container.add(sep2);
        container.add(practicalsButton);
        container.add(remindersButton);
        container.add(settingsButton);
        container.add(logOutButton);
    }

    public void addActionEvent() {
        practicalsButton.addActionListener(this);
        remindersButton.addActionListener(this);
        settingsButton.addActionListener(this);
        logOutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == logOutButton) {
                Login.main(null);
                super.dispose();
        }
    }
}
