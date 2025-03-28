// Description: This program demonstrates the linear search, binary search, and bubble sort algorithms.

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int linearSearch(int arr[], int size, int searchElement) {
    for (int i = 0; i < size; i++) {
        if (arr[i] == searchElement) {
            return i;
        }
    }
    return -1;
}

int binarySearch(int arr[], int l, int r, int x) {
    if (r >= l) {
        int mid = l + (r - l) / 2;
        if (arr[mid] == x)
            return mid;
        if (arr[mid] > x)
            return binarySearch(arr, l, mid - 1, x);
        return binarySearch(arr, mid + 1, r, x);
    }
    return -1;
}

void bubbleSort(int arr[], int n) {
    for (int i = 0; i < n-1; i++)     
        for (int j = 0; j < n-i-1; j++) 
            if (arr[j] > arr[j+1])
                swap(arr[j], arr[j+1]);
}

int main() {
    /*
    const int SIZE = 1000;
    int numbers[SIZE];
    for (int i = 0; i < SIZE; i++) {
        numbers[i] = i + 1; 
    }
    int numberToFind;
    cout << "Enter a number to find: ";
    cin >> numberToFind;
    int binaryIndex = binarySearch(numbers, 0, SIZE - 1, numberToFind);
    if (binaryIndex != -1) {
        cout << "Number found at index (Binary Search): " << binaryIndex << endl;
    } else {
        cout << "Number not found in the array (Binary Search)." << endl;
    }
    int linearIndex = linearSearch(numbers, SIZE, numberToFind);
    if (linearIndex != -1) {
        cout << "Number found at index (Linear Search): " << linearIndex << endl;
    } else {
        cout << "Number not found in the array (Linear Search)." << endl;
    }
    return 0;
    */
    
    srand(time(0));
    const int SIZE = 5;
    int numbers[SIZE];
    cout << "Original Array: ";
    for (int i = 0; i < SIZE; i++) {
        numbers[i] = rand() % 100; 
        cout << numbers[i] << " ";
    }
    cout << endl;

    bubbleSort(numbers, SIZE);

    cout << "Sorted Array: ";
    for (int i = 0; i < SIZE; i++) {
        cout << numbers[i] << " ";
    }
    cout << endl;

    return 0;
}