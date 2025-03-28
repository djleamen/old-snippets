#include "item.h"
#include <iostream>
#include <string>
using namespace std;

Item* resize(Item* arr, int* size) {
    (*size)++;
    Item* newArr = new Item[*size];
    for (int i = 0; i < *size - 1; i++) {
        newArr[i] = arr[i];
    }
    delete[] arr;
    return newArr;
}

Item search(int target, Item* arr, int size) {
    for (int i = 0; i < size; i++) {
        if (arr[i].id == target) {
            return arr[i];
        }
    }
    throw runtime_error("Item not found");
}
