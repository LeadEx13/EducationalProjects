#pragma once
#include "Employee.h"
#include "Delivery.h"
#include <iostream>
using namespace std;

class Driver :public Employee
{
private:
	int salary, size;
	Delivery* arr;
public:
	Driver(char* name, long id, int experience, int size1, Delivery* arr1);
	void print(){ salarysum(); cout << "Name of Driver is: " << name << endl << "Salary of Driver is: " << salary << endl; }
	void salarysum(){ salary = roadssalary() + (300 * experience); }
	int roadssalary();
	int over8000();
	long getid(){ return id; }
	void setArr(Delivery a);
	~Driver(){ delete[]arr; }
};
