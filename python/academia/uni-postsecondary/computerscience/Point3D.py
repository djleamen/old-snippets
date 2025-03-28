# Description: This file contains examples of object-oriented programming in Python

import math

class Point3D:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def get_distance_to(self, Point3D):
        distance = math.sqrt(math.pow((self.x - Point3D.x), 2) + math.pow((self.y - Point3D.y), 2) + math.pow((self.z - Point3D.z), 2))
        return distance

if __name__ == '__main__':

    p1 = Point3D(2.0, 2.0, 4.0)

    p2 = Point3D(3.0, 1.0, 2.0)

 

    print(f'distance between p1 and p2: {p1.get_distance_to(p2)}')

    # output:  distance between p1 and p2: 2.449489742783178