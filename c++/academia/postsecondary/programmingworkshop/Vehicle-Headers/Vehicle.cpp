#include "Vehicle.h"

Vehicle::Vehicle(string n, int k)
{
    name = n;
    kms = k;
}
void Vehicle::display()
{
    cout <<"Name: "<<name<<endl;
    cout <<"KMs: "<<kms<<endl;
}
Vehicle::~Vehicle()
{
    cout <<"Vehicle destructor"<<endl;
}