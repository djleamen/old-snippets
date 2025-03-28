package csci2020u.lab09.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphComponent;
import csci2020u.lab09.components.Point;

public class PointClickListener implements MouseListener {

    private final GraphGUI gui;
    private final HashSet<Point> points;

    public PointClickListener(GraphGUI gui) {
        this.gui = gui;
        points = new HashSet<>();
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        if (!points.isEmpty()) {
            points.stream().filter((point) -> (point.getPoint().contains(event.getX(), event.getY()))).forEach((point) -> {
                gui.setPointLabel(point.toString(), point.getRootType());
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Not needed!
    }

    public void setPoints(LinkedList<GraphComponent> points) {
        this.points.clear();
        points.stream().filter((c) -> (c instanceof Point)).forEach((c) -> {
            this.points.add((Point) c);
        });
    }
}
