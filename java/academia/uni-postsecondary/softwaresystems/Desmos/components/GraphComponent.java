/*
 * Description: This class represents a component of the graph 
 * and serves as an abstract base class for all graph components.
 * It provides a constructor that takes a GraphGUI object and
 * an abstract method for drawing the component using a Graphics2D object.
 */

import java.awt.Graphics2D;
import GraphGUI;

public abstract class GraphComponent {

    protected GraphGUI gui;
    
    public GraphComponent(GraphGUI gui) {
        this.gui = gui;
    }
    
    public abstract void draw(Graphics2D graphics2D);
}
