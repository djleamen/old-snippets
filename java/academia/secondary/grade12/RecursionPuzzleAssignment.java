// Description: This program contains a recursive method that determines if a board is solvable. 
// It uses a boolean array to track visited elements and returns true if the board is solvable, 
// false if it is not. The main method asks for user input and then calls the tracker method to 
// determine if the board is solvable. The result is then printed to the console.

public class RecursionPuzzleAssignment {
// Solvable method using boolean[] 'visited' to track visited elements
private static boolean Solvable(int start, int[] board, boolean[] visited) {
    if (start < 0 || start >= board.length || visited[start]) return false; // invalid
    if (board[start] == 0) return true; // index start is 0, valid
    visited[start] = true;  // mark element as visited
    int n = board[start]; // where n is the integer stored at your current location in the array, determines how to jump to next element
    boolean result = Solvable(start + n, board, visited) || Solvable(start - n, board, visited); // jump forwards or backwards by n from current index
    visited[start] = false;  // unmark element as visited
    return result;
}
// Tracker method, passes board to Solvable and back to main
    public static boolean Track(int start, int[] board) {
    boolean[] visited = new boolean[board.length];
    return Solvable(start, board, visited);
}
    public static void main(String[] args) {
        int[] board = { // enter board here
        };
        int start = 0;
        boolean result = Track(start, board);
        System.out.println(result);  
    }
}
