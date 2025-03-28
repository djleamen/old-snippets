// Description: This program demonstrates how to use pointers to pass values 
// to a function and return a pointer from a function.

#include <iostream>
#include <string>
using namespace std;

int* add_nums (int *x, int *y, int sum) {
    sum = *x + *y;
    int* sum_pointer = &sum;
    return sum_pointer;
}


int main() {
  
  int *p1, *p2;
  int sum = 0;
  
  int pointer1value = 10;
  int pointer2value = 5;
  
  p1 = &pointer1value;
  p2 = &pointer2value;
  
  int *sum_pointer = add_nums(p1,p2,sum);
  
  cout << *sum_pointer << endl;

  return 0;
}