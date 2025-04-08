// Description: ATMClient.java
// This program implements a simple ATM client that connects 
// to a server and allows users to perform various banking operations.

import java.io.*;
import java.net.*;
import java.util.*;

public class ATMClient {
    private Socket socket;
    private PrintWriter networkOut;
    private BufferedReader networkIn;

    // we can read this from the user too
    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 16789;

    // authentication status
    // it's fine if the user manipulates this variable since the server also handles
    // authentication
    // this is just used to limit the commands available to the user
    boolean auth = false;

    private final ATMGUI atmGUI;

    public ATMClient() {
        atmGUI = new ATMGUI();
        /// connecting to the Server
        try {
            // trying to connect to the server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (UnknownHostException e) {
            // catching connection errors
            System.err.println("Unknown host: " + SERVER_ADDRESS);
        } catch (IOException e) {
            // catching connection errors
            System.err.println("IOException while connecting to server: " + SERVER_ADDRESS);
        }

        // aborting if we couldn't establish a connection
        if (socket == null) {
            System.err.println("socket is null");
            System.exit(1);
        }

        // get in and outputstream from the socket/connection
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOException while opening a read/write connection");
            System.exit(1);
        }

        /// reading initial response from the server
        try {
            // reading the first two messages
            atmGUI.setText(networkIn.readLine()); // Welcome to chat
            if (getStatusCode(networkIn.readLine()) != 100) {
                System.out.println("Incorrect greeting from server, aborting");
                System.exit(1);
            }
        } catch (IOException e) {
            // should break since there is an error
            System.err.println("Error reading initial greeting from socket.");
            System.exit(1);
        }
        setUpButtons();
    }

    /**
     * this function processes user input, if a command returns false, break out of
     * the parent loop
     *
     * @see #login()
     * @see #createNewAccount()
     * @see #logout()
     * @see #viewBalance()
     * @see #depositMoney()
     * @see #withdrawMoney()
     */
    protected void processUserInput(int input) {
        // try/catch to if the user doesn't input a number
        try {
            // dropping into a switch statement
            switch (input) {
                case 0:
                    login();
                    break;
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    logout();
                    break;
                case 3:
                    viewBalance();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withdrawMoney();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid command.");
        }
    }

    /**
     * login function
     *
     */
    protected void login() {
        // clearing the auth state if the user decides to log into another account
        auth = false;
        setupBackButton();
        // get username
        atmGUI.setText("Type your username:");
        // reading user input

        ATMGUI.InputCallback passwordCallback = new ATMGUI.InputCallback() {
            @Override
            public void onInputRead(String password) {
                if (password.isEmpty() || password.contains(" ")) {
                    atmGUI.setText("Invalid password");
                    tryReadInput(this);
                } else {
                    String message;
                    // sending the password to the server
                    networkOut.println("PWD " + password);
                    try {
                        message = networkIn.readLine();

                        // parsing the status code
                        if (getStatusCode(message) != 200) {
                            setUpButtons("Login unsuccessful:<br>" + getStatusMessage(message));
                        } else {
                            // login success
                            auth = true;
                            setUpButtons();
                        }
                    } catch (IOException e) {
                        setUpButtons("Error reading response to PWD");
                    }
                }
            }
        };

        ATMGUI.InputCallback usernameCallback = new ATMGUI.InputCallback() {
            @Override
            public void onInputRead(String username) {
                if (username.isEmpty() || username.contains(" ")) {
                    atmGUI.setText("Invalid username");
                    tryReadInput(this);
                } else {
                    String message;
                    // sending the user id to the server
                    networkOut.println("UID " + username);
                    try {
                        message = networkIn.readLine();

                        if (getStatusCode(message) != 100) {
                            setUpButtons("Something went wrong when trying to send the username.<br>Reason: " + getStatusMessage(message));
                            return;
                        }
                    } catch (IOException e) {
                        setUpButtons("Error reading response to UID.");
                        return;
                    }
                    /// get password
                    atmGUI.setText("Type your passcode:");
                    // reading user input
                    tryReadInput(passwordCallback);
                }
            }
        };
        tryReadInput(usernameCallback);
    }

    /**
     * First, show a 'Back' button, if pressed, will cancel the request. Use setupBackButton() (no argument).
     *
     * this function should prompt the user for a new username and password.
     *
     * the function should reject the input if the user inputted a space in either
     * the username or password
     *
     * the function should reject the input if the user didn't input anything (e.g., ' ', '', etc.)
     *
     * if the input is valid send a request of "NEW <username> <password>"
     *
     * on success, the server will return "201 Created"
     * on failure, the server will return "400 Username or password is invalid"
     * if there is a server error, the server will return "500 Internal server
     * error"
     *
     * some functions that will be of use to you. Look at login() for a template.
     *
     * you can nest tryReadinput() to get multiple values. You can copy login() as the starting point.
     *
     * @see #tryReadInput(ATMGUI.InputCallback)
     * @see #login()
     * @see #getStatusCode(String)
     * @see #getStatusMessage(String)
     * @see #setupBackButton() (String)
     */
    protected void createNewAccount() {
        // clearing the auth state if the user decides to log into another account
        setupBackButton();
        atmGUI.setText("Type new username:");
    
        // reading user input
        ATMGUI.InputCallback usernameCallback = new ATMGUI.InputCallback() {
            @Override
            public void onInputRead(String username) {
                // check if username is valid (username should not be empty or contain spaces)
                if (username == null || username.trim().isEmpty() || username.contains(" ")) {
                    atmGUI.setText("Invalid username. Please try again:");
                    tryReadInput(this);
                    return;
                }
                // send the username request to the server
                atmGUI.setText("Type new password:");
                ATMGUI.InputCallback passwordCallback = new ATMGUI.InputCallback() {
                    // this function is called when the user inputs a value
                    @Override
                    public void onInputRead(String password) {
                        if (password == null || password.trim().isEmpty() || password.contains(" ")) {
                            atmGUI.setText("Invalid password. Please try again:");
                            tryReadInput(this);
                            return;
                        }
                        // send the NEW <username> <password> request to the server
                        networkOut.println("NEW " + username + " " + password);
                        try {
                            String response = networkIn.readLine();
                            int code = getStatusCode(response);
                            // check the response code (201 for success)
                            if (code == 201) {
                                setUpButtons("Account created successfully");
                            } else {
                                setUpButtons("Error creating account:<br>" + getStatusMessage(response));
                            }
                        } catch (IOException e) {
                            // error reading response from server
                            setUpButtons("Error reading response from server:<br>" + e);
                        }
                    }
                };
                tryReadInput(passwordCallback);
            }
        };
        tryReadInput(usernameCallback);
    }

    /**
     * this function sends the logout command to the server
     */
    protected void logout() {
        networkOut.println("LOGOUT");

        // aborting program, close the socket
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    protected void viewBalance() {
        // sending prompt to the server
        networkOut.println("VIEW");

        // getting reply from the server
        try {
            // reading the message and status code
            String message = networkIn.readLine();
            int statusCode = getStatusCode(message);

            // reading status code
            if (statusCode == 200) {
                atmGUI.setText("Account balance:<br>$" + getStatusMessage(message));
            } else {
                atmGUI.setText("Error retrieving balance from the server.<br>Reason: " + getStatusMessage(message));
            }

        } catch (IOException e) {
            atmGUI.setText("Error reading information from the server:<br>" + e);
        }
    }

    protected void depositMoney() {
        // sending prompt to the server
        networkOut.println("DEP");

        // responses from the server
        String message;

        // getting reply from the server
        try {
            // reading the message and status code
            message = networkIn.readLine();
            int statusCode = getStatusCode(message);

            // reading status code
            if (statusCode != 100) {
                setUpButtons("Error retrieving balance from the server.<br>Reason: " + getStatusMessage(message));
                return;
            }
        } catch (IOException e) {
            setUpButtons("Error reading information from the server:<br>" + e);
            return;
        }
        setupBackButton("DEP");

        // getting the amount the user wants to deposit
        atmGUI.setText("Enter the amount you would like to deposit<br>Account balance:<br>$" + getStatusMessage(message));
        final Integer[] amount = {null};

        ATMGUI.InputCallback callback = new ATMGUI.InputCallback() {
            @Override
            public void onInputRead(String input) {
                // parsing the integer from the input
                try {
                    amount[0] = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    atmGUI.setText("Enter a valid number");
                }

                if (amount[0] == null) {
                    tryReadInput(this);
                    return;
                }
                // making another request to the server
                networkOut.println("DEP " + amount[0]);

                // getting response from the server
                try {
                    // reading the message and status code
                    String message = networkIn.readLine();
                    int statusCode = getStatusCode(message);

                    // reading status code
                    if (statusCode == 202) {
                        setUpButtons("Account balance:<br>$" + getStatusMessage(message));
                    } else {
                        setUpButtons("Error retrieving balance from the server.<br>Reason: " + getStatusMessage(message));
                    }
                } catch (IOException e) {
                    setUpButtons("Error reading information from the server:<br>" + e);
                }
            }
        };
        tryReadInput(callback);
    }

    /**
     * this function reads input from the user of how much money they would like to
     * withdraw.
     * they shouldn't be able to withdraw more than what they have, the client &
     * server should check this.
     *
     * First, show a 'Back' button, if pressed, send "WITH BREAK" to the server
     * to let it know that there won't be any other requests. Use setupBackButton("WITH").
     *
     * after sending the withdraw request: "WITH", the server should respond with
     * "100 <user-balance>"
     *
     * you'll need to save the <user-balance> into an Integer variable, if there are
     * any server errors or errors parsing the response from the server you'll need to break out of the function.
     *
     * after reading the response from the server, you'll then prompt the client for
     * an amount (that cannot be more than what the server sent).
     *
     * if the user inputs a valid number, send another response to the server of
     * form: "WITH <amount>", the server should
     * check that this amount is less or equal to the balance that is saved.
     *
     * on success, the server should send "200 <new-balance>"
     * if there are any errors (if the amount to withdraw is greater than the
     * balance on file) the server should reply
     * with "400 Bad request"
     *
     * some functions that will be of use to you. You can copy depositMoney() as the starting point.
     *
     * @see #tryReadInput(ATMGUI.InputCallback)
     * @see #depositMoney()
     * @see #getStatusCode(String)
     * @see #getStatusMessage(String)
     * @see #setupBackButton() (String)
     */
    protected void withdrawMoney() {
        // sending prompt to the server
        setupBackButton("WITH");
        networkOut.println("WITH");
        // getting reply from the server

        // responses from the server
        try {
            String response = networkIn.readLine();
            int statusCode = getStatusCode(response);

            if (statusCode != 100) {
                // error retrieving balance
                setUpButtons("Error retrieving balance from the server.<br>Reason: " + getStatusMessage(response));
                return;
            }

            // reading the message and status code
            String balanceStr = getStatusMessage(response);
            int balance;

            // parsing the balance
            try {
                balance = Integer.valueOf(balanceStr);
            } catch (NumberFormatException e) {
                // error parsing balance
                setUpButtons("Error parsing balance from server:<br>" + e);
                return;
            }

            // checking if the balance is valid
            atmGUI.setText("Account balance:<br>$" + balance);
            if (balance == 0) {
                setUpButtons("You cannot withdraw any money; balance is 0.");
                return;
            }

            // prompting the user for the amount to withdraw
            atmGUI.setText("Enter the amount you would like to withdraw<br>Account balance:<br>$" + balance);
            // reading user input
            ATMGUI.InputCallback callback = new ATMGUI.InputCallback() {
                // this function is called when the user inputs a value
                @Override
                public void onInputRead(String input) {
                    int amount;

                    // parsing the integer from the input
                    try {
                        amount = Integer.valueOf(input.trim());
                    } catch (NumberFormatException e) {
                        atmGUI.setText("Enter a valid number");
                        tryReadInput(this);
                        return;
                    }

                    // checking if the amount is valid
                    if (amount > balance) {
                        atmGUI.setText("Amount exceeds available balance. Please enter a valid amount.");
                        tryReadInput(this);
                        return;
                    }
                    // making another request to the server
                    networkOut.println("WITH " + amount);
                    try {
                        // getting response from the server
                        String response = networkIn.readLine();
                        int code = getStatusCode(response);
                        // reading status code (200 for success)
                        if (code == 202) {
                            setUpButtons("Account balance:<br>$" + getStatusMessage(response));
                        } else {
                            setUpButtons("Error withdrawing money.<br>Reason: " + getStatusMessage(response));
                        }
                    } catch (IOException e) {
                        setUpButtons("Error reading response from server:<br>" + e);
                    }
                }
            };
            // reading user input
            tryReadInput(callback);
        } catch (IOException e) {
            setUpButtons("Error reading information from server:<br>" + e);
        }
    }

    /// ------------------------- helper functions -------------------------

    protected int getStatusCode(String message) {
        StringTokenizer st = new StringTokenizer(message);
        String code = st.nextToken();
        return Integer.parseInt(code);
    }

    protected String getStatusMessage(String message) {
        StringTokenizer st = new StringTokenizer(message);
        String code = st.nextToken();
        String errorMessage = null;
        if (st.hasMoreTokens()) {
            errorMessage = message.substring(code.length() + 1);
        }
        return errorMessage;
    }
    private void setUpButtons() {
        setUpButtons(null);
    }

    private void setUpButtons(String message) {
        atmGUI.removeAllActionListeners();
        String[] labels = {
            "Login", "Create Account", "Quit", "View Balance", "Deposit Money", "Withdraw Money"
        };

        for (int i = 0; i < (auth ? 6 : 3); i++) {
            int index = i;

            atmGUI.addActionListener(i, _ -> {
                processUserInput(index);
            }, labels[i]);
        }
        atmGUI.disableInput();

        if (auth) {
            atmGUI.setText("Choose an option<br>" + (message != null ? "<br>" + message : ""));
        } else {
            atmGUI.setText("Welcome to the ATM machine<br>" + (message != null ? "<br>" + message : ""));
        }
    }

    protected void tryReadInput(ATMGUI.InputCallback callback) {
        atmGUI.getInput(callback);
    }

    protected void setupBackButton() {
        setupBackButton(null);
    }

    protected void setupBackButton(String command) {
        atmGUI.removeAllActionListeners();
        atmGUI.addActionListener(2, _ -> {
            if (command != null)
                networkOut.println(command + " BREAK");
            setUpButtons();
        }, "Back");
    }

    public static void main(String[] args) {
        // main
        new ATMClient();
    }
}