// Description: This program reads a file and counts the number of times each word appears in the file.
#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
#include <cctype> // Include for std::tolower
using namespace std;

class WordCounter {
private:
    string word;
    int count;
public:
    WordCounter(string word) {
        this->word = word;
        count = 1;
    }

    string getWord() const {
        return this->word;
    }
    int getCount() const {
        return this->count;
    }

    void incrementWordCount() {
        this->count++;
    }
};

int findIndex(vector<WordCounter>& v, string& word) {
    for (int i = 0; i < v.size(); i++) {
        if (v[i].getWord() == word) {
            return i;
        }
    }
    return -1;
}

bool compareByCount(const WordCounter& a, const WordCounter& b) {
    return a.getCount() > b.getCount();
}

void printReport(vector<WordCounter>& v) {
    sort(v.begin(), v.end(), compareByCount);

    for (WordCounter& wc : v) {
        if (wc.getCount() > 1) { // Only print words with a count greater than 1
            cout << wc.getWord() << ": " << wc.getCount() << " times." << endl;
        }
    }
}

// Function to convert a string to lowercase
string toLowerCase(const string& str) {
    string lowerCaseStr;
    for (char ch : str) {
        lowerCaseStr += tolower(ch);
    }
    return lowerCaseStr;
}

int main() {
    vector<WordCounter> words;
    ifstream file("data.txt");
    string word;
    if (!file.is_open()) {
        cout << "Error opening file" << endl;
        return 1;
    }
    while (file >> word) {
        word = toLowerCase(word); // Convert word to lowercase
        int index = findIndex(words, word);
        if (index != -1) {
            words[index].incrementWordCount();
        } else {
            words.push_back(WordCounter(word));
        }
    }
    file.close();
    printReport(words);
    return 0;
}
