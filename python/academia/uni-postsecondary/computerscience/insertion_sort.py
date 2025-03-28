# Description: This file contains examples of insertion sort in Python

def insertion_sort(values):
    comparison_count = 0
    for j in range(1, len(values)):
        key = values[j]
        i = j - 1
        while i >= 0:
            comparison_count += 1 
            if values[i] > key:
                values[i + 1] = values[i]
                i -= 1
            else:
                comparison_count += 1
                break
        values[i + 1] = key
    return values, comparison_count

'''
max_elements = 30
for length in range(1, max_elements):
    unsorted = list(range(length, 0, -1))
    sorted, num_comparisons = insertion_sort(unsorted)

    print(format(num_comparisons, '03d'), '*' * (num_comparisons // 10))
'''