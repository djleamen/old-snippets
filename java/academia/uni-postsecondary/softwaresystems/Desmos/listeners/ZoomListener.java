package csci2020u.lab09.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphPanel;

public class ZoomListener implements ActionListener {
    
    private final int ZOOM_FACTOR = 10;
    private final GraphGUI gui;
    private final GraphPanel panel;

    public ZoomListener(GraphGUI gui, GraphPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("+")) {
            gui.setZoom(gui.getZoom() + ZOOM_FACTOR);
        } else if (gui.getZoom() - ZOOM_FACTOR > 0) {
            gui.setZoom(gui.getZoom() - ZOOM_FACTOR);
        }
        panel.repaint();
    }
}
