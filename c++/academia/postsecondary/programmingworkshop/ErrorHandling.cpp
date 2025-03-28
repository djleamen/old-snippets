// Description: This program demonstrates how to use the exceptions method to handle errors in C++.

#include <iostream>
using namespace std;

int main() {
    int x;

    // enabling error raising
    cin.exceptions(ios_base::failbit);

    try {
        cout << "Enter a number: ";
        cin >> x;
        cout << "You entered, " << hex << x << endl;
    }
    
    catch (ios_base::failure& e) {
    {
        cout << "Something happened... "
                  << e.what() << endl;
        return -1;
    }
    } catch (...) {
        cout << "Unhandled exception!" << endl;
        return -1;
    }

    return 0;
}