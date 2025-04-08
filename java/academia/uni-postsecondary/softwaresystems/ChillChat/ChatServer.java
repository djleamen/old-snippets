/*
 * This file contains the implementation of a chat server that allows multiple clients to connect and communicate with each other.
 * It supports private messaging, thread creation, and replies to threads.
 * The server handles client connections, manages user sessions, and broadcasts messages to all connected clients.
 */

 import java.io.*;
 import java.net.*;
 import java.util.*;
 
 public class ChatServer {
     // Set to keep track of all active client handler threads
     private static final Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
 
     // Map to associate usernames with their client handlers
     private static final Map<String, ClientHandler> clients = Collections.synchronizedMap(new HashMap<>());
 
     // List to store discussion threads created in the chat
     private static final List<ChatThread> threads = Collections.synchronizedList(new ArrayList<>());
 
     public static void main(String[] args) {
         final int PORT = 4829;
         System.out.println("Chat server started at port " + PORT);
 
         try (ServerSocket serverSocket = new ServerSocket(PORT)) {
             // Continuously accept new client connections
             while (true) {
                 Socket socket = serverSocket.accept();
                 new ClientHandler(socket).start();
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     // Class to represent a discussion thread
     private static class ChatThread {
         private final String title; // Title of the thread
         private final String creator; // Creator's username
         private final List<String> replies = new ArrayList<>(); // List of replies to this thread
 
         public ChatThread(String title, String creator) {
             this.title = title;
             this.creator = creator;
         }
 
         public String getTitle() {
             return title;
         }
 
         public String getCreator() {
             return creator;
         }
 
         public List<String> getReplies() {
             return replies;
         }
 
         public void addReply(String reply) {
             replies.add(reply);
         }
     }
 
     // Handles communication with a single client
     private static class ClientHandler extends Thread {
         private Socket socket;
         private PrintWriter out;
         private BufferedReader in;
         private String username;
 
         public ClientHandler(Socket socket) {
             this.socket = socket;
         }
 
         @Override
         public void run() {
             try {
                 // Set up input and output streams for communication
                 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 out = new PrintWriter(socket.getOutputStream(), true);
 
                 // Read username from client
                 username = in.readLine();
 
                 // Validate username
                 if (username == null || username.trim().isEmpty() || username.contains(" ") || clients.containsKey(username)) {
                     out.println("LIVE:Invalid or taken username. Usernames cannot contain spaces. Connection closing...");
                     socket.close();
                     return;
                 }
 
                 // Add user to the global lists
                 clients.put(username, this);
                 clientHandlers.add(this);
 
                 // Notify others about new user
                 broadcast("LIVE:" + username + " has joined the chat.");
                 broadcastUserList(); // Update user list for all clients
                 sendThreadsToClient(); // Send existing threads to the new client
 
                 String message;
                 while ((message = in.readLine()) != null) {
                     // Handle different types of messages
                     if (message.startsWith("@")) {
                         handlePrivateMessage(message); // Private message
                     } else if (message.startsWith("/newthread ")) {
                         // Create a new thread
                         String title = message.substring(11).trim();
                         if (!title.isEmpty()) {
                             ChatThread newThread = new ChatThread(title, username);
                             threads.add(newThread);
                             broadcastThreadsUpdate(); // Notify all clients
                         }
                     } else if (message.startsWith("/reply ")) {
                         // Reply to an existing thread
                         String[] parts = message.substring(7).split(" ", 2);
                         if (parts.length == 2) {
                             try {
                                 int threadIndex = Integer.parseInt(parts[0]);
                                 String replyText = parts[1].trim();
                                 if (threadIndex >= 0 && threadIndex < threads.size()) {
                                     ChatThread thread = threads.get(threadIndex);
                                     thread.addReply(username + ": " + replyText);
                                     broadcastThreadsUpdate();
                                 } else {
                                     out.println("LIVE:Invalid thread index.");
                                 }
                             } catch (NumberFormatException e) {
                                 out.println("LIVE:Invalid thread index format.");
                             }
                         }
                     } else {
                         // Regular public message
                         broadcast("LIVE:" + username + ": " + message);
                     }
                 }
 
             } catch (IOException e) {
                 e.printStackTrace();
             } finally {
                 cleanup(); // Clean up after disconnection
             }
         }
 
         // Handles sending private messages
         private void handlePrivateMessage(String message) {
             String targetUser = message.split(" ")[0].substring(1); // Extract username
             ClientHandler targetClient = clients.get(targetUser);
 
             if (targetClient != null) {
                 String privateMessage = message.substring(targetUser.length() + 2); // Extract message
                 targetClient.out.println("PM from " + username + ": " + privateMessage);
                 out.println("PM to " + targetUser + ": " + privateMessage); // Confirmation
             } else {
                 out.println("LIVE:User " + targetUser + " not found.");
             }
         }
 
         // Sends updated list of users to all connected clients
         private void broadcastUserList() {
             StringBuilder userList = new StringBuilder();
             synchronized (clients) {
                 for (String user : clients.keySet()) {
                     userList.append(user).append(",");
                 }
             }
             String userListMessage = "USERS_UPDATE:" + userList.toString().replaceAll(",$", ""); // Remove trailing comma
             synchronized (clientHandlers) {
                 for (ClientHandler ch : clientHandlers) {
                     ch.out.println(userListMessage);
                 }
             }
         }
 
         // Broadcast a message to all clients
         private void broadcast(String msg) {
             synchronized (clientHandlers) {
                 for (ClientHandler ch : clientHandlers) {
                     ch.out.println(msg);
                 }
             }
         }
 
         // Notify all clients about updates to threads
         private void broadcastThreadsUpdate() {
             synchronized (clientHandlers) {
                 for (ClientHandler ch : clientHandlers) {
                     ch.sendThreadsToClient();
                 }
             }
         }
 
         // Sends the list of threads and replies to a specific client
         private void sendThreadsToClient() {
             out.println("THREADS_UPDATE");
             synchronized (threads) {
                 for (int i = 0; i < threads.size(); i++) {
                     ChatThread t = threads.get(i);
                     out.println("THREAD|" + i + "|" + t.getTitle() + "|" + t.getCreator());
                     for (String r : t.getReplies()) {
                         out.println("REPLY|" + i + "|" + r);
                     }
                 }
             }
             out.println("END_THREADS_UPDATE");
         }
 
         // Cleans up user session after disconnecting
         private void cleanup() {
             if (username != null) {
                 broadcast("LIVE:" + username + " has left the chat.");
                 clients.remove(username);
                 clientHandlers.remove(this);
                 broadcastUserList();
             }
             if (out != null) {
                 out.close();
             }
             try {
                 if (in != null) in.close();
                 if (socket != null && !socket.isClosed()) socket.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             clientHandlers.remove(this);
             clients.remove(username);
         }
     }
 }
 