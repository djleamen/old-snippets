// Description: This program determines if a user input is a prime number.
#include <iostream>
using namespace std;

int main() {
    int input;
    bool isPrime = true;
    cout << "Enter a positive integer variable (not 1): " << endl;
    cin >> input;
    if (input <= 1) {
        cout << "Please enter a valid positive variable." << endl;
    } else {
        for (int i = 2; i <= input/2; ++i) {
            if (input % i == 0) {
                isPrime = false;
                break;
            }
        }
    
    if (isPrime == true) {
        cout << input << " is a prime number." << endl;
    } else {
        cout << input << " is not a prime number." << endl;
    }
    }
    
    return 0;
}