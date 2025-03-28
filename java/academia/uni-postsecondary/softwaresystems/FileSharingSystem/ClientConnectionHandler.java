/*
 * CSCI 2020U - Assignment 02
 * DJ Leamen, Sanjith Krishnamoorthy
 * 2025-03-23
 */

 /*
  * This is the server side of the file sharing application. It listens for incoming connections from clients 
  * and handles them in separate threads. The server has a shared folder where files can be uploaded and downloaded by clients.
  */

// Import necessary packages
import java.io.*;
import java.net.*;

// This class handles the connection to a single client
public class ClientConnectionHandler implements Runnable {
    private Socket socket;
    private String sharedFolder;
    
    public ClientConnectionHandler(Socket socket, String sharedFolder) {
        this.socket = socket;
        this.sharedFolder = sharedFolder;
    }
    
    // This method is called when the thread is started
    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            // Read the command from the client
            String commandLine = in.readLine();
            if (commandLine == null) {
                socket.close();
                return;
            }
            // Handle the command
            if (commandLine.startsWith("DIR")) {
                File dir = new File(sharedFolder);
                if (!dir.exists() || !dir.isDirectory()) {
                    out.write("ERROR: Shared folder does not exist.\n");
                } else {
                    String[] files = dir.list();
                    if (files != null) {
                        for (String fileName : files) {
                            out.write(fileName + "\n");
                        }
                    }
                }
                out.flush();
            } else if (commandLine.startsWith("UPLOAD")) {
                // Get the filename from the command
                String[] tokens = commandLine.split(" ");
                if (tokens.length < 2) {
                    out.write("ERROR: No filename provided for UPLOAD.\n");
                    out.flush();
                } else {
                    // Write the file to the shared folder
                    String fileName = tokens[1];
                    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(sharedFolder + "/" + fileName))) {
                        String line;
                        while ((line = in.readLine()) != null) {
                            if (line.equals("EOF")) break;
                            fileWriter.write(line + "\n");
                        }
                    } catch (IOException ex) {
                        out.write("ERROR: Failed to write file.\n");
                        out.flush();
                    }
                }
                // Send a response to the client
            } else if (commandLine.startsWith("DOWNLOAD")) {
                String[] tokens = commandLine.split(" ");
                if (tokens.length < 2) {
                    out.write("ERROR: No filename provided for DOWNLOAD.\n");
                    out.flush();
                } else {
                    // Send the file to the client
                    String fileName = tokens[1];
                    File file = new File(sharedFolder, fileName);
                    if (!file.exists() || !file.isFile()) {
                        out.write("ERROR: File not found.\n");
                    } else {
                        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = fileReader.readLine()) != null) {
                                out.write(line + "\n");
                            }
                        } catch (IOException ex) {
                            out.write("ERROR: Failed to read file.\n");
                        }
                    }
                    out.flush();
                }
            }
            socket.close();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}