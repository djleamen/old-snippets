/*
 * Description:
 * This class implements the ChangeListener interface to handle changes in a JSlider.
 * It adjusts the delay of a Timer based on the slider value.
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {
    private final Timer timer;
    private final SpriteGUI gui;

    public SliderListener(Timer t, SpriteGUI gui) {
        this.timer = t;
        this.gui = gui;
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        int sliderValue = source.getValue();

        int delay = Math.max(10, 200 - sliderValue * 20);
        System.out.println("Slider value: " + sliderValue + ", Timer delay: " + delay);
        timer.setDelay(delay);

        // Request focus back to the sprite panel
        gui.getSpritePanel().requestFocusInWindow();
    }
}