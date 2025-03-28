// Description: This program demonstrates the use of templates and arrays in C++.
// used Stack Overflow and cppreference to learn how to use for_each

#include <iostream>
#include <array>
#include <algorithm>

using namespace std;


template<typename T, size_t size>
void processArray(array<T, size>& arr) {
    cout << "Original: ";
    for_each(arr.begin(), arr.end(), [](T& element) {
        cout << element << "     ";
    });
    cout << endl;

    sort(arr.begin(), arr.end());

    cout << "Sorted: ";
    for_each(arr.begin(), arr.end(), [](T& element) {
        cout << element << "     ";
    });
    cout << endl;
}

int main() {
    array<int, 5> intArray = {3, 1, 2, 4, 5};
    cout << "Integer Array: " << endl;
    processArray(intArray);

    array<double, 3> doubleArray = {3.14, 2.718, 1.618};
    cout << "\nDouble Array: " << endl;
    processArray(doubleArray);

    array<char, 4> charArray = {'B', 'C', 'D', 'A'};
    cout << "\nCharacter Array: " << endl;
    processArray(charArray);

    cout << endl;
    return 0;
}
