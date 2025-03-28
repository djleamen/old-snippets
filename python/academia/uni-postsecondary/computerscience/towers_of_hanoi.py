# Description: Count the number of operations in the Towers of Hanoi problem

def towers_of_hanoi(n, start, end, temp, count=0):
    if n < 1:
        return count
    count = towers_of_hanoi(n - 1, start, temp, end, count)
    # print('Move 1 ring from', start, 'to', end) # count this line only
    count += 1
    count = towers_of_hanoi(n - 1, temp, end, start, count)
    return count

print('Counts for different values of n:')
for i in range(1, 9):
    print(f'n = {i}, print operations = {towers_of_hanoi(i, 1, 2, 3)}')

'''
Best guess: 2^n - 1
n   prints
----------
1   1
2   3
3   7
4   15
5   31
6   63
7   127
8   255
'''
