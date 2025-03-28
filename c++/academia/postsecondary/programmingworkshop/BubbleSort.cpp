// Description: This program demonstrates the bubble sort algorithm.

#include <iostream>
#include <cstdlib>
using namespace std;

void bubbleSort(int numbers[], int size){
    bool something_swapped = true;
    //while(something_swapped == false){
    while(something_swapped){
        something_swapped = false;
        for (int i = 1; i < size; i++){
            if (numbers[i-1] > numbers[i]){
                int temp = numbers[i-1];
                numbers[i-1] = numbers[i];
                numbers[i] = temp;
                
                something_swapped = true;
            }
        }
    }
}

int main(){
    srand(time(NULL));
    int size = 100000;
    int numbers[100000];
    cout << "Array before: \n";
    for (int i = 0; i < size; i++){
        numbers[i] = rand() % 10000;
        cout << numbers[i] << ", ";
    }
    cout << endl;
    
    bubbleSort(numbers,size);
    
    cout << "Array after: \n";
    for (int i = 0; i < size; i++){
        cout << numbers[i] << ", ";
    }
    cout << endl;
    
    return 0;
}