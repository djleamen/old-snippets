#include "point.h"

int main() {
    Point point1(3.5, 4.0);
    Point point2(-5.1, 10.8);

    cout << "Creating Point...\n";
    cout << "Creating Point...\n";
    cout << "Point 1: " << point1 << endl;
    cout << "Point 2: " << point2 << endl;

    cout << "The distance between points is " << point1.distance_to(point2) << endl;

    return 0;
}
