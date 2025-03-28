// Description: This program demonstrates the use of inheritance and operator overloading in C++.

#include <iostream>
#include <cstring>
using namespace std;

class LibraryItem {
    private:
    char * title;
    int year;
    public:
    LibraryItem() {
        this->title = nullptr;
        this->year = 0;
    }
    
    LibraryItem(const char* title, int year) {
        this->title = new char[strlen(title) + 1];
        strcpy(this->title, title);
        this->year = year;
    }
    
    // copy constructor
    LibraryItem(const LibraryItem& other) {
        if (other.title != nullptr) {
            this->title = new char[strlen(other.title) + 1];
            strcpy(this->title, other.title);
        } else {
            this->title = nullptr;
        }
        this->year = other.year;
    }
    
    // opr overloading
    LibraryItem& operator=(const LibraryItem& other) {
        if (this != &other) {
            delete[] this->title;
            if (other.title != nullptr) {
                this->title = new char[strlen(other.title) + 1];
                strcpy(this->title, other.title);
            } else {
                this->title = nullptr;
            }
            this->year = other.year;
        }
        return *this;
    }
    
    ~LibraryItem() {
        delete[] this->title;
    }

    virtual void display() {
        cout << "Title: " << this->title << " (" << this->year << ")"<<endl;
    }
};

class Book : public LibraryItem {
    private:
    char* author;
    public:
    // param constructor
    Book(const char* title, int year, const char* author) : LibraryItem(title, year) {
        this->author = new char[strlen(author) + 1];
        strcpy(this->author, author);
    }
    // copy constructor
    Book(const Book& other) : LibraryItem(other) {
        if(other.author != nullptr) {
            this->author = new char[strlen(other.author) + 1];
            strcpy(this->author, other.author);
        } else {
            this->author = nullptr;
        }
    }
    
    Book& operator=(const Book& other) {
     if (this != &other) {
         LibraryItem::operator=(other); // Call parent’s assignment operator
         delete[] this->author;
         if (other.author != nullptr) {
             this->author = new char[strlen(other.author) + 1];
             strcpy(this->author, other.author);
         } else {
             this->author = nullptr;
         }
     }
        return *this;
    }
    
    void display() {
        LibraryItem::display();
        cout << "Author: " << this->author << endl << endl;
    }
    
    ~Book() {
        delete[] this->author;
    }
};

// global display
void display(Book * items[], int size) {
    for (int i = 0; i < size; ++i) {
        items[i]->display();
    }
}

int main() {
    const int SIZE = 5;
    
    Book* books[SIZE] = {
        new Book("The Catcher in the Rye", 1951, "J.D. Salinger"),
        new Book("The Hobbit", 1937, "J.R.R. Tolkien"),
        new Book("Pride and Prejudice", 1813, "Jane Austen"),
        new Book("1984", 1949, "George Orwell"),
        new Book("The Great Gatsby", 1925, "F. Scott Fitzgerald")
        };

    display(books, SIZE);
    
    // Test the copy constructor
    Book* copiedBook = new Book(*books[0]);
    
    cout << "\nCopied Book:\n";
    copiedBook->display();
    delete copiedBook;
    
    return 0;
}