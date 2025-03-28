// Description: This program counts the number of instances of a letter in a string.

#include <iostream>
using namespace std;

int count(string text, char letter) {
    int amount = 0;
    for (int i = 0; i < text.length(); i++) {
        if (text[i] == letter)
            amount++;
    }
    return amount;
}

int main() {
    string sentence;
    char letter;
    cout << "Enter a few words: " << endl;
    getline(cin, sentence);
    cout << "Enter a letter to look for: " << endl;
    cin >> letter;
    
    if (!(letter >= 'A' && letter <= 'Z') && !(letter >= 'a' && letter <= 'z')) {
        cout << "Invalid letter." << endl;
    } else {
        int letters = count(sentence, letter);
        cout << "There are " << letters << " instance(s) of the letter." << endl;
    }
    
    return 0;
}
