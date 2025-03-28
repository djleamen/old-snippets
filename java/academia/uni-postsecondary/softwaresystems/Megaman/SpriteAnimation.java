package softwaresystems.Megaman;
import softwaresystems.Megaman.SpriteGUI;
import softwaresystems.Megaman.KeyBoardListener;

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
