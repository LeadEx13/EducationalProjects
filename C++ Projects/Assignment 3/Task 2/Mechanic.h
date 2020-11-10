#pragma once
#include "Employee.h"
#include <iostream>
using namespace std;
class Mechanic :public Employee
{
private:
	int extra, salary;
public:
	Mechanic(char* name, long id, int experience, int extra) :Employee(name, id, experience) { this->extra = extra; }
	void salarysum(){ salary += 5000; salary += (500 * experience); salary += (100 * extra); }
	void print(){ salarysum(); cout << "Name of Mechanic is: " << this->name << endl << "Salary of Mechanic is: " << this->salary << endl; }
	int getextra(){ return extra; }
	void getname() const{ cout << name; }
	void printextra() const { if (this->extra > 0) cout << "The Mechanic: " << this->name << " have more than 1 extra hour " << endl; }
};