// Description: This program demonstrates the use of templates and arrays in C++.

#include <iostream>
#include <array>

using namespace std;


template<typename T>
void display(const T& dataStructure) {
    for (const auto& element : dataStructure) {
        cout << element << "     ";
    }
    cout << endl;
}

template<typename T>
void sort(T& dataStructure) {
    bool swapped = true;
    int j = 0;
    typename T::size_type n = dataStructure.size();
    while (swapped) {
        swapped = false;
        j++;
        for (typename T::size_type i = 0; i < n - j; ++i) {
            if (dataStructure[i] > dataStructure[i + 1]) {
                swap(dataStructure[i], dataStructure[i + 1]);
                swapped = true;
            }
        }
    }
}


int main() {
    array<int, 5> intArray = {3, 1, 2, 4, 5};
    cout << "Integer Array: " << endl;
    cout << "Original: ";
    display(intArray);
    sort(intArray);
    cout << "Sorted: ";
    display(intArray);

    array<double, 3> doubleArray = {3.14, 2.718, 1.618};
    cout << "\nDouble Array: " << endl;
    cout << "Original: ";
    display(doubleArray);
    sort(doubleArray);
    cout << "Sorted: ";
    display(doubleArray);

    array<char, 4> charArray = {'B', 'C', 'D', 'A'};
    cout << "\nCharacter Array: " << endl;
    cout << "Original: ";
    display(charArray);
    sort(charArray);
    cout << "Sorted: ";
    display(charArray);

    return 0;
}