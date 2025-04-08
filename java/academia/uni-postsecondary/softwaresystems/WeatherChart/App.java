/*
 * Description: This is the main class for the WeatherChart application.
 */

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 06");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.add(new WeatherPanel());
        frame.setVisible(true);
    }
}
