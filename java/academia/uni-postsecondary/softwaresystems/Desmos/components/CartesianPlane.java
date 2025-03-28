 package csci2020u.lab09.components;

import java.awt.Graphics2D;
import csci2020u.lab09.GraphGUI;
import java.awt.Color; // added for color

public class CartesianPlane extends GraphComponent {

    public CartesianPlane(GraphGUI gui) {
        super(gui);
    }
    
    @Override
    public void draw(Graphics2D graphics2D) {
        int width = gui.getPlaneWidth();
        int height = gui.getPlaneHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        graphics2D.setColor(Color.LIGHT_GRAY);

        // Draw the grid
        for (int x = 0; x <= width; x += 50) {
            graphics2D.drawLine(x, 0, x, height);
        }
        for (int y = 0; y <= height; y += 50) {
            graphics2D.drawLine(0, y, width, y);
        }
    
        // Draw the axes
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, centerY, width, centerY);
        graphics2D.drawLine(centerX, 0, centerX, height);
    
        // Draw the labels
        for (int x = 0; x <= width; x += 50) {
            int domainValue = (int) ((x - centerX) * (gui.getDomainStep() / gui.getZoom()));
            graphics2D.drawString(String.valueOf(domainValue), x + 2, centerY - 2);
        }
        for (int y = 0; y <= height; y += 50) {
            int rangeValue = (int) (-(y - centerY) * (gui.getRangeStep() / gui.getZoom()));
            graphics2D.drawString(String.valueOf(rangeValue), centerX + 2, y - 2);
        }
    }
}