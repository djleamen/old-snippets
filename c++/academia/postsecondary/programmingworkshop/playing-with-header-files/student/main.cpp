#include "student.h"

// test students
int main() {
    Student s1(3.0, "123456789", "DJ");
    Student s2(2.0, "100000000", "Person");

    Student higherGPA = higher(s1, s2);
    cout << "The student with the higher GPA is: " << higherGPA.name << endl;

    if (s1 == s2) {
        cout << "The students have the same ID." << endl;
    } else {
        cout << "The students have different IDs." << endl;
    }

    return 0;
}
