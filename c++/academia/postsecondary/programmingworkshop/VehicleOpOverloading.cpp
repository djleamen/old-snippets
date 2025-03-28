// Description: This program demonstrates operator overloading in C++.
#include <iostream>
#include <vector>
using namespace std;

class Vehicle
{
private:
	string name;   
	int kms;
public:
	Vehicle(string name, int kms)
	{
		this->name = name;
		this->kms = kms;
	}
	string getName()
	{
		return name;
	}
	int getKms()
	{
		return kms;
	}
};

ostream& operator<<(ostream& output, Vehicle& vehicle)
{
	output << "Name: " << vehicle.getName() << endl;
	output << "Kms: " << vehicle.getKms() << endl;
	return output;
}

class Collections
{
public:
	vector<Vehicle> v;
 
	void operator+=(Vehicle& vehicle)
	{
		v.push_back(vehicle);
	}
};


int main()
{
	Vehicle car1 = Vehicle("Toyota RAV4", 1234);
	Vehicle car2 = Vehicle("Honda CRV", 5678);
	cout << car1 << car2;
    cout << "My car is " << car1;
    
    Collections c;
    c += car1;
    c += car2;
    cout << c.v[1].getName();
 
	return 0;
}
