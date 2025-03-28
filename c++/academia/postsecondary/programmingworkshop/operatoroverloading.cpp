// Description: This program demonstrates the use of operator overloading in C++.

#include <iostream>
#include <cstring>

using namespace std;

// book class with price, genre, and title attributes
class Book {
    private:
    double price;
    char genre; 
    char* title; // ptr to a dynamically allocated array for the book's title

    public:
    // default constructor
    Book() {
        price = 0.0;
        genre = 'U'; 
        title = nullptr;
    }
    
    // parameterized constructor 
    Book(double p, const char* t, char g) {
        if (p > 0 && (g == 'F' || g == 'N' || g == 'S')) {
            price = p;
            genre = g;
            title = new char[strlen(t) + 1]; // allocate memory for title
            strcpy(title, t); // copy the title
        } else {
            price = 0;
            genre = 'U';
            title = nullptr;
        }
    }

    // overload the += operator to increment the book's price
    Book& operator+=(double p) {
        if (p > 0) {
            price += p;
        }
        return *this; 
    }

    // overload the == operator to compare two books
    bool operator==(Book &book) {
        return (price == book.price) && (genre == book.genre) && strcmp(title, book.title) == 0;
    }

    // friend function to allow ostream to access private members of Book
    friend ostream& operator<<(ostream& out, Book &book);
    
    // getter for the book's title
    char* getTitle() {
        return title;
    }
    
    // conversion operator to check if the book's price is positive
    operator bool() { 
        return price > 0; 
    }
    
    void display() {
        cout << "Book | ";
        if (*this) { 
            cout << "Available | ";
            cout << "Price: $" << price << " | Genre: " << genre << " | Title: " << title;
        } else {
            cout << "Unknown";
        }
        cout << endl;
    }
    
    // destructor
    ~Book() {
        delete[] title;
    }
};

// overload the << operator to output book details 
ostream& operator<<(ostream& out, Book &book) {
    book.display();
    return out;
}


int main() {
    Book libraryBooks[] = {
        {25.0, "To Kill a Mockingbird", 'F'},
        {20.0, "Sapiens", 'N'},
        {15.0, "The Martian", 'S'},
        {-10.0, "1984", 'F'}, // invalid
        {30.0, "The Catcher in the Rye", 'F'},
        {-20, "Cosmos", 'S'}, // invalid
    };
    int size = sizeof(libraryBooks) / sizeof(libraryBooks[0]);
    cout << "\nDisplaying Books..." << endl;
    for (int i = 0; i < size; i++) {
        cout << libraryBooks[i];
    }
    
    libraryBooks[1] += 5.0; // Incrementing price of the second book
    cout << "\nDisplaying Books after incrementing price of Book 2..." << endl;
    for (int i = 0; i < size; i++) {
        cout << libraryBooks[i];
    }

    cout << "\nComparing Book 1 and Book 2..." << endl;
    if (libraryBooks[0] == libraryBooks[1]) {
        cout << "Book[0] and Book[1] are similar" << endl;
    } 
    else {
        cout << "Book[0] and Book[1] are not similar" << endl;
    }
    
    return 0;
}