#include "point.h"

Point::Point(double x_value, double y_value) : x(x_value), y(y_value) {}

double Point::distance_to(const Point &other) {
    return sqrt(pow(other.y - y, 2) + pow(other.x - x, 2));
}

ostream &operator<<(ostream &os, const Point &obj) {
    os << "(" << obj.x << ", " << obj.y << ")";
    return os;
}
