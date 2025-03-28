## Roman to Integer - Python

def romanToInt(roman):
    letters = {
        'I': 1,
        'V': 5,
        'X': 10,
        'L': 50,
        'C': 100,
        'D': 500,
        'M': 1000,
        'A': 0, 
        'B': 0,
        'E': 0,
        'F': 0,
        'G': 0,
        'H': 0,
        'J': 0,
        'K': 0,
        'N': 0,
        'O': 0,
        'P': 0,
        'Q': 0,
        'R': 0,
        'S': 0,
        'T': 0,
        'U': 0,
        'W': 0,
        'Y': 0,
        'Z': 0 }
    integer = 0
    oldValue = 0

    for letter in roman:
        value = letters[letter]
        if value > oldValue:
            integer += value - 2 * oldValue
        elif value == 0:
            integer = 0
        else:
            integer += value
        oldValue = value
    return integer

inputNum = input("Enter a Roman numeral: ")
# No need to import and declare a scanner, built in scanner function "input"

if romanToInt(inputNum) == 0:
    print("Invalid character entered.")
else:
    print(f"The corresponding Integer value is: {romanToInt(inputNum)}.")
