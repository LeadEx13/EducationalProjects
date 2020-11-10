#define _CRT_SECURE_NO_WARNINGS

#include <stdlib.h>
#include <stdio.h>

void allocate();
void init();
void BuildMatrix();
void FreeMatrix();
void PrintMatrix();
int** MultiplyMatrixes();

void main() {
//--------------------------------//
	int ** matrix1 = NULL;
	int ** matrix2 = NULL;
	int ** matrix3 = NULL;

	int size1,size2,size3;

//---------------------------------//
	printf("Enter size of the folowing matrixes\nA(a x b) , B(b x c)\na=...\n");
	scanf("%d", &size1);
	printf("b=...\n");
	scanf("%d", &size2);
	printf("c=...\n");
	scanf("%d", &size3);
	printf("Enter the first Matrix(%dx%d):\n",size1,size2);
	BuildMatrix(&matrix1, size1, size2);
	printf("Enter the second Matrix(%dx%d):\n", size2, size3);
	BuildMatrix(&matrix2, size2, size3);
	printf("matrix A=\n");
	PrintMatrix(matrix1, size1, size2);
	printf("matrix B=\n");
	PrintMatrix(matrix2, size2, size3);
	matrix3=MultiplyMatrixes(matrix1, matrix2, size1, size2,size3);
	printf("matrix A*B=\n");
	PrintMatrix(matrix3, size1, size3);
	FreeMatrix(&matrix1, size1);
	FreeMatrix(&matrix2, size2);
	FreeMatrix(&matrix3, size1);
	system("pause");
}


void allocate(int*** matrix, int row, int column) {
	*matrix = (int**)malloc((row) * sizeof(int*));
	for (int i = 0; i < column; i++) {
		(*matrix)[i] = (int*)malloc(sizeof(int) * column);
	}
}

void init(int*** matrix, int row, int column) {
	for (int i = 0; i < row; i++)
		for (int j = 0; j < column; j++)
			scanf("%d", &(*matrix)[i][j]);
}

void BuildMatrix(int*** matrix, int row, int column)
{
	allocate(matrix, row, column);
	init(matrix, row, column);
}

void FreeMatrix(int*** matrix, int row) {
	int i;
	for (i = 0; i < row; i++)
		free(*(*matrix + i));
	free(*matrix);
}
void PrintMatrix(int** matrix, int row, int column) {
	for (int i = 0; i < row; i++)
	{
		for (int j = 0; j < column; j++)
			printf("%d ", matrix[i][j]);

		printf("\n");
	}
}
int** MultiplyMatrixes(int** a, int** b, int size1, int size2, int size3)
{
	int** new_mat = NULL;
	allocate(&new_mat, size1, size3);
	for (int i = 0; i < size1; i++)
		for (int j = 0; j < size3; j++)

			for (int z = 0; z < size2; z++)
				if (z == 0)       //if its the first input i need to get rid of the trash data.
					new_mat[i][j] = (a[i][z] * b[z][j]);
				else          //all the rest needs to add the values and not changing them.+
					new_mat[i][j] += (a[i][z] * b[z][j]);

	return new_mat;
}
