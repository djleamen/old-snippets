/**
 * The MovieNode class represents a node in a binary search tree, containing a key and a movie value.
 */
public class MovieNode {
    private final int key; // The key of the movie node.
    private final Movie value; // The movie value associated with the node.
    private MovieNode left, right; // References to the left child and right child nodes.

    /**
     * Constructs a MovieNode object with the specified key and movie value.
     *
     * @param value the movie value associated with the node
     */
    public MovieNode(Movie value) {
        this.key = value.getRating();
        this.value = value;
        this.left = this.right = null;
    }

    /**
     * Gets the left child node of this movie node.
     *
     * @return the left child node
     */
    public MovieNode getLeft() {
        return left;
    }

    /**
     * Gets the right child node of this movie node.
     *
     * @return the right child node
     */
    public MovieNode getRight() {
        return right;
    }

    /**
     * Sets the left child node of this movie node.
     *
     * @param left the left child node to be set
     */
    public void setLeft(MovieNode left) {
        this.left = left;
    }

    /**
     * Sets the right child node of this movie node.
     *
     * @param right the right child node to be set
     */
    public void setRight(MovieNode right) {
        this.right = right;
    }

    /**
     * Gets the movie value associated with this movie node.
     *
     * @return the movie value
     */
    public Movie getValue() {
        return value;
    }

    /**
     * Gets the key of this movie node.
     *
     * @return the key
     */
    public int getKey() {
        return key;
    }
}
