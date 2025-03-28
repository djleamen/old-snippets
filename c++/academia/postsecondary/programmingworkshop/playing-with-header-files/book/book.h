#include <string>
using namespace std;

class Book{
    public:
        string name;
        string year;
        string publisher;
        Book();
        Book(string name, string year, string publisher);   
};

//arr is an array of size-1 Books
Book* resize(Book* arr, int* size);