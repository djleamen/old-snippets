/*
 * Description:
 * This class implements the ActionListener interface and is used to handle
 * timer events for the SpriteGUI. It updates the sprite's position and
 * animation based on the current picture number and key states.
 */

import SpriteGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    private final SpriteGUI gui;

    public TimerListener(SpriteGUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If up key is pressed animation  moves up and down
        int picNumber = gui.getPicNumber();

        if (gui.isKeyHoldUp() && picNumber >= 21 && picNumber <= 23 || picNumber >= 38 && picNumber <= 40) {
            if (picNumber == 23 || picNumber == 40) {
                gui.setKeyHoldUp(false);
                gui.setJumpAction(false);
            }
        }

        // Makes sure if animation needs to be standing still or needs to be moving again
        if (picNumber == 6 || picNumber == 23 && !gui.isKeyHoldRight()) {
            picNumber = 5;
        } else if (picNumber == 24 || picNumber == 40 && !gui.isKeyHoldLeft()) {
            picNumber = 23;
        } else if (picNumber == 16 || picNumber == 23) {
            picNumber = 6;
        } else if (picNumber == 34 || picNumber == 40) {
            picNumber = 24;
        }
        gui.setPicNumber(picNumber + 1);
        this.gui.getSpritePanel().repaint();
    }
}
