/*
 * Description: This class is part of a simple sprite animation program. It uses Java Swing to create a GUI that displays a sprite image. The image is loaded from the resources folder based on the current picture number, which is managed by a timer and key listener.
 * The class extends JPanel and overrides the paintComponent method to draw the sprite image on the panel. The image is scaled to 5 times its original size for better visibility.
 */

import SpriteGUI;
import KeyBoardListener;

import java.awt.*;
import javax.swing.*;

public class SpriteAnimation extends JPanel {
    private final SpriteGUI gui;

    public SpriteAnimation(SpriteGUI gui) {
        // Adding key listener and starting the timer
        this.gui = gui;
        this.addKeyListener(new KeyBoardListener(gui));
        this.setPreferredSize(new Dimension(300, 300));
        this.setBackground(Color.GRAY);
        gui.getTimer().start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
    
        String imagePath = "/sprites/Megaman/" + gui.getPicNumber() + ".png";
        java.net.URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage();
            int width = image.getWidth(this) * 5;
            int height = image.getHeight(this) * 5;
            g.drawImage(image, 60, 40, width, height, this);
        } else {
            System.err.println("Image not found: " + imagePath);
        }
    }
}
