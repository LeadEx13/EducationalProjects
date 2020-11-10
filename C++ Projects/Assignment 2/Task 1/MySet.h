#pragma once
#include <iostream>
using namespace std;

class MySet
{
	int size, * set;
	static int total_sum;

public:
	static void init() { MySet::total_sum = 0; }
	static int get_total_sum() { return total_sum; }
	MySet();
	MySet(int*, int);
	MySet(const MySet&);
	~MySet();
	MySet operator=(const MySet&);
	MySet operator+(const MySet&);
	MySet operator-(const MySet&);
	MySet operator*(int)const;
	bool  operator&& (int)const;
	friend ostream& operator <<(ostream& , const MySet&);
	int set_sum();
};

