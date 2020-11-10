#include "Date.h"

Date::Date()
{
	day = 0;
	month = 0;
	year = 0;
}

Date::Date(int day, int month, int year)
{
	//check expections
	if (year < 1000 || year>2020)
	{
		throw "Year isnt allowd";
	}
	else if (month < 1 || month>12)
	{
		throw "Month isnt allowd";
	}
	else if (month == 12 || month == 10 || month == 8 || month == 7 || month == 5 || month == 3 || month == 1)
	{
		if (day < 1 || day>31)
			throw "Day in this month isnt allowed";
	}
	else if (month == 11 || month == 9 || month == 6 || month == 4)
	{
		if (day < 1 || day>30)
			throw "Day in this month isnt allowed";
	}
	else if (month == 2)
	{
		if (day < 1 || day > 28)
			throw "Day in this month isnt allowed";
	}
	this->day = day;
	this->month = month;
	this->year = year;
}

Date::Date(const Date& d)
{
	day = d.day;
	month = d.month;
	year = d.year;
}

bool Date::operator>(const Date& j)
{
	if (year > j.year)
		return 1;
	else if (year == j.year && month > j.month)
		return 1;
	else if (year == j.year && month == j.month && day > j.day)
		return 1;
	return 0;
}

bool Date::operator==(const Date& j)
{
	return ((day == j.day) && (month == j.month) && (year == j.year));
}