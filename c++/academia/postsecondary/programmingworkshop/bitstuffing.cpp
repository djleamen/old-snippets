// Description: This program reads a file and searches for a user-specified bit sequence.

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class Bits {
    private:
    string sequence;
    string packet;
    int count;

    public:
    Bits() {
        sequence = "";
        packet = "";
        count = 0;
    }

    // getters and setters
    void setSequence(string& s) {
        sequence = s;
    }
    
    string getSequence() {
        return sequence;
    }
    
    void setPacket(string& p) {
        packet = p;
    }
    string getPacket() {
        return packet;
    }
    
    void setCount(int c) {
        count = c;
    }
    
    int getCount() {
        return count;
    }

    void toString() {
        cout << "Sequence: " << sequence << ", Packet: " << packet << endl;
    }

    bool isEmpty() {
        return sequence.empty() || packet.empty();
    }
};

Bits* resize(Bits* arr, int& size) {
    Bits* newArr = new Bits[size + 1]; // allocate memory
    for (int i = 0; i < size; ++i) {
        newArr[i] = arr[i]; // copy existing elements
    }
    delete[] arr; // free old array's memory
    size++; 
    return newArr;
}

// call toString and show bit seq occurrences
void display(Bits* arr, int size) {
    for (int i = 0; i < size; ++i) {
        arr[i].toString();
    }
    cout << "Total occurrences of the bit sequence: " << size << endl;
}

Bits getBitsObject(string& word, string& userSequence) {
    Bits obj;
    if (word.find(userSequence) != string::npos) {
        obj.setSequence(userSequence);
        obj.setPacket(word); // assuming the whole word is the packet
    }
    return obj;
}

int main() {
    ifstream file("bits.txt");
    string userSequence;
    cout << "Enter the bit sequence to search for: ";
    cin >> userSequence;

    if (!file.is_open()) {
        cout << "Failed to open bits.txt" << endl;
        return 1;
    }

    Bits* bitArray = nullptr;
    int size = 0;
    string word;

    while (file >> word) {
        Bits bitObj = getBitsObject(word, userSequence);
        if (!bitObj.isEmpty()) {
            bitArray = resize(bitArray, size);
            bitArray[size - 1] = bitObj;
        }
    }

    if (size > 0) {
        cout << "Bit Stuffing report:" << endl;
        display(bitArray, size);
    } else {
        cout << "No occurrences of the bit sequence found." << endl;
    }

    delete[] bitArray; // free dynamically allocated memory
    file.close();
    return 0;
}