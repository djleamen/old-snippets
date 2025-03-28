'''
Python Recursion Puzzle Assignment
Comments point out differences between Python and Java as part of assignment
'''

# Starts with the class keyword followed by the class name
# Whitespace is used rather than brackets
class RecursionPuzzleAssignment:
    def solvable(self, start, board, visited):
      # solvable() is defined as a method inside the class 
      # Python has no access modifiers (public, private, etc.), and all are public by default
      # 'self' parameter refers to the current instance of a class; when defining methods 
      # in a class, we need to include 'self' as the first parameter to those methods
        if start < 0 or start >= len(board) or visited[start]:
          # Note the "or" operator instead of "||" 
          # len() function is used for finding length of a string
            return False 
          # True, False must be capitalized in Python
        if board[start] == 0:
            return True  
        visited[start] = True  
      # Calling at array index using square brackets [] is the same as Java
        n = board[start]
      # calling "self.solvable" to call back function recursively
        result = self.solvable(start + n, board, visited) or self.solvable(start - n, board, visited)  
        visited[start] = False  
        return result

    def track(self, start, board):
        visited = [False] * len(board)
        return self.solvable(start, board, visited)

    def main(self):
        board = [] 
        start = 0
        result = self.track(start, board)
        print(result)

puzzle = RecursionPuzzleAssignment()
# Creates an instance of the RecursionPuzzleAssignment class and calls its main() method, 
# which in turn calls the other methods within the class: solvable() and track()  
puzzle.main()
# No need to put these under a main() method like in Java, as main() does not 
# serve as an entry point in Python, classes and instances can be used instead