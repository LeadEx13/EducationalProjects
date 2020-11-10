#include "Employee.h"
using namespace std;

Employee::Employee(char* name, long id, int experience)
{
	this->name = new char[strlen(name) + 1];
	strcpy(this->name, name);
	this->id = id;
	this->experience = experience;
}