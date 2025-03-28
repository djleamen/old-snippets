#include <iostream>
#include <fstream>
#include <string>
#include "item.h"

using namespace std;

int main() {
    ifstream file("items.txt");
    if (!file.is_open()) {
        cerr << "Failed to open items.txt" << endl;
        return 1;
    }
    
    Item* items = new Item[0];
    int size = 0;
    string line;

    while (getline(file, line)) {
        int id, value;
        string name;
        size_t pos1 = line.find(',');
        id = stoi(line.substr(0, pos1));
        size_t pos2 = line.find(',', pos1 + 1);
        name = line.substr(pos1 + 1, pos2 - pos1 - 1);
        value = stoi(line.substr(pos2 + 1));

        items = resize(items, &size);
        items[size - 1] = Item(id, name, value);
    }
    
    file.close();
    cout << "All data read from the file to the heap..." << endl;

    cout << "Search for an id: ";
    int searchId;
    cin >> searchId;

    try {
        Item foundItem = search(searchId, items, size);
        cout << foundItem.to_string() << endl;
    }
    catch (exception& e) {
        cout << e.what() << endl;
    }
    delete[] items;
    return 0;
}