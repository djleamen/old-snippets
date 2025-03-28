import math

class Shape:
    def get_area(self):
        pass
    def get_perimeter(self):
        pass

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = float(width)
        self.height = float(height)

    def get_area(self):
        return self.width * self.height

    def get_perimeter(self):
        return (self.width * 2) + (self.height * 2)

class Circle(Shape):
    def __init__(self, radius):
        self.radius = float(radius)

    def get_area(self):
        return math.pi * self.radius ** 2

    def get_perimeter(self):
        return 2 * math.pi * self.radius