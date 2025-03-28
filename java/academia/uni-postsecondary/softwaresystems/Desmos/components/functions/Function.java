package csci2020u.lab09.components.functions;

import java.awt.Graphics2D;
import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphComponent;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.enums.FunctionType;
import csci2020u.lab09.enums.RootType;

public abstract class Function extends GraphComponent {
    
    
    protected Function(GraphGUI gui) {
        super(gui);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        // Draw the function
        double step = 0.02;
        double minX = gui.getMinDomain();
        double maxX = gui.getMaxDomain();

        for (double x = minX; x < maxX; x += step) {
            double xNext = x + step;
            double y = getValueAt(x, FunctionType.ORIGINAL);
            double yNext = getValueAt(xNext, FunctionType.ORIGINAL);

            // Check if the function is within the range
            if (y >= gui.getMinRange() && y <= gui.getMaxRange() && yNext >= gui.getMinRange() && yNext <= gui.getMaxRange()) {
                int screenX = (int) (x * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2.0);
                int screenY = (int) (-y * gui.getZoom() / gui.getRangeStep() + gui.getPlaneHeight() / 2.0);
                int screenX2 = (int) (xNext * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2.0);
                int screenY2 = (int) (-yNext * gui.getZoom() / gui.getRangeStep() + gui.getPlaneHeight() / 2.0);
                graphics2D.drawLine(screenX, screenY, screenX2, screenY2);
            }
        }
    }

    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
}
