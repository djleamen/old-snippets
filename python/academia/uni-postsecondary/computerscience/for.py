# Description: This file contains examples of loops in Python

def main():
    # part 1

    pets = ['Spot', 'Boots', 'Mrs. Fluffington', 'Lenny', 'Bowser', 'Gina']
    count = 0
    pet_name_lengths = []
    
    for pet in pets:
        count += 1
        name_length = len(pet)
        pet_name_lengths.append(name_length)
            
    print(f'There are {count} pets in the list.')
    print(f'The word lengths of each pet name are {pet_name_lengths}.')

    # part 2

    words = ['the', 'quick', 'brown', 'fox', 'jumped', 'over', 'the', 'lazy', 'dog']
    word_vowel_ratios = []

    for word in words:
        vowel_count = 0
        consonant_count = 0

        for char in word:
            if char in 'aeiou':
                vowel_count += 1
            else:
                consonant_count += 1

        ratio = vowel_count / consonant_count
        word_vowel_ratios.append(ratio)
    
    print(f'The vowel to consonant ratios of each word are {word_vowel_ratios}.')

if __name__ == "__main__":
    main()