// Description: This program demonstrates exception handling in C++.

#include <iostream>
#include <string>
using namespace std;

double calc( int a, int b ){
    if (b == 0){
      throw runtime_error("Divide By Zero Error!");
    }
    return (double) a / (double) b;
}

int main(){
    int x, y;
    double res;
    try
    {
        cout << "Enter two positive integers: ";
        cin >> x >> y;
        res = calc( x, y);
        cout << x << "/" << y << " = " << res << endl;
    }
    catch(runtime_error & e) {
        cout << e.what() << endl;
    }
    catch(...) 
    {
        cout << "Unexpected exception! \n";
        return -1;
    }
    return 0;
}