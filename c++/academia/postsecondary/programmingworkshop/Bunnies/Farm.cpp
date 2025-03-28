// Description: This program simulates a bunny farm where bunnies can reproduce and mutate.

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <ctime>
#include <cstdlib>
#include <algorithm>

std::vector<std::string> readBunnyNames(const std::string& filename) {
    std::vector<std::string> names;
    std::ifstream file(filename);
    if (file.is_open()) {
        std::string name;
        while (std::getline(file, name)) {
            names.push_back(name);
        }
    }
    file.close();
    return names;
}

class Bunny {
public:
    enum class Sex { Male, Female };
    enum class Color { White, Brown, Black, Spotted };

    Bunny(const std::vector<std::string>& names);
    Bunny(Color color, const std::vector<std::string>& names);

    Sex getSex() const;
    Color getColor() const;
    int getAge() const;
    bool isRadioactiveMutantVampireBunny() const;
    void ageOneYear();

public:
    static int getMaxAge() { return MAX_AGE; }
    void mutateToRadioactiveMutantVampireBunny();
    std::string getName() const { return name; }

private:
    static const int MAX_AGE = 10;

    Sex sex;
    Color color;
    int age;
    bool radioactiveMutantVampireBunny;
    std::string name;
};

Bunny::Bunny(const std::vector<std::string>& names) {
    sex = (rand() % 2 == 0) ? Sex::Male : Sex::Female;
    color = static_cast<Color>(rand() % 4);
    age = 0;
    radioactiveMutantVampireBunny = (rand() % 100) < 2;

    if (names.size() > 0) {
        name = names[rand() % names.size()];
    }
}
Bunny::Bunny(Color color, const std::vector<std::string>& names) : color(color) {
    sex = (rand() % 2 == 0) ? Sex::Male : Sex::Female;
    age = 0;
    radioactiveMutantVampireBunny = (rand() % 100) < 2;

    if (names.size() > 0) {
        name = names[rand() % names.size()];
    }
}

Bunny::Sex Bunny::getSex() const {
    return sex;
}

Bunny::Color Bunny::getColor() const {
    return color;
}

int Bunny::getAge() const {
    return age;
}

bool Bunny::isRadioactiveMutantVampireBunny() const {
    return radioactiveMutantVampireBunny;
}
void Bunny::ageOneYear() {
    age++;
}

void Bunny::mutateToRadioactiveMutantVampireBunny() {
    radioactiveMutantVampireBunny = true;
}

class Farm {
public:
    Farm(const std::vector<std::string>& names);
    void update();
    void printBunnies();
    void writeToFile(const std::string& filename);

private:
    std::vector<Bunny> bunnies;
    std::vector<std::string> names;

public:
    bool empty() const { return bunnies.empty(); }
    void printBunnies();
};

Farm::Farm(const std::vector<std::string>& names) : names(names) {
    for (int i = 0; i < 5; i++) {
        bunnies.push_back(Bunny(names));
    }
}

void Farm::update() {
    std::vector<Bunny> newBunnies;
    int maleCount = 0;
    int femaleCount = 0;
    int radioactiveCount = 0;
    for (auto& bunny : bunnies) {
        if (bunny.getAge() <= Bunny::getMaxAge()) {
            for (auto& bunny : bunnies) {
                bunny.ageOneYear();
                if (bunny.getAge() <= Bunny::getMaxAge()) {
                    if (bunny.getSex() == Bunny::Sex::Male) {
                        maleCount++;
                    } else {
                        femaleCount++;
                    }
                    if (bunny.isRadioactiveMutantVampireBunny()) {
                        radioactiveCount++;
                    }
                    if (bunny.getAge() >= 2 && bunny.getSex() == Bunny::Sex::Female) {
                        newBunnies.push_back(Bunny(bunny.getColor(), names));
                    }
                }
            }
        }

        if (maleCount >= 2 && femaleCount >= 1) {
            int numNewBunnies = std::min(maleCount / 2, femaleCount);
            for (int i = 0; i < numNewBunnies; i++) {
                newBunnies.push_back(Bunny(bunnies[0].getColor(), names));
            }
        }

        bunnies.insert(bunnies.end(), newBunnies.begin(), newBunnies.end());

        for (auto it = bunnies.begin(); it != bunnies.end();) {
            if (it->getAge() > Bunny::getMaxAge()) {
                it = bunnies.erase(it);
            } else {
                it++;
            }
        }

        if (radioactiveCount > 0) {
            int nonRadioactiveCount = bunnies.size() - radioactiveCount;
            if (nonRadioactiveCount > 0) {
                int indexToMutate = rand() % nonRadioactiveCount;
                int mutatedCount = 0;
                for (auto& bunny : bunnies) {
                    if (!bunny.isRadioactiveMutantVampireBunny()) {
                        if (mutatedCount == indexToMutate) {
                            bunny.mutateToRadioactiveMutantVampireBunny();
                            break;
                        }
                        mutatedCount++;
                    }
                }
            }
        }
    }
}

void Farm::printBunnies() {
    std::sort(bunnies.begin(), bunnies.end(), [](const Bunny& a, const Bunny& b) {
        return a.getAge() < b.getAge();
    });

    for (const auto& bunny : bunnies) {
        std::cout << "Name: " << bunny.getName() << ", Sex: ";
        if (bunny.getSex() == Bunny::Sex::Male) {
            std::cout << "Male, ";
        } else {
            std::cout << "Female, ";
        }
        std::cout << "Color: ";
        switch (bunny.getColor()) {
            case Bunny::Color::White:
                std::cout << "White, ";
                break;
            case Bunny::Color::Brown:
                std::cout << "Brown, ";
                break;
            case Bunny::Color::Black:
                std::cout << "Black, ";
                break;
            case Bunny::Color::Spotted:
                std::cout << "Spotted, ";
                break;
        }
        std::cout << "Age: " << bunny.getAge();
        if (bunny.isRadioactiveMutantVampireBunny()) {
            std::cout << ", Radioactive Mutant Vampire Bunny";
        }
        std::cout << std::endl;
    }
}

void Farm::writeToFile(const std::string& filename) {
    std::ofstream file(filename, std::ios::app);
    if (file.is_open()) {
        std::sort(bunnies.begin(), bunnies.end(), [](const Bunny& a, const Bunny& b) {
            return a.getAge() < b.getAge();
        });

        for (const auto& bunny : bunnies) {
            file << "Name: " << bunny.getName() << ", Sex: ";
            if (bunny.getSex() == Bunny::Sex::Male) {
                file << "Male, ";
            } else {
                file << "Female, ";
            }
            file << "Color: ";
            switch (bunny.getColor()) {
                case Bunny::Color::White:
                    file << "White, ";
                    break;
                case Bunny::Color::Brown:
                    file << "Brown, ";
                    break;
                case Bunny::Color::Black:
                    file << "Black, ";
                    break;
                case Bunny::Color::Spotted:
                    file << "Spotted, ";
                    break;
            }
            file << "Age: " << bunny.getAge();
           
            if (bunny.isRadioactiveMutantVampireBunny()) {
                file << ", Radioactive Mutant Vampire Bunny";
            }
            file << std::endl;
        }
        file.close();
    }
}

int main() {
    srand(static_cast<unsigned>(time(nullptr)));

    std::vector<std::string> names = readBunnyNames("bunny_names.txt");
    Farm farm(names);
    int year = 0;

    std::ofstream file("bunny_farm_log.txt");

    while (!farm.empty()) {
        farm.update();
        year++;
        std::cout << "Year " << year << ":\n";
        farm.printBunnies();
        farm.writeToFile("bunny_farm_log.txt");
    }
    return 0;
}