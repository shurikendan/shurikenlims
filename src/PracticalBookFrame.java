import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class PracticalBookFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    DateTimePicker picker = new DateTimePicker();
    JLabel label = new JLabel("Practical Booking");
    JTextField classField = new JTextField();
    JLabel classLabel = new JLabel("Class: ");
    JTextField codeField = new JTextField();
    JLabel codeLabel = new JLabel("Code: ");
    JPanel detailPanel = new JPanel();
    JLabel detailLabel = new JLabel("Details: ");
    JTextArea detailTextArea = new JTextArea(3, 20);
    JScrollPane scrollPane = new JScrollPane(detailTextArea);
    JPanel pickerPanel = new JPanel();
    JPanel classPanel = new JPanel();
    JPanel codePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    GridLayout layout = new GridLayout(0, 1);

    PracticalBookFrame() {
        setLayoutManager();
        addComponentsToContainer();
        setSize();
        setComponentProperties();
        addActionListeners();

    }

    private void setLayoutManager() {
        setLayout(layout);
    }

    private void addComponentsToContainer() {
        container.add(label);

        pickerPanel.add(picker);
        container.add(pickerPanel);

        classPanel.add(classLabel);
        classPanel.add(classField);
        container.add(classPanel);

        codePanel.add(codeLabel);
        codePanel.add(codeField);
        container.add(codePanel);

        detailPanel.add(detailLabel);
        detailPanel.add(scrollPane);
        container.add(detailPanel);

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        container.add(buttonPanel);

    }

    private void setComponentProperties() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(20f));

        classLabel.setFont(classLabel.getFont().deriveFont(15f));
        codeLabel.setFont(codeLabel.getFont().deriveFont(15f));
        detailLabel.setFont(detailLabel.getFont().deriveFont(15f));

        detailTextArea.setBorder(border);
    }

    private void setSize() {
        classField.setColumns(10);
        codeField.setColumns(10);

        buttonPanel.setLayout(null);
        cancelButton.setBounds(5,15,130,30);
        saveButton.setBounds(145, 15, 130, 30);
    }

    private void addActionListeners() {
        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cancelButton) {
            super.dispose();
        }
        else {
            if(actionEvent.getSource() == saveButton) {
                String date = picker.getDatePicker().getText();
                String time = picker.getTimePicker().getText();
                String _class = classField.getText();
                String code = codeField.getText();
                String message = detailTextArea.getText();
                Base.writePracticalToDatabase(date, time, _class, code, message);
            }
        }
    }
}
