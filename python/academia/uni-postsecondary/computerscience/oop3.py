# Description: This file contains examples of object-oriented programming in Python

'''
Write a set of three classes that represent:
A dog (Dog)
A cat (Cat)
A generic pet (Pet)

pets = [
  Dog('Rufus', 'Husky', 8.0, 'female'),
  Cat('Boots', 'Long hair', 3.2, 'male')
]
for pet in pets:
  pet.speak()

# Rufus: Woof!
# Boots: Meow!

'''

class Pet:
    def __init__(self, name, breed, age, sex):
        self.name = name
        self.breed = breed
        self.age = float(age)
        self.sex = sex

class Dog(Pet):
    def speak(self):
        return f"{self.name}: Woof!"

class Cat(Pet):
    def speak(self):
        return f"{self.name}: Meow!"
    
pets = [
  Dog('Rufus', 'Husky', 8.0, 'female'),
  Cat('Boots', 'Long hair', 3.2, 'male')
]

for pet in pets:
  print(pet.speak())
