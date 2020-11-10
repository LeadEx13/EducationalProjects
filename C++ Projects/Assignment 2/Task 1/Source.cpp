#include "MySet.h"
#include <iostream>
using namespace std;

MySet enter_new_set();

ostream& operator <<(ostream& os, const MySet& x) {
    os <<"{";
    for (int i = 0; i < x.size; i++)
    {
        (x.size==1)? os << x.set[i]:(i == 0) ? os << x.set[i] << "," : (i == x.size - 1)? os << x.set[i] : os << x.set[i] << ",";
        
    }
    os << "}"<<endl;
    return os;
}
int MySet::total_sum = 0;

int main()
{
    cout << "Set A, ";
    MySet A = enter_new_set();
    cout << "Set B, ";
    MySet B = enter_new_set();
    int k;
    cout << "Enter k value" << endl;
    cin >> k;
    MySet C = (A + B) * k;
    cout << A << B << C << "Total sum of the elements is: " << MySet::get_total_sum() << endl;
    cout << "Enter new k value: ";
    cin >> k;
    C = (A - B) * k;
    cout << A << B << C << "Total sum of the elements is: " << MySet::get_total_sum() << endl;
    int x;
    cout << "choose a int x: ";
    cin >> x;

    (A && x) ? cout << "x is in A" << endl : cout << "x isn't in A" << endl;
    (B && x) ? cout << "x is in B" << endl : cout << "x isn't in B" << endl;
    (C && x)? cout << "x is in C" << endl: cout << "x isn't in C" << endl;

    return 0;

}

MySet enter_new_set() {
    int size;
    cout << "Enter size: ";
    cin >> size;
    int* arr = new int[size];
    cout << "Enter elements: " << endl;
    for (int i = 0; i < size; i++)
    {
        cout << "Enter" << " value of " << i + 1 <<":"<< endl;
        cin >> arr[i];
    }
    MySet result(arr, size);
    delete[]arr;
    return result;
}
