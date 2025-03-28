package csci2020u.lab09.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphPanel;

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
