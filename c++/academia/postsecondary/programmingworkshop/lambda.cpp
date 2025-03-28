// Description: This program demonstrates the use of lambda functions in C++.

#include <algorithm>
#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main(){
    map<int,string> alphamap;
    alphamap[0] = "zero";
    alphamap[1] = "one";
    alphamap[2] = "two";
    alphamap[3] = "three";
    alphamap[4] = "four";
    alphamap[5] = "five";
    alphamap[6] = "six";
    alphamap[7] = "seven";
    alphamap[8] = "eight";
    alphamap[9] = "nine";
    
    vector<int> nums = {2,8,4,8,1,9,3};
   
    // typedef void (*fptr)(vector<int>);
    // fptr printV = [](vector<int> nums){
    
    // void (*printV)(vector<int>) = [](vector<int> nums){
    
    std::function<void(vector<int>)> printV = [](vector<int> nums){
        for (int i : nums){
            cout << i << ", ";
        }
        cout << endl;
    };
    cout << "Pre-Sorted:\n";
    printV(nums);
    
    sort(nums.begin(), nums.end(), [&](int a, int b){ 
        return alphamap[a] < alphamap[b];
    } );
    //try out: transform, foreach, substitute?, from STL
    
    cout << "Post-Sorted:\n";
    printV(nums);
    
}