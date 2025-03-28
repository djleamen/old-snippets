// Description: This program calculates the pay of an employee.

#include <iostream>

using namespace std;

class Employee
{
    protected:
    string name;
    double salary;
    
    // Parameterized Constructor
    
    public:
    virtual double calculateSalary() {
        return salary;
    }
};

class FullTime : public Employee {
    public:
    FullTime(string name, double salary) {
        this->name = name;
        this->salary = salary;
    }

};

class PartTime : public Employee
{
    private:
    int hoursWorked;
    int hoursRate;

    public:
    PartTime(string name, int hoursWorked, int hoursRate) {
        this->name = name;
        this->hoursWorked = hoursWorked;
        this->hoursRate = hoursRate;
    }

    double calculateSalary() override {
        return hoursWorked * hoursRate;
    }
};

int main()
{
    FullTime fullTime1 = FullTime("Steve", 5000);
    FullTime fullTime1 = FullTime("Steve", 5000);
    PartTime partTime1 = PartTime("Steve", 40, 100);
    PartTime partTime1 = PartTime("Steve", 20, 200);
    
    vector<Employee *> employees;
    return 0;
}