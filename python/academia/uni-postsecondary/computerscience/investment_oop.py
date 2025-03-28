# Description: This program demonstrates the use of object-oriented programming in Python.

class Investment:
    def __init__(self, value, name):
        self.value = value
        self.name = name

    def __str__(self):
        return f"Name: {self.name} (value: ${self.value:.2f})"

    def get_value(self):
        return self.value

    def set_value(self, new_value):
        self.value = new_value

class SavingsAccount(Investment):
    def __init__(self, value, name, annual_interest_rate):
        super().__init__(value, name)
        self.annual_interest_rate = annual_interest_rate

    def calculate_growth(self):
        monthly_interest = self.value * (self.annual_interest_rate / 12)
        self.set_value(self.value + monthly_interest)

class SP500(Investment):
    def __init__(self, value, name, current_growth_rate):
        super().__init__(value, name)
        self.current_growth_rate = current_growth_rate

    def set_current_growth_rate(self, current_growth_rate):
        self.current_growth_rate = current_growth_rate

    def calculate_growth(self):
        growth = self.value * self.current_growth_rate
        self.set_value(self.value + growth)

investment1 = SavingsAccount(1000.00, 'Pro Savings Plus', 0.055)
investment1.calculate_growth() 
investment1.calculate_growth()
print(f'{str(investment1)}')

investment2 = SP500(1000.00, 'Standard and Poor 500', 0.063)
investment2.calculate_growth() 
investment2.set_current_growth_rate(0.058)
investment2.calculate_growth() 
print(f'{str(investment2)}')
