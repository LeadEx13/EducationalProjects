#include <iostream>
#include "MyMatrix.cpp"
#define _CRT_SECURE_NO_WARNINGS
using namespace std;

int main()
{
	float x, y, z, t;
    cout << "Please enter matrix A:";
	cin >> x >> y >> z >> t;
	MyMatrix A(x, y, z, t);
	cout << "Please enter matrix B:";
	cin >> x >> y >> z >> t;
	MyMatrix B(x, y, z, t);
	cout << "Please enter matrix C:";
	cin >> x >> y >> z >> t;
	MyMatrix C(x, y, z, t);
	
	(A.is_equal(B)) ? cout << "A is equal to B" << endl : cout << "A isn't equal to B" << endl;
	(A.is_equal(C)) ? cout << "A is equal to C" << endl : cout << "A isn't equal to C" << endl;
	(C.is_equal(B)) ? cout << "C is equal to B" << endl : cout << "C isn't equal to B" << endl;

	cout << "Matrix A detrminanta is:" << A.det() << endl;
	cout << "Matrix B detrminanta is:" << B.det() << endl;
	cout << "Matrix C detrminanta is:" << C.det() << endl;

	MyMatrix temp(A);
	temp.mult(B);
	temp.add(C);
	cout << "A*B+C=" << endl;
	temp.print();

	cout << "Please enter new number into matrix B:";
	cin >> x >> y >> z >> t;
	MyMatrix B(x, y, z, t);

	temp.set(A);
	temp.mult(B);
	temp.add(C);
	cout << "new value for A*B+C=" << endl;
	temp.print();
	return 0;
}
