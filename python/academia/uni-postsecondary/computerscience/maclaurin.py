# Recursive Maclaurin series

# Note: max_n represents number of terms calculated to

# Step 1. Try doing it iteratively to understand WHAT the problem is

import math

def math2_iter(x, n, max_n):
    # Start with 0 for total until we return
    total = 0.0
    
    # Keep going until we reach exactly the max_n value (until we've done as many terms as we need)
    while n <= max_n:
        # Break it down!
        # 1. Give names to each bit of eq... for example (-1)^n can be called 'sign'
        # ...Use built in math.pow from math pow(base, exp)
        sign = math.pow(-1, n)
        # 2. We can call the x^2n 'exponent', do the same sort of thing
        exponent = math.pow(x, 2*n)
        # 3. Call the bottom of the equation, the factorial part, 'fact'
        fact = math.factorial(2*n)

        # All the parts are done, add bring it all together in the total
        total += (sign * exponent)/fact 

        # Increment by 1 each term
        n+=1

    return total

# Now that we know how it works iteratively, let's do it recursively
def math2(x, n, max_n):
    # All the math can stay the same, just change 'total' to a variable called 'term', without increment
    sign = math.pow(-1, n)
    exponent = math.pow(x, 2*n)
    fact = math.factorial(2*n)
    term = (sign * exponent)/fact 

    # Base case: if we're at the last term, return it
    if n==max_n:
        return term
    
    # Recursive case: while n has not reached max_n, we return the recursive call
    return term + math2(x, n+1, max_n)


print(f'{math2(0.0, n=0, max_n = 10) = }')
# output = math2(0.0, n=0, max_n = 10) = 1.0

print(f'{math2(0.5, n=0, max_n = 10) = }')
# output = math2(0.0, n=0, max_n = 10) = 0.87758...
