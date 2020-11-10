#pragma once
#include "Student.h"
#include "Employee.h"
#include <iostream>
#include <string.h>
using namespace std;

class WorkingStudent :virtual public Student, public Employee
{
private:
	bool same_institute;
public:
	WorkingStudent(long id, char* name, float average, char* institute, int salary, bool same_institute);
	WorkingStudent(const WorkingStudent& w);
	void print()const;
	int getsame_institute() { return same_institute; }
	~WorkingStudent() { if (name) delete[]name; if (institute) delete[]institute; }
};

