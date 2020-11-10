#pragma once
#include <iostream>
using namespace std;
class Date
{
private:
	int day, month, year;
public:
	Date();
	Date(int day, int month, int year);
	Date(const Date& d);
	~Date(){}
	friend ostream& operator <<(ostream& os, const Date& s);
	bool operator>(const Date& j);
	bool operator== (const Date& j);
};

