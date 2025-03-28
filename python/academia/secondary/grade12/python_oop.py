''' 
OOP Concept Examples
Comments point out differences between Python and Java as part of assignment
'''

# Composition
class Car:
    # Constructor to initizalize an object uses __init__ function
    def __init__(self, engine):
        self.setEngine(engine)

    # Setters and Getters are not as common in Python as they are
    # in Java, and may not even need to be used, but they can still
    # be used by passing self and object params
    
    def setEngine(self, engine):
        self.engine = engine

    def getEngine(self):
        return self.engine

class Engine:
    # Constructor to initizalize object uses __init__ function
    def __init__(self, horsepower):
        self.setHorsepower(horsepower)

    def setHorsepower(self, horsepower):
        self._horsepower = horsepower

    def getHorsepower(self):
        return self._horsepower

engine = Engine(250)
car = Car(engine)

print(car.getEngine().getHorsepower())


# Inheritance, Upcasting, Overriding, Polymorphism
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print("Generic animal noises")

# To declare that class Cat inherits from class Animal, Python
# requires the type to be written in the brackets following the class
# name, before the colon, rather than the "extends" keyword that Java uses
class Cat(Animal):
    def __init__(self, name):
        # Like Java, Python subclasses use the keyword "super" to call to the
        # superclass of which it inherits, and also calls the superclass's __init__
        super().__init__(name)

    def speak(self):
        # Method overriding is done implicitly in Python without @Override
        print("Meow")

animal = Animal("Animal")
cat = Cat("Rory")

# Upcasting
animal = cat  
animal.speak() 

# Downcasting
# Python doesn't (fully) have the concept of downcasting, because objects in Python
# are dynamically typed and their type can change at runtime.. N/A

# Like Java, Python uses an if statement followed by a boolean isinstance
# to determine if Animal is an instance of a Cat
if isinstance(animal, Cat):
    cat = animal  
    cat.speak()
else:
    print("Confused aninmal noises")