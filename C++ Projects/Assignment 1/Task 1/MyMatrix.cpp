#include "MyMatrix.h"
#include <iostream>
using namespace std;

MyMatrix::MyMatrix()
{
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 2; j++)
			this -> mat[i][j] = 0;
}
MyMatrix::MyMatrix(float x, float y, float z, float t)
{
	this->mat[0][0]	= x;
	this->mat[0][1] = y;
	this->mat[1][0] = z;
	this->mat[1][1] = t;
}
MyMatrix::MyMatrix(const MyMatrix& r)
{
	this->mat[0][0] = r.mat[0][0];
	this->mat[0][1] = r.mat[0][1];
	this->mat[1][0] = r.mat[1][0];
	this->mat[1][1] = r.mat[1][1];
}
MyMatrix::~MyMatrix(){}
void MyMatrix::set(const MyMatrix& r)
{
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 2; j++)
			this->mat[i][j] = r.mat[i][j];
}
void MyMatrix::Set(float x, float y, float z, float t)
{
	this->mat[0][0] = x;
	this->mat[0][1] = y;
	this->mat[1][0] = z;
	this->mat[1][1] = t;
}
void MyMatrix::print()const
{
	cout << "( " << mat[0][0] << " " << mat[0][1] << " )" << endl << "( " << mat[1][0] << " " << mat[1][1] << " )";
}
float MyMatrix::det()const {
	return((mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]));
}
void MyMatrix::add(const MyMatrix& r) {
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 2; j++)
			this->mat[i][j] += r.mat[i][j];
}
void MyMatrix::mult(const MyMatrix& r) {
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 2; j++)
			this->mat[i][j] *= r.mat[i][j];
}
bool MyMatrix::is_equal(const MyMatrix& r) const {
	return (this->mat[0][0] == r.mat[0][0] &&
		this->mat[0][1] == r.mat[0][1] &&
		this->mat[1][0] == r.mat[1][0] &&
		this->mat[1][1] == r.mat[1][1]);
}