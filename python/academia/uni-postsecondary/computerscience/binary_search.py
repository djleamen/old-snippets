# Description: Binary search algorithm implementation in Python

def binary_search(values, to_find, start_index, end_index, comparisons=0):
    comparisons += 1  
    if start_index > end_index:
        return comparisons, False

    middle = (start_index + end_index) // 2
    comparisons += 1  

    if values[middle] == to_find:
        return comparisons, True
    else:
        comparisons += 1  
        if values[middle] > to_find:
            return binary_search(values, to_find, start_index, middle - 1, comparisons)
        else:
            return binary_search(values, to_find, middle + 1, end_index, comparisons)

'''
max_elements = 10000000
for length in range(1, max_elements, 500000):
    values = list(range(1, (2 * length) + 1, 2))
    num_comparisons, found = binary_search(values, length + 1, 0, len(values) - 1)
    num_comparison_string = format(num_comparisons, '03d')
    print(num_comparison_string, '*' * (num_comparisons // 10))
'''