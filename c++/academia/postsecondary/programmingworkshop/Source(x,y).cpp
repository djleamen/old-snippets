// Description: This program demonstrates the use of the new and delete operators.

#include<iostream>
//#include "X.h"
//#include "Y.h"
using namespace std;

int main(int argc, char* argv[]) {
	int size = atoi(argv[1]);
	int *ptr = new int[size];
	
	for (int i=0; i<size; i++) {
		cout <<"Enter value at "<<i<<": ";
		cin >>ptr[i];
	}
	
	for (int i=0; i<size; i++) {
		cout <<ptr[i]<<"\t";
	}
	cout <<endl;
	
	delete[] ptr;
	
    return 0;
}