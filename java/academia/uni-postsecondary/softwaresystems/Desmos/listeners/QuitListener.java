/*
 * Description: This class implements an ActionListener that listens for a quit action.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.exit(0);
    }
}