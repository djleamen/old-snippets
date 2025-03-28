// Description: This program reads bit sequences from a file, allows the user to search 
// for a substring in the sequences, add new sequences, view all sequences, perform bit stuffing, 
// and store stuffed sequences to a file. The program also allows the user to destuff sequences 
// from a file.

#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;

string *bitSequences = nullptr;
int numSequences = 0;
    
int readFile(const string& filename) {
    ifstream file(filename);
    string line;
    int lineCount = 0;

    // First pass to count lines
    if (file.is_open()) {
        while (getline(file, line)) {
            lineCount++;
        }
        file.close();
    } else {
        return 0; // Return 0 if file cannot be opened
    }

    // Allocate dynamic array
    bitSequences = new string[lineCount];

    // Second pass to read lines
    file.open(filename);
    int i = 0;
    if (file.is_open()) {
        while (getline(file, bitSequences[i++])) {}
        file.close();
    }

    return lineCount; 
}

void writeFile(const string& filename, string stuffedBits) {
    ofstream file(filename);

    if (file.is_open()) {
        file << stuffedBits << endl;
        file.close();
    }
}

void searchBitSequence(const string& query) {
    for (int i = 0; i < numSequences; ++i) {
        if (bitSequences[i].find(query) != string::npos) {
            cout << "Found bit sequence at index " << i << ": " << bitSequences[i] << endl;
        }
    }
}


void enterNewBitSequence() {
    string newSequence;
    cout << "Enter a new bit sequence: ";
    cin >> newSequence;

    // Create new dynamic array with additional space
    string *newArray = new string[numSequences + 1];

    // Copy old sequences to new array
    for (int i = 0; i < numSequences; ++i) {
        newArray[i] = bitSequences[i];
    }

    // Add new sequence
    newArray[numSequences] = newSequence;

    // Delete old array and update pointer
    delete[] bitSequences;
    bitSequences = newArray;

    // Increment sequence count
    numSequences++;
}


/*
This function should print out all bit sequences in order.
*/
void viewAllBitSequences() {
    cout << "Bit Sequences:" << endl;
    for (int i = 0; i < numSequences; ++i) {
        cout << i << ": " << bitSequences[i] << endl;
    }
}


string performBitStuffing() {
    string delimiter = "01111110";
    string result = "";
    string temp;
    int count;

    for (int i = 0; i < numSequences; ++i) {
        temp = bitSequences[i];
        count = 0;
        for (char c : temp) {
            result += c;
            if (c == '1') {
                if (++count == 5) {
                    result += '0';
                    count = 0;
                }
            } else {
                count = 0;
            }
        }
        result += delimiter;
    }

    return result;
}

void destuffBitSequence() {
    ifstream file("data_transfer.txt");

    if (file.is_open()) {
        string fileContent;
        getline(file, fileContent);
        file.close();

        string delimiter = "01111110";
        vector<string> sequences;
        int startPos = 0;
        int endPos = fileContent.find(delimiter);

        // Extract and destuff sequences
        while (endPos != string::npos) {
            string sequence = fileContent.substr(startPos, endPos - startPos);
            string destuffedSequence = "";
            int count = 0;

            for (char c : sequence) {
                if (c == '1') {
                    count++;
                    destuffedSequence += c;
                } else {
                    if (count != 5) {
                        destuffedSequence += c;
                    }
                    count = 0;
                }
            }

            sequences.push_back(destuffedSequence);

            startPos = endPos + delimiter.length();
            endPos = fileContent.find(delimiter, startPos);
        }

        // Replace old array with new destuffed sequences
        delete[] bitSequences;
        numSequences = sequences.size();
        bitSequences = new string[numSequences];

        for (int i = 0; i < numSequences; ++i) {
            bitSequences[i] = sequences[i];
        }
    } else {
        cout << "Failed to open file: data_transfer.txt" << endl;
    }
}


int main() {
    string filename = "bit_sequences.txt";
    string transferFilename = "data_transfer.txt";
    string option;

    numSequences = readFile(filename);

    while (true) {
        cout << "Options:\n";
        cout << "1. Search bit sequences for a substring\n";
        cout << "2. Enter a new bit sequence\n";
        cout << "3. View all bit sequences\n";
        cout << "4. Examine stuffed bit sequence\n";
        cout << "5. Store stuffed bit sequence to file\n";
        cout << "6. Destuff bit sequence from file\n";
        cout << "Enter your option (or 'q' to quit): ";

        cin >> option;

        if (option == "1") {
            string query;
            cout << "Enter the substring to search for: ";
            cin >> query;
            searchBitSequence(query);
        } else if (option == "2") {
            enterNewBitSequence();
        } else if (option == "3") {
            viewAllBitSequences();
        } else if (option == "4") {
            string stuffedData = performBitStuffing();
            cout << "Bit-stuffed data: " << stuffedData << endl;
        } else if (option == "5") {
            string stuffedData = performBitStuffing();
            cout << "Generated data: " << stuffedData << endl;
            writeFile(transferFilename, stuffedData);
            cout << "Data written to file: " << transferFilename << endl;
        } else if (option == "6") {
            cout << "Destuffing bit sequences... ";
            destuffBitSequence();
            cout << "Destuffed. " << endl;
            viewAllBitSequences();
        } else if (option == "q") {
            break;
        } else {
            cout << "Invalid option. Please try again.\n";
        }

        cout << endl;
    }

    delete[] bitSequences;

    return 0;
}