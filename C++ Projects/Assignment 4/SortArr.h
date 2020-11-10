#pragma once
#include <iostream>
using namespace std;

template <class A>
class SortArr
{
private:
	A* arr;
	int size;
	static int obj_count,total_size;
public:
	SortArr();
	SortArr(A* arr, int size);
	SortArr(const SortArr& s);
	~SortArr() { if (arr) delete[]arr; }
	SortArr<A>& operator =(const SortArr& s);
	bool operator ==(const SortArr& s);
	bool insert(A& s);
	friend ostream& operator <<(ostream& os, const SortArr& s) 
	{
		for (int i = 0; i < s.size; i++)
			os << s.arr[i];
		return os;
	}
	static void print_static() { cout << "Total size is: " << total_size << endl << "Amount of objects: " << obj_count << endl; }
};

template<class A>
inline SortArr<A>::SortArr()
{
	arr = NULL;
	size = 0;
	obj_count++;
}

template<class A>
inline SortArr<A>::SortArr(A* arr, int size)
{
	obj_count++;
	for (int i = 0; i+1 < size; i++)
		if (arr[i] > arr[i + 1] || (!(arr[i] > arr[i + 1]) && !(arr[i + 1] > arr[i])))
		{
			this->size = 1;
			total_size++;
			this->arr = new A[1];
			this->arr[0] = arr[0];
			return;
		}

	total_size += size;
	this->arr = new A[size];
	this->size = size;
	for (int i = 0; i < size; i++)
		this->arr[i] = arr[i];
}

template<class A>
inline SortArr<A>::SortArr(const SortArr& s)
{
	obj_count++;
	this->size = s.size;
	total_size += s.size;
	this->arr = new A[size];
	for (int i = 0; i < size; i++)
		this->arr[i] = s.arr[i];
}

template<class A>
inline SortArr<A>& SortArr<A>::operator=(const SortArr &s)
{
	total_size -= size;
	this->size = s.size;
	delete[]arr;
	this->arr = new A[size];
	for (int i = 0; i < size; i++)
		this->arr[i] = s.arr[i];
	total_size += size;
	return *this;
}

template<class A>
inline bool SortArr<A>::operator==(const SortArr& s)
{
	if(size!=s.size)
		return false;
	for(int i=0;i<size;i++)
		if (arr[i] > s.arr[i] || s.arr[i] > arr[i])
			return false;
	return true;
}

template<class A>
inline bool SortArr<A>::insert(A& s)
{
	int i;
	for (i = 0; i < size; i++)
	{
		if (arr[i] == s)
			return false;
		else break;
	}


	size++;
	A* temp;
	temp = new A[size];
	for (int j = 0; j < i; j++)
	{
		temp[i] = arr[i];
	}
	temp[i] = s;
	for (i; i < size; i++)
	{
		arr[i] = temp[i];
	}

	delete[]arr;
	arr = new A[size];

	for (int q = 0; q < size; q++)
	{
		arr[q] = temp[q];
	}
	delete[]temp;


	return true;
}
