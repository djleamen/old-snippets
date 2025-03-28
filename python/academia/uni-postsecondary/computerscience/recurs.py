# Description: This file contains examples of recursion in Python

def multiply(n):
    return n*n

def map_rec(op, values):
    if len(values) < 1:
        return []
    else:
        return [op(values[0])] + map_rec(op, values[1:])
    
# Test code print(map_rec(multiply, [1,2,3,4,5]))