# MyTime Py

# Python is dynamically typed, while Java is statically typed. This means
# that Python allows objects to change types at runtime, while Java
# requires objects to be defined with a specific type at compile time

# Description: A Python script that demonstrates object-oriented programming concepts

class MyTime:
    # Python uses the __init__ method as a constructor when creating objects
    # "self" keyword is Python version of Java's "this" keyword, but must be passed
    def __init__(self, hour=0, minute=0, second=0):
        if 0 <= hour < 24 and 0 <= minute < 60 and 0 <= second < 60:
            self.hour = hour
            self.minute = minute
            self.second = second
        else:
            return "ERROR"
        
    # The __str__ function is the equivalence of Java's toString() method
    def __str__(self):
        return f"{self.hour:02}:{self.minute:02}:{self.second:02}"

    def setTime(self, hour, minute, second):
        if 0 <= hour < 24 and 0 <= minute < 60 and 0 <= second < 60:
            self.hour = hour
            self.minute = minute
            self.second = second
        else:
            return "ERROR"

    # Process of defining setters/getters is the same, passing parameters to
    # the setters and simply putting a return statement for the getters
    def setHour(self, hour):
        if 0 <= hour < 24:
            self.hour = hour
        else:
            return "ERROR"

    def setMinute(self, minute):
        if 0 <= minute < 60:
            self.minute = minute
        else:
            return "ERROR"

    def setSecond(self, second):
        if 0 <= second < 60:
            self.second = second
        else:
            return "ERROR"

 # Python allows methods to return any type of value or no value at all,
 # while Java requires methods to return a specific type of value or "void"

    def getHour(self):
      return self.hour

    def getMinute(self):
      return self.minute

    def getSecond(self):
      return self.second
    
    def nextSecond(self):
        self.second = (self.second + 1) % 60
        if self.second == 0:
            self.nextMinute()

    def nextMinute(self):
        self.minute = (self.minute + 1) % 60
        if self.minute == 0:
            self.nextHour()

    def nextHour(self):
        self.hour = (self.hour + 1) % 24

    def previousSecond(self):
        self.second = (self.second - 1) % 60
        if self.second == 59:
            self.previousMinute()

    def previousMinute(self):
        self.minute = (self.minute - 1) % 60
        if self.minute == 59:
            self.previousHour()

    def previousHour(self):
        self.hour = (self.hour - 1) % 24


class TimeTester:
    def runTests(self):
        # Creating MyTime object does not require "new" keyword like Java
        newClock = MyTime()

    # **Set the time here**
        newClock.setTime(0, 0, 0)

        print(newClock)

        newClock.nextSecond()
        print(newClock)

        newClock.nextMinute()
        print(newClock) 

        newClock.nextHour()
        print(newClock)

        newClock.previousSecond()
        print(newClock)

        newClock.previousMinute()
        print(newClock)

        newClock.previousHour()
        print(newClock) 

# Python does not require a main class
tester = TimeTester()
tester.runTests()

# OTHER FACTS ABOUT PYTHON'S OOP:
#   - Python supports multiple inheritance, while Java only supports single inheritance
#   - Python supports "duck typing", which means that an object's suitability for a particular
#    role is determined by its behavior rather than its type, while Java requires objects
#    to be explicitly cast to a certain type before they can be used in a specific way
