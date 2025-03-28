// Description: This program removes duplicates from an array of positive integers.
#include <iostream>
using namespace std;

int main() {
    int array[5];

    // Input
    cout << "Enter five positive integers with duplicates: ";
    for (int i = 0; i < 5; i++) {
        cin >> array[i];
        if (array[i] <= 0) {
            cout << "Error, must be positive integers." << endl;
            return 1; // Exit with an error code
        }
    }

    // Duplicate Detection and Removal
    int newSize = 5; // Track the new size of the array
    for (int i = 0; i < newSize; i++) {
        for (int j = i + 1; j < newSize; ) {
            if (array[i] == array[j]) {
                // Shift elements left to remove the duplicate
                for (int k = j; k < newSize - 1; k++) {
                    array[k] = array[k + 1];
                }
                newSize--; // Decrease the size of the array
            } else {
                j++; // Move to the next element
            }
        }
    }

    // Display the array after removing duplicates
    cout << "Array after removing duplicates: ";
    for (int i = 0; i < newSize; i++) {
        cout << array[i] << " ";
    }

    return 0;
}


/*
We'll take an example array looks like this: [1, 1, 3, 4, 3], and newSize is set to 5, representing the current length of the array.

Step 1: Compare and Shift
Start with the first element (array[0] = 1). Compare it to each subsequent element to find duplicates.
The next element (array[1] = 1) is a duplicate. So, we shift all elements after array[1] one position to the left. The array becomes [1, 3, 4, 3, _], and newSize is decremented to 4. The last element is now a "ghost" element, a remnant of the shift, and is ignored in future iterations because of the reduced newSize.
Continue comparing the first element (array[0] = 1) with the next one (array[1] = 3). Since it's not a duplicate, move to the next element.

Step 2: Continue the Process
Move to the second element (array[1] = 3) and compare it with subsequent elements.
The last element (array[3] = 3) is a duplicate. So, we shift the next element (in this case, none, as it's the end of the array) to the left. The array now looks like [1, 3, 4, _, _], and newSize becomes 3.
No more comparisons are needed for the second element.

Step 3: Finish with No More Duplicates
The third element (array[2] = 4) has no duplicates after it. So, no shifts are necessary.
The loop ends as we've checked all elements up to the updated newSize.

Result
The modified array is [1, 3, 4], with newSize = 3, indicating that the array now contains 3 unique elements.
*/