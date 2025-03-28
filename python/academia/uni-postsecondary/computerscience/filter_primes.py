import math

# Description: This file contains examples of recursion in Python

def is_prime(n):
    if n < 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    max_divisor = math.isqrt(n)
    for d in range(3, max_divisor + 1, 2):
        if n % d == 0:
            return False
    return True

def filter_primes(elements):
    if not elements:
        return []
    return [elements[0]] + filter_primes(elements[1:]) if is_prime(elements[0]) else filter_primes(elements[1:])

if __name__ == '__main__':

    print(f'filter_primes(range(20)): {filter_primes(range(20))}')

    # output:  filter_primes(range(20)): [1, 2, 3, 4, 5, 7, 11, 13, 17, 19]

 

    print(f'filter_primes(range(100,200)): {filter_primes(range(100,200))}')