#include "Car.h"

Car::Car(string n, int k, string t) : Vehicle(n, k)
{
    type = t;
    cout <<"Car parameterized constructor"<<endl;
}

void Car::display()
{
    Vehicle::display();
    cout <<"Type: "<<type<<endl;
}
Car::~Car()
{
    cout <<"Car destructor"<<endl;
}