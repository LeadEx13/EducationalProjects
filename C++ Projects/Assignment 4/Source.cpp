#include <iostream>
#include <string.h>
#include "Date.h"
#include "SortArr.h"
using namespace std;




ostream& operator << (ostream& os, const Date& s)
{
    os << s.day << "/" << s.month << "/" << s.year;
    return os;
}

int SortArr<Date>::obj_count = 0;
int SortArr<Date>::total_size = 0;
int SortArr<int>::obj_count = 0;
int SortArr<int>::total_size = 0;


int main()
{

    int* arr1;
    int* arr2;
    int size1, size2;

    cout << "Enter the size of the first array: " << endl;
    cin >> size1;
    arr1 = new int[size1];
    cout << "Enter the values of the first array: " << endl;
    for (int i = 0; i < size1; i++)
        cin >> arr1[i];
    SortArr<int> a(arr1, size1);

    cout << "Enter the size of the first array: " << endl;
    cin >> size2;
    arr2 = new int[size2];
    cout << "Enter the values of the second array: " << endl;
    for (int i = 0; i < size2; i++)
        cin >> arr2[i];
    SortArr<int>  b(arr2, size2);
    if (a == b)
        cout << "The arrays are equal." << endl;
    else
        cout << "The arrays are not equal." << endl;
    cout << endl << "Arr number 1 : " << endl << a << endl << "Arr number 2 : " << endl << b << endl;

    //Date function start
    int day, month, year, size3, size4;
    Date** arr3;
    Date** arr4;

    try
    {
        cout << "Enter amount of dates u want in first array: ";
        cin >> size3;
        arr3 = new Date * [size3];
        for (int i = 0; i < size3; i++)
        {
            cout << "Enter day: ";
            cin >> day;
            cout << "Enter month: ";
            cin >> month;
            cout << "Enter year: ";
            cin >> year;
            //cout << endl;
            arr3[i] = new Date(day, month, year);


        }


        cout << "Enter amount of dates you want in second array: ";
        cin >> size4;
        arr4 = new Date * [size4];
        for (int i = 0; i < size4; i++)
        {
            cout << "Enter day: ";
            cin >> day;
            cout << "Enter month: ";
            cin >> month;
            cout << "Enter year: ";
            cin >> year;
            //cout << endl;
            arr4[i] = new Date(day, month, year);

        }

        Date* temp1 = new Date[size3];
        for (int i = 0; i < size3; i++)
            temp1[i] = *arr3[i];

        Date* temp2 = new Date[size4];
        for (int i = 0; i < size4; i++)
            temp2[i] = *arr4[i];

        SortArr<Date> w1(temp1, size3);
        SortArr<Date> w2(temp2, size4);

        if (w1 == w2)
            cout << "The dates are equal" << endl;
        else
            cout << "The dates are not equal" << endl;
        cout << "DATE Arr number 1 : " << endl << w1 << endl << "DATE Arr number 2 : " << endl << w2 << endl;

        cout << "Enter one more Date for arr1 : " << endl << "Enter day: ";
        cin >> day;
        cout << "Enter month: ";
        cin >> month;
        cout << "Enter year: ";
        cin >> year;
        Date c(day, month, year);
        cout << endl;

        if (w1.insert(c))
            cout << "The date does been added to array 1" << endl;
        else
            cout << "The date does not been added to array 1" << endl;

        cout << "Enter one more Date for arr2: " << endl << "Enter day: ";
        cin >> day;
        cout << "Enter month: ";
        cin >> month;
        cout << "Enter year: ";
        cin >> year;
        Date d(day, month, year);
        cout << endl;
        if (w2.insert(d))
            cout << "The date has been added to array 2" << endl;
        else
            cout << "The date hasNOT been added to array 2" << endl;

        cout << "DATE Arr number 1: " << w1 << "DATE Arr number 2 : " << w2 << "DATE STATIC ARE : ";
        SortArr<Date>::print_static();


    }
    catch (const char* str)
    {
        cout << str << endl;
    }

    cout << endl;
    int insert1, insert2;
    cout << "Enter one more integer for arr1: ";
    cin >> insert1;
    cout << "Enter one more integer for arr2: ";
    cin >> insert2;
    if (a.insert(insert1))
        cout << "The int does been added to array 1" << endl;
    else
        cout << "The int does not been added to array 1" << endl;
    cout << endl;
    if (b.insert(insert2))
        cout << "The int does been added to array 2" << endl;
    else
        cout << "The int does not been added to array 2" << endl;

    cout << "INT Arr number 1: " << a << endl << "INT Arr number 2: " << b << endl << "INT STATIC ARE: "<<endl;
    SortArr<int>::print_static();

    return 0;
}