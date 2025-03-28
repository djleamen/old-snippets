package csci2020u.lab10.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ATMGUI extends JFrame {
    private final JButton[] buttons = new JButton[6];
    private final JLabel[] labels = new JLabel[6];
    private final JTextField input;
    private final JLabel screenLabel;

    public ATMGUI() {
        // Frame setup
        setTitle("ATM Interface");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header Panel
        JPanel headerPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight());
            }
        };
        headerPanel.setBackground(Color.decode("#3b4046")); // Dodger Blue
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(-1, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);

        JLabel headerLabel = new JLabel(logo);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);
        makeDraggable(this, headerPanel);

        JPanel footerPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillArc(0, -getHeight(), getWidth(), getHeight() * 2, 0, -180);
            }
        };
        footerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        footerPanel.setBackground(Color.decode("#3b4046"));

        add(footerPanel, BorderLayout.SOUTH);

        // Center Panel (Screen)
        JPanel centerPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.decode("#3b4046"));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(getBackground());
                g2d.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 15, 15);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw border
                g2.setColor(Color.decode("#272a32"));
                g2.setStroke(new BasicStroke(5));
                g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 15, 15);

            }
        };
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.decode("#3c465d"));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        // Screen Area
        screenLabel = new JLabel("<html><div style='text-align: center;'>" + "Welcome to Modern ATM<br>Please choose an option." + "</div></html>");
        screenLabel.setFont(new Font("Monospace", Font.BOLD, 20));
        screenLabel.setForeground(Color.WHITE);
        screenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        screenLabel.setPreferredSize(new Dimension(2, 2));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 150;
        centerPanel.add(screenLabel, c);

        c.gridwidth = 1;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        centerPanel.add(Box.createGlue(), c);
        c.weighty = 0;

        input = new JTextField() {
            @Override
            public void paintComponent(Graphics g) {
                setOpaque(false);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the border with rounded corners
                g2.setStroke(new BasicStroke(3));
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
            }
            @Override
            public Insets getInsets() {
                // Add padding to move text slightly to the right
                return new Insets(5, 10, 5, 5); // Top, Left, Bottom, Right
            }
        };
        input.setFont(new Font("Monospace", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 3;
        c.ipady = 5;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 100, 0, 100);
        centerPanel.add(input, c);

        // Screen Options
        String[] leftOptions = {" ", " ", " "};
        String[] rightOptions = {" ", " ", " "};
        c.ipady = 21;
        c.ipadx = 0;
        c.weightx = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < leftOptions.length; i++) {
            JLabel leftOption = new JLabel(leftOptions[i], JLabel.LEFT) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (isOpaque()) {
                        setOpaque(false);
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(getBackground());

                        g2.fillRect(0, 0, getWidth() / 2, getHeight());
                        g2.fillRoundRect(getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(), 15, 15);
                        super.paintComponent(g);
                    }
                }
            };
            leftOption.setFont(new Font("Monospace", Font.BOLD, 16));
            leftOption.setForeground(i == 2 ? Color.decode("#c81f1b") : Color.decode("#3c465d"));
            leftOption.setPreferredSize(new Dimension(2, 19));
            labels[i] = leftOption;

            c.gridx = 0;
            c.gridy = i + 2;
            c.insets = new Insets(15, 5, 15, 15);
            centerPanel.add(leftOption, c);
        }
        c.weightx = 1;

        for (int i = 0; i < rightOptions.length; i++) {
            JLabel rightOption = new JLabel(rightOptions[i], JLabel.RIGHT) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (isOpaque()) {
                        setOpaque(false);
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        g2.setColor(getBackground());

                        g2.fillRect(getWidth() / 2, 0, getWidth() / 2, getHeight());
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                        super.paintComponent(g);
                    }
                }
            };
            rightOption.setFont(new Font("Monospace", Font.BOLD, 16));
            rightOption.setForeground(Color.decode("#3c465d"));
            rightOption.setPreferredSize(new Dimension(2, 19));
            labels[i + 3] = rightOption;

            c.gridx = 1;
            c.gridy = i + 2;
            c.insets = new Insets(15, 15, 15, 5);
            centerPanel.add(rightOption, c);
        }
        add(centerPanel, BorderLayout.CENTER);

        // Left Button Panel
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new GridBagLayout());
        leftButtonPanel.setBackground(Color.decode("#3b4046"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        leftButtonPanel.add(Box.createGlue(), gbc);

        for (int i = 0; i < 3; i++) {
            JButton button = createSideButton();
            buttons[i] = button;

            gbc.gridy = 4 + i;
            gbc.ipady = 30;
            gbc.ipadx = 40;
            gbc.weighty = 0;
            gbc.insets = new Insets(15, 50, 15, 20);
            gbc.fill = GridBagConstraints.NONE;

            leftButtonPanel.add(button, gbc);
        }
        add(leftButtonPanel, BorderLayout.WEST);

        // Right Button Panel
        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new GridBagLayout());
        rightButtonPanel.setBackground(Color.decode("#3b4046"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        rightButtonPanel.add(Box.createGlue(), gbc);
        gbc.gridwidth = 1;

        for (int i = 0; i < 3; i++) {
            JButton button = createSideButton();
            buttons[i + 3] = button;

            gbc.gridy = 4 + i;
            gbc.ipady = 30;
            gbc.ipadx = 40;
            gbc.weighty = 0;
            gbc.insets = new Insets(15, 20, 15, 50);
            gbc.fill = GridBagConstraints.NONE;

            rightButtonPanel.add(button, gbc);
        }
        add(rightButtonPanel, BorderLayout.EAST);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
        input.setVisible(false);
    }

    // Helper method to create numbered side buttons
    private JButton createSideButton() {
        JButton button = new JButton() { // Prevent default button background painting
            @Override
            protected void paintComponent(Graphics g) {
                setContentAreaFilled(false);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background color
                if (getModel().isPressed()) {
                    g2.setColor(Color.GRAY);
                } else {
                    g2.setColor(getBackground());
                }

                // Draw rounded rectangle
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw border
                g2.setColor(getBackground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

                g2.dispose();
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#acaeac"));
        button.setForeground(Color.WHITE);

        return button;
    }

    public void addActionListener(int index, ActionListener listener, String label) {
        buttons[index].addActionListener(listener);

        if (index < 3) {
            labels[index].setText("     " + label);
        } else {
            labels[index].setText(label + "     ");
        }
        labels[index].setOpaque(true);
    }

    public void removeActionListener(int index) {
        if (buttons[index] != null) {
            for (ActionListener listener : buttons[index].getActionListeners()) {
                buttons[index].removeActionListener(listener);
                labels[index].setText("");
                labels[index].setOpaque(false);
            }
        }
    }

    public void removeAllActionListeners() {
        for (int i = 0; i < buttons.length; i++) {
            removeActionListener(i);
        }
    }

    public interface InputCallback {
        void onInputRead(String input);
    }

    public void getInput(InputCallback callback) {
        for (ActionListener listener : input.getActionListeners()) {
            input.removeActionListener(listener);
        }
        input.setEnabled(true);
        input.setVisible(true);
        input.requestFocusInWindow();

        // Add ActionListener to JTextField to handle Enter key
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = input.getText();
                input.setEnabled(false);
                input.setVisible(false);
                input.setText("");
                input.removeActionListener(this);
                callback.onInputRead(userInput);
            }
        });
    }

    public void disableInput() {
        for (ActionListener listener : input.getActionListeners()) {
            input.removeActionListener(listener);
        }
        input.setEnabled(false);
        input.setVisible(false);
        input.setText("");
    }

    public void setText(String text) {
        screenLabel.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
    }

    private static void makeDraggable(JFrame frame, JComponent component) {
        final Point[] mouseLocation = {null}; // Store the mouse's initial position

        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseLocation[0] = e.getPoint(); // Capture the initial point of click
            }
        });

        component.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseLocation[0] != null) {
                    Point frameLocation = frame.getLocation(); // Get the frame's current position
                    int x = frameLocation.x + e.getX() - mouseLocation[0].x;
                    int y = frameLocation.y + e.getY() - mouseLocation[0].y;
                    frame.setLocation(x, y); // Update the frame's position
                }
            }
        });
    }
}
