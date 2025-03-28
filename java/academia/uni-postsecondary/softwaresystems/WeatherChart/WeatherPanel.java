import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeatherPanel extends JPanel {
    private static final Color[] PIE_COLOURS = {
        Color.CYAN, Color.YELLOW, Color.ORANGE,
        Color.PINK, Color.GREEN, Color.MAGENTA
    };

    private Map<String, Integer> warningCounts; 

    public WeatherPanel() {
        warningCounts = new HashMap<>();

        try (Scanner sc = new Scanner(new File("weatherwarnings-2015.csv"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 5) {
                    String warningType = parts[5].trim();
                    warningCounts.put(warningType,
                        warningCounts.getOrDefault(warningType, 0) + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String[] warningOrder = {
            "FLASH FLOOD",
            "SEVERE THUNDERSTORM",
            "SPECIAL MARINE",
            "TORNADO"
        };

        int total = 0;
        for (String w : warningOrder) {
            total += warningCounts.getOrDefault(w, 0);
        }

        int centerX = 300;
        int centerY = 100;
        int diameter = 300;
        int startAngle = 0;

        for (int i = 0; i < warningOrder.length; i++) {
            String w = warningOrder[i];
            int count = warningCounts.getOrDefault(w, 0);
            if (total == 0) {
                continue;
            }

            double fraction = (double) count / total;
            int arcAngle = (int) Math.round(fraction * 360.0);

            g.setColor(PIE_COLOURS[i]); 
            g.fillArc(centerX, centerY, diameter, diameter, startAngle, arcAngle);

            startAngle += arcAngle;
        }

        g.setColor(Color.BLACK);
        g.drawOval(centerX, centerY, diameter, diameter);

        int legendX = 50, legendY = 50;
        int boxSize = 15;
        int lineSpacing = 20;

        for (int i = 0; i < warningOrder.length; i++) {
            String w = warningOrder[i];
            g.setColor(PIE_COLOURS[i]);
            g.fillRect(legendX, legendY + i * lineSpacing, boxSize, boxSize);

            g.setColor(Color.BLACK);
            g.drawString(w, 
                         legendX + boxSize + 5, 
                         legendY + i * lineSpacing + (boxSize - 2));
        }
    }
}