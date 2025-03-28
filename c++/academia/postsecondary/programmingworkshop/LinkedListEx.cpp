// Description: This program is a simple linked list program that allows the user to add, 
// delete, display, search, and process data for animals. The user can also find the oldest 
// animal in the list and delete the entire list. The program uses a struct to define the 
// Animal structure and a linked list to store the animals. The program provides a menu with 
// options to perform different operations on the list of animals.

#include <iostream>
#include <string>

using namespace std;

struct Animal{
    string name;
    int age;
    string species;
    Animal* next;
};

Animal* head = nullptr;

void searchAnimal();
Animal* searchAnimalRecursive(Animal* current, const string& name);
void deleteList();
void deleteListRecursive(Animal* current);
void deleteAnimal();
void deleteAnimalRecursive(Animal* previous, Animal* current, const string& name);

void addAnimal(Animal*& head) {
    Animal* newAnimal = new Animal();
    
    string name;
    int age;
    string species;
    cout << "Enter the name of the animal: ";
    cin >> name;
    cout << "Enter the age of the animal: ";
    cin >> age;
    cout << "Enter the species of the animal: ";
    cin >> species;
    
    //newAnimal a;
    newAnimal->name = name;
    newAnimal->age = age;
    newAnimal->species = species;
    
   newAnimal-> next = nullptr;
    
    if(head == nullptr){
        head = newAnimal;
    }
    
    else{
        Animal* last = head; //note for self: start at first node in the list
        while(last->next!=nullptr){ //note for self: the thing with linked lists is that you have to go through the WHOLE thing to find something in the list, rather than give an index.
            last = last->next; //note for self: move the last thing further down the list. we're looking for the last node so we can add at the end of the list.
        }
        
        last->next = newAnimal; //note for self: last element in list is pointing to the new node
    }    

    cout << "Animal added successfully." << endl;

}

void displayList(Animal*head) {
    while(head!=nullptr){
        cout << "Name: " << head->name << "\nAge: " << head->age << "\nSpecies: " << head->species << "\n----------" << endl;
        head = head->next; //change value of a to next node in the list
    }
}

void deleteAnimal() {
    if (head == nullptr) {
        cout << "The list is empty.";
        return;
    }

    string name;
    cout << "Enter the name of the animal to delete: " << endl;
    cin >> name;

    if (head->name == name) {
        Animal* temp = head;
        head = head->next;
        delete temp;
        cout << "Animal deleted successfully." << endl;
        return;
    }

    deleteAnimalRecursive(nullptr, head, name);
}

void deleteAnimalRecursive(Animal* previous, Animal* current, const string& name) {
    if (current == nullptr) {
        cout << "List is empty." << endl;
        return;
    }
    if (current->name == name) {
        previous->next = current->next;
        delete current;
        cout << "Animal deleted successfully." << endl;
    } else {
        deleteAnimalRecursive(current, current->next, name);
    }
}

void deleteList() {
    if (head == nullptr) {
        cout << "List is empty." << endl;
        return;
    }
    deleteListRecursive(head);
    head = nullptr;
    cout << "List deleted successfully." << endl;
}

void deleteListRecursive(Animal* current) {
    
    if(current == nullptr){
        return;
    }
    deleteListRecursive(current->next);
    delete current;
        
}

void searchAnimal() {
    
    if (head == nullptr) {
        cout << "The list is empty." << endl;
        return;
    }

    string name;
    cout << "Enter the name of the animal to search for: ";
    cin >> name;

    Animal* foundAnimal = searchAnimalRecursive(head, name);
    
        if (foundAnimal != nullptr) {
            cout << "Name: " << foundAnimal->name << "\nAge: " << foundAnimal->age << "\nSpecies: " << head->species << endl;
        }
        else {
            cout << "Animal not found." << endl;
        }
}

Animal* searchAnimalRecursive(Animal* current, const string& name) {
    if (current == nullptr) {
        return nullptr;
    }
    
    if (current->name == name) {
        return current;
    }
    
    return searchAnimalRecursive(current->next, name);
}

void processAnimalData(){
    if (head == nullptr) {
        cout << "The list is empty." << endl;
        return;
    }
    Animal* current = head;
    int totalAge = 0;
    int animalCount = 0;
    while(current!=nullptr){
        totalAge += current->age;
        animalCount += 1;
        current = current->next;
    }
    
    double averageAge = static_cast<double>(totalAge) / animalCount;

    cout << "Total animals: " << animalCount << endl;
    cout << "Average age: " << averageAge << endl;
}

void findOldestAnimal(Animal*& head) {
     Animal* oldestAnimal = head; //note for self: oldest animal would have to be the same type as head, not int so Animal* oldestAnimal
     while(head!=nullptr){
         if(oldestAnimal->age < head->age){
             oldestAnimal = head;
         }
        head = head->next;
    }
    cout << "Name: " << oldestAnimal->name << "\nAge: " << oldestAnimal->age << "\nSpecies: " << oldestAnimal->species << endl;
}

int main() {
    int choice;

    do {
        cout << "Menu:" << endl;
        cout << "1. Add an animal" << endl;
        cout << "2. Delete an animal" << endl;
        cout << "3. Display list of animals" << endl;
        cout << "4. Search for an animal" << endl;
        cout << "5. Process animal data" << endl;
        cout << "6. Find the oldest animal" << endl;
        cout << "7. Delete the entire list" << endl;
        cout << "8. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                addAnimal(head);
                break;
            case 2:
                deleteAnimal();
                break;
            case 3:
                displayList(head);
                break;
            case 4:
                searchAnimal();
                break;
            case 5:
                processAnimalData();
                break;
            case 6:
                findOldestAnimal(head);
                break;
            case 7:
                deleteList();
                break;
            case 8:
                cout << "Exiting the program." << endl;
                break;
            default:
                cout << "Invalid choice. Please try again." << endl;
        }

        cout << endl;
    } while (choice != 8);

    return 0;
}

