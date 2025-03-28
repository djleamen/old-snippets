# Description: This file contains examples of object-oriented programming in Python

import unittest

class Product:

    def __init__(self, name, price, weight):

        self.name = name

        self.price = price

        self.weight = weight

 

    def get_shipping_cost(self):

        return self.weight * 10

 

    def get_tax(self):

        return self.price * 0.13

 

    def get_total_price(self):

        return self.price + self.get_shipping_cost() + self.get_tax()
    

class Product_Test(unittest.TestCase):
    def test_get_shipping_cost(self):
        laptop = Product("Macbook Pro", 5299.00, 2.13)
        self.assertEqual(laptop.get_shipping_cost(), 21.299999999999997)

    def test_get_tax(self):
        laptop = Product("Macbook Pro", 5299.00, 2.13)
        self.assertEqual(laptop.get_tax(), 688.87) 

    def test_get_total_price(self):
        laptop = Product("Macbook Pro", 5299.00, 2.13)
        self.assertEqual(laptop.get_total_price(), 6009.17)  

if __name__ == '__main__':
    unittest.main()