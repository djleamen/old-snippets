/*
 * Description: This is a simple socket server that listens for incoming connections
 * from clients. It accepts a connection, reads a user name from the client, and then
 * enters a loop where it reads messages from the client and sends responses back.
 * The server will continue to run until the client sends the message "exit".
 */

import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        int port = 6000; // Server will listen on this port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Accept client connection
                System.out.println("New client connected");

                // Create input and output streams
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                // Server reads the greeting with name first
                String userName = input.readLine();
                System.out.println("User connected: " + userName);

                // Server keeps reading until client is done
                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    // Server replies include the user name; until the user enters 'exit' as message
                    if ("exit".equalsIgnoreCase(clientMessage)) {
                        break;
                    }
                    System.out.println("Received: " + clientMessage);
                    output.println("Server: " + userName + ", you said - " + clientMessage);
                }

                // Close connection
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}