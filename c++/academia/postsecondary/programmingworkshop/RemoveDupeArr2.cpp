// Description: This program removes duplicates from an array of positive numbers.

#include <iostream>

using namespace std;

void removeDuplicates(int *numbers, int size, int *n) {
    int unique[5]; // Temporary array to hold unique values
    int count = 0; // Count of unique values

    for (int i = 0; i < size; i++) {
        bool isDuplicate = false;
        for (int j = 0; j < count; j++) {
            if (numbers[i] == unique[j]) {
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {
            unique[count++] = numbers[i];
        }
    }

    // Copy unique values back to the original array
    for (int i = 0; i < count; i++) {
        numbers[i] = unique[i];
    }

    *n = count; // Update the size of the array after removing duplicates
}

int main() {
    int numbers[5];
    int size = 5;
    int newSize;

    // Taking input from the user, ensuring at least one duplicate
    cout << "Enter 5 positive numbers (ensure there's at least one duplicate): ";
    for (int i = 0; i < size;) {
        int num;
        cin >> num;
        if (num > 0) {
            numbers[i++] = num;
        } else {
            cout << "Only positive numbers are allowed. Please re-enter: ";
        }
    }

    removeDuplicates(numbers, size, &newSize);

    cout << "Array after removing duplicates: ";
    for (int i = 0; i < newSize; i++) {
        cout << numbers[i] << " ";
    }
    cout << endl;

    return 0;
}

/*
Step 1: Initialize Temporary Storage
A temporary array unique[5] is created to hold the unique elements from the original array.
count is initialized to 0 to track the number of unique elements found.

Step 2: Identify Unique Elements
Iterate through each element of the original array (numbers).
For each element in numbers, use a nested loop to check against all elements currently in unique to see if it has already been added. This is done using the isDuplicate flag.
If the current element from numbers is not found in unique, it is considered unique, and then it's added to the unique array, and count is incremented.
If the element is found in unique, the isDuplicate flag is set to true, and the loop breaks early without adding the element to unique.
Using an example array, let's see how unique elements are identified:

Start with 1. Since unique is empty, add 1 to unique, and increment count to 1.
The next 1 is recognized as a duplicate because it matches the existing element in unique.
Continue with 3. It's not in unique, so add it, and increment count to 2.
Next, 4 is also unique, so add it to unique, count becomes 3.
The last 3 is a duplicate of an existing element in unique, so it's skipped.

Step 3: Copy Back to Original Array
After identifying all unique elements, the code then copies these back into the original array numbers for the length of count, which represents the number of unique elements found.
This overwrites the original elements in numbers with the unique set, essentially "removing" duplicates.

Step 4: Update the Original Array Size
The pointer *n is used to update the original array's size to reflect the new size after duplicate removal (count).

Final Output
Finally, main prints the updated numbers array, which now contains only unique values: 1, 3, 4, and newSize is updated to 3, indicating the new size of the array.
*/