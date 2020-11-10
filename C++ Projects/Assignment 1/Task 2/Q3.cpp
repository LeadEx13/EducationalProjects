#include <iostream>
#include "Winners.cpp"
#define _CRT_SECURE_NO_WARNINGS
#include <string.h> 
#define SIZE 30
using namespace std;

int main()
{
    int d, e, f;
    char a[SIZE], b[SIZE], c[SIZE];
    cout << "Enter three names and three scores in that order(name1 score1 name2 score2 name3 score3):" << endl;
    cin >> a >> d >> b >> e >> c >> f;
    Winners list(a, b, c, d, e, f);
    list.print_winners();
    cout << "Enter a new winner name and his score:" << endl;
    cin >> a >> d;
    list.Update(a, d);
    list.print_winners();
	return 0;
}
