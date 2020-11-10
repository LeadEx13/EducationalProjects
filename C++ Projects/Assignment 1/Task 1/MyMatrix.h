#pragma once
class MyMatrix
{
private:
	float mat[2][2];
public:
	MyMatrix();
	MyMatrix(float x,float y,float z,float t);
	MyMatrix(const MyMatrix& r);
	~MyMatrix();
	void set(const MyMatrix& r);
	void Set(float x, float y, float z, float t);
	void print()const;
	float det()const;
	void add(const MyMatrix& r);
	void mult(const MyMatrix& r);
	bool is_equal(const MyMatrix& r)const;
};

