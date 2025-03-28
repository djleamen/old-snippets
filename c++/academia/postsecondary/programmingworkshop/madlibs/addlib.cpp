// Description: This program adds a new MadLib story to the stories.txt file.

#include <iostream>
#include <fstream>
using namespace std;

int main() {
    string filename;
    cout << "Enter the filename containing the new Mad Lib story: ";
    cin >> filename;
    ifstream input_stream(filename);
    if (!input_stream) {
        cerr << "Failed to open " << filename << endl;
        return 1;
    }
    ofstream output_stream("stories.txt", ios::app);
    string line;
    while (getline(input_stream, line)) {
        output_stream << line << "\n";
    }
    output_stream << "---" << "\n";
    input_stream.close();
    output_stream.close();
    cout << "Story added to stories.txt!" << endl;
    return 0;
}

