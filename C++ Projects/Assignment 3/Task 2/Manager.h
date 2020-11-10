#pragma once
#include "Employee.h"
#include <iostream>
using namespace std;
class Manager :public Employee
{
private:
	int salary = 0;
public:
	Manager(char* name, long id, int experience) :Employee(name, id, experience) { }
	void salarysum(){ salary += 12000 + (1000 * experience); }
	void print(){ salarysum(); cout << "Name of Manager is : " << this->name << endl << "Salary of Manager is : " << this->salary << endl; }
};