# Description: This file contains examples of object-oriented programming in Python

class Student:
    # gpa 
    # age
    # name
    # student num

    # do not forget self
    def __init__(self, student_num, age, name):
        self.set_student_num(student_num)
        self.__age = age # private variable uses double underscore before name
        self.name = name
        print('__init__ called')

    # getter
    def get_student_number(self):
        return self.student_num

    # setter
    def set_student_num(self, new_student_num):
        self.student_num = new_student_num
        print('set_student called')

    def encourage(self, message):
        print(f'{self.name}: {message}')
        
    def __str__(self):
        return f'{self.name} ({self.student_num})'

DJ = Student(100908864, 17, "DJ Leamen")
# print(f'{DJ.__age = }') yay enforced priv variable

DJ_string = str(DJ)
print(f'{DJ_string = }')

class Cat:
    def __init__(self, name, mass):
        self.__name = name
        self.__mass = mass
    
    def __str__(self):
        return f'{self.__name} weighs probably like {self.__mass} pounds. :('
    
    def __lt__(self, other_cat):
        return self.__mass < other_cat.__mass

rory = Cat("Rory", 20.0)
print(f'{str(rory) = }')