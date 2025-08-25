// DJ Leamen - Fractal Assignment
// Upside down tree

import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;

public class Fractal1 extends JPanel{

	int levels = 8;

	public void drawRect(Graphics g, int x, int y, int length, int thickness, int angle, int level) {
		if (level >= 1) {
                    int x2 = (int) (x + Math.cos(Math.toRadians(angle)) * length);
                    int y2 = (int) (y - Math.sin(Math.toRadians(angle)) * length);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(thickness));
                    g2d.drawLine(x, y, x2, y2);
                    int newLength = (int) (length * 0.7);
                    int newThickness = (int) (thickness * 0.7);
                    int newAngle1 = angle + 20;
                    int newAngle2 = angle - 20;
                    drawRect(g, x2, y2, newLength, newThickness, newAngle1, level - 1);
                    drawRect(g, x2, y2, newLength, newThickness, newAngle2, level - 1);
			
		}
	}

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		drawRect(g, 300, 200, 100, 10, -90, levels);

	}

	public static void main(String[] args) {

		Fractal1 panel = new Fractal1();
		JFrame window = new JFrame();
		panel.setBackground(Color.WHITE);

		window.setTitle("Fractal");
		window.setSize(800, 800);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);

	}

}