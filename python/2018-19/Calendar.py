# Python program to display calendar of given month of the year

# import module
import calendar

yy = 2018
mm = 10

# To ask month and year from the user
# yy = int(input("Enter year: "))
# mm = int(input("Enter month: "))

if mm is 10:
    print ("Deej's birthday month!")

if mm is 4:
    print ("Aydin's birthday month!")

# display the calendar
print(calendar.month(yy, mm))
