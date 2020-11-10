#include "MySet.h"
#include <iostream>
using namespace std;

//Default constructure
MySet::MySet() {
	set = NULL;
	this->size = 0;
}

//Constructure
MySet::MySet(int* set, int size) {
	this->set = new int[size];
	this->size = size;
	for (int i = 0; i < size; i++)
		this->set[i] = set[i];
	total_sum += this->set_sum();
}

//Copy constructure
MySet::MySet(const MySet& x)
{
	this->set = new int[x.size];

	for (int i = 0; i < x.size; i++)
	{
		this->set[i] = x.set[i];
	}
	this->size = x.size;
	total_sum += this->set_sum();
}

//Deconstructor
MySet::~MySet() {
	total_sum -= this->set_sum();
	if (set) delete[] set;

}

//Operator=
MySet MySet::operator=(const MySet& x) {
	total_sum -= this->set_sum();
	delete this->set;
	this->set = new int[x.size];

	for (int i = 0; i < x.size; i++)
	{
		this->set[i] = x.set[i];
	}
	this->size = x.size;
	total_sum += this->set_sum();
	return x;
}

//Operator+
MySet MySet::operator+(const MySet& x) { ///Operator +	
	int* temp = new int[size + x.size];
	int temp_index = 0;
	bool flag;
	for (int i = 0; i < size; i++)
	{
		flag = 0;
		for (int j = 0; j < x.size; j++)
		{
			if (set[i] == x.set[j])
				flag = 1;
		}
		if (!flag)
		{
			temp[temp_index] = set[i];
			temp_index++;
		}
	}
	for (int i = 0; i < x.size; i++)
	{
		temp[temp_index] = x.set[i];
		temp_index++;
	}

	int* result = new int[temp_index];

	for (int i = 0; i < temp_index; i++)
	{
		result[i] = temp[i];
	}


	int tempint;
	flag = 1;
	while (flag)
	{

		flag = 0;
		for (int i = 1; i <= temp_index; i++)
		{
			if (result[i] >= result[i - 1])
			{
				tempint = result[i];
				result[i] = result[i - 1];
				result[i - 1] = tempint;
				flag = 1;
			}
		}
	}
	MySet finalset(result, temp_index);
	delete[]temp;
	delete[]result;

	return finalset;

}

//Operator-
MySet MySet::operator-(const MySet& x) {


	int* temp = new int[size];
	int* minus;
	int counter = 0;
	bool T = true;

	for (int i = 0; i < size; i++)   
	{
		T = false;
		for (int j = 0; j < x.size; j++)
		{
			if (set[i] == x.set[j])
			{
				T = true;
				break;
			}
		}
		if (T == false)
		{
			temp[counter] = set[i];
			counter++;
		}
	}
	minus = new int[counter];
	for (int i = 0; i < counter; i++)
	{
		minus[i] = temp[i];
	}

	MySet resultt(minus, counter);

	return resultt;
}

//Operator*
MySet MySet:: operator*(int x)const {
	int* multy = new int[size];
	for (int i = 0; i < this->size; i++)
	{
		multy[i] = x * set[i];
	}
	MySet Multy(multy, size);
	return Multy;
}

//Operator&
bool MySet:: operator&& (int a)const {
	for (int i = 0; i < this->size; i++)
		if (a == this->set[i])
			return true;
	return false;
}

//Sum
int MySet::set_sum() {
	int sum = 0;
	for (int i = 0; i < this->size; i++) {
		sum += this->set[i];
	}
	return sum;
}
