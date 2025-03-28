#ifndef AVEHICLE_H
#define AVEHICLE_H
#include <iostream>

namespace space2 {
    int i = 7;
    class Vehicle
    {
        private:
        int capacity;
        public:
        void engineStart()
        {
            std::cout <<"Engine Started"<<std::endl;
        }
    };

    void display()
    {
        std::cout <<"I am inside AnotherVehicle.h"<<std::endl;
    }
}
#endif