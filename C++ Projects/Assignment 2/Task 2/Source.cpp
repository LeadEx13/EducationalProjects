#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "MyString.h"
#include <string.h>
using namespace std;

int  MyString::total_lenght1 = 0, MyString::total_digits1 = 0;

ostream& operator <<(ostream& os, const MyString& s)
{
	os << s.String << endl;
	return os;
}
istream& operator >>(istream& is, MyString& s)
{
	char temp[30];
	MyString::total_digits1 -= s.totdig();
	MyString::total_lenght1 -= strlen(s.String);
	delete[]s.String;
	is >> temp;
	s.String = new char[strlen(temp) + 1];
	strcpy(s.String, temp);
	MyString::total_lenght1 += strlen(s.String);
	MyString::total_digits1 += s.totdig();

	return is;
}


int main()
{
	char name[30];
	char name1[30];
	int index, index1;
	cout << "Write first string: ";
	cin >> name;
	cout << "Write second string: ";
	cin >> name1;

	MyString A(name);
	MyString B(name1);
	MyString C = A;
	C += B;

	cout << "Object are: " << endl;
	cout << A << endl << B << endl << C << endl;
	cout << "Total lenght is: ";
	cout << MyString::total_lenght() << endl;
	cout << "Total digits is: " << endl << MyString::total_digits() << endl;
	cout << endl;

	cout << "Choose new C values: ";
	cin >> C;

	cout << "Object are: " << endl;
	cout << A << endl << B << endl << C << endl;
	cout << "Total lenght is: ";
	cout << MyString::total_lenght() << endl;
	cout << "Total digits is: " << MyString::total_digits() << endl;

	cout << "Choose a index: ";
	cin >> index;
	A -= C[index];
	B -= C[index];
	C -= C[index];

	cout << "Object are: " << endl;
	cout << A << endl << B << endl << C << endl;
	cout << "Total lenght is: ";
	cout << MyString::total_lenght() << endl;
	cout << "Total digits is: " << MyString::total_digits() << endl;

	cout << "Choose another index: ";
	cin >> index1;
	A *= index1;
	B *= index1;
	C *= index1;

	cout << "Object are: " << endl;
	cout << A << endl << B << endl << C << endl;
	cout << "Total lenght is: ";
	cout << MyString::total_lenght() << endl;
	cout << "Total digits is:" << MyString::total_digits() << endl;

	return 0;
}