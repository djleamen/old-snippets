/*
 * Description: This class implements the ActionListener interface to handle zoom in and zoom out actions.
 * When the user clicks the zoom in or zoom out button, it adjusts the zoom level of the graph.
 * It uses a constant ZOOM_FACTOR to determine the amount of zoom change.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GraphGUI;
import GraphPanel;

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
