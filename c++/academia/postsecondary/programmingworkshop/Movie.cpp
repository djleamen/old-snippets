// Description: This program demonstrates a simple class definition in C++.

#include <iostream>
using namespace std;

class Movie {
    private:
        string title;
    public:
        Movie() { title = "Up"; } //constructor
        string getTitle(); //accessor
        void setTitle(string t); //mutator
        void watchMovie();
};

void Movie::watchMovie(){
    cout << "Wow " << title << " is such a great film" << endl;
}

void main() {
Movie m = Movie();
m.watchMovie();
}