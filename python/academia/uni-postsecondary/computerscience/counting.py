# Description: Counting the number of operations in matrix multiplication

def matrix_multiply(m1, m2, n, count = 0):
    result = []
    for r in range(n):
        new_row = []
        for c in range(n):
            new_row.append(0)
            for i in range(n):
                new_row[c] += m1[r][i] * m2[i][c]
                count = count+1
        result.append(new_row)
    return count

def generate_n_by_n_matrix(n):
    result = []
    for r in range(n):
        row = []
        for c in range(n):
            row.append(r * n + c)
        result.append(row)
    return result

import random

for n in range(20, 201, 20):
    matrix1 = generate_n_by_n_matrix(n)                          # generate an nxn matrix (matrix1)
    matrix2 = generate_n_by_n_matrix(n)                          # generate an nxn matrix (matrix2)
    result = round(matrix_multiply(matrix1, matrix2, n)/100000)  # multiply the two matrices
    

    print("*" * result)

# Best guess: O(n^3)