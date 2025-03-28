// Description: This program demonstrates how to trim whitespace from the end of a string.

#include <iostream>
#include <string>

using namespace std;

string rtrim(string s) {
    size_t end = s.find_last_not_of(" ");
    if(end != string::npos) {
        s = s.substr(0, end + 1);
    }
    return s;
}

int main() {

    string untrimmed = "this is a string   .   ";

    string trimmed = rtrim(untrimmed);

    cout << "Before: '" << untrimmed << "'" << endl;
    cout << "After: '" << trimmed << "'" << endl;

    return 0;
}