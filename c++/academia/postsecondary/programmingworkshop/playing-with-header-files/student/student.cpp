#include "student.h"

Student::Student(double gpa, string id, string name) : gpa(gpa), id(id), name(name) {}

Student higher(Student s1, Student s2) {
    if (s1.gpa > s2.gpa) {
        return s1;
    } else {
        return s2;
    }
}

bool Student::operator==(Student other) {
    return this->id == other.id;
}
