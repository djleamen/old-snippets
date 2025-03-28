// Description: This program demonstrates a binary search algorithm.

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <ctime>
using namespace std;

int binarySearch(long int randoms[], int size, int target){
    //if the target appears in the random array, return the index
    //otherwise return -1
    
    //suppose i have 10 numbers
    //upper is 9, lower is 0, mid 4
    //if mid is too low, then
    //upper is 9, lower is mid+1 = 5, 
        
    int upper_index = size-1;
    int lower_index = 0;
    int mid = (upper_index + lower_index)/2;
    while( lower_index <= upper_index ){
        if (randoms[mid] == target){
            return mid;
        } 
        if (randoms[mid] < target){
            lower_index = mid+1;
            mid = (upper_index + lower_index)/2;
        } else { //randoms[mid] > target
            upper_index = mid-1;
            mid = (upper_index + lower_index)/2;
        }
    }
    return -1;
}

//1000
//500
//250
//125
//63
//31
//16
//8
//4
//2
//1

int main(){
    srand(time(NULL));
    //Array of 1000 random numbers
    int size = 1000000;
    long int randoms[size];
    randoms[0] = rand()%10;
    
    ifstream readfile;
    readfile.open("randoms2.txt");
    for (int i = 0; i < size; i++){
        readfile >> randoms[size];
    }
    readfile.close();
    
    // ofstream myfile;
    // myfile.open("randoms2.txt");
    // myfile << randoms[0] << endl;
    // //make a sorted random array by assigning each element to be
    // //1-10 larger than the previous
    // for (int i = 1; i < size; i++){
    //     randoms[i] = randoms[i-1] + (rand()%10)+1;
    //     myfile << randoms[i] << endl;
    // } 
    // myfile.close();
    
    for (int i = 0; i < 5000; i++){
        int target_index = binarySearch(randoms,size,-1);
        if (target_index == -1){
            cout << "Not found!\n";
        } else{
            cout << "Found 2 at " << target_index << endl;
        }
    }
    
    return 0;
}