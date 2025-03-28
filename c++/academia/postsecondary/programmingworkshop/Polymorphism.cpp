// Description: This program demonstrates the use of polymorphism in C++.

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Student {
    protected:
    string name;
    char level;
    public:
    Student() : name(""), level('0') {
    } // default constructor
    Student(string n, char l) : name(n), level(l) {
    } // parameterized constructor
    virtual ~Student() {
    } // virtual destructor
    virtual void showDetails() = 0; // pure virtual
    char getLevel() {
        return level;
    }
};

class Undergraduate : public Student {
    private:
    double CGPA;
    public:
    Undergraduate(string n, char l, double cgpa) : Student(n, l), CGPA(cgpa) {
    } // parameterized constructor 
    void showDetails() override { // override showDetails
        cout << "Name: " << name << endl;
        cout << "Level: " << level << endl;
        cout << "CGPA: " << CGPA << endl;
    }
};

class Graduate : public Student {
    private:
    string specialization;
    public:
    Graduate(string n, char l, string s) : Student(n, l), specialization(s) {
    } // parameterized constructor
    void showDetails() override { // override showDetails
        cout << "Name: " << name << endl;
        cout << "Level: " << level << endl;
        cout << "Specialization: " << specialization << endl;
    }
};

void display(vector<Student *> &students) {
    int undergradCount = 0;
    int gradCount = 0;
    for (auto &student : students) {
        student->showDetails();
        // using dynamic_cast for ptr student (easiest w auto) 
        if (dynamic_cast<Undergraduate *>(student)) {
            undergradCount++;
        } else if (dynamic_cast<Graduate *>(student)) {
            gradCount++;
        }
    }
    cout << "Total Graduate Students: " << gradCount << endl;
    cout << "Total Undergraduate Students: " << undergradCount << endl;
}

int main() {
    char choice;
    string name;
    char level;
    vector<Student *> students;
    do {
        cout << "\nEnter Student's Full Name: ";
        getline(cin, name);
        cout << "Enter Level of Student (G = Graduate, U = Undergraduate): ";
        cin >> level;
        level = toupper(level);
        cin.ignore(); // Clear the buffer

        if (level == 'U') {
            double cgpa;
            cout << "Enter CGPA: ";
            cin >> cgpa;
            // Creating Undergraduate object with parameterized constructor
            Undergraduate* undergraduate = new Undergraduate(name, level, cgpa);
            students.push_back(undergraduate);
        } else if (level == 'G') {
            string specialization;
            cout << "Enter Specialization: ";
            getline(cin, specialization);
            // Creating Graduate object with parameterized constructor
            Graduate* graduate = new Graduate(name, level, specialization);
            students.push_back(graduate);
        }

        cout << "\nDo you want to continue (Y/N)? ";
        cin >> choice;
        cin.ignore(); // Clear the buffer
    } while (choice == 'y' || choice == 'Y');

    display(students);

    for (auto& student : students) {
        delete student;
    }
    return 0;
}
