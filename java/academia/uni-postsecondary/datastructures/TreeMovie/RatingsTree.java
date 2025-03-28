// Description: This class represents a binary search tree of movies based on their ratings.

import java.util.LinkedList;

/**
 * The RatingsTree class represents a binary search tree of movies based on their ratings.
 */
public class RatingsTree {
    public MovieNode root; // The root node of the rating tree.

    /**
     * Constructs a RatingsTree object with the root set to null.
     */
    public RatingsTree() {
        root = null;
    }

    /**
     * Inserts a new movie into the rating tree.
     *
     * @param name   the name of the movie
     * @param rating the rating of the movie
     * @param genre  the genre of the movie
     */
    public void insert(String name, int rating, String genre) {
        root = insertRecursive(root, new MovieNode(new Movie(name, rating, genre)));
    }
    /**
     * Recursively inserts a movie node into the rating tree.
     *
     * @param root the root node of the subtree
     * @param node the movie node to be inserted
     * @return the root node of the modified subtree
     */
    private MovieNode insertRecursive(MovieNode root, MovieNode node) {
		// implement the recursive function to insert a MovieNode into the 
		// appropriate position in the tree (PART 1)

        // if the subtree is empty then new node becomes the root
        if (root == null) {
            return node;
        }
        // compare the ratings to decide the direction
        if (node.getKey() < root.getKey()) {
            root.setLeft(insertRecursive(root.getLeft(), node));
        } else {
            root.setRight(insertRecursive(root.getRight(), node));
        }
        // return the updated subtree root
        return root; 
	}

    
    /**
     * Prints all movies in the rating tree in ascending order of their ratings.
     */
    public void printAll() {
        printAllRecursive(root);
    }

    /**
     * Recursively prints all movies in the rating tree in ascending order of their ratings.
     *
     * @param root the root node of the subtree to be printed
     */
    private void printAllRecursive(MovieNode root) {
		// implement the recursive method to print the tree in order (PART 1)
        if (root != null) {
            // visit left subtree
            printAllRecursive(root.getLeft());
            // print current movie
            System.out.println(root.getValue());
            // visit right subtree
            printAllRecursive(root.getRight());
        }
	}

    /**
     * Finds and prints all movies with a rating equal to or higher than the specified rating.
     *
     * @param rating the minimum rating
     */
    public void findMoviesAtLeast(int rating) {
        // commented out line because it confused me lol
       // LinkedList<Movie> highRatings = new LinkedList<Movie>(findRatingHigher(root, rating));
        LinkedList<Movie> highRatings = new LinkedList<>();
        findRatingHigher(root, rating, highRatings);
        System.out.println("All movies with a rating of " + rating + " or higher:");
        for (Movie movie : highRatings) {
            System.out.println("\t" + movie);
        }
    }

      /**
     * Recursively finds movies with a rating equal to or higher than the specified rating.
     *
     * @param root     the root node of the subtree to be searched
     * @param minRating the minimum rating
     * @return a list of movies with ratings equal to or higher than the specified rating
     */
    private void findRatingHigher(MovieNode root, int minRating, LinkedList<Movie> movies) {
		if (root == null) {
            return;
        }
        // traverse right subtree
        findRatingHigher(root.getRight(), minRating, movies);
        // add the current node if it meets criteria
        if (root.getKey() >= minRating) {
            movies.add(root.getValue());
        }
        // traverse left subtree if needed 
        if (root.getKey() >= minRating) {
            findRatingHigher(root.getLeft(), minRating, movies);
        }
	}
	
	 /**
     * Finds and prints all movies with a rating equal to or lower than the specified rating.
     *
     * @param rating the maximum rating
     */
	public void findMoviesLower(int rating) {
        // also commented out this one...
        // LinkedList<Movie> lowRatings = new LinkedList<Movie>(findRatingLower(root, rating));
        LinkedList<Movie> lowRatings = new LinkedList<>();
        findRatingLower(root, rating, lowRatings);
        System.out.println("All movies with a rating of " + rating + " or lower:");
        for (Movie movie : lowRatings) {
            System.out.println("\t" + movie);
        }
    }

    /**
     * Recursively finds movies with a rating equal to or lower than the specified rating.
     *
     * @param root     the root node of the subtree to be searched
     * @param maxRating the maximum rating
     * @return a list of movies with ratings equal to or lower than the specified rating
     */
    private void findRatingLower(MovieNode root, int maxRating, LinkedList<Movie> movies) {
        if (root == null) {
            return;
        }
        // traverse left subtree 
        findRatingLower(root.getLeft(), maxRating, movies);
    
        // add the current node if it meets the criteria
        if (root.getKey() <= maxRating) {
            movies.add(root.getValue());
        }
    
        // traverse the right subtree if needed
        if (root.getKey() <= maxRating) {
            findRatingLower(root.getRight(), maxRating, movies);
        }
    }

    /**
     * Finds and prints all movies of the specified genre.
     *
     * @param genre the genre to search for
     */
    public void findAllGenre(String genre) {
        // ...
        //LinkedList<Movie> genreMovies = new LinkedList<Movie>(findGenre(root, genre));
        LinkedList<Movie> genreMovies = new LinkedList<>();
        findGenre(root, genre, genreMovies);
        System.out.println("All " + genreMovies.size() + " movies in the " + genre + " genre:");
        for (Movie movie : genreMovies) {
            System.out.println("\t" + movie);
        }
    }

    /**
     * Recursively finds movies of the specified genre.
     *
     * @param root  the root node of the subtree to be searched
     * @param genre the genre to search for
     * @return a list of movies of the specified genre
     */
    private void findGenre(MovieNode root, String genre, LinkedList<Movie> movies) {
        if (root == null) {
            return;
        }
        // traverse left subtree
        findGenre(root.getLeft(), genre, movies);
    
        // check if the current node's genre matches and add it to the list
        if (root.getValue().getGenre().equalsIgnoreCase(genre)) {
            movies.add(root.getValue());
        }
        // traverse right subtree
        findGenre(root.getRight(), genre, movies);
    }

}
