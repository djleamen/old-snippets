# Description: This script implements a divide and conquer algorithm to find a target value in an array.

def dc_find(arr, target):
    if not arr:
        return False
    if len(arr) == 1:
        return arr[0] == target

    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]
    return dc_find(left_half, target) or dc_find(right_half, target)


if __name__ == '__main__':

    print(f'dc_find([1,13,5,7,9,15], 8) == {dc_find([1,13,5,7,9,15], 8)}')

    # output: dc_find([1,13,5,7,9,15], 8) == False

 

    print(f'dc_find([1,13,5,7,9,15], 13) == {dc_find([1,13,5,7,9,15], 13)}')

    # output: dc_find([1,13,5,7,9,15], 13) == True