#pragma once
#include "Person.h"
using namespace std;
class Student :virtual public Person
{
protected:
	float average;
	char* institute;
public:
	Student() :Person() { average = 0.0; institute = NULL; }
	Student(long id, char* name, float average, char* institute) :Person(id, name) { this->average = average; this->institute = new char(strlen(institute) + 1); strcpy(this->institute, institute); }
	Student(const Student& s) :Person(s) { average = s.average; this->institute = new char(strlen(s.institute) + 1); strcpy(this->institute, s.institute);}
	~Student() { if (name) delete[]name; if (institute) delete[]institute; }
	void print()const { Person::print(); cout <<"Average of the student: "<< average <<endl<<"Name of the Institute: "<< institute << endl; }
	float getaverage() { return average; }
	char* getinstitute() { return institute; }
	virtual const char* GetType() const { return "Student"; }
};

