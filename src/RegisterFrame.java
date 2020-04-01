import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel infoLabel = new JLabel("Register new user");
    RegisterFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    //Ignore the use of any layout manager
    public void setLayoutManager() {
        container.setLayout(null);
    }

    //Sets the location and size of the elements
    public void setLocationAndSize() {
        infoLabel.setBounds(50,50,100,100);
    }

    //Adds all the components to the container
    public void addComponentsToContainer() {
        container.add(infoLabel);
    }

    public void addActionEvent() {

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
