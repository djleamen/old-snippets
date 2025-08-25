import java.util.LinkedList;
import java.util.Queue;

public class FrogTree {
  public static void main(String[] args) {
    System.out.println("Java Tree of Javan Tree Frogs");
    FrogTree frogTree = new FrogTree();
    frogTree.putFrogsInTree();
    frogTree.printTree(frogTree.root, 0);
  }

  Node root;

  public class Node {
    Frog value;
    Node left;
    Node right;

    Node(Frog value){
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  public void add(Frog value) {
    Node newNode = new Node(value);
    if (root == null) {
      root = newNode; // If the tree is empty, set the new node as root
      return;
    }

    // Use a queue to perform BFS
    Queue<Node> queue = new LinkedList<>();
    queue.add(root); // Start with the root node

    while (!queue.isEmpty()) {
      Node current = queue.poll(); // Get and remove the current node from the queue

      // Check for left child
      if (current.left == null) {
        current.left = newNode; // Add the new node here
        return;
      } else {
        queue.add(current.left); // Otherwise, add the left child to the queue
      }

      // Check for right child
      if (current.right == null) {
        current.right = newNode; // Add the new node here
        return;
      } else {
        queue.add(current.right); // Otherwise, add the right child to the queue
      }
    }
  }

  // Method to print the tree (for testing purposes)
  public void printTree(Node node, int depth) {
    if(node == null) {
      return;
    }
    for(int i = 0; i < depth; i++) {
      System.out.print(" ");
    }
    System.out.println(node.value);
    printTree(node.left, depth+1);
    printTree(node.right, depth+1);
  }

  public void remove(Frog value) {
    root = removeRecursive(root, value);
  }

  // Helper method for recursive removal
  private Node removeRecursive(Node node, Frog value) {
    if (node == null) {
      return null; // Base case: node is not found
    }

    // If the node to be removed is found
    if (node.value == value) {
      // Case 1: Node with two children
      if (node.left != null && node.right != null) {
        // Promote the left child
        Node newNode = node.left;
        // Attach the right child to the left child
        if (newNode.right == null) {
          newNode.right = node.right; // Attach right child directly
        } else {
          // Find the rightmost node of the left child
          Node rightmost = newNode;
          while (rightmost.right != null) {
            rightmost = rightmost.right;
          }
          rightmost.right = node.right; // Attach right child to the rightmost of the left child
        }
        return newNode; // Promote the left child
      } else {
        // Case 2: Node with only one or no children
        return (node.left != null) ? node.left : node.right;
      }
    }

    // Recursively search for the node to remove in left and right subtrees
    node.left = removeRecursive(node.left, value);
    node.right = removeRecursive(node.right, value);

    return node; // Return the current node
  }


  public void putFrogsInTree() {

    Node adam = new Node(new Frog("Adam", "blue"));
    Node bubkins = new Node(new Frog("Bubkins", "green"));
    Node carson = new Node(new Frog("Carson", "green"));
    Node dobby = new Node(new Frog("Dobby", "brown"));
    Node edward = new Node(new Frog("Edward", "brown"));
    Node frog = new Node(new Frog());
    Node gregory = new Node(new Frog("Gregory", "blue"));
    Node happy = new Node(new Frog("Happy", "orange"));
    Node iggy = new Node (new Frog("Iggy", "green"));
    Node joe = new Node(new Frog("Joe", "green"));

    root = adam;
    adam.left = bubkins;
    adam.right = carson;
    bubkins.left = dobby;
    bubkins.right = edward;
    carson.left = frog;
    carson.right = gregory;
    dobby.left = happy;
    dobby.right = iggy;
    frog.left = joe;
  }

}