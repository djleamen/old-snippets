# Description: Python basics for CSCI 1030U

#name = input('What is your name? ')
name = 'DJ'

# Output and stupid formatting
print(8)
print('CSCI 1030U')
print("This is DJ's program") # note: using double quotes for this line b/c using apostrophe in text
greeting = f'Hello, {name}!'
print(greeting)

print('Hello', name, '!')
print('Hello', name, '!', sep = '|', end = 'END\n')
# sep and end is not necessary but it's nice for picky people
# it's Randy's illness it doesn't have to be yours

price = 29.99
cost_string = f'The cost of the item is {price} each, {price * 1.13} with tax.'
print(cost_string)
print(f'as of line 10: {price=}') # useful as debug tactic

# price = 'Teddy bear' # !!!! DON'T DO THIS?
price_type = type(price)
print(f'{price_type =}')

is_in_stock = True # isInStock do not use camelCase



import turtle
window = turtle.Screen()

jarnold = turtle.Turtle()
jarnold.penup()
jarnold.right(180)
jarnold.forward(200)
jarnold.pendown()
jarnold.right(90)
jarnold.forward(200)
jarnold.right(90)
jarnold.forward(100)
jarnold.right(60)
jarnold.forward(110)
jarnold.right(60)
jarnold.forward(120)
jarnold.right(60)
jarnold.forward(95)

jarnold.penup()
jarnold.right(180)
jarnold.forward(200)

jarnold.pendown()
jarnold.left(90)
jarnold.forward(200)
jarnold.right(180)
jarnold.forward(200)
jarnold.left(90)
jarnold.forward(150)


window.mainloop()
