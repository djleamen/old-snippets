# Description: This program takes a sentence and processes each word in the sentence

def get_word_size(word):
    if word == "":
        return 0
    else:
        # return 1+what's left of the word every time u circle back and remove a letter
        return 1 + get_word_size(word[1:])

def process_sentence(sentence, process_word):
    words = sentence.split() 
    my_sentence_list = []
    for word in words:
        my_sentence_list.append(process_word(word))
    return my_sentence_list 

print(get_word_size("the quick brown fox jumped over the lazy dog"))
print(process_sentence("the quick brown fox jumped over the lazy dog", get_word_size))
