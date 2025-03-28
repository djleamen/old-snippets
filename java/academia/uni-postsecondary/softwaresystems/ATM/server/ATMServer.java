package csci2020u.lab10.server;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ATMServer {
    // protected modifier means these attributes are accessible inside the same
    // class
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ATMThread[] threads = null;
    protected int numClients = 0;

    // vars to be passed into the thread
    protected HashMap<String, byte[]> users = new HashMap<>();
    protected HashMap<String, Integer> balances = new HashMap<>();

    // vars
    public final static int SERVER_PORT = 16789;
    public final static int MAX_CLIENTS = 25;

    public ATMServer() {
        /// setup

        // admin username (the password is the same)
        String admin_user = "admin";

        // hashing default users
        try {
            // hashing the password, you should NEVER under any circumstance, store any type
            // of password, yours or another person's as plaintext
            byte[] admin_pass = MessageDigest.getInstance("MD5").digest(admin_user.getBytes(StandardCharsets.UTF_8));

            // default user & pass is: admin, admin
            users.put(admin_user, admin_pass);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not find algorithm:\n" + e);
        }

        // adding default balances
        balances.put("admin", 10000);

        /// trying to launch the server

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            threads = new ATMThread[MAX_CLIENTS];

            // setup
            System.out.println("Listening to PORT " + SERVER_PORT);
            System.out.println("Waiting for clients to connect: up-to " + MAX_CLIENTS + " clients in total are allowed.");

            // keep listening to the SERVER_PORT, create a thread for each Client connection
            while (true) {
                String threadName = "Client #" + (numClients + 1);

                clientSocket = serverSocket.accept();
                System.out.println(threadName + " connected.");
                threads[numClients] = new ATMThread(threadName, clientSocket, users, balances);
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException e) {
            // cannot create socket, aborting
            System.err.println("IOException while creating server connection. Total connections used to date:" + numClients);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // main program loop
        new ATMServer();
    }
}