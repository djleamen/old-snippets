// Description: This program uses recursion to determine if a word is a palindrome.

#include <iostream>

using namespace std;

bool isPalindrome(string word){
    //base case
    if (word.length() <= 1){
        return true;
    }
    
    //do the last and first not match?
    if (word[0] != word[word.length()-1]){
        return false;
    }
    
    //look at the remaining letters recursively
    return isPalindrome(word.substr(1, word.length() - 2));
    
}

void main() {
    string mypal = "lonelytylenol";
    isPalindrome(mypal);
}
