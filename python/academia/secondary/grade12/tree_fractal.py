# DJ Leamen - Python Tree Fractal

import turtle
# Python uses the 'turtle' library to draw,
# while Java uses the built-in 'Graphics' class.

# The method signature in Python def tree(branch, draw) uses a turtle
# object 't' as a parameter while the Java method uses a Graphics 'g'
# object as a parameter to draw the tree.

def tree(branch, t):
    if branch > 5:
        t.forward(branch)
        t.right(20)
        tree(branch-15, t)
        t.left(40)
        tree(branch-15, t)
        t.right(20)
        t.backward(branch)
    # turtle moves around the screen and draws lines and circles using commands
    # like forward(), right(), left(), backward(). In contrast, the Java program
    # uses drawing methods like drawLine(), drawOval(), and drawRect().

# turtle can configure various aspects of the drawing,
# such as the speed, color, and line thickness.
# In contrast, Java uses instance variables and
# constructor parameters to control the tree's appearance.

turtle.speed("fastest")
turtle.left(90)
turtle.penup()
turtle.goto(0,-200)
turtle.pendown()
turtle.color("green")
tree(100, turtle)
turtle.done()

# Python automatically creates a window to display the drawing,
# while Java creates a JFrame object and adds the drawing
# to it. This allows for greater control over the appearance
# and behaviour of the window in Java.

# Python's turtle is automatically animated as it's being created
# because Py is based on immediate mode drawing. Java's graphics
# are based on retained mode drawing, meaning that the drawing
# is only shown after the recursive calls have reached their max level,
# when it is complete.
