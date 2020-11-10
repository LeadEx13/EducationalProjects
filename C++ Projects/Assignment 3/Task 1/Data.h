#pragma once
#include "WorkingStudent.h"
class Data
{
private:
	Person** arr;
	int size;
public:
	Data(){ size = 0; arr = NULL; }
	~Data(){ if (arr) delete[]arr; }
    void operator+=(Person* p);
    bool find(long id_num);
    void init();
    void print_all() const;
    void print_excellent() const;
    void print_rich() const;
};

