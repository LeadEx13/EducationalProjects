#include "Driver.h"

Driver::Driver(char* name, long id, int experience, int size1, Delivery* arr1)
	:Employee(name, id, experience)
{
	size = size1;
	arr = new Delivery[size];
	for (int i = 0; i < size; i++)
		arr[i] = arr1[i];
}

int Driver::roadssalary()
{
	int roads_salary = 0;
	for (int i = 0; i < size; i++)
	{
		roads_salary += arr[i].salarysum();
		if (arr[i].getweight() > 8000 && experience >= 3)
			roads_salary += 200;
	}
	return roads_salary;
}

int Driver::over8000()
{
	int count = 0;
	for (int i = 0; i < size; i++)
	{
		if (arr[i].getweight() > 8000)
			count++;
	}
	return count;
}

void Driver::setArr(Delivery a)
{
	Delivery* temp;
	temp = new Delivery[size];
	for (int i = 0; i < size; i++)
		temp[i] = arr[i];
	delete[]arr;
	size += 1;
	arr = new Delivery[size];
	for (int i = 0; i < size - 1; i++)
		arr[i] = temp[i];
	arr[size - 1] = a;
	delete[]temp;
}