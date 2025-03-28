// Description: Dynamic memory allocation in C++

#include <iostream>
#include <string>
using namespace std;

int main() {
    // normal
    double normalDouble = 10.5;
    // dynamic
    double* dynamicDouble = new double;
    *dynamicDouble = 20.5;
    delete dynamicDouble;

    int arraySize;
    cout << "Enter the number of words you will input: ";
    cin >> arraySize;

    if (arraySize <= 0) {
        cout << "Invalid size. Please enter a positive number." << endl;
        return -1;
    }

    string* words = new string[arraySize];

    cout << "Enter " << arraySize << " words:" << endl;
    for (int i = 0; i < arraySize; i++) {
        cin >> words[i];
    }

    cout << "You entered these words:" << endl;
    for (int i = 0; i < arraySize; i++) {
        cout << words[i] << endl;
    }

    delete[] words;

    int capacity = 5;
    int size = 0;
    string* words = new string[capacity];

    while (true) {
        string word;
        cout << "Enter a word (or 'STOP' to finish): ";
        cin >> word;

        if (word == "STOP") {
            break;
         }

        if (size == capacity) {
            capacity += 5;
            string* newWords = new string[capacity];

            for (int i = 0; i < size; i++) {
                newWords[i] = words[i];
            }
            delete[] words;
            words = newWords;
        }

        words[size] = word;
        size++;
    }

    cout << "You entered these words:" << endl;
    for (int i = 0; i < size; i++) {
        cout << words[i] << endl;
    }

    string wordToRemove;
    cout << "Enter a word to remove: ";
    cin >> wordToRemove;

    string* newWords = new string[capacity];
    int newSize = 0;
    for (int i = 0; i < size; i++) {
        if (words[i] != wordToRemove) {
            newWords[newSize++] = words[i];
        }
    }

    delete[] words;
    words = newWords;
    size = newSize;

    cout << "Remaining words:" << endl;
    for (int i = 0; i < size; i++) {
        cout << words[i] << endl;
    }

     delete[] words;

}