// Description: This program demonstrates how to read and write to a file in C++.

#include <iostream>
#include <fstream>
using namespace std;

int main(){
    ofstream myOutput;
    myOutput.open("NewFile.txt", ios::app);
    myOutput << "Hello File Reader!" << endl;
    myOutput.close();
    
    ifstream myInput;
    string text;
    myInput.open("NewFile.txt");
    //myInput >> text; 
    getline(myInput, text);
    myInput.close();
    cout << "The file contains: " << text << endl;
    
    return 0;
}