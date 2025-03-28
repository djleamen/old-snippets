#ifndef VEHICLE_H
#define VEHICLE_H
#include <iostream>

namespace space1 {
    int i = 10;
    class Vehicle
    {
        private:
        int kms;
        public:
        void drive()
        {
            std::cout <<"I can drive"<<std::endl;
        }
    };

    void display()
    {
        std::cout <<"I am inside Vehicle.h"<<std::endl;
    }
}
#endif