# Description: This file contains examples of conditional statements in Python

x = input("Please provide a number: ")
y = input("Please provide a second number: ")

x = int(x)
y = int(y)

if x % 2 == 0 and y % 2 == 0:
    print("Both numbers are even")
else:
    print("Both numbers are not even")


# Write a program that asks the user for a single mark (out of 100), and outputs the letter grade that corresponds to that mark
# Use the following ranges:
# 0-49: 	F
# 50-59: 	D
# 60-69: 	C
# 70-79: 	B
#80-100:    A

grade=input("What is your mark out of 100? ")
grade = int(grade)
if grade > 0 and grade < 50:
    print("Your Alpha grade is F")
elif grade > 49 and grade < 60:
    print("Your Alpha grade is D")
elif grade > 59 and grade < 70:
    print("Your Alpha grade is C")
elif grade > 69 and grade < 80:
    print("Your Alpha grade is B")
elif grade > 79 and grade <= 100:
    print("Your Alpha grade is A")
else: print("Invalid mark.")
