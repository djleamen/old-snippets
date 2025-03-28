// Description: This program is designed to return a pointer to the first occurrence of a character in a string.

#include <iostream>
using namespace std;

char* getCharPtr(char* myString, char* myCharPointer, int size){
  for (int i = 0; i < size; i++){
    if (myString[i] == *myCharPointer){
      return &myString[i];  
    }  
  }
}

int main() {
  
  //string myString = "Ontario Tech";
  char* myString = "Ontario Tech";
  char letter = 'n';
  char* myCharPointer = &letter;
  char* newPtr = getCharPtr(myString,myCharPointer,12);

  return 0;
}