package csci2020u.lab09.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.exit(0);
    }
}