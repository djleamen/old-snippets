# Description: This file contains examples of conditional statements in Python

num1_s = input("Enter your first number: ")
num1 = int(num1_s)
num2_s = input("Enter your second number: ")
num2 = int(num2_s)
sum = (num1+num2)
result = sum % 5
print(result)


# Write a program that asks the user for a single mark (out of 100), and outputs the letter grade that corresponds to that mark

midterm_mark_s = input("Enter your midterm mark: ")
midterm_mark = int(midterm_mark_s)
lab_mark_s = input("Enter your lab mark: ")
lab_mark = int(lab_mark_s)
final_mark_s = input("Enter your final exam mark: ")
final_mark = int(final_mark_s)

mark = (midterm_mark / 80 * 30) + lab_mark + (final_mark / 180 * 40)
mark_s = str(mark)
print("Your overall mark in the course is: " + mark_s)