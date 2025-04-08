// Description: A simple bookshelf class that uses a HashMap to store books.

import java.util.HashMap;

public class HashBookShelf {

    HashMap<Integer, Book> books = new HashMap<Integer, Book>();

    public void addBook(Book b) {
        books.put(b.hashCode(), b);
    }

    public void removeBook(int key) {
        books.remove(key); 
    }

    public boolean contains(int key) {
        return books.containsKey(key);
    }

    public int bookCount() {
        return books.size();
    }

    private static class Book {
        String title;
        String author;;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        @Override
        public int hashCode() {
            return author.length() * title.length();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Book book = (Book) obj;
            return title.equals(book.title) && author.equals(book.author);
        }

        @Override
        public String toString() {
            return title + " by " + author;
        }
    }

    public static void main(String[] args) {
        Book a = new Book("1984", "George Orwell");
        Book b = new Book("Interview With the Vampire", "Anne Rice");
        Book c = new Book("The Handmaid's Tale", "Margaret Atwood");

        HashBookShelf bookDirectory = new HashBookShelf();
        bookDirectory.addBook(a);
        bookDirectory.addBook(b);
        bookDirectory.addBook(c);

        System.out.println("Amount of books in directory: " + bookDirectory.bookCount());
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());

        bookDirectory.removeBook(a.hashCode());
        System.out.println("Removed book A");
        System.out.println("Amount of books in directory: " + bookDirectory.bookCount());

        System.out.println(bookDirectory.contains(b.hashCode()));

        System.out.println(a.equals(b));


    }
}