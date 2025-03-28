// Description: This program demonstrates how to resize an array in C++.

#include <iostream>
using namespace std;

void resize(int* &arr, int old_size, int new_size){
    int* new_array = new int [new_size ];    
    for (int i = 0; i < old_size; i++){
        new_array [ i ] = arr [ i ];
    }
    delete[] arr; //remove the original array
    arr = new_array;
}

int main(){
    int* myptr = new int[5];
    for (int i = 0 ; i < 5; i++){
        myptr[i] = i*10;
    }
    resize(myptr,5,10);
    cout << myptr[2] << endl;
}