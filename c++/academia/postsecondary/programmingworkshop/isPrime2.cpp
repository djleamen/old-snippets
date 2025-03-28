// Description: This program reads a file of integers and determines if each integer is prime. 
// It then calculates the average of the prime numbers.

#include <iostream>
#include <fstream>

bool determinePrime(int number)
{
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
    
    // alt code
    // if (is_prime){
    //     std::cout << "It is prime!" << std::endl;
    // } else {
    //     std::cout << "It is not prime!" << std::endl;
    // }
    
    return is_prime;
}

void readPrimeFile(std::string filename){
    //open the file for reading
    std::ifstream myinput;
    myinput.open(filename);
    
    int sum = 0;
    int count = 0;
    int current_number;
    
    while(myinput >> current_number){
        if (determinePrime(current_number)){
            sum += current_number;
            count++;
        }
    }
    
    myinput.close();
    
    std::cout << "The average of the primes is: " << (double) sum / count << std::endl;
}

int main(){
    readPrimeFile("myprimes.txt");
}