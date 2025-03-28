#include <iostream>
#include <fstream>
#include <string>
#include "book.h"

int main() {
    ifstream booksFile("books.txt");
    string line;
    int size = 0;
    Book* books = new Book[size];

    while (getline(booksFile, line)) {
        size_t firstComma = line.find(',');
        size_t lastComma = line.rfind(','); // reverse find for last comma occurance  
        string name = line.substr(0, firstComma);
        string year = line.substr(firstComma + 1, lastComma - firstComma - 1);
        string publisher = line.substr(lastComma + 1);
        books = resize(books, &size); 
        books[size - 1] = Book(name, year, publisher);
    }
    booksFile.close();

    for (int i = 0; i < size; ++i) {
        cout << "Book: " << books[i].name << endl;
        cout << "Year: " << books[i].year << endl;
        cout << "Publisher: " << books[i].publisher << endl << endl;
    }

    delete[] books; 
    return 0;
}
