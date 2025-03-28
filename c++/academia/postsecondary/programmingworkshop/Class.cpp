// Description: This program demonstrates the use of classes in C++. 
// It defines a Person class with attributes and methods to describe a person.

#include <iostream>

using namespace std;

class Person {
    public:
    string firstName;
    string lastName;
    int age;
    bool isEmployed;
    bool isStudent;
    
    string description() {
        return "First name: " + firstName + ", Last name: " + lastName + ", Age: " + to_string(age) + ", Employed?: " + (isEmployed ? "true" : "false") + ", Student?: " + (isStudent ? "true" : "false");
    }
    
    void employable() {
        if (this->age < 16 || this->age > 65) {
            this->isEmployed = false;
        }
    }
};

Person makePerson(string firstName, string lastName, int age, bool isEmployed, bool isStudent) {
    Person me;
    me.firstName = firstName;
    me.lastName = lastName;
    me.age = age;
    me.isEmployed = isEmployed;
    me.isStudent = isStudent;
    return me;
}
    

int main() {
    Person me = makePerson("DJ", "Leamen", 18, false, true);
    cout << me.description();
    
    /*
    int pAge* = me.age;
    cout << pAge;
    */
}