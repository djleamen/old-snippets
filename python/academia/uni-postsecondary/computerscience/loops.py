# Description: This file contains examples of loops in Python

x = 0
while x < 10:
    print(f'{x} is small.')
    x = x + 1
    # x = x - 1 inf loop

print("While loop")
num = 5
while num <= 10:
    print(num)
    num += 1

print("For loop")
for num in [5, 6, 7, 8, 9, 10]:
    print(num)

print("For loop using range")
for num in range(5, 11):
    print(num)

print("For loop with step size")
for num in range(10, 200, 15):
    print(num)

print("For loop with neg step size")
for num in range(200, 10, -15):
    print(num)


import math

# Code challenge:
estimate = 0
n = 0
num_reps = 100
x = 1
# operator for exp in Python -> ** built-in
# but no built in factorial in Python without import math
for n in range(num_reps):
    term = x ** n / math.factorial(n)
    estimate += term
print(f'{estimate = }')

estimate = 0
n = 0
num_reps = 100
x = 1
# operator for exp in Python -> ** built-in
# but no built in factorial in Python without import math
for n in range(num_reps, 2):
    term = x ** n / math.factorial(n)
    estimate += term
print(f'{estimate = }')
