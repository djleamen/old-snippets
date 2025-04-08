// Description: A simple Rock-Paper-Scissors game server that allows two players to connect and play against each other.

import java.io.*;
import java.net.*;
import java.util.concurrent.locks.*;

class RockPaperScissorsServer {
    private static final int PORT = 2222;
    private Socket player1Socket, player2Socket;
    private BufferedReader in1, in2;
    private PrintWriter out1, out2;
    private String choice1, choice2;
    private final Object lock = new Object();

    public static void main(String[] args) {
        new RockPaperScissorsServer().startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started PORT "+PORT+". Waiting for players...");

            player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected");
            out1 = new PrintWriter(player1Socket.getOutputStream(), true);
            in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            out1.println("ID 1");

            player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected");
            out2 = new PrintWriter(player2Socket.getOutputStream(), true);
            in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            out2.println("ID 2");

            out1.println("Both players connected. Make your move: Rock, Paper, or Scissors");
            out2.println("Both players connected. Make your move: Rock, Paper, or Scissors");

            new Thread(() -> handlePlayer(in1, out1, 1)).start();
            new Thread(() -> handlePlayer(in2, out2, 2)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePlayer(BufferedReader in, PrintWriter out, int playerNumber) {
        try {
            while (true) {
                String choice = in.readLine();
                synchronized(lock) {
                    if(playerNumber==1) {
                        choice1 = choice;
                        out2.println("Player 1 has chosen. Waiting for you...");
                    } else {
                        choice2 = choice;
                        out1.println("Player 2 has chosen. Waiting for you...");
                    }
                    // if both players have made their choices
                    if(choice1 != null && choice2 != null) {
                        String result = determineWinner();
                        // broadcast result
                        out1.println(result);
                        out2.println(result);
                        // reset choices for next round
                        choice1 = null;
                        choice2 = null;
                        out1.println("Both players connected. Make your move: Rock, Paper, or Scissors");
                        out2.println("Both players connected. Make your move: Rock, Paper, or Scissors");
                    }
                } // end of synchronized block
            }
        } catch (IOException e) {
            out.println("Connection lost.");
        }
    }

    private String determineWinner() {
        if (choice1.equals(choice2)) {
            return "It's a tie!";
        } else if ((choice1.equals("Rock") && choice2.equals("Scissors")) ||
                (choice1.equals("Scissors") && choice2.equals("Paper")) ||
                (choice1.equals("Paper") && choice2.equals("Rock"))) {
            return "Player 1 wins!";
        } else {
            return "Player 2 wins!";
        }
    }
}