// Description: This program removes duplicates from a vector of integers.

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int main() {
    vector<int> numbers;
    int input;
    cout << "Enter 5 positive integers (ensure at least one duplicate): " << endl;

    while(numbers.size() < 5) {
        cin >> input;
        if(input > 0) {
            numbers.push_back(input);
        } else {
            cout << "Please enter a positive number." << endl;
        }
    }

    // Converting vector to set to remove duplicates
    set<int> s(numbers.begin(), numbers.end());
    numbers.assign(s.begin(), s.end());

    // Displaying the vector without duplicates
    cout << "Vector without duplicates: ";
    for (int num : numbers) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
