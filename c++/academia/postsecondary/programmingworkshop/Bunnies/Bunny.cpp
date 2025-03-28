// Description: This program simulates a bunny breeding program.

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <string>

class Bunny {
public:
    enum class Sex { Male, Female };
    enum class Color { White, Brown, Black, Spotted };

    Bunny() {
        // Seed the random number generator
        srand(static_cast<unsigned>(time(nullptr)));

        // Randomly choose sex
        sex = rand() % 2 == 0 ? Sex::Male : Sex::Female;

        // Randomly choose color
        color = static_cast<Color>(rand() % 4);

        // Randomly choose age
        age = rand() % 11;

        // Randomly choose name
        static const std::vector<std::string> bunnyNames = {"Fluffy", "Thumper", "Bugs", "Snowball", "Cotton"};
        name = bunnyNames[rand() % bunnyNames.size()];

        // Randomly choose if radioactive mutant vampire bunny
        radioactive_mutant_vampire_bunny = (rand() % 100) < 2;
    }

    void display() {
        std::cout << "Name: " << name << std::endl;
        std::cout << "Sex: " << (sex == Sex::Male ? "Male" : "Female") << std::endl;
        std::cout << "Color: ";
        switch (color) {
            case Color::White:
                std::cout << "White";
                break;
            case Color::Brown:
                std::cout << "Brown";
                break;
            case Color::Black:
                std::cout << "Black";
                break;
            case Color::Spotted:
                std::cout << "Spotted";
                break;
        }
        std::cout << std::endl;
        std::cout << "Age: " << age << " years old" << std::endl;
        std::cout << "Radioactive mutant vampire bunny: " << (radioactive_mutant_vampire_bunny ? "true" : "false") << std::endl;
    }

private:
    Sex sex;
    Color color;
    int age;
    std::string name;
    bool radioactive_mutant_vampire_bunny;
};

int main() {
    Bunny bunny;
    bunny.display();
    return 0;
}
