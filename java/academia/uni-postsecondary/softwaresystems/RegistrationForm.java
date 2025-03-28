package softwaresystems;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.MaskFormatter;

public class RegistrationForm {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 04 Solution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        addComponent(panel, usernameLabel, usernameField, gbc, 0);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        addComponent(panel, passwordLabel, passwordField, gbc, 1);

        // Full Name label and field
        JLabel fullNameLabel = new JLabel("Full Name:");
        JTextField fullNameField = new JTextField();
        addComponent(panel, fullNameLabel, fullNameField, gbc, 2);

        // E-Mail label and field
        JLabel emailLabel = new JLabel("E-Mail:");
        JTextField emailField = new JTextField();
        addComponent(panel, emailLabel, emailField, gbc, 3);

        // Phone Number label and field
        JLabel phoneLabel = new JLabel("Phone #:");
        JFormattedTextField phoneField = createPhoneNumberField();
        addComponent(panel, phoneLabel, phoneField, gbc, 4);
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        JLabel dobLabel = new JLabel("Date of Birth:");
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.getEditor().setText("03.01.2025");  // Default date format
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(dobLabel, gbc);
        gbc.gridx = 1;
        panel.add(datePicker, gbc);

        // Register button
        JButton registerButton = new JButton("Register");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);
        registerButton.addActionListener(e -> {
            System.out.println("Username: " + usernameField.getText());
            System.out.println("Password: " + new String(passwordField.getPassword()));
            System.out.println("Full Name: " + fullNameField.getText());
            System.out.println("E-Mail: " + emailField.getText());
            System.out.println("Phone #: " + phoneField.getText());
            System.out.println("Date of Birth: " + datePicker.getDate());
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    // Helper method to create a formatted text field for phone numbers
    private static JFormattedTextField createPhoneNumberField() {
        try {
            MaskFormatter phoneFormatter = new MaskFormatter("(###) ###-####");
            phoneFormatter.setPlaceholderCharacter('_');
            return new JFormattedTextField(phoneFormatter);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }
    // Helper method to add a label and text field to the panel
    private static void addComponent(JPanel panel, JLabel label, JTextField field, GridBagConstraints gbc, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setColumns(20);
        panel.add(field, gbc);
    }
}