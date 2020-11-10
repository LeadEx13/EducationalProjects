/#define _CRT_SECURE_NO_WARNINGS
#define ARRY1_SIZE 100 //Defenite value for the size of the array for prime check.
#define ARRY2_SIZE 6 //Defenite value for the size of the array for quick check.

#include <stdio.h>

//True/False function.
typedef enum { false, true }boolean;

//Functions to call.
boolean prime();
void primes();
void quick();

//Main Function.
void main()
{
	int size = ARRY1_SIZE;
	int arry1[ARRY1_SIZE];
	primes(&arry1, size);//Prime Function Use.
	for (int i = 0; i < size; i++)
		printf("%d) %d\n", i + 1, arry1[i]); //Print every prime number with size of arry1_size.

	size = ARRY2_SIZE;
	int arry2[ARRY2_SIZE];
	printf("\nEnter 12 numbers:\n\n");
	for (int i = 0; i < ARRY2_SIZE; i++)
	{
		printf("%d) ", i + 1);
		scanf("%d", &arry2[i]);  //Input for every cell of the array.
	}

	quick(&arry2, size); //Quick Function Use.
	printf("\nThe new array is:\n\n");
	for (int i = 0; i < size; i++)
		printf("%d) %d\n", i + 1, arry2[i]); 

}

boolean prime(int num)
{
	for (int i = 2; i < num / 2; i++)
		if (num % i == 0) 
			return false; //If it can be devided by a number lower than it its not a primal number.
	return true;
}

void primes(int arry1[], int size) //Question 1
{
	for (int j = 2, i = 0; i < size; j++) //Adding numbers to arry.
		if (prime(j))  // j-Check number to add.
		{
			arry1[i] = j;
			i++; // i-array index. 
		}
}
//Quick Function.
void quick(int arry1[], int size) //Question 2
{
	int positive, negative;
	for (int j = 0; j < size; j++) //Arry2 fixer.
	{
		positive = -1;
		negative = -1;

		for (int i = 0; negative == -1 && i < size; i++)//looking for a negative number.
		{
			if (arry1[size - 1 - i] < 0)
				negative = size - 1 - i;
		}

		for (int i = 0; positive == -1 && i < negative; i++)//looking for a positive number
			if (arry1[i] > 0)positive = i;

		if (positive != -1 && negative != -1)
		{
			arry1[positive] += arry1[negative];
			arry1[negative] = arry1[positive] - arry1[negative];
			arry1[positive] -= arry1[negative];

		}
	}
}