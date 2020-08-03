import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingTNFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
    JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
    JSeparator calSep = new JSeparator(SwingConstants.VERTICAL);
    JLabel weekView = new JLabel("Weekly Overview");
    JSeparator viewSep = new JSeparator(SwingConstants.HORIZONTAL);
    JLabel calView = new JLabel("Calendar");
    JSeparator calSep2 = new JSeparator(SwingConstants.HORIZONTAL);
    JButton practicalsButton = new JButton("Practicals ");
    JButton remindersButton = new JButton("Reminders");
    JButton tasksButton = new JButton("Tasks");
    JButton databaseButton = new JButton("Database");
    JButton settingsButton = new JButton("Settings");
    JButton logOutButton = new JButton("Log Out");

    LandingTNFrame() {
        setLayoutManager();
        setLocationAndSize();
        setTextSizeAndStyle();
        addComponentsToContainer();
        addActionEvent();

    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        sep1.setBounds(256, 0, 1, 768);
        sep2.setBounds(256, 360, 768, 1);
        calSep.setBounds(768, 360, 1, 512);
        weekView.setBounds(266, 365, 200, 25);
        viewSep.setBounds(256, 395, 512, 1);
        calView.setBounds(778,365, 200, 25);
        calSep2.setBounds(768, 395, 256, 1);
        practicalsButton.setBounds(10, 10, 236, 108);
        remindersButton.setBounds(10, 128, 236, 108);
        tasksButton.setBounds(10, 246, 236, 108);
        databaseButton.setBounds(10, 364, 236, 108);
        settingsButton.setBounds(10, 482, 236, 108);
        logOutButton.setBounds(10, 600, 236, 108);
    }

    public void setTextSizeAndStyle() {
        weekView.setFont(weekView.getFont().deriveFont(20f));
        calView.setFont(calView.getFont().deriveFont(20f));
    }

    public void addComponentsToContainer() {
        container.add(sep1);
        container.add(sep2);
        container.add(calSep);
        container.add(weekView);
        container.add(viewSep);
        container.add(calView);
        container.add(calSep2);
        container.add(practicalsButton);
        container.add(remindersButton);
        container.add(tasksButton);
        container.add(databaseButton);
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
