# Description: This file contains examples of higher-order functions in Python

from functools import reduce

invoice_items = [
    {'item_price': 10, 'quantity': 2},
    {'item_price': 20, 'quantity': 3},
    {'item_price': 5, 'quantity': 5},
    # Add more dictionary items as needed
]

total_cost = reduce(
    lambda acc, item: acc + item['item_price'] * item['quantity'],
    invoice_items,
    0  # Initial value for accumulator
)

print(total_cost)

def myfilter(check, values):
    result = []
    for value in values:
        if check(value):
            result.append(value)
    return result

marks = [64.5, 87.0, 55.5, 94.0, 71.5, 46.0, 100.0]
a_grades = myfilter(lambda mark: mark > 80.0, marks)
print(a_grades)  # [87.0, 94.0, 100.0]
