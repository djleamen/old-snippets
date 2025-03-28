// Description: This program demonstrates how to use the heap to allocate memory for a variable.
#include <iostream>

using namespace std;

int main() {
    int* sum = new int(0);
    cout << "Enter numbers to sum (enter -1 to stop): " << endl;

    while (true) {
        int number;
        cin >> number;

        // Break the loop if user enters -1
        if (number == -1) {
            break;
        }
        *sum += number;
    }

    cout << "The sum is: " << *sum << endl;
    cout << "The sum's address in memory is: " << sum << endl;

    // Deallocate the memory and set the pointer to nullptr
    delete sum;
    sum = nullptr;

    return 0;
}
