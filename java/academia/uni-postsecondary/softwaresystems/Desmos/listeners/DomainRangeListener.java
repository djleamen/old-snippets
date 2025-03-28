package csci2020u.lab09.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import csci2020u.lab09.components.GraphPanel;

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
