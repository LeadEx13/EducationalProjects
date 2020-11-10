#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string.h>
using namespace std;

class MyString
{
private:
	char* String;
	int lenght;
	static int total_lenght1, total_digits1;
public:
	MyString();
	MyString(char* name);
	MyString(const MyString& s);
	~MyString();
	void operator+=(const MyString& s);
	MyString operator=(const MyString& s);
	void operator-=(char char1);
	void operator*=(int num);
	char operator[](int index);
	static int total_lenght();
	static int total_digits();
	int totdig();
	friend ostream& operator <<(ostream& os, const MyString& s);
	friend istream& operator >>(istream& is, MyString& s);
};
