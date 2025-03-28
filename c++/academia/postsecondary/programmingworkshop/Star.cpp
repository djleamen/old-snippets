// Description: This program demonstrates the use of operator overloading and friend functions in C++.

#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

class Star {
private:
    double brightness;
    double mass;
    string name;

public:
    Star(string n, double b, double m) : name(n), brightness(b), mass(m) {}

    // Getter functions
    double getBrightness() const { return brightness; }
    double getMass() const { return mass; }
    string getName() const { return name; }

    // Friend functions
    friend void compareStars(const Star& star1, const Star& star2);
    friend ostream& operator<<(ostream& os, const Star& star);
    friend void outputStarAttributes(const class Galaxy& galaxy, int index);
};

// Overloading the << operator for Star objects
ostream& operator<<(ostream& os, const Star& star) {
    os << "Name: " << star.name << ", Brightness: " << star.brightness << ", Mass: " << star.mass;
    return os;
}

// Compare function for two stars
void compareStars(const Star& star1, const Star& star2) {
    cout << "Comparing " << star1.getName() << " and " << star2.getName() << ":\n";
    if (star1.getBrightness() > star2.getBrightness()) {
        cout << star1.getName() << " is brighter.\n";
    } else if (star1.getBrightness() < star2.getBrightness()) {
        cout << star2.getName() << " is brighter.\n";
    } else {
        cout << "Both stars have equal brightness.\n";
    }

    if (star1.getMass() > star2.getMass()) {
        cout << star1.getName() << " has more mass.\n";
    } else if (star1.getMass() < star2.getMass()) {
        cout << star2.getName() << " has more mass.\n";
    } else {
        cout << "Both stars have equal mass.\n";
    }
}

class Galaxy {
private:
    vector<Star> stars;

public:
    // Add a star to the galaxy
    void addStar(const Star& star) {
        stars.push_back(star);
    }

    // Overload the [] operator to access stars
    Star& operator[](int index) {
        return stars[index];
    }

    const Star& operator[](int index) const {
        return stars[index];
    }

    // Overload the () operator to output galaxy information
    void operator()() const {
        cout << "Galaxy Information:\n";
        cout << "Number of Stars: " << stars.size() << endl;
    }

    friend void outputStarAttributes(const Galaxy& galaxy, int index);
};

// Output the attributes of a star at a given index
void outputStarAttributes(const Galaxy& galaxy, int index) {
    if (index >= 0 && index < galaxy.stars.size()) {
        const Star& star = galaxy.stars[index];
        cout << "Star " << index << ": " << star << endl;
    } else {
        cout << "Star index out of range." << endl;
    }
}

int main() {
    Galaxy galaxy;
    galaxy.addStar(Star("Sirius", 1.5, 2.1));
    galaxy.addStar(Star("Vega", 3.3, 2.8));

    // Outputting Galaxy Information
    galaxy();

    // Accessing and outputting attributes of a specific Star
    outputStarAttributes(galaxy, 0);
    outputStarAttributes(galaxy, 1);

    return 0;
}
