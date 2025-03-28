// Description: This program determines if a number is prime or not.

#include <iostream>

int main(){
    int number = 2;
    bool is_prime = true;
    
    if (number <= 1) {
        is_prime = false;
    } else {
        for (int i = 2; i <= number/2 ; i++){
            if (number % i == 0){
                is_prime = false;
                break;
            }
        }
    }
                
    if (is_prime){
        std::cout << "It is prime!" << std::endl;
    } else {
        std::cout << "It is not prime!" << std::endl;
    }
}