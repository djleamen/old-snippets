// Define a class, BST, that represents a binary search tree.

import java.util.ArrayList;
import java.util.List;

public class BST {
  // BST Attributes
  private Node root;

  // Node class to represent each element in the tree
  static class Node {
    // Node attributes
    int data;
    Node left;
    Node right;

    // Constructor
    public Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  // Constructor for the BST
  public BST() {
    this.root = null;
  }

  // Insertion method for adding a value to the tree
  public void insert(int data) {
    this.root = insertRecursive(this.root, data);
  }

  // Helper method for recursive insertion
     // reminder from michael: all the recursive functions return the root
  private Node insertRecursive(Node node, int data) {
    // if spot found, insert data as new node
    if (node == null) {
      return new Node(data);
    }
    //otherwise check if the data is less than or greater than the current value
    //and insert recursively
    if (data < node.data) {
      node.left = insertRecursive(node.left, data);
    }
    else if (data > node.data) {
      node.right = insertRecursive(node.right, data);
    }
    //return the root node
    return node;
  }

  // Depth-First Search (DFS) method for searching a specific value
  public boolean dfsSearch(int target) {
    //begin recursive search at the root
    return dfsSearchRecursive(this.root, target);
  }

  // Recursive DFS Search helper method
  private boolean dfsSearchRecursive(Node node, int target) {
    //if null reached, it is not found in this branch (our base case)
    if (node == null) {
      return false;
    }
    // Check if the current node contains the target value
    if (node.data == target) {
      return true;
    }
    // Recursively search in the left or right subtrees based on value
    if (target < node.data) {
      return dfsSearchRecursive(node.left, target);
    }
    return dfsSearchRecursive(node.right, target);
  }

  // In-order Traversal method
  public void inOrderTraversal() {
    //begin traversal at the root
    inOrderRecursive(this.root);
  }

  // Helper method for in-order traversal
  private void inOrderRecursive(Node node) {
    if (node == null) {
      return;
    }
    //if we're not yet at a leaf, perform in order (left, node, right) traversal
    inOrderRecursive(node.left);
    System.out.print(node.data + " ");
    inOrderRecursive(node.right);
  }

  // Deletion method for removing a value from the tree
  public void delete(int data) {
    //set the root to be the result of recursively deleting a value
    this.root = deleteRecursive(this.root, data);
  }

  // Helper method for recursive deletion
  private Node deleteRecursive(Node node, int data) {
    //if the node isn't found, return null (base case)
    if (node == null) {
      return null;
    }
    //if the data is less than the node value, search left, if greater, search right, otherwise, you found it
    if (data < node.data) {
      node.left = deleteRecursive(node.left, data);
    } else if (data > node.data) {
      node.right = deleteRecursive(node.right, data);
    } else {
      // Node to be deleted is found
      //handle cases of it having one or no children by simply replacing it with the child
      if (node.left == null) {
         return node.right;
      } else if (node.right == null) {
        return node.left;
      }
      // Node has two children: Get the inorder successor (smallest in the right subtree) and use that as the new value
      node.data = findMin(node.right).data;
      //then delete the in order successor from the right subtree to avoid duplicates
      node.right = deleteRecursive(node.right, node.data);

    }
    //return the node that is now the root of the subtree
    return node;
  }

  // Helper method to find the minimum value in the tree
  private Node findMin(Node node) {
    //keep traversing left until no more children found
    while(node.left != null) {
      node = node.left;
    }
    //return that node
    return node;
  }

  // Balancing method (simplified version)
  public void balance() {
    // Step 1: Store the elements of the BST in a sorted list
    List<Integer> sortedList = new ArrayList<>();
    //helper method call
    storeInOrder(root, sortedList);
    // Step 2: Rebuild the tree by recursively inserting the middle element - helper method
    root = buildBalancedTree(sortedList, 0, sortedList.size()-1);
  }

  // Helper method to perform an in-order traversal and store elements in a sorted list
  private void storeInOrder(Node node, List<Integer> list) {
    //using in-order traversal, but rather than print, add to list
    if (node != null) {
      storeInOrder(node.left, list);
      list.add(node.data);
      storeInOrder(node.right, list);
    }
  }

  // Helper method to rebuild the tree from the sorted list
  private Node buildBalancedTree(List<Integer> sortedList, int start, int end) {
    //base case: if the ends of the list pass each other
    if (start > end) {
      return null;
    }
    // Find the middle element and make it the root
    int mid = (start+end)/2;
    Node node = new Node(sortedList.get(mid));
    // Recursively build the left and right subtrees b providing appropriate side of list
    node.left = buildBalancedTree(sortedList, start, mid - 1);
    node.right = buildBalancedTree(sortedList, mid + 1, end);
    //return the root
    return node;
  }

  // Method to print the tree in a visually hierarchical way
  public void printTree() {
    printTreeRecursive(root, "", true);
  }

  // Helper method to print the tree recursively
  private void printTreeRecursive(Node node, String indent, boolean isLeft) {
    if (node == null) {
      return;
    }

    // Print the current node value with its indentation
    System.out.println(indent + (isLeft ? "L--- " : "R--- ") + node.data);

    // Recursively print the left and right subtrees
    printTreeRecursive(node.left, indent + (isLeft ? "|   " : "    "), true);
    printTreeRecursive(node.right, indent + (isLeft ? "|   " : "    "), false);
  }

  public static void main(String[] args) {
    BST bst = new BST();

    // Insertion
    bst.insert(7);
    bst.insert(4);
    bst.insert(11);
    bst.insert(2);
    bst.insert(5);
    bst.insert(1);
    bst.insert(3);
    bst.insert(10);
    bst.insert(14);
    bst.insert(9);

    bst.printTree();

    // DFS Search
    System.out.println("DFS Search for 3: " + bst.dfsSearch(3)); // true
    System.out.println("DFS Search for 100: " + bst.dfsSearch(100)); // false

    // In-order Traversal
    System.out.print("In-order Traversal: ");
    bst.inOrderTraversal();
    System.out.println();

    // Deletion
    bst.delete(3);
    System.out.println("after Deletion of 3: ");
    bst.printTree();

    /*
    // Balancing
    bst = new BinarySearchTree();
    bst.insert(20);
    bst.insert(10);
    bst.insert(5);
    bst.insert(11);
    bst.insert(2);
    bst.insert(8);
    bst.insert(15);
    bst.insert(14);
    bst.insert(16);
    bst.insert(13);
    bst.insert(17);

    System.out.println("unbalanced tree: ");
    bst.printTree();

    // Balancing
    bst.balance();
    System.out.println("after Balancing: ");
    bst.printTree();
    */
  }
}