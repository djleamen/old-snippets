// Description: A simple chat server that allows multiple clients to connect and communicate with each other.

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final Set<ClientHandler> clientHandlers = new HashSet<>();
    private static final Map<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final int PORT = 4829;
        System.out.println("Chat server started at port " + PORT);
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            new ClientHandler(serverSocket.accept()).start();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Prompt client for username
                do {
                    username = in.readLine();
                    if (username == null || username.trim().isEmpty() || clients.containsKey(username)) {
                        out.println("Invalid or taken username. Please reconnect with a valid username.");
                        socket.close();
                        return;
                    }
                } while (username == null || username.trim().isEmpty());

                synchronized (clients) {
                    clients.put(username, this);
                }

                // Send list of connected clients and their names
                StringBuilder clientsList = new StringBuilder("Connected users: ");
                synchronized (clients) {
                    for (String user : clients.keySet()) {
                            clientsList.append(user).append(" ");
                    }
                }
                out.println(clientsList.toString());

                // Notify other clients about new user
                synchronized (clientHandlers) {
                    for (ClientHandler handler : clientHandlers) {
                        handler.out.println(username + " has joined the chat!");
                    }
                    clientHandlers.add(this);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(username + ": " + message);
                    // Broadcast message
                    synchronized (clientHandlers) {
                        for (ClientHandler handler : clientHandlers) {
                            if (handler != this) {
                                handler.out.println(username + ": " + message);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientHandlers) {
                    clientHandlers.remove(this);
                }
                synchronized (clients) {
                    clients.remove(username);
                }

                // Notify others when a client leaves
                synchronized (clientHandlers) {
                    for (ClientHandler handler : clientHandlers) {
                        handler.out.println(username + " has left the chat.");
                    }
                }
            }
        }
    }
}

