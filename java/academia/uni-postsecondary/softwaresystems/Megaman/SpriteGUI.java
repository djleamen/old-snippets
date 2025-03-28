package softwaresystems.Megaman;
import softwaresystems.Megaman.SpeedSlider;
import softwaresystems.Megaman.SpriteAnimation;
import softwaresystems.Megaman.TimerListener;

import javax.swing.*;
import java.awt.*;

public class SpriteGUI {
    private final JFrame frame = new JFrame("Sprite Animation");
    private final Timer timer = new Timer(50, new TimerListener(this));

    private final SpriteAnimation spritePanel = new SpriteAnimation(this);
    private final SpeedSlider speedSlider = new SpeedSlider(this);

    private int picNumber = 0;
    private boolean keyHoldRight = false, keyHoldLeft = false, keyHoldUp = false, jumpAction = false;

    public SpriteGUI() {
    frame.add(speedSlider, BorderLayout.NORTH);
    frame.add(spritePanel, BorderLayout.SOUTH);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(5000, 5000);
    frame.pack();
    frame.setLocationRelativeTo(null);

    // Set the initial delay of the timer based on the initial slider value
    int initialSliderValue = 5;
    int initialDelay = Math.max(10, 200 - initialSliderValue * 20);
    timer.setDelay(initialDelay);

    // Request focus on the spritePanel to detect keyboard strokes
    spritePanel.setFocusable(true);
    spritePanel.requestFocusInWindow();
}

    public Timer getTimer() {
        return timer;
    }

    public int getPicNumber() {
        return picNumber;
    }

    public void setPicNumber(int picNumber) {
        this.picNumber = picNumber;
    }

    public boolean isKeyHoldRight() {
        return keyHoldRight;
    }

    public void setKeyHoldRight(boolean keyHoldRight) {
        this.keyHoldRight = keyHoldRight;
    }

    public boolean isKeyHoldLeft() {
        return keyHoldLeft;
    }

    public void setKeyHoldLeft(boolean keyHoldLeft) {
        this.keyHoldLeft = keyHoldLeft;
    }

    public boolean isKeyHoldUp() {
        return keyHoldUp;
    }

    public void setKeyHoldUp(boolean keyHoldUp) {
        this.keyHoldUp = keyHoldUp;
    }

    public boolean isJumpAction() {
        return jumpAction;
    }

    public void setJumpAction(boolean jumpAction) {
        this.jumpAction = jumpAction;
    }

    public SpriteAnimation getSpritePanel() {
        return spritePanel;
    }

    public static void main(String[] args) {
        SpriteGUI spriteGUI = new SpriteGUI();
    }
}