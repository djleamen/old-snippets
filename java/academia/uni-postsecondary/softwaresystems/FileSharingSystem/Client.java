/*
 * CSCI 2020U - Assignment 02
 * DJ Leamen, Sanjith Krishnamoorthy
 * 2025-03-23
 */

 /*
  * This is the client side of the file sharing application. It allows the user to upload and download files to and from the server.
  * The client has a GUI that displays the files in the client's shared folder and the server's shared folder.
  * The user can select a file from either list and upload/download it to/from the server.
  */

  // Import necessary packages
  import javax.swing.*;
  import java.awt.*;
  import java.awt.event.*;
  import java.io.*;
  import java.net.*;
  import java.nio.file.Files;
  import java.nio.file.StandardCopyOption;
  
  public class Client {
      private JFrame frame;
      private JList<String> clientList, serverList;
      private DefaultListModel<String> clientModel, serverModel;
      private static String serverHost;
      private static final int PORT = 8080; // Server port - change if necessary
      private static String clientSharedFolder;
      private static final String SERVER_PASSWORD = "csci2020u"; 
      private JProgressBar progressBar;
      private JLabel statusLabel;
      
      public static void main(String[] args) {
          if (args.length < 2) {
              System.err.println("Usage: java Client <serverHost> <clientSharedFolder>");
              System.exit(1);
          }
          serverHost = args[0];
          clientSharedFolder = args[1];
          SwingUtilities.invokeLater(Client::new);
      }
      
      // Constructor to create the GUI
      public Client() {
          frame = new JFrame("File Sharer");
          frame.setSize(700, 500);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setLayout(new BorderLayout(10, 10));
  
          // Top panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton uploadButton = new JButton("Upload");
        JButton downloadButton = new JButton("Download");
        JButton refreshButton = new JButton("Refresh");
        JButton viewButton = new JButton("View");

        // Add action listeners to the buttons
        uploadButton.addActionListener(e -> initiateUpload());
        downloadButton.addActionListener(e -> initiateDownload());
        refreshButton.addActionListener(e -> refreshLists());
        viewButton.addActionListener(e -> viewFile());

        // Add buttons to the top panel
        topPanel.add(uploadButton);
        topPanel.add(downloadButton);
        topPanel.add(refreshButton);
        topPanel.add(viewButton);
          
          // Status panel with a label and a progress bar
          JPanel statusPanel = new JPanel(new BorderLayout(5, 5));
          statusLabel = new JLabel("Ready");
          progressBar = new JProgressBar();
          progressBar.setIndeterminate(true);
          progressBar.setVisible(false);
          statusPanel.add(statusLabel, BorderLayout.WEST);
          statusPanel.add(progressBar, BorderLayout.EAST);
  
          // File lists panel
          clientModel = new DefaultListModel<>();
          serverModel = new DefaultListModel<>();
          clientList = new JList<>(clientModel);
          serverList = new JList<>(serverModel);
          
          // Add scroll panes to the lists
          JScrollPane clientScroll = new JScrollPane(clientList);
          JScrollPane serverScroll = new JScrollPane(serverList);
          clientScroll.setBorder(BorderFactory.createTitledBorder("Client Files"));
          serverScroll.setBorder(BorderFactory.createTitledBorder("Server Files"));
          
          // Add the lists to a panel
          JPanel listPanel = new JPanel(new GridLayout(1, 2, 10, 10));
          listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
          listPanel.add(clientScroll);
          listPanel.add(serverScroll);
          
          // Add panels to the frame
          frame.add(topPanel, BorderLayout.NORTH);
          frame.add(listPanel, BorderLayout.CENTER);
          frame.add(statusPanel, BorderLayout.SOUTH);
          
          // Refresh the file lists
          refreshLists();
          frame.setVisible(true);

          // fix visual bug where both lists can be selected at the same time
          clientList.addListSelectionListener(e -> {
              if (!e.getValueIsAdjusting() && clientList.getSelectedIndex() != -1) {
                  serverList.clearSelection();
              }
          });
      
          serverList.addListSelectionListener(e -> {
              if (!e.getValueIsAdjusting() && serverList.getSelectedIndex() != -1) {
                  clientList.clearSelection();
              }
          });
      }
      
      // Helper method to update status and progress indicator
      private void setStatus(String message, boolean busy) {
          statusLabel.setText(message);
          progressBar.setVisible(busy);
      }
      
      // Refresh the file lists for both client and server shared folders
      private void refreshLists() {
          setStatus("Refreshing file lists...", true);
          // Refresh client list
          clientModel.clear();
          File clientFolder = new File(clientSharedFolder);
          if (!clientFolder.exists()) {
              clientFolder.mkdirs();
          }
          String[] localFiles = clientFolder.list();
          if (localFiles != null) {
              for (String file : localFiles) {
                  clientModel.addElement(file);
              }
          }
          
          // Refresh server list in a background thread
          SwingWorker<Void, Void> worker = new SwingWorker<>() {
              protected Void doInBackground() {
                  serverModel.clear();
                  // Connect to the server and request the list of files
                  try (Socket socket = new Socket(serverHost, PORT);
                       BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                       BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                      out.write("DIR\n");
                      out.flush();
                      String filename;
                      while ((filename = in.readLine()) != null) {
                          serverModel.addElement(filename);
                      }
                  } catch (IOException e) {
                    // Display an error message if the server is not reachable
                      SwingUtilities.invokeLater(() -> 
                          JOptionPane.showMessageDialog(frame, "Error fetching server files: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
                  }
                  return null;
              }
              
              // Update the status label when the background task is done
              protected void done() {
                  setStatus("Ready", false);
              }
          };
          // Execute the background task
          worker.execute();
      }
      
      // Initiate the upload process using a SwingWorker for background processing
      private void initiateUpload() {
          String selectedFile = clientList.getSelectedValue();
          // If no file is selected, open a file chooser to let the user pick one
          if (selectedFile == null) {
              JFileChooser fileChooser = new JFileChooser();
              fileChooser.setDialogTitle("Select a file to upload");
              int result = fileChooser.showOpenDialog(frame);
              if (result == JFileChooser.APPROVE_OPTION) {
                  File chosenFile = fileChooser.getSelectedFile();
                  // Copy the chosen file to the client's shared folder
                  File destination = new File(clientSharedFolder, chosenFile.getName());
                  // Check if the file already exists locally
                  try {
                      Files.copy(chosenFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                      selectedFile = destination.getName();
                      refreshLists();
                  } catch (IOException ex) {
                      JOptionPane.showMessageDialog(frame, "Error copying file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
              } else {
                  return; // User cancelled selection
              }
          }
          final String fileToUpload = selectedFile;
          // Start a SwingWorker to upload the file in the background
          SwingWorker<Void, Void> uploadWorker = new SwingWorker<>() {
              protected Void doInBackground() {
                // Connect to the server and upload the file
                  setStatus("Uploading " + fileToUpload + "...", true);
                  // Open a socket connection to the server
                  try (Socket socket = new Socket(serverHost, PORT);
                       BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                       BufferedReader fileReader = new BufferedReader(new FileReader(clientSharedFolder + File.separator + fileToUpload))) {
                      out.write("UPLOAD " + fileToUpload + "\n");
                      String line;
                      while ((line = fileReader.readLine()) != null) {
                          out.write(line + "\n");
                      }
                      out.write("EOF\n");
                      out.flush();
                  } catch (IOException e) {
                    // Display an error message if the server is not reachable
                      SwingUtilities.invokeLater(() -> 
                          JOptionPane.showMessageDialog(frame, "Error uploading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
                  }
                  return null;
              }
              
              // Update the status label when the background task is done
              protected void done() {
                  setStatus("Upload complete", false);
                  refreshLists();
              }
          };
          uploadWorker.execute();
      }
      
      // Initiate the download process using a SwingWorker for background processing
      private void initiateDownload() {
          String selectedFile = serverList.getSelectedValue();
          if (selectedFile == null) {
              JOptionPane.showMessageDialog(frame, "Please select a file from the server list to download.", "No file selected", JOptionPane.WARNING_MESSAGE);
              return;
          }
          File destination = new File(clientSharedFolder, selectedFile);
          // Check if the file already exists locally
          if (destination.exists()) {
              int option = JOptionPane.showConfirmDialog(frame, "File already exists locally. Overwrite?", "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
              if (option != JOptionPane.YES_OPTION) {
                  return;
              }
          }
          // Start a SwingWorker to download the file in the background
          final String fileToDownload = selectedFile;
          SwingWorker<Void, Void> downloadWorker = new SwingWorker<>() {
              protected Void doInBackground() {
                // Connect to the server and download the file
                  setStatus("Downloading " + fileToDownload + "...", true);
                  try (Socket socket = new Socket(serverHost, PORT);
                    // Open a socket connection to the server
                       BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                       BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                       BufferedWriter fileWriter = new BufferedWriter(new FileWriter(clientSharedFolder + File.separator + fileToDownload))) {
                      out.write("DOWNLOAD " + fileToDownload + "\n");
                      out.flush();
                      
                      String line;
                      boolean errorOccurred = false;
                      while ((line = in.readLine()) != null) {
                        if (line.startsWith("ERROR")) {
                            // Display an error message if the server encountered an error
                            final String errorMessage = line;
                            SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(frame, errorMessage, "Download Error", JOptionPane.ERROR_MESSAGE));
                            errorOccurred = true;
                            break;
                        }
                          fileWriter.write(line + "\n");
                      }
                      if (!errorOccurred) {
                          fileWriter.flush();
                      }
                  } catch (IOException e) {
                    // Display an error message if the server is not reachable
                      SwingUtilities.invokeLater(() -> 
                          JOptionPane.showMessageDialog(frame, "Error downloading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
                  }
                  return null;
              }
              // Update the status label when the background task is done
              protected void done() {
                  setStatus("Download complete", false);
                  refreshLists();
              }
          };
          downloadWorker.execute();
    }

    private void viewFile() {
        String selectedFile = serverList.getSelectedValue();
    
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(frame, "Please select a file from the server list to view.", "No file selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Prompt for the password using a JPasswordField
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(frame, passwordField, "Enter password to view server file:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (option != JOptionPane.OK_OPTION) {
            return; // User canceled the dialog
        }
    
        String password = new String(passwordField.getPassword());
    
        if (!password.equals(SERVER_PASSWORD)) {
            JOptionPane.showMessageDialog(frame, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Fetch the file content from the server
        SwingWorker<String, Void> viewWorker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                try (Socket socket = new Socket(serverHost, PORT);
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
    
                    // Send the DOWNLOAD command to the server
                    out.write("DOWNLOAD " + selectedFile + "\n");
                    out.flush();
    
                    // Read the file content
                    StringBuilder fileContent = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.startsWith("ERROR")) {
                            throw new IOException(line);
                        }
                        fileContent.append(line).append("\n");
                    }
                    return fileContent.toString();
                }
            }
    
            @Override
            protected void done() {
                try {
                    // Display the file content in a dialog
                    String content = get();
                    JTextArea textArea = new JTextArea(20, 40);
                    textArea.setEditable(false);
                    textArea.setText(content);
    
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(frame, scrollPane, "Viewing File: " + selectedFile, JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Error viewing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    
        viewWorker.execute();
    }
}