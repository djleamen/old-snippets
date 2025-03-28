# Description: This file contains examples of file handling in Python


def get_average_thriller_rating(filename):
    total_rating = 0
    num_of_books = 0
    with open(filename, 'r') as file:
        book = file.readline().strip()
        while book['Category'] == 'Thriller':
            total_rating = total_rating + book['Rating']
            num_of_books = num_of_books + 1
            average_rating = total_rating/num_of_books
        return average_rating

print(get_average_thriller_rating('filename'))

