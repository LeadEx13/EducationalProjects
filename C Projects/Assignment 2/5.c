#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int thirdnumber()
{
	int first=0, second=0, third=0,input;
	printf("Enter a series of numbers and the third biggest will be choiced: ");
	do
	{
		scanf("%d", &input);
		if (input > first)
		{
			third = second;
			second = first;
			first = input;
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

	} 
	while (input >= 0);
		return third;
}

void main()
{
	printf("%d", thirdnumber());
}