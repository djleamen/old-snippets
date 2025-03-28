// Description: This program determines if a number is prime or not.

#include <iostream>
using namespace std;

bool isPrime(int input) {
    bool isPrime = true;
    for (int i = 2; i <= input/2; ++i) {
            if (input % i == 0) {
                isPrime = false;
            }
        }
    return isPrime;
}

int main() {
    int input;
    cout << "Enter a positive integer variable (not 1): " << endl;
    cin >> input;
    if (input <= 1) {
        cout << "Please enter a valid positive number." << endl;
    } else {
    if (isPrime(input)) {
        cout << input << " is a prime number." << endl;
    } else {
        cout << input << " is not a prime number." << endl;
    }
    }
    
    return 0;
}