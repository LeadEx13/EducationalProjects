#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>


int main()
{
	int num;
	printf("write a number between 100-999?\n");
	scanf("%d", &num);
	if (num > 100 && num < 999)
	{
		int Max=0, Min=9, Check;
		while (num > 0)
		{
			Check = num % 10;
			num = num / 10;
			if (Check > Max)
				Max = Check;
			if (Check < Min)
				Min = Check;
		}
		printf("Max digit is: %d\nMin digit is:%d", Max,Min);
	}
	else
	{
		printf("Wrong number!");
	}
	return 0;
}