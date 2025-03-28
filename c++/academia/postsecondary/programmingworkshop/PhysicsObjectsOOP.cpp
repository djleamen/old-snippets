// Description: This program demonstrates the use of classes and objects in C++. 
// It defines a PhysicsObject class with properties like velocity and mass, and a PhysicsObjectCollection class to store multiple PhysicsObjects.

#include <iostream> // Include the standard I/O library for input and output operations
#include <string>   // Include the string library
#include <cstdlib>  // Include the standard library for general purpose functions
#include <ctime>    // Include the library for time functions
using namespace std; // Use the standard namespace

class PhysicsObject {
public:
    double vel_x, vel_y, vel_z; // Declare variables for velocity in the x, y, and z directions
    double mass;                // Declare variable for mass

    // Default constructor
    PhysicsObject() {
        randomizePhysicsObject(*this); // Call the randomize function to initialize the object with random values
    }

    // Parameterized constructor
    PhysicsObject(double vx, double vy, double vz, double m) : vel_x(vx), vel_y(vy), vel_z(vz), mass(m) {}

    // Static method to randomize a PhysicsObject's properties
    static void randomizePhysicsObject(PhysicsObject &obj) {
        obj.vel_x = rand() % 1000 + 1; // Randomize velocity in x direction
        obj.vel_y = rand() % 1000 + 1; // Randomize velocity in y direction
        obj.vel_z = rand() % 1000 + 1; // Randomize velocity in z direction
        obj.mass = rand() % 100 + 1;   // Randomize mass
    }

    // Method to convert the object's properties to a string
    string toString() {
        return "Velocity: (" + to_string(vel_x) + ", " + to_string(vel_y) + ", " + to_string(vel_z) + "), Mass: " + to_string(mass);
    }
};

// Function to combine two PhysicsObjects into one
string combinePhysicsObjects(PhysicsObject obj1, PhysicsObject obj2) {
    PhysicsObject combined(obj1.vel_x + obj2.vel_x, 
                           obj1.vel_y + obj2.vel_y, 
                           obj1.vel_z + obj2.vel_z, 
                           obj1.mass + obj2.mass);  // Combine the properties of both objects
    return combined.toString(); // Return the combined object as a string
}

// Function to calculate the momentum of a PhysicsObject
double* GetMomentum(PhysicsObject& obj) {
    double* momentum = new double[3]; // Allocate memory for momentum array
    momentum[0] = obj.vel_x * obj.mass; // Calculate momentum in x direction
    momentum[1] = obj.vel_y * obj.mass; // Calculate momentum in y direction
    momentum[2] = obj.vel_z * obj.mass; // Calculate momentum in z direction
    return momentum; // Return the momentum array
}

class PhysicsObjectCollection {
private:
    PhysicsObject* objects; // Pointer to an array of PhysicsObjects
    int size;               // Variable to store the number of objects

public:
    // Constructor
    PhysicsObjectCollection(int n) : size(n) {
        objects = new PhysicsObject[size]; // Allocate memory for the array of PhysicsObjects
    }

    // Destructor
    ~PhysicsObjectCollection() {
        delete[] objects; // Deallocate memory for the array of PhysicsObjects
    }

    // Method to add a new object to the collection
    void addObject(PhysicsObject* newObject) {
        PhysicsObject* newObjects = new PhysicsObject[size + 1]; // Allocate memory for a new array with one more element
        for (int i = 0; i < size; i++) {
            newObjects[i] = objects[i]; // Copy existing objects to the new array
        }
        newObjects[size] = *newObject; // Add the new object
        delete[] objects; // Deallocate the old array
        objects = newObjects; // Point to the new array
        size++; // Increase the size
    }

    // Method to delete an object from the collection
    void deleteObject(PhysicsObject* objectToDelete) {
        int indexToDelete = -1;
        // Find the index of the object to delete
        for (int i = 0; i < size; i++) {
            if (&objects[i] == objectToDelete) {
                indexToDelete = i;
                break;
            }
        }

        // If the object was not found, return
        if (indexToDelete == -1) {
            return;
        }

        PhysicsObject* newObjects = new PhysicsObject[size - 1]; // Allocate memory for a new array with one less element

        // Copy all objects except the one to delete
        for (int i = 0, j = 0; i < size; i++) {
            if (i != indexToDelete) {
                newObjects[j++] = objects[i];
            }
        }

        delete[] objects; // Deallocate the old array
        objects = newObjects; // Point to the new array
        size--; // Decrease the size
    }

    // Overload the subscript operator to access objects in the collection
    PhysicsObject& operator[](int index) {
        return objects[index];
    }

    // Method to get the size of the collection
    int getSize() const {
        return size;
    }
};

int main() {
    srand(static_cast<unsigned int>(time(nullptr))); // Seed the random number generator with the current time

    PhysicsObject obj1, obj2; // Create two PhysicsObjects
    cout << "Object 1: " << obj1.toString() << endl; // Print the details of object 1
    cout << "Object 2: " << obj2.toString() << endl; // Print the details of object 2

    cout << "Combined Object: " << combinePhysicsObjects(obj1, obj2) << endl; // Print the details of the combined object

    // Demonstrating PhysicsObjectCollection
    PhysicsObjectCollection collection(0); // Create a collection of PhysicsObjects with initial size 0
    collection.addObject(&obj1); // Add object 1 to the collection
    collection.addObject(&obj2); // Add object 2 to the collection

    cout << "Physics Object Collection: " << endl;
    // Print the details of each object in the collection
    for (int i = 0; i < collection.getSize(); i++) {
        cout << "Object " << i + 1 << ": " << collection[i].toString() << endl;
    }

    return 0; // End of the program
}
