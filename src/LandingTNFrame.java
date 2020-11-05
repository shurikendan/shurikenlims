import com.github.lgooddatepicker.components.CalendarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
    JTextArea overviewTextArea = new JTextArea();
    CalendarPanel calendar = new CalendarPanel();

    LandingTNFrame() {
        setLayoutManager();
        setLocationAndSize();
        setTextSizeAndStyle();
        addComponentsToContainer();
        addActionEvent();
        handleOverviewArea();

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
        overviewTextArea.setBounds(266, 405, 490, 300);
        calendar.setBounds(770, 400, 250, 300);
    }

    public void setTextSizeAndStyle() {
        weekView.setFont(weekView.getFont().deriveFont(20f));
        calView.setFont(calView.getFont().deriveFont(20f));
        practicalsButton.setFont(calView.getFont().deriveFont(20f));
        remindersButton.setFont(calView.getFont().deriveFont(20f));
        tasksButton.setFont(calView.getFont().deriveFont(20f));
        databaseButton.setFont(calView.getFont().deriveFont(20f));
        settingsButton.setFont(calView.getFont().deriveFont(20f));
        logOutButton.setFont(calView.getFont().deriveFont(20f));
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
        container.add(overviewTextArea);
        container.add(calendar);
    }

    public void addActionEvent() {
        practicalsButton.addActionListener(this);
        remindersButton.addActionListener(this);
        tasksButton.addActionListener(this);
        settingsButton.addActionListener(this);
        databaseButton.addActionListener(this);
        logOutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (logOutButton.equals(source)) {
            Login.main(null);
            super.dispose();
        }
        else if (practicalsButton.equals(source)) {
            PracticalBook.main(null);
        }
        else if (tasksButton.equals(source)) {
            Base.getPracticalFromDatabase();
        }
        else if (databaseButton.equals(source)) {
            BaseView.main(null);
        }
    }

    public void handleOverviewArea() {
        StringBuilder builder = new StringBuilder();
        for (String value : Base.getPracticalFromDatabase()) {
            builder.append(value);
        }
        String practicalsData = builder.toString();
        //String practicalsData = Arrays.toString(Base.getPracticalFromDatabase());
        overviewTextArea.setText("Tasks due for week commencing 12/10/20:\n\nNo Tasks " +
                "Found\n\nPracticals due:\n\n" + practicalsData);
        overviewTextArea.setFont(calView.getFont().deriveFont(15f));
        System.out.println(Base.fetchTasks());
    }
}
