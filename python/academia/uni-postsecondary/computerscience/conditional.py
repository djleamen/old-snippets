# Description: This file contains examples of conditional statements in Python

def main():
    # part 1

    defense = 5
    attack = 8
    hp = 10

    if attack <= defense:
        print("No damage, since the defense stat is too high.")
    elif attack > defense:
        damage = attack - defense
        hp = hp - damage
        print(f"{damage} damage inflicted, enemy HP is now {hp}.")
        
    # part 2

    lab_mark = 9
    midterm_mark = 40
    final_exam_mark = 135
    alpha_grade = ' '

    scaled_lab_mark = (lab_mark/10.0)*30.0
    scaled_midterm_mark = (midterm_mark/80.0)*30.0
    scaled_final_exam_mark = (final_exam_mark/180.0)*40.0

    numerical_grade = scaled_midterm_mark+scaled_lab_mark+scaled_final_exam_mark

    if numerical_grade >= 0.0 and numerical_grade < 50.0:
        alpha_grade = "F"
    elif numerical_grade >= 50.0 and numerical_grade < 60.0:
        alpha_grade = "D"
    elif numerical_grade >= 60.0 and numerical_grade < 70.0:
        alpha_grade = "C"
    elif numerical_grade >= 70.0 and numerical_grade < 80.0:
        alpha_grade = "B"
    elif numerical_grade >= 80.0 and numerical_grade <= 100.0:
        alpha_grade = "A"
    else: 
        alpha_grade = "null"
        print("Invalid grade entered.")

    print(f"This student's final grade is {alpha_grade}.")

    #This student's final grade is B.


if __name__ == "__main__":
    main()

