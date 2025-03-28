# Description: Divide and Conquer algorithm implementations in Python

def sum_dc(values):
    if len(values) == 0:
        return values
    mid = len(values) // 2
    left_half = values[:mid]
    right_half = values[mid:]
    left_sum = 0
    right_sum = 0
    for i in left_half:
        left_sum = left_sum+i
    for i in right_half:
        right_sum = right_sum+i
    return left_sum+right_sum

print(f'{sum_dc([106,5,-3,17,-21,39]) = }')
# expected output: 143

print(f'{sum_dc([1,2,3,4,5]) = }')
# expected output: 15

print(f'{sum_dc([8,14,2,-1,7]) = }')
# expected output: 30
