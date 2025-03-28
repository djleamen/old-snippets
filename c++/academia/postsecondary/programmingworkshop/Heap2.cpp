// Description: This program reads an array of integers and creates a new array with only the even numbers from the original array.
#include <iostream>
using namespace std;

int countEvenNumbers(int* arr, int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
        if (arr[i] % 2 == 0) {
            count++;
        }
    }
    return count;
}

void fillEvenNumbersArray(int* source, int* destination, int size) {
    int j = 0;
    for (int i = 0; i < size; i++) {
        if (source[i] % 2 == 0) {
            destination[j++] = source[i];
        }
    }
}

int main() {
    int size = 0;
    cout << "Enter the size of the array: ";
    cin >> size;
    int* arr = new int[size];

    cout << "Enter " << size << " numbers:" << endl;
    for (int i = 0; i < size; i++) {
        cin >> arr[i];
    }

    int evenCount = countEvenNumbers(arr, size);
    int* evenNumbers = new int[evenCount];
    fillEvenNumbersArray(arr, evenNumbers, size);

    cout << "Even numbers are:" << endl;
    for (int i = 0; i < evenCount; i++) {
        cout << evenNumbers[i] << " ";
    }
    cout << endl;

    delete[] arr;
    delete[] evenNumbers;
    return 0;
}
