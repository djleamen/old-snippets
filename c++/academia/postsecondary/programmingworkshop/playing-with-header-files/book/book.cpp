#include "book.h"

Book::Book() {}
Book::Book(string name, string year, string publisher) : name(name), year(year), publisher(publisher) {}

Book* resize(Book* arr, int* size) {
    Book* newArr = new Book[*size + 1];
    for (int i = 0; i < *size; ++i) {
        newArr[i] = arr[i]; 
    }
    delete[] arr;
    (*size)++;
    return newArr;
}
