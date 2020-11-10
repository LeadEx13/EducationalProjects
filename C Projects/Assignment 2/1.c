#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

void Checknumber()
{
	int even=0, num=0, neg=0,i = 0,max=0,min=0;
	for (; num >= 0; i++)
	{
		printf("Enter number: ");
		scanf("%d", &num);
		if (num < 0)
			break;
		if (num % 2==0)
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
		printf("Length=%d \nEven=%d \nsum odd=%d \nMin=%d \nMax=%d",i,even,neg,min,max);
}

void main()
{
	Checknumber();
}
