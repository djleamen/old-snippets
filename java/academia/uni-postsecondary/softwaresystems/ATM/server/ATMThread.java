package csci2020u.lab10.server;

import java.net.Socket;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ATMThread extends Thread {
    // server vars
    protected String id;
    protected Socket socket;
    protected PrintWriter out = null;
    protected BufferedReader in = null;

    // vars to track the user trying to log in
    protected String attempted_user = null;
    protected String attempted_pass = null;

    // if the user successfully logs in, set to true to enable other commands
    protected boolean auth = false;
    protected String user = null;

    // collection of users, storing their <username, password> but their password is
    // encrypted
    protected HashMap<String, byte[]> users;
    protected HashMap<String, Integer> balances;

    /// list of possible commands

    protected final static String PWD = "PWD"; // password command
    protected final static String UID = "UID"; // username command
    protected final static String NEW = "NEW"; // new user command
    protected final static String DEP = "DEP"; // deposit command
    protected final static String WITH = "WITH"; // withdraw command
    protected final static String VIEW = "VIEW"; // view balance command
    protected final static String LOGOUT = "LOGOUT"; // logout command

    // storing the list of recognized commands
    protected final static String[] COMMANDS = {
            PWD,
            UID,
            NEW,
            DEP,
            WITH,
            VIEW,
            LOGOUT
    };

    // constructor
    public ATMThread(String _id, Socket _socket, HashMap<String, byte[]> _users,
                     HashMap<String, Integer> _balances) {
        // calling the Thread super class
        super();

        // copying over the arguments into the class
        this.id = _id;
        this.socket = _socket;
        this.users = _users;
        this.balances = _balances;

        // establishing a connection to the server
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            this.err("IOException while opening a read/write connection");
        }
    }

    public void run() {
        // initialize interaction
        out.println("Welcome to the ATM Machine");
        out.println("100 Ready");

        // main loop
        while (processCommand())
            ;

        /// closing the thread

        // printing a closing message
        if (user != null) {
            this.log(user + " disconnected");
        } else {
            this.log("Client disconnected");
        }

        // closing the socket
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this function parses a command from the client
     *
     * @return true if the command was valid, false otherwise
     */
    protected boolean processCommand() {
        // the incoming command from the client
        String message;

        // trying to read a message from the client
        try {
            message = in.readLine();
        } catch (IOException e) {
            // there was an error, abort
            this.err("Error reading command from socket.");
            return false;
        }

        // if no command was passed to the server, ignore and continue
        if (message == null) {
            return true;
        }

        // parsing the message received from client, format: "CMD argument(s)"
        StringTokenizer st = new StringTokenizer(message);
        String command = st.nextToken();
        String args = null;
        if (st.hasMoreTokens()) {
            args = message.substring(command.length() + 1);
        }

        // process command using the cmd and the arguments parsed
        return processCommand(command, args);
    }

    /**
     * Method processes the known commands:
     * - UID: receives the username
     * - PWD: checks if sent password matches the user
     * - LOGOUT: stops the thread
     *
     * @return true if the command was valid, false otherwise
     */
    public boolean processCommand(String command, String arguments) {
        // uppercasing the command to feed it into a switch statement
        command = command.toUpperCase();

        // logging the command that was passed to the server
        this.log("Caught command " + command + " from the user");

        // rejecting any command the server doesn't recognize
        if (!isValidCommand(command)) {
            out.println("404 Unrecognized Command: " + command);
            return true;
        }

        // droppping into a switch statement
        switch (command.toUpperCase()) {
            case LOGOUT:
                // grabbing the username from the login exchange
                return logout();
            case UID:
                // grabbing the username from the login exchange
                return processUID(arguments);
            case PWD:
                // grabbing the password from the login exchange
                return processPWD(arguments);
            case NEW:
                // creating a new user
                return processNEW(arguments);
            default:
                // if the user gets here, break and go to the auth section
                break;
        }

        // if the user is authenticated, allow for more commands
        if (auth) {
            this.log("Allowing authorized commands for user: " + user);

            // droppping into a switch statement
            switch (command.toUpperCase()) {
                case VIEW:
                    // grabbing the password from the login exchange
                    return processVIEW();
                case WITH:
                    // grabbing the password from the login exchange
                    return processWITH(arguments);
                case DEP:
                    // creating a new user
                    return processDEP(arguments);
                default:
                    // the user should never get here
                    break;
            }
        } else {
            out.println("401 Unauthenticated user");
            this.log("User tried to send a command without authorization");
        }

        // base case
        return true;
    }

    /// command implementations

    protected boolean processUID(String argument) {
        // accept the incoming username, then prompt for password
        attempted_user = argument;
        out.println("100 Continue");
        return true;
    }

    protected boolean processPWD(String password) {
        // testing if there is a password set
        if (attempted_user == null) {
            out.println("400 No username set when sending password");
            return true;
        }

        // checking the username and password
        byte[] hashed = users.get(attempted_user);
        if (hashed == null) {
            // there is no such user `attempted_user`
            out.println("404 No such user exists");
            return true;
        }

        // matching the password to the stored hash
        byte[] attempted_hash;
        try {
            attempted_hash = MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            // if we catch an error, break out of the main loop and exit
            this.err("Could not find algorithm:\n" + e);
            out.println("500 Internal server error");
            return false;
        }

        // comparing both hashes
        if (isSameHash(hashed, attempted_hash)) {
            // updating internal variables
            auth = true;
            user = attempted_user;

            // logging for the server
            this.log(user + " logged into the server");
            out.println("200 Login successful");
        } else {
            out.println("404 Incorrect login");
        }

        // resetting the attempted vars
        attempted_user = null;
        attempted_pass = null;

        // returning true to accept more commands
        return true;
    }

    protected boolean processNEW(String argument) {
        // the request should be of form: "NEW USERNAME PASSWORD"

        // seeing if the user inputted a proper request
        if (argument == null) {
            this.log("Client gave an empty request.");
            out.println("400 Username or password is invalid");
            return true;
        }

        // parsing username and password from argument
        String username, password;

        try {
            // splitting on space
            String[] parts = argument.split(" ");

            username = parts[0];
            password = parts[1];
        } catch (Exception e) {
            this.log("Client gave an incorrect request");
            out.println("400 Username or password is invalid");
            return true;
        }

        // hashing the password
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            // if we catch an error, break out of the main loop and exit
            this.err("Could not find algorithm:\n" + e);
            out.println("500 Internal server error");
            return false;
        }

        // logging
        this.log("Creating a new account for " + username + " with a balance of zero.");

        // storing the new combination in the hashmap
        users.put(username, hash);
        balances.put(username, 0);

        // replying to the client
        out.println("201 Created");

        // returning
        return true;
    }

    protected boolean processDEP(String argument) {
        if (argument == null) {
            // echoing the amount of money the client has
            if (!processVIEW("100"))
                return false; // breaking if error

            // breaking from function
            return true;
        }

        // breaking early
        if (argument.equalsIgnoreCase("BREAK")) {
            this.log(DEP + " Nothing further from the user.");
            return true;
        }

        // user is depositing with an amount
        Integer amount;
        try {
            amount = Integer.valueOf(argument);
        } catch (NumberFormatException e) {
            this.err(DEP + " User " + user + " didn't give a number.");
            out.println("400 Bad request");
            return false;
        }

        // incrementing the balance by amount
        balances.put(user, balances.get(user) + amount);

        // logging
        this.log("Incremented balance of " + user + " by " + amount);
        out.println("202 " + balances.get(user));

        // returning from function
        return true;
    }

    /**
     * this function handles the user withdrawing money from their account.
     * like in `ATMClient.java`, this is how the function should flow:
     *
     * if the user sends "WITH": (argument is null)
     *  respond with the call from `processVIEW` with a status code of 100
     * if the user sends "WITH BREAK"
     *  log "WITH Nothing further from the user." and return true from the function
     * if the user sends "WITH <amount>"
     *  then try to deduct `<amount>` from the user's balance
     *
     * if the user doesn't have a balance, respond with "500 Internal server error"
     * if the amount isn't a number, respond with "400 Bad request"
     * if the amount is greater than the balance, respond with "400 Bad request"
     *
     * on success, respond with "200 <user's new balance>"
     * and return true from the function
     *
     * @param argument if empty, respond with the user's balance, if non empty, try
     *                 to withdraw `argument` from the user's balance
     *                 if the argument is "BREAK" then return true from the function,
     *                 there are no further requests
     * @see #processDEP(String)
     * @see #processVIEW(String)
     * @see #balances
     * @return
     */
    protected boolean processWITH(String argument) {
        // if no argument is provided, respond with the current balance using a 100 status code.
        if (argument == null) {
            return processVIEW("100");
        }
    
        if (argument.trim().equalsIgnoreCase("BREAK")) {
            // if the user pressed the 'Back' button, log the event and break out.
            this.log("WITH Nothing further from the user.");
            return true;
        }
    
        // attempt to parse the amount from the argument.
        int amount;
        try {
            // parse the argument as an integer
            amount = Integer.valueOf(argument.trim());
        } catch (NumberFormatException e) {
            out.println("400 Bad request");
            return true;
        }
    
        // get the current balance for the user
        Integer balance = balances.get(user);
        if (balance == null) {
            // if the user has no balance, log the error and respond with a 500 status code
            this.err("User " + user + " has no balance.");
            out.println("500 Internal server error");
            return true;
        }
    
        // check if the requested withdrawal exceeds the available balance
        if (amount > balance) {
            out.println("400 Bad request");
            return true;
        }
    
        // deduct the amount from the balance and update the hashmap
        int newBalance = balance - amount;
        balances.put(user, newBalance);
        this.log("Deducted " + amount + " from " + user);
        out.println("202 " + newBalance);
    
        return true;
    }

    protected boolean processVIEW() {
        return processVIEW("200");
    }

    protected boolean processVIEW(String code) {
        // getting balance from the hashmap
        Integer balance = balances.get(user);

        // testing if null
        if (balance == null) {
            this.err("User " + user + " has no balance.");
            out.println("500 Internal server error");
            return false;
        }

        // replying to the client
        out.println(code + " " + balance);

        // returning
        return true;
    }

    protected boolean logout() {
        // returning false so we break out of the main loop
        return false;
    }

    /**
     * this function is a wrapper around System.out.println
     * it prints the thread id + the message
     *
     * @param message the message to print
     */
    protected void log(String message) {
        System.out.println(this.id + ": " + message);
    }

    /**
     * this function is a wrapper around System.err.println
     * it prints the thread id + the message
     *
     * @param message the message to print
     */
    protected void err(String message) {
        System.err.println(this.id + ": " + message);
    }

    /**
     * this function tests if the list contains target
     *
     * @param target the target to look for
     * @return true if found, false otherwise
     */
    protected boolean isValidCommand(String target) {
        for (String e : ATMThread.COMMANDS) {
            if (e.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compares two hashes and returns true if they are the same
     *
     * @param a hash a of length n
     * @param b hash b of length m
     * @return returns true if same, false if different length or values
     */
    protected boolean isSameHash(byte[] a, byte[] b) {
        // testing if both hashes are the same length, if not, return false
        if (a.length != b.length)
            return false;

        // unfortunately, using byte[].equals(byte[]) won't work
        // so we need to iterate over each individual byte
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}