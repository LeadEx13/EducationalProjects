#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "MyString.h"
#include <string.h>
using namespace std;

//Default constructor
MyString::MyString()
{
	String = NULL;
	lenght = 0;
}

//Regular constructor
MyString::MyString(char* name)
{

	total_lenght1 += strlen(name);
	lenght = strlen(name) + 1;
	String = new char[lenght];
	strcpy(String, name);
	total_digits1 += totdig();


}

//Copy constructor
MyString::MyString(const MyString& s)
{
	total_lenght1 += strlen(s.String);
	lenght = s.lenght;
	String = new char[lenght];
	strcpy(String, s.String);
	total_digits1 += totdig();
}

//Destructor
MyString::~MyString()
{
	total_digits1 -= totdig();
	total_lenght1 -= strlen(String);
	delete[]String;
}

//Operator+
void MyString::operator+=(const MyString& s)
{
	total_digits1 -= totdig();
	total_lenght1 -= strlen(String);
	char* arr;
	arr = new char[lenght];
	strcpy(arr, String);
	delete[]String;
	lenght += lenght + strlen(s.String);
	String = new char[lenght];
	strcpy(this->String, arr);
	strcat(this->String, s.String);
	total_lenght1 += strlen(String);
	total_digits1 += totdig();
	delete[]arr;
}

//Operator=
MyString MyString::operator=(const MyString& s)
{
	delete[]String;
	lenght = strlen(s.String) + 1;
	String = new char[lenght];
	strcpy(String, s.String);


	return *this;
}

//Operator-=
void MyString::operator-=(char char1)
{

	int count = lenght;
	int count1 = 0;
	char* NewChar = new char[lenght];
	char* NewChar1 = new char[lenght];
	strcpy(NewChar1, String);
	bool flag = true;

	for (int i = 0; i < lenght; i++)
	{
		flag = true;;

		if (NewChar1[i] == char1)
		{
			total_lenght1--;
			count--;
			flag = false;

		}

		if (flag == true) {
			NewChar[count1] = NewChar1[i];
			count1++;
		}
	}

	lenght = count;
	delete[]String;
	String = new char[lenght];
	strcpy(this->String, NewChar);
	delete[]NewChar;
	delete[]NewChar1;

}

//Operator*=
void MyString::operator*=(int num)
{
	total_lenght1 -= strlen(String);
	total_digits1 += (totdig() * (num - 1));

	lenght *= num;
	char* arr = new char[lenght / num];
	strcpy(arr, String);
	delete[]String;
	String = new char[lenght];
	strcpy(String, arr);
	for (int i = 0; i < num - 1; i++)
	{
		strcat(String, arr);
	}
	total_lenght1 += strlen(String);
	delete[]arr;

}

//Operator[] Index return
char MyString::operator[](int index)
{
	int index1 = index;
	bool flag = true;
	if (index <= strlen(String))
	{
		for (int i = 0; i < strlen(String); i++)
		{

			if (String[i] == String[index])
			{
				return String[i];
			}
		}
	}

	return 0;
}

//Digits and lenght static info return 
int MyString::total_lenght()
{
	return total_lenght1;
}
int MyString::total_digits()
{
	return total_digits1;
}

//Function to find amount of digits in string by ASCII value
int MyString::totdig()
{
	int count = 0;
	for (int i = 0; i < strlen(String); i++)
	{
		if (String[i] >= 48 && String[i] <= 57)
			count++;
	}
	return count;
}




