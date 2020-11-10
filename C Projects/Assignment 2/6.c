#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
typedef enum { FALSE, TRUE }boolean;
void two_prime();
#define last 999 
#define first 100

void main()
{
	int choice;
	printf("Please choice the option you like to use: \n1 – Series \n2 – Two primes \n3 – Rectangle \n4 – Omit digit \n5 – Third \n6 - Quit \n");
	scanf("%d", &choice);
	if(choice == 1)
		Checknumber();
	if (choice == 2)
		two_prime();
	if (choice == 3)
		NumericRectangle();
	if (choice == 4)
	{
		unsigned int x, y = 0;
		printf("Enter number between (0~2147483647) and digit between (0~9) you want to remove from it: ");
		while (y < 10)
		{
			scanf("%u %u", &x, &y);
			printf("%u\n", Ccalculator(x, y));
		}
		exit(0);

	}
	if (choice == 5)
	{
		printf("%d", thirdnumber());
		exit(0);
	}
	if (choice == 6)
	{
		printf("You choice to quit.");
		exit(0);
	}
	if (choice > 6 || choice < 1)
	{
		printf("Wrong choice.");
		exit(0);
	}
}
//choice 1
int Checknumber()
{
	int even = 0, num = 0, neg = 0, i = 0, max = 0, min = 0;
	for (; num >= 0; i++)
	{
		printf("Enter number: ");
		scanf("%d", &num);
		if (num < 0)
			break;
		if (num % 2 == 0)
			even++;
		else
			neg = neg + num;
		if (num >= 0)
		{
			if (num > max)
				max = num;
			if (num < min)
				min = num;
		}
	}
	if (i == 0)
		printf("There is no positive number");
	else
		printf("Length=%d \nEven=%d \nsum odd=%d \nMin=%d \nMax=%d", i, even, neg, min, max);
	exit(0);
}

//choice 2
void two_prime()
{
	int n = 0, result, check1 = 1, check2;

	printf("Enter a number: \n");
	scanf("%d", &n);
	if (n <= last && n >= first)
	{
		for (int i = 2; n % i != 0; i++)
			check1 = i++;
		check2 = n / check1;
		if (check_prime(check1) && check1 != 1)
		{
			if (check_prime(check2) && check2 != 1)
				printf("True");
			else
				printf("False, The number is non prime or is 1");
		}
		else
			printf("False, The number is non prime or is 1");
	}
	else
		printf("number need to be between 100~999");
	exit(0);
}
//prime check choice 2
boolean check_prime(int a)
{
	int c;

	for (c = 2; c <= a - 1; c++)
	{
		if (a % c == 0)
			return FALSE;
	}
	if (c == a)
		return TRUE;
}

//choice 3
int NumericRectangle()
{
	int height, width;
	printf("Please enter the rectangle circumference values: \n");
	scanf("%d %d", &height, &width);

	for (int i = 1; i <= height; i++)
	{
		for (int j = 1; j <= width; j++)
			if (i < j && i < (width + 1 - j) && (i < height + 1 - i)) printf("%2d", i);
			else if (j < (width + 1 - j) && (j < height + 1 - i)) printf("%2d", j);
			else if ((height + 1 - i) < (width + 1 - j)) printf("%2d", (height + 1 - i));
			else printf("%2d", (width + 1 - j));
		printf("\n");
	}
	exit(0);
}

//choice 4
unsigned int Ccalculator(unsigned int num, unsigned int digit)
{
	int check = num, zero = 1;
	unsigned result = 0;

	while (check > 0)
	{
		check = num / 10;
		if (num % 10 != digit)
		{
			result = result + (num % 10) * zero;
			zero = zero * 10;
			num = num / 10;
		}
		else
			num = num / 10;
	}


	return result;
}

//choice 5
int thirdnumber()
{
	int biggest = 0, second = 0, third = 0, input;
	printf("Enter a series of numbers and the third biggest will be choiced: ");
	do
	{
		scanf("%d", &input);
		if (input > biggest)
		{
			third = second;
			second = biggest;
			biggest = input;
		}
		else
		{
			if (input > second)
			{
				third = second;
				second = input;
			}
			else
				if (input >= third)
					third = input;
		}

	} while (input >= 0);
	return third;
}