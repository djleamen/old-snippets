#ifndef VEHICLE_H
#define VEHICLE_H

#include <iostream>
using namespace std;

class Vehicle
{
    protected:
    string name;
    int kms;
    
    public:
    Vehicle(string n, int k);
    void display();
    ~Vehicle();
};

#endif