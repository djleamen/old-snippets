// Description: This program demonstrates the use of a function that reverses a string.

#include <iostream>
#include <string>

using namespace std;

string reverse(string s) {
    string reverse = "";

    for (int i = s.length() - 1; i >= 0; i--) {
        reverse += s[i];
    }

    return reverse;
}

int main() {

    string normal = "forward";
    string lamron = "drawrof";

    cout << "reverse(normal) = " << reverse(normal) << endl;
    cout << "reverse(lamron) = " << reverse(lamron) << endl;

    return 0;
}