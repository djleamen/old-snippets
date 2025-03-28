// Description: This program demonstrates the use of pointers and references.

#include <iostream>
#include <string>
using namespace std;

void capitalize(string &s1){
  s1[0] = toupper(s1[0]);  
}

int main() {
  
  int *p2; 
  int x = 0; 
  p2 = &x; 
  *p2 = 12; 
  cout << *p2 << " " << x << endl;

  return 0;
}