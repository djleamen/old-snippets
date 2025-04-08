/**
 * A class to solve the Towers of Hanoi problem.
 * This class provides the helper methods, however students must implement the actual problem
 * solution by finishing the provided stub methods.
 */
public class TowersOfHanoiLab {

    static int n = 3; // Default number of discs for testing 
    static int moveCount = 0; // Initialize move count
    static int towerWidth = Math.max(n, 11);
    public static final String ANSI_YELLOW = "\u001B[33m"; 
    public static final String ANSI_RESET = "\u001B[0m"; 
    static Tower source = new Tower(centerPad("Tower A", towerWidth));
    static Tower auxiliary = new Tower(centerPad("Tower B", towerWidth));
    static Tower destination = new Tower(centerPad("Tower C", towerWidth));

    /**
     * The main method that sets up the Towers of Hanoi problem and solves it.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Initialize the source tower with n discs
        for (int i = n; i > 0; i--) {
            source.push(new Disc(i)); // Keep numeric widths for internal use
        }

        System.out.println("Example Run - " + n + " Discs\n");
        System.out.println("Initial state:\n");
        printTowers();

        // Solve the puzzle
        moveDiscs(n, source, destination, auxiliary);

        // Display the total move count at the end
        System.out.println("Puzzle solved in " + moveCount + " moves.");
    }

    /**
     * Solves the Towers of Hanoi problem recursively.
     *
     * @param n the number of discs to move
     * @param s the source tower
     * @param d the destination tower
     * @param a the auxiliary tower
     */
    public static void moveDiscs(int n, Tower s, Tower d, Tower a) {
        if (n == 1) {
            // Base case: move single disc from source to destination
            moveTopDisc(s, d);
            System.out.println(); // Extra newline after move
            return;
        }
        
        // Move n-1 discs from source to auxiliary tower
        moveDiscs(n-1, s, a, d);
        
        // Move largest disc from source to destination
        moveTopDisc(s, d);
        System.out.println(); // Extra newline after move
        
        // Move n-1 discs from auxiliary to destination
        moveDiscs(n-1, a, d, s);
    }
    
    
    
    /**
     * Moves the top disc from one tower to another and prints the updated state.
     *
     * @param s the source tower
     * @param d the destination tower
     * @throws IllegalArgumentException if the source or destination towers are null
     */
    public static void moveTopDisc(Tower s, Tower d) {
        if (s == null || d == null) {
            throw new IllegalArgumentException("Source and destination towers cannot be null.");
        }
    
        Disc discToMove = s.pop();
        if (discToMove == null) {
            return; // No disc to move
        }
    
        boolean moveSuccessful = d.push(discToMove);
        if (moveSuccessful) {
            moveCount++; // Increment move count
            String discSymbol = "-".repeat(discToMove.getWidth()); // Visualize disc
            System.out.println(ANSI_YELLOW + "Move " + moveCount + ": Disc [" + discSymbol + "] from " 
                + s.getName().trim() + " to " + d.getName().trim() + ANSI_RESET);
        } else {
            s.push(discToMove); // Undo if invalid
            System.out.println("Invalid move: Disc is larger than the top disc on " 
                + d.getName().trim());
        }
    
        printTowers();
    }


    /**
     * Centers a string within a given length, padding with spaces as necessary.
     *
     * @param input the string to center
     * @param length the desired total length
     * @return the centered string
     * @throws IllegalArgumentException if the input string is null
     */
    public static String centerPad(String input, int length) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        if (input.length() >= length) {
            return input;
        }

        int totalPadding = length - input.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        return " ".repeat(leftPadding) + input + " ".repeat(Math.max(0, rightPadding));
    }

    /**
     * Prints the current state of all three towers.
     */
    public static void printTowers() {
        System.out.println();
        int max = Math.max(source.getSize(), Math.max(auxiliary.getSize(), destination.getSize()));
        Disc l = source.getTop();
        Disc c = auxiliary.getTop();
        Disc r = destination.getTop();
        for (int i = max; i > 0; i--) {
            l = printTowerDiscOrBlank(source, l, i);
            c = printTowerDiscOrBlank(auxiliary, c, i);
            r = printTowerDiscOrBlank(destination, r, i);
            System.out.println();
        }
        System.out.println(source.getName() + auxiliary.getName() + destination.getName());
        System.out.println();
    }

    /**
     * Prints a disc or blank space for a given tower.
     *
     * @param t the tower
     * @param d the disc to print
     * @param i the current level
     * @return the next disc to print
     */
    private static Disc printTowerDiscOrBlank(Tower t, Disc d, int i) {
        if (t.getSize() >= i) {
            return printDisc(d);
        } else {
            printBlanks();
            return d;
        }
    }

    /**
     * Prints a disc with padding for visual representation.
     *
     * @param disc the disc to print
     * @return the disc below the current disc
     */
    public static Disc printDisc(Disc disc) {
        if (disc == null) {
            printBlanks();
            return null;
        }
        System.out.print(centerPad("-".repeat(disc.getWidth()), towerWidth));
        return disc.getBelow();
    }

    /**
     * Prints blank spaces for visual alignment.
     */
    public static void printBlanks() {
        System.out.print(" ".repeat(towerWidth));
    }

}
