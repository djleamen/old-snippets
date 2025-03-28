package softwaresystems.Megaman;
import softwaresystems.Megaman.SpriteGUI;
import softwaresystems.Megaman.SliderListener;

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