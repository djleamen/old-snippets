# Description: This file contains examples of sum of squares in Python

import math

def sum_of_squares(min, max):
    sum_of=0
    i=0
    while i<=max:
        sum_of += math.pow(i, 2)
        i = i+1
    return int(sum_of)


if __name__ == '__main__':

    print(f'sum_of_squares(1,10): {sum_of_squares(1,10)}')

    # output: sum_of_squares(1,10): 385

    print(f'sum_of_squares(1,5): {sum_of_squares(1,5)}')

    # output: sum_of_squares(1,5): 55