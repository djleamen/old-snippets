// Description: This program reads a file with movie ratings and genres and creates a binary search tree with the movies.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MovieRatings {

    public static  void main(String[] args) throws FileNotFoundException {
        RatingsTree myMovie = new RatingsTree();
        Scanner movies = new Scanner(new File("movies.txt"));
        while (movies.hasNext()){
            int rating = movies.nextInt();
            String genre = movies.next();
            String title = movies.nextLine().trim();
            myMovie.insert(title, rating, genre);
        }
        movies.close();

        System.out.println("All movies, sorted by ranking:");
        myMovie.printAll();

        myMovie.findMoviesAtLeast(90);
        myMovie.findMoviesLower(10);
        myMovie.findAllGenre("Romance");

    }
}
