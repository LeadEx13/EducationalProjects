#define _CRT_SECURE_NO_WARNINGS
#define SIZE 5 //Matrix size

#include <stdio.h>

//Function to call.
void divide();

//Main Function.
void main()
{
	int arry_lines[SIZE] = { 0 };
	int arry_cols[SIZE] = { 0 };
	int d; //Divide number.
	int mat[SIZE][SIZE];

	printf("Please enter values for the %d*%d matrix:\n", SIZE, SIZE);//input of the matrix
	for (int i = 0; i < SIZE; i++)
		for (int j = 0; j < SIZE; j++)
		{
			printf("Col %d, Line %d: ", j + 1, i+1);
			scanf(" %d", &mat[j][i]);
		}

	printf("\nEnter the digit you want to check:\n");
	scanf("%d", &d);//input of the devide.

	divide(&mat, &arry_lines, &arry_cols, d);//divide Function Use.
	for (int j = 0; j < SIZE; j++)//The line array
		printf("\nLine %d  :%d\n", j + 1, arry_lines[j]);

	for (int j = 0; j < SIZE; j++)//The col array
		printf("\nCol %d  :%d\n", j + 1, arry_cols[j]);
}

//Divide Function.
void divide(int mat[SIZE][SIZE], int arry_lines[SIZE], int arry_cols[SIZE], int d)
{
	for (int i = 0; i < SIZE; i++)
		for (int j = 0; j < SIZE; j++)
			if (mat[j][i] % d == 0)
			{
				arry_lines[i] ++;
				arry_cols[j]++;
			}
}