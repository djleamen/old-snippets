'''
Description: Practice programming problems

Question	1
Create a function (drawSquare) that, for any positive integer n, prints an nxn square made of ‘*’ symbols.
Example output for n = 4:
>>> drawSquare(4)
****
****
****
****
Example output for n = 3:
>>> drawSquare(3)
***
***
***
Hint: You can draw a single * character without going to the next line using the following line of code (note the
comma at the end of the line, which prevents the new line):
print "*" '''

def drawSquare(n):
    if n <= 0:
        print("Please enter a positive integer.")
        return
    
    for _ in range(n):
        print("*" * n),

drawSquare(4)

'''
Question	2
Create a function (drawTriangle) that, given any positive integer n, prints an nxn triangle made of ‘*’ symbols.
Example output for n = 4:
>>> drawTriangle(4)
*
**
***
****
Example output for n = 3:
>>> drawTriangle(3)
*
**
***
'''
def drawTriangle(n):
    if n <= 0:
        print("Please enter a positive integer.")
        return
    
    for i in range(1, n + 1):
        print("*" * i),

drawTriangle(3)

'''
Question	3
Create a function (drawTriangle2) that, given any positive integer n, prints an upside-down nxn triangle made of
‘*’ symbols.
Example output for n = 4:
>>> drawTriangle2(4)
****
 ***
 **
 *
Example output for n = 3:
>>> drawTriangle2(3)
***
 **
 *
Hint: You can draw a single * character without going to the next line using the following line of code (note the
comma at the end of the line, which prevents the new line):
print "*",
'''
def drawTriangle2(n):
    if n <= 0:
        print("Please enter a positive integer.")
        return
    
    for i in range(n, 0, -1):
        spaces = ' ' * (n - i)
        stars = '*' * i
        print(spaces + stars)

drawTriangle2(3)

'''
Question	4
Create a function (jumpMaximum) that, given any list of integers list, returns a list with the same elements as
list, except that the first element has been swapped with the maximum element in list.
Note: This function should not print the list, but return it.
Example output for list = [1,2,3,4]:
>>> jumpMaximum([1,2,3,4])
[4,2,3,1]
Example output for list = [5,8,3,21,7,4,14]:
>>> jumpMaximum([5,8,3,21,7,4,14])
[21,8,3,5,7,4,14]
'''
def jumpMaximum(lst):
    maximum = 0
    first = lst[0]
    max_index = 0
    for i in range(len(lst)):
        if lst[i] > maximum:
            maximum = lst[i]
            max_index = i
    
    lst[0] = maximum
    lst[max_index] = first
    return lst

print(jumpMaximum([1, 4, 2, 100, 1, 23, 7]))


'''
Question	5
Create a function (doubleList) that, given any list of floating point numbers list, returns a list where every
element of the output list corresponds to the element at the same position in list, but doubled (times two).
Note: This function should not print the list, but return it.
Example output for list = [1,2,3,4]:
>>> doubleList([1,2,3,4])
[2,4,6,8]
Example output for list = [5,8,3,21,7,4,14]:
>>> doubleList([5,8,3,21,7,4,14])
[10,16,6,42,14,8,28]
'''

def doubleList(lst):
    for i in range(len(lst)):
        lst[i] = lst[i]*2
    return lst

print(doubleList([1, 4, 2, 100, 1, 23, 7]))

'''
Question	6
Create a function (sublistInRange) that, given a list of floating point numbers, list, and two numbers (min
and max), returns a list (or modify list) where the elements of the output list corresponds to the elements of list
that are greater than or equal to min and less than or equal to max.
Note: This function should not print the list, but return it.
Example output for list = [1,2,3,4,5], min = 2, and max = 4:
>>> sublistInRange([1,2,3,4,5], 2, 4)
[2,3,4]
Example output for list = [5,8,3,21,7,4,14], min = 4, and max = 14:
>>> sublistInRange([5,8,3,21,7,4,14], 4, 14)
[5,8,7,4,14]
'''

def sublistInRange(lst, min, max):
    new_list = []
    for i in lst[min-1:max]:
        new_list.append(i)
    return new_list

print(sublistInRange([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 6, 10))

'''
Question	7
Write a function (drawParallelogram) that takes a single argument, n, and draws a parallelogram with height n
and width n, as shown in the sample output, below:
Note: You can use any built-in functions and operators that you want for this question.
Example output:
>>> drawParallelogram(7)
*******
 *******
 *******
 *******
 *******
 *******
 *******
>>> drawParallelogram(5)
*****
 *****
 *****
 *****
 *****
 '''

def drawParallelogram(n):
    for i in range(n):
        for j in range(i):
            print(" ", end="")
        for k in range(n):
            print("*", end="")
        print()

drawParallelogram(7)
drawParallelogram(5)

'''
Question	8
Write a function (countLessThan) that takes a number (max) and a list of numbers (list), and returns all the
numbers in list that are strictly less than (<) max.
Note: The sample code below does not need to be copied into your answer. You are responsible for the function
described above, and nothing else.
Permitted Functions for this question
Sample Output:
list = [7,21,4,19,6,31,16,20,11]
print("how many values in", list, "<", 7, "?", countLessThan(list, 7))
# 2
print("how many values in", list, "<", 20, "?", countLessThan(list, 20))
# 6
print("how many values in", list, "<", 2, "?", countLessThan(list, 2))
# 0
Function name Description
range(x, y, z) Returns a list of numbers between x and y (not including y), spaced apart by z.
Example:
range(0, 7, 2) => [0,2,4,6]
len(s) Returns the number of elements in the list s.
Example:
len([0,2,4,6]) => 4
'''

def countLessThan(lst, max):
    count = 0
    for i in range(len(lst)):
        if lst[i] < max:
            count = count + 1
    return count
        

my_list = [12, 1, 6, 23, 3, 6, 8, 9, 1, 23, 4]
print("how many values in", my_list, "<", 7, "?", countLessThan(my_list, 7))

'''
Question	9
Write a function that takes two lists, and returns True if the first list is a reordering of the same elements as the second
list, and False otherwise.
Note: You can use any built-in functions and operators that you want for this question.
Example output:
>>> isReordering([4,1,3,2],[1,2,3,4])
True
>>> isReordering([5,8,3,21],[5,21,8])
False
'''

def isReordering(list1, list2):
    sorted_list1 = sorted(list1)
    sorted_list2 = sorted(list2)
    
    return sorted_list1 == sorted_list2

'''
Question	10
Write a function that takes two lists, and returns True if the first list is the reverse of the same elements in the second
list, and False otherwise.
Note: You can use any built-in functions that you want for this question.
Example output:
>>> isReverse([4,3,2,1],[1,2,3,4])
True
>>> isReverse([4,1,3,2],[1,2,3,4])
False '''

def isReverse(list1, list2):
    reversed_list1 = list(reversed(list1))
    return reversed_list1 == list2
    
print(isReverse([4,1,3,2],[1,2,3,4]))
print(isReverse([4,3,2,1],[1,2,3,4]))

def drawDiamond(n):
    if n % 2 == 0:
        n += 1  # Make sure n is odd to create a proper rhombus

    for i in range(n):
        spaces = abs(n // 2 - i)
        stars = n - 2 * spaces
        print(" " * spaces + "*" * stars)

# Let's test it with n = 5
drawDiamond(5)

