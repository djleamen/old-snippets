// Description: This program is a collection of exercises that demonstrate the use of pointers in C++.

#include <iostream>
using namespace std;
const int SIZE = 3;

void swap_nums(int *x, int *y) {
    int temp = *x;
    *x = *y;
    *y = temp;
}

void increment(int *ptr) {
     (*ptr)++;
}

void reverseArray(int *arr, int size) {
    int *leftPtr = arr;                 
    int *rightPtr = arr + size - 1;    

    while (leftPtr < rightPtr) {
        int temp = *leftPtr;
        *leftPtr = *rightPtr;
        *rightPtr = temp;
        leftPtr++;
        rightPtr--;
    }
}

struct Point {
    int x, y;
};

void transposeMatrix(int (*matrix)[SIZE], int size) {
    for (int i = 0; i < size; ++i) {
        for (int j = i + 1; j < size; ++j) {
            int temp = *(*(matrix + i) + j);
            *(*(matrix + i) + j) = *(*(matrix + j) + i);
            *(*(matrix + j) + i) = temp;
        }
    }
}

void printMatrix(int matrix[SIZE][SIZE], int size) {
    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

/*
Write a program that declares a function pointer to a function that takes 
two integer parameters and returns their sum. Use the function pointer to 
call the function and print the result.
*/

int sum(int num1, int num2){
    return num1+num2;
 }

void main () {
    int my_num = 1;
    int* myp = &my_num;

    int my_num1 = 1;
    int* myp1 = &my_num1;
    int my_num2 = 2;
    int* myp2 = &my_num2;
    swap_nums(myp1, myp2);

    int array[] = {1,2,3,4,5};
    int size = sizeof(array) / sizeof(array[0]);
    int *arrayPtr = array;
    for (int i = 0; i < size; ++i) {
        cout << "Element at position " << i << " is: " << *(arrayPtr + i) << endl;
    }

    int myarray[5];
    int *newptr = myarray;

    myarray[0] = 10;
    myarray[1] = 20;
    myarray[2] = 30;
    myarray[3] = 40;
    myarray[4] = 50;
    for (int i = 0; i < 5; ++i) {
        cout << "Value at index " << i << " is: " << *(newptr + i) << endl;
    }

    int (*functionPtr)(int, int) = sum;
    int result = functionPtr(10, 3);
    cout << "The sum is: " << result << endl;
    
    const int mycons = 10;
    const int* consptr = &mycons;

    int lol = 420;
    int* lolzor = &lol;
    increment(lolzor);

    char ch = 'D';
    char *chptr = &ch;

    int array[] = {1, 2, 3, 4};
    int size = sizeof(array) / sizeof(array[0]);
    reverseArray(array, size);
    cout << "Reversed array: ";
    for (int i = 0; i < size; i++) {
           cout << array[i] << " ";
    }
    cout << endl;

    char initials[] = {'D', 'J', 'L'};
    char* initialptr = initials;
    int count = 0;
    while (*initialptr != '\0') {
        count++;
        initialptr++; 
    }
    cout << "The array contains " << count << " characters." << endl;

    int arrA[] = {5, 10, 15, 20, 25};
    int arrB[] = {2, 4, 6, 8, 10};
    int resultArray[5];
    int* ptrA = arrA;
    int* ptrB = arrB;
    int *ptrResult = resultArray;
    for (int i = 0; i < 5; ++i) {
       *(ptrResult + i) = *(ptrA + i) * *(ptrB + i);
    }

    cout << "Result array: ";
    for (int i = 0; i < 5; ++i) {
       cout << *(ptrResult + i) << " ";
    }
    cout << endl;

    Point points[3];
    points[0] = {1, 2};
    points[1] = {3, 4};
    points[2] = {5, 6};
    Point *ptr = points;
    for (int i = 0; i < 3; ++i) {
        cout << "Point " << i + 1 << ": (" << ptr[i].x << ", " << ptr[i].y << ")" << endl;
    }

    int matrix[3][3] = {
        {1, 2, 3},
        {5, 6, 7},
        {9, 10, 11}
    };

    int *rowPtr = matrix[0];

    cout << "Elements of row " << 2 << ": ";
    for (int i = 0; i < 4; ++i) {
        cout << *(rowPtr + i) << " ";
    }
    cout << endl;

    int matrix[SIZE][SIZE] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    
    cout << "Original Matrix:" << endl;
    printMatrix(matrix, SIZE);
    transposeMatrix(matrix, SIZE);
    cout << "Transposed Matrix:" << endl;
    printMatrix(matrix, SIZE);
}