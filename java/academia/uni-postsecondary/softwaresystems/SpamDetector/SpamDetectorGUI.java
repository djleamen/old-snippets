/*
 * CSCI 2020U - Assignment 01
 * DJ Leamen
 * 2025-03-01
 * 
 * This program implements a spam detector using a Naive Bayes classifier.
 * It reads training data from "train/spam" and "train/ham" directories,
 * and tests the classifier using data from "test/spam" and "test/ham" directories.
 * The program calculates the probability of a file being spam based on the words it contains.
 * The training data is used to compute the probability of each word being spam or ham.
 * The test data is then classified based on these probabilities.
 */

 import javax.swing.*;
 import javax.swing.table.DefaultTableModel;
 import java.awt.*;
 import java.io.File;
 import java.util.List;
 

 public class SpamDetectorGUI extends JFrame {
     private JTable table;
     private DefaultTableModel tableModel;
     private JLabel accuracyLabel;
     private JLabel precisionLabel;
 
     // Constructor
     public SpamDetectorGUI() {
         super("Spam Detector");
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setSize(900, 600);
         setLayout(new BorderLayout(10, 10)); 
        // Improved GUI by adding a blue title label
         JLabel titleLabel = new JLabel("CSCI 2020U - Naive Bayes Spam Detector", SwingConstants.CENTER); 
         titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
         titleLabel.setOpaque(true);
         titleLabel.setBackground(new Color(70, 130, 180)); 
         titleLabel.setForeground(Color.WHITE);
         titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
 
         // Add the title label to the top of the frame
         add(titleLabel, BorderLayout.NORTH);
 
         // Create a table to display the results
         String[] columnNames = {"File", "Actual Class", "Spam Probability"};
         tableModel = new DefaultTableModel(columnNames, 0);
         table = new JTable(tableModel);
         table.setRowHeight(22);
         table.setFont(new Font("SansSerif", Font.PLAIN, 14));
         table.setShowGrid(true);
         table.setGridColor(Color.LIGHT_GRAY);
         JScrollPane scrollPane = new JScrollPane(table);
 
         // Add the table to the center of the frame
         add(scrollPane, BorderLayout.CENTER);

         // Create a panel for the accuracy and precision labels
         JPanel bottomPanel = new JPanel();
         bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
         bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
         accuracyLabel = new JLabel("Accuracy: ");
         precisionLabel = new JLabel("Precision: ");
         accuracyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
         precisionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
         accuracyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         precisionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         bottomPanel.add(accuracyLabel);
         bottomPanel.add(precisionLabel);
 
         // Add the panel to the bottom of the frame
         add(bottomPanel, BorderLayout.SOUTH);
 
        // Train the spam detector and test it
         SpamDetector detector = new SpamDetector();
 
         File mainDirectory = new File("src/main/resources/data");
 
         detector.train(mainDirectory);
         List<TestFile> results = detector.test(mainDirectory);
 
         // Build confusion matrix
         int truePos = 0, trueNeg = 0, falsePos = 0, falseNeg = 0;
 
         // Display the results in the table
         for (TestFile tf : results) {
             double prob = tf.getSpamProbability();
             String guess = (prob > 0.5) ? "spam" : "ham";
 
             // Update confusion matrix
             String actual = tf.getActualClass();
             if (guess.equals("spam") && actual.equals("spam")) {
                 truePos++;
             } else if (guess.equals("ham") && actual.equals("ham")) {
                 trueNeg++;
             } else if (guess.equals("spam") && actual.equals("ham")) {
                 falsePos++;
             } else if (guess.equals("ham") && actual.equals("spam")) {
                 falseNeg++;
             }
 
             // Add a row to the table
             tableModel.addRow(new Object[]{
                 tf.getFilename(),
                 tf.getActualClass(),
                 tf.getSpamProbRounded()
             });
         }

         // Calculate and display accuracy and precision
         int total = results.size();
         double accuracy = 0.0;
         double precision = 0.0;
 
         if (total > 0) {
             accuracy = (truePos + trueNeg) / (double) total;
         }
         int guessedSpam = truePos + falsePos;
         if (guessedSpam > 0) {
             precision = truePos / (double) guessedSpam;
         }
 
         accuracyLabel.setText(String.format("Accuracy:  %.4f", accuracy));
         precisionLabel.setText(String.format("Precision: %.4f", precision));
     }
 
     // Main method
     public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
             SpamDetectorGUI gui = new SpamDetectorGUI();
             gui.setVisible(true);
         });
     }
 }