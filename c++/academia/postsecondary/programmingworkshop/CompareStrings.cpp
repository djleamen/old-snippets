// Description: This program removes duplicates from an array of positive numbers.

#include <iostream>
using namespace std;

bool compareStrings(string s1, string s2){
    //return true if s1 < s2 based on the last letters
    //e.g. todo < hello
    
    int s1_last = s1.length()-1;
    int s2_last = s2.length()-1;
    
    while (s1_last >= 0 && s2_last >= 0){
        if (s1[s1_last] > s2[s2_last]){
            return false;
        } else if (s1[s1_last] < s2[s2_last]){
            return true;
        } 
        s1_last--;
        s2_last--;
    }
    
    return s1_last < s2_last;
}

void bubbleSort(string my_array[], int size){
    //sort a string array of size elements using the above comparison method
    
    bool sorted = false;
    while (!sorted){
        sorted = true;
        
        for (int i = 0; i < size-1; i++){
            if (!(compareStrings(my_array[i], my_array[i+1]))){
                string temp = my_array[i];
                my_array[i] = my_array[i+1];
                my_array[i+1] = temp;
                sorted = false;
            }
        }
    }
}

void main() {
    compareStrings("todo", "hello");
    string my_array[] = {"Hello", "how", "are", "todo", "hello", "ello"};
    bubbleSort(my_array,6);
}