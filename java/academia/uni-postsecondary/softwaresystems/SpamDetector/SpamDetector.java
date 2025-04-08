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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import static java.lang.Math.*;
import java.nio.charset.StandardCharsets;


public class SpamDetector {

    private Map<String, Integer> spamWordCount;
    private Map<String, Integer> hamWordCount;

    // Probability map: P_r(S|W_i)
    private Map<String, Double> probSpamGivenWord;

    private int totalSpamFiles;
    private int totalHamFiles;

    // Constructor
    public SpamDetector() {
        spamWordCount = new HashMap<>();
        hamWordCount  = new HashMap<>();
        probSpamGivenWord = new HashMap<>();
        totalSpamFiles = 0;
        totalHamFiles = 0;
    }

    // Train the spam detector by reading the training data - look for "train/spam" and "train/ham" 
    public void train(File mainDirectory) {
        File trainSpamFolder = new File(mainDirectory, "train/spam");
        File trainHamFolder  = new File(mainDirectory, "train/ham");
        File trainHam2Folder = new File(mainDirectory, "train/ham2");

        // spam
        if (trainSpamFolder.exists() && trainSpamFolder.isDirectory()) {
            File[] spamFiles = trainSpamFolder.listFiles();
            if (spamFiles != null) {
                totalSpamFiles = spamFiles.length;
                for (File f : spamFiles) {
                    if (f.isFile()) {
                        Set<String> wordsInFile = parseFile(f);
                        for (String w : wordsInFile) {
                            spamWordCount.put(w, spamWordCount.getOrDefault(w, 0) + 1);
                        }
                    }
                }
            }
        }

        // ham
        if (trainHamFolder.exists() && trainHamFolder.isDirectory()) {
             File[] hamFiles = trainHamFolder.listFiles();
            if (hamFiles != null) {
                totalHamFiles += hamFiles.length;
                for (File f : hamFiles) {
                    if (f.isFile()) {
                        Set<String> wordsInFile = parseFile(f);
                        for (String w : wordsInFile) {
                            hamWordCount.put(w, hamWordCount.getOrDefault(w, 0) + 1);
                        }
                    }
                }
            }
        }

        // ham2
        if (trainHam2Folder.exists() && trainHam2Folder.isDirectory()) {
            File[] ham2Files = trainHam2Folder.listFiles();
            if (ham2Files != null) {
                totalHamFiles += ham2Files.length;
                for (File f : ham2Files) {
                    if (f.isFile()) {
                        Set<String> wordsInFile = parseFile(f);
                        for (String w : wordsInFile) {
                            hamWordCount.put(w, hamWordCount.getOrDefault(w, 0) + 1);
                        }
                    }
                }
            }
        }

        // Compute P_r(S|W_i) for each word encountered in the training data
        Set<String> allWords = new HashSet<>();
        allWords.addAll(spamWordCount.keySet());
        allWords.addAll(hamWordCount.keySet());

        for (String w : allWords) {
            double p_w_s = 0.0;
            double p_w_h = 0.0;
            int spamCount = spamWordCount.getOrDefault(w, 0);
            int hamCount  = hamWordCount.getOrDefault(w, 0);
            if (totalSpamFiles > 0) {
                p_w_s = (double) spamCount / (double) totalSpamFiles;
            }
            if (totalHamFiles > 0) {
                p_w_h = (double) hamCount / (double) totalHamFiles;
            }
            double p_s_w;
            if ((p_w_s + p_w_h) == 0.0) {
                p_s_w = 0.5;  
            } else {
                p_s_w = p_w_s / (p_w_s + p_w_h);
            }
            probSpamGivenWord.put(w, p_s_w);
        }
    }

    // Test the spam detector by reading the test data - look for "test/spam" and "test/ham"
    public List<TestFile> test(File mainDirectory) {
        List<TestFile> results = new ArrayList<>();

        File testSpamFolder = new File(mainDirectory, "test/spam");
        File testHamFolder  = new File(mainDirectory, "test/ham");

        // spam
        if (testSpamFolder.exists() && testSpamFolder.isDirectory()) {
            File[] spamFiles = testSpamFolder.listFiles();
            if (spamFiles != null) {
                for (File f : spamFiles) {
                    if (f.isFile()) {
                        double spamProb = calculateSpamProbability(f);
                        TestFile tf = new TestFile(f.getName(), spamProb, "spam");
                        results.add(tf);
                    }
                }
            }
        }

        // ham
        if (testHamFolder.exists() && testHamFolder.isDirectory()) {
            File[] hamFiles = testHamFolder.listFiles();
            if (hamFiles != null) {
                for (File f : hamFiles) {
                    if (f.isFile()) {
                        double spamProb = calculateSpamProbability(f);
                        TestFile tf = new TestFile(f.getName(), spamProb, "ham");
                        results.add(tf);
                    }
                }
            }
        }

        return results;
    }

    // Calculate the probability that a file is spam
    private double calculateSpamProbability(File f) {
        Set<String> wordsInFile = parseFile(f);

        double sumLogOneMinusP = 0.0;
        double sumLogP         = 0.0;

        // Process each word in the file and compute the probability
        for (String w : wordsInFile) {
            double p = probSpamGivenWord.getOrDefault(w, 0.5); 
            // to avoid log(0)
            if (p < 0.0000001) p = 0.0000001;
            if (p > 0.9999999) p = 0.9999999;
            sumLogOneMinusP += log(1.0 - p);
            sumLogP         += log(p);
        }

        // compute eta
        double eta = sumLogOneMinusP - sumLogP;
        // compute probability
        double spamProbability = 1.0 / (1.0 + exp(eta));

        return spamProbability;
    }

    // Parse a file and return a set of distinct words
    private Set<String> parseFile(File f) {
        Set<String> wordSet = new HashSet<>();
        try {
            // Explicitly use ISO_8859_1 to avoid encoding issues
            List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.ISO_8859_1);
            for (String line : lines) {
                String[] tokens = line.toLowerCase().split("\\W+");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        wordSet.add(token);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordSet;
    }
}