# Description: This file contains the solutions to basic Python problem questions.

'''
Write a Python function (reverse_rec) that takes a single string and returns the reverse of 
that string using recursion.
'''
print("Question 1:")
def reverse_rec(str):
    if len(str) < 1:
        return str
    return reverse_rec(str[1:]) + str[0]

print('reverse_rec("CSCI 1030U"):', reverse_rec('CSCI 1030U'))
print('reverse_rec("Python!"):', reverse_rec('Python!'))


'''
Write a recursive function (palindrome_rec) that takes a string (str), and 
returns True or False, depending on whether or not str is a palindrome.
Note: The sample code below does not need to be copied into your answer. 
You are responsible for the function described above, and nothing else.

'''
print("Question 2:")
def palindrome_rec(str):
    if len(str) < 2:
        return True
    if str[0] != str[len(str)-1]:
        return False
    return palindrome_rec(str[1:len(str)-1])

print("is level a palindrone?",palindrome_rec("level"))
# True
print("is lever a palindrone?",palindrome_rec("lever"))
# False


'''
Write a class (Product) that has three instance variables:
- name (a string)
- price (a floating point number)
- weight (a floating point number)
Your class should also have four member functions:
1. a constructor – This function will store the values of the three instance variables, 
which will be passed as arguments in the order given above (see the sample output, below, for an example)
2. get_shipping_cost() – This function will calculate the cost of shipping the item using the formula:
- shipping_cost = weight * 10
3. get_tax() – This function will calculate the tax on the item (not including shipping):
- tax = price * 0.13
4. get_total_cost() – This function will calculate the total cost of the item:
- total_cost = price + tax + shipping_cost
'''
# Question 3
class Product:
    def __init__(self, name, price, weight):
        self.name = name
        self.price = price
        self.weight = weight
        
    def getShippingCost(self):
        return self.weight * 10

    def getTax(self):
        return self.price * 0.13
    
    def getTotalPrice(self):
        return self.price + self.getShippingCost() + self.getTax()

razor = Product("Electric Razor", 49.99, 0.25)
homeGym = Product("Home Gym", 879.99, 115.0)
print("total cost of", razor.name, ":", razor.getTotalPrice())
# 58.9887
print("total cost of", homeGym.name, ":", homeGym.getTotalPrice())
# 2144.3887


'''
Write a Python class (Student_Entry) that represents a student in our class. 
The following instance variables should be represented:
- labs: The mark for the student’s labs (out of 10)
- assignments: The mark for the student’s assignments (out of 20)
- midterm: The student’s midterm mark (out of 100)
- final: The student’s final exam mark (out of 100)
- name: The student’s full name
- sid: The student’s ID
Your class should include these methods:
1. A constructor: Given the name and student ID, initializes all of the variables 
(the marks should be zero initially)
2. get_average(): Uses the values of the given marks to calculate the student’s average. 
The assignment and lab marks are out of their correct weight, but the midterm is worth 30% 
and the final worth 40%, so those grades need to be scaled down accordingly.
3. letter_grade(): Returns the student’s grade (A,B,C,D,F), according to the following rules:
a. A: 80-100
b. B: 70-79
c. C: 60-69
d. D: 50-59
e. F: 0-49
4. A comparison function allowing two Student_Entry instances to be compared using the < (less than)
operator
Note: For this class, you can use any built-in functions that you’d like
'''
print("Question 4:")
class StudentEntry:
    def __init__(self, name, sid):
        self.labs = 0.0
        self.assignments = 0.0
        self.midterm = 0.0
        self.final = 0.0
        self.name = name
        self.sid = sid

    def average(self):
        return self.labs + self.assignments + (30.0*self.midterm)/100.0 + (40.0*self.final)/100.0
    
    def letterGrade(self):
        mark = self.average()
        print("Mark: ", mark)
        if mark < 50:
            return "F"
        elif mark < 60:
            return "D"
        elif mark < 70:
            return "C"
        elif mark < 80:
            return "B"
        else:
            return "A"

bsmith = StudentEntry("Bob Smith", "1000001")
bsmith.labs = 9.0
bsmith.assignments = 17.0
bsmith.midterm = 57.5
bsmith.final = 60.0
print("Bob Smith: ", bsmith.letterGrade())

sjones = StudentEntry("Sally Jones", "1000002")
sjones.labs = 9.5
sjones.assignments = 19.5
sjones.midterm = 81.0
sjones.final = 74.5
print("Sally Jones: ", sjones.letterGrade())


'''
Write a class (Dictionary) that implements a dictionary (also known as an associative array) 
using the list of tuples underlying data structure discussed in the lectures. This class will 
support the following methods:
- An initializer (initializes the list of tuples, initially empty)
- set (given key and value arguments, key being a string value, inserts a new key/value pair into the list)
- get (given a key string argument, will return the corresponding value in the dictionary)
- A string converter function (outputs the list of tuples)
Write another class (KeyNotFoundError) that you can use for an exception, which will be thrown when 
the get function is called with a key that isn't anywhere in the dictionary.
Note: For this class, you must implement the class using a list of tuples. An implementation that uses 
a dictionary will not be accepted.
'''
print('Question 5:')
class KeyNotFoundError(Exception):
    pass

class Dictionary:
    def __init__(self):
        self.keys_and_values = []

    def get(self, key_to_find):
        for key, value in self.keys_and_values:
            if key == key_to_find:
                return value
        
    def set(self, key, value):
        self.keys_and_values.append((key, value))

    def __str__(self):
        result = '['
        for key, value in self.keys_and_values:
            if len(result) == 1:
                result += f'({key}, {value})'
            else:
                result += f', ({key}, {value})'
                result += ']'
        return result

products = Dictionary()
products.set('RTX3060', 329.99)
products.set('RTX3070', 499.99)
products.set('RTX3080', 1499.99)
products.set('RTX3090', 1999.99)
print(f'products = {products}')
# output: products = [('RTX3060', 329.99), ('RTX3070', 499.99),
# ('RTX3080', 1499.99), ('RTX3090', 1999.99)]
print(f'RTX3090 = {products.get("RTX3090")}')
# output: RTX3090 = 1999.99

'''
try:
    print(f'RTX3050 = {products.get("RTX3050")}')
except KeyNotFoundError as error:
    print(f'Cannot find key: {error}')
# output: Cannot find key: Key not found: RTX3050
'''

'''
Write a function, math1, which calculates a specified number of terms from the following
MacLaurin series (like we did in section 03a):
The arguments of the function will be:
- x - the value to be used for x shown in the MacLaurin series
- n – the starting n value for which a term should be calculated
- max_n - the final n value for which a term should be calculated
'''
print('Question 6:')
import math
def math1(x, n, max_n):
    total = 0.0
    for i in range(n, max_n + 1):
        term = math.pow(-1, i) * math.pow(x, i) / math.factorial(i)
        total += term
    return total

print(f'{math1(0.0, n = 0, max_n = 10) = }')
# output: math1(0.0, n = 0, max_n = 10) = 1.0
print(f'{math1(0.5, n = 0, max_n = 10) = }')
# output: math1(0.5, n = 0, max_n = 10) = 0.606530659724375


'''
Write a function, math2, which calculates a specified number of terms from the following
MacLaurin series (like we did in section 03a):
The arguments of the function will be:
- x - the value to be used for x shown in the MacLaurin series
- n – the starting n value for which a term should be calculated
- max_n - the final n value for which a term should be calculated
'''
print('Question 7:')
def math2(x, n, max_n):
    total = 0.0
    for i in range(n, max_n + 1):
        term = 2 * math.pow(-1, i) * math.pow(x, 2 * i + 1) / (math.sqrt(math.pi) * (2 * i + 1) * math.factorial(i))
        total += term
    return total

print(f'{math2(0.0, n = 0, max_n = 10) = }')
# output: math2(0.0, n = 0, max_n = 10) = 0.0
print(f'{math2(0.5, n = 0, max_n = 10) = }')
# output: math2(0.5, n = 0, max_n = 10) = 0.5204998778130467