/*
 * This file contains the implementation of a chat client that connects to a chat server.
 * It allows users to send and receive messages, create threads, and reply to existing threads.
 * The client uses a GUI to display the chat interface and manage user interactions.
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.io.*;
 import java.net.*;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ChatClientApp extends JFrame {
     private String username;
     private Socket socket;
     private PrintWriter out;
     private BufferedReader in;
 
     // GUI components
     private JTextArea liveChatArea;
     private JTextField liveChatField;
     private JTextArea threadsArea;
     private JComboBox<String> userDropdown;
     private JButton sendButton, newThreadButton, replyButton;
 
     // Stores the list of threads and their replies
     private final List<ThreadData> threadList = new ArrayList<>();
 
     // Inner class to hold thread information
     private static class ThreadData {
         int index;
         String title;
         String creator;
         List<String> replies = new ArrayList<>();
     }
 
     public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> new ChatClientApp().startClient());
     }
 
     // Starts the client and prompts for a valid username
     private void startClient() {
         while (true) {
             username = JOptionPane.showInputDialog(null, "Enter your username:", "Username", JOptionPane.PLAIN_MESSAGE);
             if (username == null) return;
             username = username.trim();
             if (username.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                 continue;
             }
             if (username.contains(" ")) {
                 JOptionPane.showMessageDialog(null, "Username cannot contain spaces.");
                 continue;
             }
             break;
         }
 
         try {
             // Connect to the server
             socket = new Socket("localhost", 4829);
             out = new PrintWriter(socket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
             // Send the username to the server
             out.println(username);
 
             // Initialize GUI and start listening for server messages
             setupGUI();
             setTitle(username + "'s Chat Client");
             setVisible(true);
             new Thread(this::listenServer).start();
         } catch (IOException e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Unable to connect to server on port 4829.", "Connection Error", JOptionPane.ERROR_MESSAGE);
         }
     }
 
     // Initializes the GUI components and layout
     private void setupGUI() {
         Font defaultFont = new Font("Ubuntu'", Font.PLAIN, 14);
         Color lightGray = new Color(193,208,181);
         Color borderGray = new Color(115,134,120);
 
         setSize(600, 400);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
 
         JTabbedPane tabbedPane = new JTabbedPane();
 
         // Live Chat Tab
         JPanel liveChatPanel = new JPanel(new BorderLayout(10, 10));
         liveChatPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
 
         liveChatArea = new JTextArea();
         liveChatArea.setEditable(false);
         liveChatArea.setFont(defaultFont);
         liveChatArea.setMargin(new Insets(10, 10, 10, 10));
         liveChatArea.setBackground(new Color (203, 195, 227));
         liveChatArea.setForeground(Color.DARK_GRAY);
         liveChatArea.setBorder(BorderFactory.createLineBorder(borderGray));
         liveChatArea.setCaretColor(Color.BLUE);
 
         JScrollPane scrollPane = new JScrollPane(liveChatArea);
         scrollPane.setBorder(BorderFactory.createEmptyBorder());
 
         liveChatField = new JTextField();
         liveChatField.setFont(defaultFont);
         liveChatField.setBorder(BorderFactory.createCompoundBorder(
                 BorderFactory.createLineBorder(new Color(243,233,215), 3),
                 BorderFactory.createEmptyBorder(5, 8, 5, 8)
         ));
         liveChatField.setBackground(Color.WHITE);
         liveChatField.setForeground(Color.BLACK);
 
         sendButton = new JButton("Send");
         sendButton.setFont(defaultFont);
         sendButton.setFocusPainted(false);
         sendButton.setBackground(new Color(194,207,207));
         sendButton.setForeground(Color.WHITE);
         sendButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
         sendButton.setToolTipText("Send message");
 
         // Panel for send button
         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
         buttonPanel.add(sendButton);
 
         // Dropdown for user selection (for DMs)
         userDropdown = new JComboBox<>();
         userDropdown.setFont(defaultFont);
         userDropdown.setBackground(Color.WHITE);
         userDropdown.setForeground(Color.BLACK);
         userDropdown.addItem("Broadcast");
 
         // Panel for input field and buttons
         JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
         inputPanel.add(userDropdown, BorderLayout.WEST);
         inputPanel.add(liveChatField, BorderLayout.CENTER);
         inputPanel.add(buttonPanel, BorderLayout.EAST);
 
         liveChatPanel.add(scrollPane, BorderLayout.CENTER);
         liveChatPanel.add(inputPanel, BorderLayout.SOUTH);
 
         tabbedPane.addTab("Live Chat", liveChatPanel);
 
         // Threads Tab
         JPanel threadsPanel = new JPanel(new BorderLayout(10, 10));
         threadsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
 
         threadsArea = new JTextArea();
         threadsArea.setEditable(false);
         threadsArea.setFont(defaultFont);
         threadsArea.setBackground(new Color(203, 195, 227));
         threadsArea.setForeground(Color.DARK_GRAY);
         threadsArea.setBorder(BorderFactory.createLineBorder(borderGray));
         threadsArea.setMargin(new Insets(10, 10, 10, 10));
 
         JScrollPane threadsScroll = new JScrollPane(threadsArea);
         threadsScroll.setBorder(BorderFactory.createEmptyBorder());
 
         // Buttons for creating/replying to threads
         replyButton = new JButton("Reply");
         replyButton.setFont(defaultFont);
         replyButton.setBackground(new Color(215, 200, 165));
         replyButton.setForeground(Color.BLACK);
         replyButton.setFocusPainted(false);
         replyButton.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
         replyButton.setToolTipText("Reply to selected message");
 
         newThreadButton = new JButton("New Thread");
         newThreadButton.setFont(defaultFont);
         newThreadButton.setBackground(new Color(184, 167, 158));
         newThreadButton.setForeground(Color.BLACK);
         newThreadButton.setFocusPainted(false);
         newThreadButton.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
         newThreadButton.setToolTipText("Start a new thread");
 
         JPanel threadButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
         threadButtonPanel.add(newThreadButton);
         threadButtonPanel.add(replyButton);
 
         threadsPanel.add(threadsScroll, BorderLayout.CENTER);
         threadsPanel.add(threadButtonPanel, BorderLayout.SOUTH);
 
         tabbedPane.addTab("Threads", threadsPanel);
 
         add(tabbedPane);
 
         // Action listeners for chat and thread functionality
         liveChatField.addActionListener(e -> sendLiveChatMessage());
         sendButton.addActionListener(e -> sendLiveChatMessage());
         newThreadButton.addActionListener(e -> createNewThread());
         replyButton.addActionListener(e -> replyToThread());
     }
 
     // Sends live chat message (DM or broadcast)
     private void sendLiveChatMessage() {
         String msg = liveChatField.getText().trim();
         if (!msg.isEmpty()) {
             String selectedUser = (String) userDropdown.getSelectedItem();
             if (selectedUser != null) {
                 if (!selectedUser.equals("Broadcast")) {
                     out.println("@" + selectedUser + " " + msg); // Private message
                 } else {
                     out.println(msg); // Broadcast message
                 }
             }
             liveChatField.setText("");
         }
     }
 
     // Opens prompt to create a new thread
     private void createNewThread() {
         String title = JOptionPane.showInputDialog(this, "Enter thread title:", "New Thread", JOptionPane.PLAIN_MESSAGE);
         if (title != null && !title.trim().isEmpty()) {
             out.println("/newthread " + title);
         }
     }
 
     // Opens prompt to reply to a specific thread
     private void replyToThread() {
         String indexStr = JOptionPane.showInputDialog(this, "Enter thread index:", "Reply to Thread", JOptionPane.PLAIN_MESSAGE);
         if (indexStr == null) return;
         indexStr = indexStr.trim();
         if (indexStr.isEmpty()) return;
 
         try {
             int threadIndex = Integer.parseInt(indexStr);
             String reply = JOptionPane.showInputDialog(this, "Enter your reply:", "Reply", JOptionPane.PLAIN_MESSAGE);
             if (reply != null && !reply.trim().isEmpty()) {
                 out.println("/reply " + threadIndex + " " + reply);
             }
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "Invalid thread index.", "Error", JOptionPane.ERROR_MESSAGE);
         }
     }
 
     // Listens to incoming messages from the server
     private void listenServer() {
         try {
             String line;
             while ((line = in.readLine()) != null) {
                 if (line.startsWith("LIVE:")) {
                     liveChatArea.append(line.substring("LIVE:".length()) + "\n");
                 } else if (line.startsWith("USERS_UPDATE:")) {
                     updateUserDropdown(line.substring("USERS_UPDATE:".length()));
                 } else if (line.startsWith("THREADS_UPDATE")) {
                     updateThreadsTab();
                 } else {
                     liveChatArea.append(line + "\n");
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             closeConnection();
         }
     }
 
     // Updates the Threads tab with current threads and replies
     private void updateThreadsTab() {
         try {
             threadList.clear();
             threadsArea.setText("");
 
             String line;
             while (!(line = in.readLine()).equals("END_THREADS_UPDATE")) {
                 if (line.startsWith("THREAD|")) {
                     String[] parts = line.split("\\|");
                     int index = Integer.parseInt(parts[1]);
                     String title = parts[2];
                     String creator = parts[3];
 
                     ThreadData thread = new ThreadData();
                     thread.index = index;
                     thread.title = title;
                     thread.creator = creator;
                     threadList.add(thread);
 
                     threadsArea.append("[" + index + "] " + title + " (by " + creator + ")\n");
                 } else if (line.startsWith("REPLY|")) {
                     String[] parts = line.split("\\|", 3);
                     int threadIndex = Integer.parseInt(parts[1]);
                     String reply = parts[2];
 
                     for (ThreadData thread : threadList) {
                         if (thread.index == threadIndex) {
                             thread.replies.add(reply);
                             threadsArea.append("    - " + reply + "\n");
                             break;
                         }
                     }
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     // Updates the dropdown list of users for DMs
     private void updateUserDropdown(String users) {
         SwingUtilities.invokeLater(() -> {
             userDropdown.removeAllItems();
             userDropdown.addItem("Broadcast"); // Default option
             for (String user : users.split(",")) {
                 if (!user.equals(username)) { // Exclude self
                     userDropdown.addItem(user);
                 }
             }
         });
     }
 
     // Closes all connections
     private void closeConnection() {
         try {
             if (in != null) in.close();
             if (out != null) out.close();
             if (socket != null && !socket.isClosed()) socket.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
 