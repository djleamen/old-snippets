# Write some code that takes a full name (format: First Last), and separates the two names 
# into their own variables

full_name = "DJ Leamen"
first = ''
last = ''
first_name_done = False

for char in full_name:
    if char == ' ':
        first_name_done = True
    elif not first_name_done:
        # first = first + char
        first += char
    else:
        last += char

print(f'"{first}","{last}"')

marks = [10.0, 7.5, 8.0, 7.0, 8.25]
mark_sum = 0.0
for mark in marks:
    mark_sum+=mark

print(f'{mark_sum/len(marks) = }')

# Coding challenge for hw