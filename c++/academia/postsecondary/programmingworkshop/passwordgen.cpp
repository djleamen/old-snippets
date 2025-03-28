// Description: This program generates a secure password of a specified length.

#include <iostream>
#include <string>
#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
#include <time.h> 
using namespace std;

const int VAL = 81; // the total number of characters that can be generated (used in generatePassword)

string generatePassword(int n) {
    // initialize the values we are using, including lowercase, uppercase, and special chars (minus backslash)
    char values[VAL] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 
                          'h', 'i', 'j', 'k', 'l', 'm', 'n',  
                          'o', 'p', 'q', 'r', 's', 't', 'u', 
                          'v', 'w', 'x', 'y', 'z', 'A', 'B', 
                          'C','D','E','F','G','H','I','J','K',
                          'L','M', 'N', 'O','P','Q','R','S','T',
                          'U','V','W','X','Y','Z','!','@','#','$',
                            '%','^','&', '*','(',')','-','+', '_', 
                            '=','[','{',']','}','|', ';', ':', '<', 
                            '>', ',','.', '?', '/', '~', '`'}; 
    string password = ""; 
    // randomly choose a value to add to our string until we hit the amount needed (n)
    for (int i = 0; i < n; i++)  
        password = password + values[rand() % VAL]; 
    return password; 
}

bool isSecurePassword(const string& password, int length) {
    for (int i = 0; i < length; i++) {
        // check for '123'
        if (i + 2 < length && password[i] == '1' && password[i + 1] == '2' && password[i + 2] == '3') {
            return false;
        }
        // check for 'abc'
        if (i + 2 < length && password[i] == 'a' && password[i + 1] == 'b' && password[i + 2] == 'c') {
            return false;
        }
        // check for 'ABC'
        if (i + 2 < length && password[i] == 'A' && password[i + 1] == 'B' && password[i + 2] == 'C') {
            return false;
        }
        // check for 'password' and 'PASSWORD'
        if (i + 7 < length) {
            string subStr = password.substr(i, 8);
            if (subStr == "password" || subStr == "PASSWORD") {
                return false;
            }
        }
        // check for 'dj' and 'DJ' my name
        if (i + 1 < length && ((password[i] == 'd' && password[i + 1] == 'j') || (password[i] == 'D' && password[i + 1] == 'J'))) {
            return false;
        }
    }
    return true;
}    

int main() {
    srand(time(NULL));

    int length;
    cout << "Enter the desired length of the password (within 12 and 16 characters.)" << endl;
    while (true) {
        cin >> length;
        if (length >= 12 && length <= 16) break;
        cout << "Invalid password length. Please enter a length between 12 and 16 characters." << endl;
    }

    string password;
    do {
        password = generatePassword(length);
    } while (!isSecurePassword(password, length));

    cout << "Secure Password: " << password << endl;

    return 0;
}