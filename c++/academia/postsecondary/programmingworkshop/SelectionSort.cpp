// Description: This program demonstrates the selection sort algorithm.

#include <iostream>
#include <cstdlib>
using namespace std;

void selectionSort(int numbers[], int size){
    for (int i = 0; i < size; i++){
        //select the smallest and move it to the beginning of the sorted part
        int index_of_smallest = i;
        for (int j = i+1; j < size; j++){
            //        for (int j = i+1; j < size; j++){
            //suppose i = size -1
            //         for (int j = size; j < size; j++){
            
            if (numbers[j] < numbers[index_of_smallest]){
                index_of_smallest = j;
            }
        }
        
        int temp = numbers[index_of_smallest];
        numbers[index_of_smallest] = numbers[i];
        numbers[i] = temp;
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
    
    selectionSort(numbers,size);
    
    cout << "Array after: \n";
    for (int i = 0; i < size; i++){
        cout << numbers[i] << ", ";
    }
    cout << endl;
    
    return 0;
}