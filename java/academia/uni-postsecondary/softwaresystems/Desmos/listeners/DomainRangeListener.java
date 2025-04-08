/*
 * Description: This class listens for changes in the domain and range 
 * text fields in a graphing application. When the user inputs a value,
 * it checks if the value is a valid number. If not, it shows an error
 * message. The class is part of a larger application that allows users
 * to graph functions and manipulate their properties.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import GraphPanel;

public class DomainRangeListener implements ActionListener {

    private final GraphPanel panel;

    public DomainRangeListener(GraphPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Double.parseDouble(((JTextField) event.getSource()).getText());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, ((JTextField) event.getSource()).getText() + " is an invalid domain or range value", "Domain/Range Error", JOptionPane.ERROR_MESSAGE);
        }
        panel.repaint();
    }
}
