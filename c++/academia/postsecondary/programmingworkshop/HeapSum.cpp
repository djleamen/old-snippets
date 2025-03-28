// Description: This program sums numbers entered by the user and stores the sum in the heap.

#include <iostream>

using namespace std;

int main() {
    // Allocate memory for sum in the heap
    int* sum = new int(0);

    cout << "Enter numbers to sum (enter -1 to stop): " << endl;

    while (true) {
        int number;
        cin >> number;

        // Break the loop if user enters -1
        if (number == -1) {
            break;
        }

        // Add the number to sum
        *sum += number;
    }

    // Print the sum and its memory address
    cout << "The sum is: " << *sum << endl;
    cout << "The sum's address in memory is: " << sum << endl;

    // Deallocate the memory and set the pointer to nullptr
    delete sum;
    sum = nullptr;

    return 0;
}
