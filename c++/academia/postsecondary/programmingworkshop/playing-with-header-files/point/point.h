#include <iostream>
#include <cmath>
using namespace std;

class Point
{
public:
    // properties
    double x;
    double y;

    // constructor
    Point(double, double);

    // methods
    double distance_to(const Point &other);
};

// operator overloading
ostream &operator<<(ostream &os, const Point &obj);