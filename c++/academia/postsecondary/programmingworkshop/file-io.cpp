// Description: This program demonstrates how to write to a file and read from a file.

#include <iostream>
#include <fstream>
using namespace std;

int main(){
    ofstream myoutput;
    myoutput.open("text.txt", ios::app);
    
    string theirnumber;
    for (int i = 0; i < 5; i++){
        cout << "Enter a number: ";
        cin >> theirnumber;
        myoutput << theirnumber << '\n';
    }
    
    myoutput.close();
    
    ifstream myinput;
    myinput.open("Lecture6b.txt");
    
    //int theirnumber;
    //while(getline(myinput,theirnumber)){
    while(myinput >> theirnumber){
        cout << theirnumber << '\n';
    }
    
    myinput.close();
    
    return 0;
}