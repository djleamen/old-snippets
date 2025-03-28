#include <string>
#include <iostream>
using namespace std;

class Student{
    double gpa;
    public:
        Student(double gpa, string id, string name); 
        string id;
        string name;
        friend Student higher(Student s1, Student s2);
        bool operator==(Student other);
};