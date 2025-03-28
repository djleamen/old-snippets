// Description: This program simulates a computer memory system with 10 memory registers. 
// The user can read, write, display, search, and rearrange memory values.

#include <iostream>
#include <iomanip>
#include <algorithm>
#include <string>

using namespace std;

const int MEMORY_SIZE = 10;  // Size of the computer memory

/* 
Function to read value from memory
The user chooses a memory register index (0-9), and the program should print both
the address of that register as well as the value stored there.
Print a message if the register index is not valid.
*/
void readValue(string* memory) {
    int mem_num;
    cout << memory[1];
    cout << "Enter the index of the register to read (0-9): ";
    cin >> mem_num;
    if (mem_num > 9 || mem_num < 0) {
        cout << "Invalid input.";
    } else {
        cout << "Value at: " << mem_num << "(" << &memory[mem_num] << ") is: " ;
        cout << memory[mem_num];
    }
}

/*
Function to write value to memory
The user chooses a memory register (0-9) and enters any amount of text to be stored in that register.
The user indicates when to stop using a blank line of text.
*/
void writeValue(string* memory) {
    int writeAddress;
    string entered_text;
    cout << "Enter the memory register to write (0-9): ";
    cin >> writeAddress;
    cin.ignore();  // Ignore the newline character from previous input to avoid errors with getline
    getline(cin, entered_text);
    string* ptr;
    memory[writeAddress] = entered_text;
    if (memory[writeAddress] == entered_text) {
        cout << "The string was correctly entered.";
    } else {
        cout << "The string registry was invalid.";
    }
}

/*
Function to display the memory contents
Print the address of each memory register as well as the data stored there.
There will be MEMORY_SIZE registers in total.
*/
void displayMemory(string* memory) {
    for (int i = 0; i <= sizeof(memory)+1; i++) {
        cout << "Index: " << i << "\nAddress: " << &memory[i] << "\nValue: " << memory[i] << endl;
        
    }
}

/*
Function to search and print memory values containing the entered string.
The user enters a string to search for, and the program should print all
memory registers (with address) that contain the search string.

If the string is not found in memory, print:
"No memory values contain the entered string."
*/
void SearchMemory(string* memory) {
    /*
    First, you need to prompt the user for a string they want to search for. Then, for each register in memory, look to see if that string appears anywhere inside the data. If it does, you will need to print out the register in the same format as shown earlier. Finally, if the string does not appear anywhere in the data, you should print out a short message (e.g. "No memory values contain the entered string.").
    */
    string str_entered;
    cout << "What string do you want to search for?";
    cin >> str_entered;
    for (int i = 0; i < MEMORY_SIZE; i++) {
        if (memory[i].find(str_entered) != -1) {
            cout << "Index: " << i << "\nAddress: " << &memory[i] << "\nValue: " << memory[i] << endl;
            return;
        }
    }
    cout << "No memory values contain the entered string.";
}

/*
Given two string pointers, swap the values they point to.

This is not a call by reference function, which means that you need
to modify the values in memory, and you cannot change where the pointers
are pointing.
*/
void swapValues(string* str1, string* str2) {
    string temp = *str1;
    *str1 = *str2;
    *str2 = temp;
}

/*
This function asks the user for 2 memory registers, and swaps the values there. 
It should use the swapValues function to swap the values.
The only parameter is the computer memory.
If the users wants to swap multiple times, allow them to do so until they indicate
they do not want to.
*/
void rearrangeValues(string* memory){
    string answer = "Y";
    
    int reg1;
    int reg2;
    
    do {
        cout << "Enter the first register";
        cin >> reg1;
        cout << "Enter the second register";
        cin >> reg2;
    
        swapValues(&memory[reg1], &memory[reg2]);
        
        displayMemory(memory);
        
        cout << "Would you like to keep swapping variables?\nEnter N to exit";
        cin >> answer;
    }
    while (answer != "N");
        
}

int main() {
    string memory[MEMORY_SIZE];  // Computer memory
    int choice;
    do {
        cout << "Memory Visualization Program" << endl;
        cout << "1. Read value from memory" << endl;
        cout << "2. Write value to memory" << endl;
        cout << "3. Display memory contents" << endl;
        cout << "4. Search values in memory" << endl;
        cout << "5. Rearrange values in memory" << endl;
        cout << "6. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                readValue(memory);
                break;
            case 2:
                writeValue(memory);
                break;
            case 3:
                displayMemory(memory);
                break;
            case 4:
                SearchMemory(memory);
                break;
            case 5:
                rearrangeValues(memory);
                break;
            case 6:
                cout << "Exiting program..." << endl;
                break;
            default:
                cout << "Invalid choice!" << endl;
                break;
        }

        cout << endl;
    } while (choice != 6);

    return 0;
}