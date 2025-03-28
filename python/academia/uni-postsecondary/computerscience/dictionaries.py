# Description: Dictionary and list examples in Python

def main():
    # part 1

    names = ['Lucie Manette', 'Charles Darnay', 'Sydney Carton']
    test1_marks = [34.0, 75.5, 58.0]
    test2_marks = [47.5, 82.0, 63.5]

    student_data = []

    for i in range (len(names)):
        first_name, last_name = names[i].split()
    
        student_dict = {
            'first_name': first_name,
            'last_name': last_name,
            'test1': test1_marks[i],
            'test2': test2_marks[i]
              }
        
        student_data.append(student_dict)
        
    student_data.pop()

    print(student_data)

    # part 2

    character = {
        'name': 'Grimbor',
        'race': 'Dwarf',
        'class': 'Warrior',
        'hp': 58,
        'level': 9,
    }

    summary = f"{character['name']} (level {character['level']} {character['race']} {character['class']}) - HP: {character['hp']}"
    print(summary)


if __name__ == "__main__":
    main()