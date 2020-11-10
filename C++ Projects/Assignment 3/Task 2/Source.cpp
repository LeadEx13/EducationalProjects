#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "Employee.h"
#include "Mechanic.h"
#include "Manager.h"
#include "Driver.h"
#include "Delivery.h"
#include <string.h>

using namespace std;
int main()
{
	int size, choice, experience, extra, amount;
	int weight, distance;
	long id;
	char name[30];
	Delivery* arr1;
	Employee** listworkers;

	cout << "Enter the amount of employee's: " << endl;
	cin >> size;

	listworkers = new Employee * [size];

	for (int i = 0; i < size; i++)
	{
		cout << "-----------------------------" << endl << "Please choice from the menu: " << endl << "1.Mechanic" << endl << "2.Manager" << endl << "3.Delivery driver" << endl;
		cin >> choice;
		cout << "-----------------------------" << endl;
		switch (choice)
		{
		case 1:
			cout << "Enter the name: ";
			cin >> name;
			cout << "Enter the ID: ";
			cin >> id;
			cout << "Enter the experience: ";
			cin >> experience;
			cout << "Enter the extra hours: ";
			cin >> extra;
			listworkers[i] = new Mechanic(name, id, experience, extra);
			break;
		case 2:
			cout << "Enter the name: ";
			cin >> name;
			cout << "Enter the ID: ";
			cin >> id;
			cout << "Enter the experience: ";
			cin >> experience;
			listworkers[i] = new Manager(name, id, experience);
			break;
		case 3:
			cout << "Enter amount of deliveries: ";
			cin >> amount;
			cout << "Enter the name: ";
			cin >> name;
			cout << "Enter the ID: ";
			cin >> id;
			cout << "Enter the experience: ";
			cin >> experience;
			arr1 = new Delivery[amount];
			for (int i = 0; i < amount; i++)
			{
				cout << "Enter order " << i + 1 << " details: " << endl;
				cout << "Weight: ";
				cin >> weight;
				cout << "Distance: ";
				cin >> distance;
				arr1[i].set(distance, weight);
			}
			listworkers[i] = new Driver(name, id, experience, amount, arr1);
			break;
		default:
			cout << "Wrong choice, try again." << endl;
			i--;
			continue;
		}
	}
	if (size > 0)
	{

		int weight1, distance1, counter = 0;
		long id1;
		cout << "-----------------------------" << endl << "Enter another driver ID: ";
		cin >> id1;
		cout << "Enter another delivery weight: ";
		cin >> weight1;
		cout << "Enter another delivery distance: ";
		cin >> distance1;
		for (int i = 0; i < size; i++)
			if ((dynamic_cast<Driver*>(listworkers[i])))
				if ((dynamic_cast<Driver*>(listworkers[i]))->getid() == id1)
				{
					counter++;
					Delivery a(distance1, weight1);
					(dynamic_cast<Driver*>(listworkers[i])->setArr(a));
				}
		cout << endl;
		if (counter == 0) cout << "There is no Driver with this ID" << endl;
		if (counter != 0) cout << "The delivery has been added" << endl;
		cout << "-----------------------------" << endl;

		for (int i = 0; i < size; i++)
		{
			listworkers[i]->print();
			cout << "-----------------------------" << endl;

		}

		int sum = 0;
		for (int i = 0; i < size; i++)
			if (dynamic_cast<Driver*>(listworkers[i]))
			{
				sum += (dynamic_cast<Driver*>(listworkers[i]))->over8000();
			}
		cout << "Number of Drives with over 8000 kg are: " << sum << endl << "-----------------------------" << endl << "Name of employees with more than 1 extra hour: " << endl;
		for (int i = 0; i < size; i++)
			if (dynamic_cast<Mechanic*>(listworkers[i]))
				if ((dynamic_cast<Mechanic*>(listworkers[i]))->getextra() >= 1)
				{
					(dynamic_cast<Mechanic*>(listworkers[i]))->getname();
					cout << endl << "-----------------------------" << endl;
				}
		for (int i = 0; i < size; i++) delete listworkers[i];

		delete[]listworkers;
	}
	return 0;
}