#pragma once
#include "Person.h"
using namespace std;
class Employee :virtual public Person
{
protected:
	int salary;
public:
	Employee() :Person() { salary = 0; }
	Employee(long id, char* name, int salary) :Person(id, name) { this->salary = salary; }
	Employee(const Employee& e) :Person(e) { salary = e.salary; }
	~Employee() { if (name) delete[]name; }
	void print()const { Person::print(); cout <<"Salary of the employee: "<< salary << endl; }
	int getsalary() { return salary; }
	virtual const char* GetType() const { return "Employee"; }
};

