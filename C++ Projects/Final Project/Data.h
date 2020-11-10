#pragma once
#include "Animal.h"
class Data
{
private:
	Animal** arr;
	int size;
public:
	Data() { size = 0; arr = NULL; }
	~Data() { if (arr) delete[]arr; }
	void operator+=(Animal* a);//Adding an animal into the array
	void operator-=(Animal* a);//Subtracting an animal from the array
	bool find_animal(char* a) const;//Finding if the animal name exist in thhe array
	void print_animal(char* a) const;//Prints the specic animal
	int find_index(char* a) const;//finding the index by name
	void init();//initiallization
	void print_all() const;
	void print_welcome() const;
	bool yesno_insert();//Converts yes-no inputs into a bool value
};

