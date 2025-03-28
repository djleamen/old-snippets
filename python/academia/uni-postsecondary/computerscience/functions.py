# Description: Function exercises in Python

# part 1

def is_vowel(character):
    if (character == 'a' or character == 'e' or character == 'i' or character=='o' or character == 'u'):
        return True
    else:
        return False

# part 2

def count_vowels_iter(sentence):
    count = 0
    for letter in sentence:
        if is_vowel(letter) == True:
            count = count + 1
    return count

# part 3

def count_vowels_rec(sentence):
    if len(sentence) == 0:
        return 0
    if is_vowel(sentence[0]):
        return 1 + count_vowels_rec(sentence[1:])
    else:
        return count_vowels_rec(sentence[1:])

# part 4

def my_reduce(values, start_val, op):
    current_val = start_val
    for value in values:
        current_val = op(current_val, value)
    return current_val  