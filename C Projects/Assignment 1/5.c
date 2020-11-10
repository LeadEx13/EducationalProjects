#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>


int main()
{
	int num1,num2;
	printf("Enter 2 numbers between 1000~9999\n");
	scanf("%d %d", &num1,&num2);

	if (num1 > 1000 && num1 < 9999 && num2 > 1000 && num2 < 9999)
	{
		int Counter=0,Check1, Check2;
		while (num1 > 0 && num2 > 0)
		{
			Check1 = num1 % 10;
			Check2 = num2 % 10;
			num1 = num1 / 10;
			num2 = num2 / 10;
			if (Check1 == Check2)
				Counter++;
		}
		printf("Number of same numbers is: %d", Counter);
	}
	else
	{
		printf("Wrong number!");
	}
	return 0;
}