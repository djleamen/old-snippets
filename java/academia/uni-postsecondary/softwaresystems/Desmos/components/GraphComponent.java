package csci2020u.lab09.components;

import java.awt.Graphics2D;
import csci2020u.lab09.GraphGUI;

public abstract class GraphComponent {

    protected GraphGUI gui;
    
    public GraphComponent(GraphGUI gui) {
        this.gui = gui;
    }
    
    public abstract void draw(Graphics2D graphics2D);
}
