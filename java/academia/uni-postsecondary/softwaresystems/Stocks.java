package softwaresystems;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Stocks extends JPanel {
    private List<Float> stock1;
    private List<Float> stock2;
    private final String label1 = "AAPL";
    private final String label2 = "GOOG";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stock Performance");
        Stocks panel = new Stocks();
        frame.setContentPane(panel);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.loadStockData();
    }

    public Stocks() {
        this.stock1 = new ArrayList<>();
        this.stock2 = new ArrayList<>();
    }

    private void loadStockData() {
        stock1 = downloadStockPrices("aapl");
        stock2 = downloadStockPrices("goog");
        repaint();
    }

    private List<Float> downloadStockPrices(String ticker) {
        List<Float> prices = new ArrayList<>();
        try {
            URL url = new URL("https://raw.githubusercontent.com/OntarioTech-CS-program/w25-lab08-Stock-Datasets/refs/heads/main/data/" + ticker + ".us.csv");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!inputLine.startsWith("Date")) {
                    String[] data = inputLine.split(",");
                    prices.add(Float.parseFloat(data[4]));
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;
        g2d.setColor(Color.BLACK);
        g2d.drawLine(padding, getHeight() - padding, padding, padding);
        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);

        plotLine(g2d, stock1, Color.BLUE, padding, width, height);
        plotLine(g2d, stock2, Color.RED, padding, width, height);

        drawLegend(g2d, new String[]{label1, label2}, new Color[]{Color.BLUE, Color.RED});
    }

    private void plotLine(Graphics2D g2d, List<Float> stock, Color color, int padding, int width, int height) {
        if (stock.isEmpty()) return;
        g2d.setColor(color);

        float maxPrice = stock.stream().max(Float::compare).orElse(1f);
        float minPrice = stock.stream().min(Float::compare).orElse(0f);
        
        int prevX = padding;
        int prevY = getHeight() - padding - (int) ((stock.get(0) - minPrice) / (maxPrice - minPrice) * height);

        for (int i = 1; i < stock.size(); i++) {
            int x = padding + i * width / stock.size();
            int y = getHeight() - padding - (int) ((stock.get(i) - minPrice) / (maxPrice - minPrice) * height);
            g2d.draw(new Line2D.Float(prevX, prevY, x, y));
            prevX = x;
            prevY = y;
        }
    }

    private void drawLegend(Graphics2D g2d, String[] labels, Color[] colors) {
        int x = 80, y = 60, width = 50, height = 20, spacing = 30;
        for (int i = 0; i < labels.length; i++) {
            g2d.setColor(colors[i]);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, width, height);
            g2d.drawString(labels[i], x + 60, y + 15);
            y += spacing;
        }
    }
}