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
    JLabel weekView = new JLabel("Weekly Overview");
    JSeparator viewSep = new JSeparator(SwingConstants.HORIZONTAL);
    JLabel calView = new JLabel("Calendar");
    JSeparator calSep2 = new JSeparator(SwingConstants.HORIZONTAL);
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
        calSep.setBounds(768, 369, 1, 512);
        weekView.setBounds(266, 375, 200, 25);
        viewSep.setBounds(256, 405, 512, 1);
        calView.setBounds(778,375, 200, 25);
        calSep2.setBounds(768, 405, 256, 1);
        practicalsButton.setBounds(10, 10, 236, 172);
        remindersButton.setBounds(10, 192, 236, 172);
        settingsButton.setBounds(10, 374, 236, 172);
        logOutButton.setBounds(10, 556, 236, 172);
        calendar.setBounds(775, 410, 250, 300);
    }

    public void addComponentsToContainer() {
        container.add(sep1);
        container.add(sep2);
        container.add(weekView);
        container.add(viewSep);
        container.add(calView);
        container.add(calSep2);
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
        weekView.setFont(weekView.getFont().deriveFont(20f));
        calView.setFont(calView.getFont().deriveFont(20f));
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
        Object source = actionEvent.getSource();
        if(source == logOutButton) {
                Login.main(null);
                super.dispose();
        }
        else if(source == practicalsButton) {
            PracticalBook.main(null);
        }
    }
}
