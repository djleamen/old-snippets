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

public class Server {
    public static void main(String[] args) {
        final int PORT = 8080; // Port number for the server - change if necessary
        final String SHARED_FOLDER = "server";
        // Create the shared folder if it doesn't exist
        File sharedFolder = new File(SHARED_FOLDER);
        if (!sharedFolder.exists()) {
            if (sharedFolder.mkdirs()) {
                // Create the shared folder
                System.out.println("Created shared folder: " + SHARED_FOLDER);
            } else {
                System.err.println("Failed to create shared folder.");
                return;
            }
        }
        // Start the server
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientConnectionHandler(clientSocket, SHARED_FOLDER)).start();
            }
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}