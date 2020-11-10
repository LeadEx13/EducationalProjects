#include "Winners.h"
#include <iostream>
using namespace std;

Winners::Winners() {
	first_name = new char(5);
	second_name = new char(5);
	third_name = new char(5);
	strcpy(first_name,"null");
	strcpy(second_name, "null");
	strcpy(third_name, "null");
	first_score = 0;
	second_score = 0;
	third_score = 0;
}
Winners::Winners(char* const a, char* const b, char* const c, int const d, int const e, int const f) {
	first_name = new char(strlen(a) + 1);
	second_name = new char(strlen(b) + 1);
	third_name = new char(strlen(c) + 1);
	strcpy(first_name, a);
	strcpy(second_name, b);
	strcpy(third_name, c);
	first_score = d;
	second_score = e;
	third_score = f;
}
Winners::Winners(const Winners& r) {
	this->first_name = new char(strlen(r.first_name) + 1);
	this->second_name = new char(strlen(r.second_name) + 1);
	this->third_name = new char(strlen(r.third_name) + 1);
	strcpy(first_name, r.first_name);
	strcpy(second_name, r.second_name);
	strcpy(third_name, r.third_name);
	this->first_score = r.first_score;
	this->second_score = r.second_score;
	this->third_score = r.third_score;
}
Winners::~Winners() {
	delete[]first_name;
	delete[]second_name;
	delete[]third_name;
}
void Winners::print_winners()const {
	cout << "(" << first_name << "," << first_score << "),(" << second_name << "," << second_score << "),(" << third_name << "," << third_score << ")" << endl;
}
void Winners::Update(char* a, int b) {
	if (b > third_score)
		if (b > second_score)
			if (b > first_score)
			{
				third_score = second_score;
				strcpy(third_name, second_name);
				second_score = first_score;
				strcpy(second_name, first_name);
				first_score = b;
				strcpy(first_name, a);
			}
			else
			{
				third_score = second_score;
				strcpy(third_name, second_name);
				second_score = b;
				strcpy(second_name, a);
			}
		else
		{
			third_score = b;
			strcpy(third_name, a);
		}
}