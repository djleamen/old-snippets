/*
 * Description: This class implements the KeyListener interface to handle keyboard events for the SpriteGUI.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    private final SpriteGUI gui;

    public KeyBoardListener(SpriteGUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gui.isKeyHoldRight() && !gui.isKeyHoldLeft() && !gui.isJumpAction()) {
            if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gui.setKeyHoldRight(true);
                gui.setPicNumber(7);
            } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                gui.setKeyHoldLeft(true);
                gui.setPicNumber(25);
            }
        }

        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            // Move Mega Man up when w or up arrow key is pressed
            if (!gui.isKeyHoldUp()) {
                gui.setKeyHoldUp(true);
                gui.setJumpAction(true);

                if (gui.getPicNumber() <= 16 && gui.getPicNumber() >= 6) {
                    gui.setPicNumber(17);
                } else if (gui.getPicNumber() <= 34 && gui.getPicNumber() >= 24) {
                    gui.setPicNumber(35);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // When key has been released this will make Mega Man stand
        if ((e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) && !gui.isKeyHoldLeft()) {
            gui.setKeyHoldRight(false);

            if (!gui.isJumpAction()) {
                gui.setPicNumber(6);
            }
        } else if ((e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) && !gui.isKeyHoldRight()) {
            gui.setKeyHoldLeft(false);

            if (!gui.isJumpAction()) {
                gui.setPicNumber(24);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
