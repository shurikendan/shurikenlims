import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingTRFrame  extends JFrame implements ActionListener {
    Container container = getContentPane();
    JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
    JButton practicalsButton = new JButton("Test Button");
    JButton remindersButton = new JButton("Test Button");
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
        practicalsButton.setBounds(10, 10, 236, 182);
        remindersButton.setBounds(10, 202, 236, 182);
    }

    public void addComponentsToContainer() {
        container.add(sep1);
        container.add(practicalsButton);
        container.add(remindersButton);
    }

    public void addActionEvent() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
