/*
 * Description: This class implements the ActionListener interface to handle
 * the reset action in a graphing application. When the reset button is clicked,
 * it resets the fields in the GUI and repaints the graph panel.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GraphGUI;
import GraphPanel;

public class ResetListener implements ActionListener {

    private final GraphGUI gui;
    private final GraphPanel panel;

    public ResetListener(GraphGUI gui, GraphPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gui.resetFields();
        panel.repaint();
    }
}
