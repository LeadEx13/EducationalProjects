#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include <string.h>
#include <iostream>
using namespace std;

class Employee
{
protected:
	char* name;
	long id;
	int experience;
public:
	Employee(char* name, long id, int experience);
	virtual void print() = 0;
	virtual ~Employee() { delete[]name; }
};