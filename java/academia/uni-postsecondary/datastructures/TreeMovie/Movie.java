/**
 * The Movie class represents a movie with its title, rating, and genre.
 */
public class Movie {
    private String title; // The title of the movie.
    private int rating; // The rating of the movie.
    private String genre; // The genre of the movie.

    /**
     * Constructs a Movie object with the specified title, rating, and genre.
     *
     * @param title   the title of the movie
     * @param rating the rating of the movie
     * @param genre  the genre of the movie
     */
    public Movie(String title, int rating, String genre) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title the title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the rating of the movie.
     *
     * @return the rating of the movie
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the movie.
     *
     * @param rating the rating of the movie
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the genre of the movie.
     *
     * @return the genre of the movie
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param genre the genre of the movie
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns a string representation of the Movie object.
     *
     * @return a string representation of the Movie object
     */
    @Override
    public String toString() {
        return title + " [" + genre + "] (" + rating + ")";
    }
}
