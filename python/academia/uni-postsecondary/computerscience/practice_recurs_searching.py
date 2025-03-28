# Description: Practice with recursion and searching

class NoMatchFoundException(Exception):
    pass

def has_no_repeated_letter(string, index=0):
    if index >= len(string) - 1:
        return True

    for next_index in range(index + 1, len(string)):
        if string[index] == string[next_index]:
            return False
    return has_no_repeated_letter(string, index + 1)

def get_longest_matching_substring(string, check_match):
    longest_match = ""
    for start in range(len(string)):
        for end in range(start, len(string) + 1):
            substring = string[start:end]
            if check_match(substring) and len(substring) > len(longest_match):
                longest_match = substring

    if not longest_match:
        raise NoMatchFoundException("No matching substring found")
    return longest_match

print('ablewasiereisawelba:', has_no_repeated_letter('ablewasiereisawelba'))
# expected output: ablewasiereisawelba: False

print('abcd:', has_no_repeated_letter('abcd'))
# expected output: abcd: True

print('longest with no repeated letter:', get_longest_matching_substring('i saw abba, but ablewasiereisawelba by the racecar', has_no_repeated_letter))
# expected output: longest with no repeated letter: ut ablew