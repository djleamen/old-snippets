'''
Searching and Sorting
This program reads an array of integers from a file, and then performs a linear search on the array.

Uncomment "index = ..." line and "Value found at..." line in main to test linear search.
Uncomment "sortType(array)" line and "Array sorted in..." line in main to test selection and bubble,
change sortType to either selectionSort or bubbleSort.
'''

# Python needs to import time as a module, while in Java it is a part of System 
import time

def main():
    # readArray function is passed a String of the file's name in both Java and Python
    array = readArray("FILENAME")
    # Asking for current time is time.time() rather than System.nanoTime();
    start = time.time()
    
#   index = linearSearch(array, NUM)
#   sortType(array)

    end = time.time()
    elapsed = end - start
    elapsedMicroseconds = elapsed * 1000000 
    # print("Value found at", index, "in", elapsedMicroseconds, "microseconds.")
    # print("Array sorted in:", elapsedMicroseconds, "microseconds.")

def readArray(file_name):
    array = []
    try:
    # In Python, exceptions are caught using try-except, in Java, exceptions are caught using try-catch
    # "rb" means read binary, and is used in Python to indicate that it's reading a binary file
        with open(file_name, "rb") as file:
        # In Python code, a file is opened using open(), which automatically handles closing. In Java,
        # a file is opened using FileInputStream and closed explicitly 
            while True:
                element = file.read(4)
                if not element:
                    break
                # Py reads the file byte by byte and converts the bytes to integers using int.from_bytes()
                # Java uses FileInputStream, BufferedInputStream, and DataInputStream and reads ints using in.readInt().
                num = int.from_bytes(element, byteorder="big")
                array.append(num)
                 # arrays can dynamically grow as elements are appended using array.append(), while in Java, if arrays
                 # needs to be resized, a new array needs to be created with the desired length and elements copied 
    except FileNotFoundError as e:
        # Only one except statement used in Py to catch I/O errors, while in Java, two separate catch blocks are used
        # to catch FileNotFoundException and IOException individually
        print(e)
    return array

def linearSearch(array, num):
    for x in range(len(array)):
        if array[x] == num:
            return x
    return -1

def bubbleSort(array):
    swapped = True
    while swapped:
        swapped = False
        for i in range(1, len(array)):
            if array[i - 1] > array[i]:
                temp = array[i]
                array[i] = array[i - 1]
                array[i - 1] = temp
                swapped = True

def selectionSort(array):
    n = len(array)
    for i in range(n - 1):
        minIndex = i
        for j in range(i + 1, n):
            if array[j] < array[minIndex]:
                minIndex = j
        array[i], array[minIndex] = array[minIndex], array[i]

# must call to run the main method
if __name__ == "__main__":
    main()
