# Description: This script demonstrates the use of higher-order functions and lambda functions in Python.

# lambda review: a lambda function is a simple, nameless function used for a short task

from functools import reduce

invoice_items = [
    {'item_price': 10, 'quantity': 2},
    {'item_price': 5, 'quantity': 5},
    {'item_price': 20, 'quantity': 1}
]

total_cost = reduce(lambda total, item: total + item['item_price'] * item['quantity'], invoice_items, 0)

print(total_cost)


# Exercise 2
def myfilter(check, values):
    # Initialize an empty list, say filtered_values, to store the values that pass the check.
    filtered_values = []
    # Iterate over each element in values.
    for value in values:
        # Apply the check function to each element.
        if check(value):
            #  check returns True for an element, add that element to filtered_values.
            filtered_values.append(value)
    # Return filtered_values after the iteration is complete.
    return filtered_values

marks = [64.5, 87.0, 55.5, 94.0, 71.5, 46.0, 100.0]

a_grades = myfilter(lambda mark: mark > 80.0, marks)
print(a_grades)

