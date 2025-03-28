// Description: This program demonstrates the use of pointers and references.

#include <iostream>
#include <vector>
using namespace std;

vector<string*> generateSPVector(vector<string>& vec){
    vector<string*> sp_vec;
    
    for (int i = 0; i < vec.size(); i++){
        string* sp = &vec[i];
        sp_vec.push_back(sp);
    }
    
    return sp_vec;
}

int main(){
    vector<string> vec;
    vec.push_back("Hello");
    vec.push_back("How");
    vec.push_back("Are");
    vec.push_back("You?");
    
    vector<string*> sp_vec = generateSPVector(vec);
    
    cout << "The memory addresses of the pointers are:" << endl;
    for (int i = 0; i < sp_vec.size(); i++){
        cout << sp_vec[i] << endl;
    }
    
    cout << "The values pointed to by the pointers are:" << endl;
    for (int i = 0; i < sp_vec.size(); i++){
        cout << *sp_vec[i] << endl;
    }
}