# Description: This file contains examples of conditional statements in Python

import math

'''
Write a recursive function (get_powers_of_2) that takes an integer 
(n) and returns a list of the powers of 2 between 2^0 and 2^n (inclusive).
'''
 
def get_powers_of_2(n):
    if n == 0:
        return [1]
    smaller_powers = get_powers_of_2(n-1)
    return smaller_powers + [int(math.pow(2, n))]

if __name__ == '__main__':

    print(f'2^0 .. 2^4: {get_powers_of_2(4)}')

    # output: [1, 2, 4, 8, 16]


    print(f'2^0 .. 2^10: {get_powers_of_2(10)}')

    # output: [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]