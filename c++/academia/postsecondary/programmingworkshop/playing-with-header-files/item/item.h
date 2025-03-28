#include <string>
using namespace std;

class Item {
public:
    int id;
    string name;
    int value;

    Item() {
        id = 0;
        name = "";
        value = 0;
    }
    
    Item(int id, string name, int value) : id(id), name(name), value(value) {}

    string to_string() {
        return "ID: " + std::to_string(id) + ", " + name + " (" + std::to_string(value) + ")";
    }
};

Item* resize(Item* arr, int* size);
Item search(int target, Item* arr, int size);
