# Description: This file contains examples of higher order functions in Python

def higher_order2(values, greater_than1):
    current_max = 0
    for i in range(len(values)):
        if greater_than1(i, i+1)==True:
            current_max = i
    return current_max

def greater_than1(x, y):
   return x * 2 > y * y

def greater_than1(x, y):
   return x * 2 > y * y
print(f'{higher_order2([2,1,3], greater_than1) = }')
# output: higher_order2([2,1,3], greater_than1) = 3

print(f'{higher_order2([-6,3,2], lambda x, y: x ** 2 > 4 * y) = }')
# output: higher_order2([-6,3,2], lambda x, y: x ** 2 > 4 * y) = 3
