// Description: This program demonstrates dynamic memory allocation in C++.

#include <iostream>
using namespace std;
int main() {
    //Create a dynamic array that contains 5 strings.
    int num_of_words = 5;
    string* words = new string [num_of_words];
    int words_so_far = 0;
    string their_input;
    do{
        //Ask the user for words and put them in the array
        cout << "Enter a word: ";
        cin >> their_input;
        words[words_so_far] = their_input;
        words_so_far++;
        
        //Whenever the array is full, 
        if (words_so_far % 5 == 0){
            //create a new array that fits 5 more strings, 
            string* new_words = new string[num_of_words+5];
            //add all the words entered so far to it, 
            for (int i = 0; i < words_so_far; i++){
                new_words[i] = words[i];
            }
            //then delete the original array.
            delete[] words;
            words = new_words;
            
            num_of_words += 5;
        }
        
    } while(their_input != "STOP"); //until they type “STOP”. 
    
    //prompt the user to enter a word from the array. 
    cout << "Enter a word to get rid of: ";
    cin >> their_input;
    
    /*Create a new dynamic array that has all the words from the array 
*except* the one they just entered, */
    string* new_words = new string [num_of_words-1];
    int j = 0;
    for (int i = 0; i < num_of_words; i++){
        if (words[i] != their_input){
            new_words[j] = words[i];
            j++;
        }
    }
    
    for (int i = 0; i < num_of_words-1; i++){
        cout << new_words[i] << endl;
    }
  
  return 0;
}