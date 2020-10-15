import com.github.lgooddatepicker.components.CalendarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandingTRFrame  extends JFrame implements ActionListener {
    Container container = getContentPane();
    JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
    JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
    JSeparator calSep = new JSeparator(SwingConstants.VERTICAL);
    JButton practicalsButton = new JButton("Practicals ");
    JButton remindersButton = new JButton("Reminders");
    JButton settingsButton = new JButton("Settings");
    JButton logOutButton = new JButton("Log Out");
    CalendarPanel calendar = new CalendarPanel();

    LandingTRFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTextSizeAndStyle();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        sep1.setBounds(256, 0, 10, 768);
        sep2.setBounds(256, 369, 768, 10);
        calSep.setBounds(768, 360, 1, 512);
        practicalsButton.setBounds(10, 10, 236, 172);
        remindersButton.setBounds(10, 192, 236, 172);
        settingsButton.setBounds(10, 374, 236, 172);
        logOutButton.setBounds(10, 556, 236, 172);
        calendar.setBounds(770, 400, 250, 300);
    }

    public void addComponentsToContainer() {
        container.add(sep1);
        container.add(sep2);
        container.add(practicalsButton);
        container.add(remindersButton);
        container.add(settingsButton);
        container.add(logOutButton);
        container.add(calendar);
        container.add(calSep);
    }

    public void setTextSizeAndStyle() {
        practicalsButton.setFont(practicalsButton.getFont().deriveFont(20f));
        remindersButton.setFont(remindersButton.getFont().deriveFont(20f));
        settingsButton.setFont(settingsButton.getFont().deriveFont(20f));
        logOutButton.setFont(logOutButton.getFont().deriveFont(20f));
        //TODO Change font size for sidebar buttons
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
