/*
 * Description: Point class representing a point on the graph.
 * It extends the GraphComponent class and provides methods to
 * get the coordinates, root type, and draw the point on the graph.
 * The class uses an Ellipse2D object to represent the point.
 */

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import GraphGUI;
import RootType;

public class Point extends GraphComponent {
    
    private final Ellipse2D.Double point;
    private final int RADIUS = 15;
    private final RootType rootType;
    private double x;
    private double y;
    
    public Point(GraphGUI gui, RootType rootType, double x, double y) {
        super(gui);
        this.rootType = rootType;
        this.x = x;
        this.y = y;
        point = new Ellipse2D.Double(x * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2 - RADIUS / 2, -y * gui.getZoom() / gui.getRangeStep() + gui.getPlaneHeight() / 2 - RADIUS / 2, RADIUS, RADIUS);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public Ellipse2D.Double getPoint() {
        return point;
    }
    
    public RootType getRootType() {
        return rootType;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode();
    }

   @Override
    public boolean equals(Object object) {
        return object.hashCode() ==  Double.valueOf(x).hashCode();
    }

    @Override
    public String toString() {
        double xZero = (x == 0) ? 0 : x;
        double yZero = (y == 0) ? 0 : y;
        return getRootType().getPointName() + ": (" + xZero + ", " + yZero + ")";
    }
    
    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(rootType.getPointColor());
        graphics2D.fill(point);
    }

}