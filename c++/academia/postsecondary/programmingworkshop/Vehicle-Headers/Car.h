#ifndef CAR_H
#define CAR_H

#include "Vehicle.h"

class Car : public Vehicle
{
    private:
    string type;
    
    public:
    Car(string n, int k,string t);
    void display();
    ~Car();
};

#endif