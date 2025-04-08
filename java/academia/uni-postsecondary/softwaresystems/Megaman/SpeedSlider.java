/*
 * Description: 
 * This class creates a slider to control the speed of the animation in the SpriteGUI.
 * It uses a JSlider to allow the user to select a speed from slow to fast.
 * The slider's value is linked to a Timer that controls the animation speed.
 */

import SpriteGUI;
import SliderListener;

import javax.swing.*;
import java.util.Hashtable;

public class SpeedSlider extends JPanel {
    public SpeedSlider(SpriteGUI gui) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);

        JLabel description = new JLabel("Press WASD or arrow keys");
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Drag to change animation speed")));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(slider);
        this.add(description);

        Hashtable<Integer, JLabel> sliderTable = new Hashtable<>();
        sliderTable.put(0, new JLabel("Slow"));
        sliderTable.put(5, new JLabel("Normal"));
        sliderTable.put(10, new JLabel("Fast"));

        slider.addChangeListener(new SliderListener(gui.getTimer(), gui));
        slider.setLabelTable(sliderTable);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }
}