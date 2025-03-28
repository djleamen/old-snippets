// Description: This program demonstrates the use of templates and arrays in C++.

#include <iostream>
#include <string>
#include <array>

using namespace std;

class Student {
    private:
    string name;
    public:
    Student() {
        name = "";
    }
    Student(const string& n) {
        name = n;
    }
    string getName() {
        return name;
    }
};

class Faculty {
    private:
    string name;
    public:
    Faculty() {
        name = "";
    }
    Faculty(const string& n) {
        name = n;
    }
    string getName() {
        return name;
    }
};

// size doesn't work as type int, need size_t
template<typename T, size_t size>
void displayArray(array<T, size>& arr) {
    for (auto& person : arr) {
        cout << person.getName() << endl;
    }
}

int main() {
    array<Student, 5> studentArray = {Student("Elon"), Student("Warren"), Student("Lizzy"), Student("Maria"), Student("Tony")};
    array<Faculty, 3> facultyArray = {Faculty("Prof. Steve"), Faculty("Prof. Bill"), Faculty("Prof. Tim")};

    cout << "List of Students:" << endl;
    displayArray(studentArray);

    cout << "\nList of Faculty:" << endl;
    displayArray(facultyArray);

    return 0;
}