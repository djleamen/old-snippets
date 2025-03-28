// Description: This program is designed to find the pointer that points to the largest value in an array of pointers.

#include <iostream>
using namespace std;

int** pointThePointer(int *pointers[], int **&ptr_to_ptr, int size){
  ptr_to_ptr = &pointers[0];
  for (int i = 1; i < size; i++){
    if (*pointers[i] > **ptr_to_ptr){
      ptr_to_ptr = &pointers[i];  
    }  
  }
}

int main() {
  
  int num1 = 1;
  int num2 = 200;
  int num3 = 3;
  int *pointers[] = {&num1,&num2,&num3};
  int **ptr_to_ptr;
  pointThePointer(pointers,ptr_to_ptr,3);

  return 0;
}