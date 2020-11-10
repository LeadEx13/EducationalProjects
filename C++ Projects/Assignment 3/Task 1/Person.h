#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string.h>
using namespace std;

class Person
{
protected:
	long id;
	char* name;
public:
	Person() { name = NULL; id = 0; }
	Person(long id, char* name) { this->id = id; this->name = new char(strlen(name) + 1); strcpy(this->name, name);}
	Person(const Person& p) { id = p.id; name = new char((char)strlen(p.name) + 1); strcpy(this->name, p.name);}
	~Person() {}
	virtual void print() const { cout <<"ID: "<< id <<endl<<"Name: "<< name << endl; }
	long getid() { return id; }
	char* getname() { return name; }
};

