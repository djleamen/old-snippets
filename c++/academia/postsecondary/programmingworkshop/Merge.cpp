// Description: This program is designed to merge two arrays at a specified position.

#include <iostream>
#include <cstdlib>
#include <cstddef>

int *merge(int arr1[], int arr2[], int size1, int size2, int pos){
    if( pos < 0 || pos > size1) {
        return NULL;
    }
    int *result = new int[size1+size2]; // added new

    int i = 0, i2 = pos;
    
    for(i = 0; i < pos; ++i) { // 1st part
        result[i] = arr1[i];
    }
    for(int i1 = 0; i1 < size2; ++i1, ++i) { // 2nd part
        result[i] = arr2[i1];
    }
    for(i2 = pos; i2 < size1; ++i2, ++i) { // 3rd part
        result[i] = arr1[i2];
    }
    return result;
}

int main () {
    int arr1[] = {1, 2, 3};
    int arr2[] = {1, 2, 3};
    int* merged = merge(arr1, arr2, 3, 3, 2);
    for (int i = 0; i< 6; i++) {
        std::cout << merged[i] << std::endl;
    }
    
    delete[] merged;
    return 0;
}