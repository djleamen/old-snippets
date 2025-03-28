// Description: This program swaps the values of two integers using pointers.

#include <iostream>
#include <string>
using namespace std;

void swap_nums (int *x, int *y){
    //x -> 10, y -> 5
    *x = *x + *y; //*x = 15
    *y = *x - *y; //*y = 10 
    *x = *x - *y; //*x = 5
}


int main() {
  
  int *p1;
  int *p2;
  
  int pointer1value = 10;
  int pointer2value = 5;
  
  p1 = &pointer1value;
  p2 = &pointer2value;
  
  swap_nums(p1,p2);
  
  cout << *p1 << endl;

  return 0;
}