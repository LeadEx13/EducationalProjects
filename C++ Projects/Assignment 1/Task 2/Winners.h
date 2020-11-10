#pragma once
class Winners
{
private:

	char* first_name, * second_name, * third_name;
	int first_score, second_score, third_score;

public:

	Winners();
	Winners(char* const, char* const, char* const, int const, int const, int const);
	Winners(const Winners&);
	~Winners();
	void print_winners()const;
	void Update(char*, int);
};
