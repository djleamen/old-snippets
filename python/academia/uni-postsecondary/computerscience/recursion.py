# Description: Recursion examples

def fibonacci(n):
    if n <= 1:
        return n
    else:
        return fibonacci(n-1) + fibonacci(n-2)

def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
    

def myfilter(check, values):
    if not values:
        return []
    if check(values[0]):
        return [values[0]] + myfilter(check, values[1:])
    else:
        return myfilter(check, values[1:])

# Test
marks = [64.5, 87.0, 55.5, 94.0, 71.5, 46.0, 100.0]
a_grades = myfilter(lambda mark: mark >= 80.0, marks)
print(a_grades)  # [87.0, 94.0, 100.0]

