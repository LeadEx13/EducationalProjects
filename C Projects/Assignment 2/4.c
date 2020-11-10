#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

unsigned int Ccalculator(unsigned int num, unsigned int digit)
{
	int check=num, zero=1;
	unsigned result=0;
	
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
			num =num/10;
	}


	return result;
}

void main()
{
	unsigned int x, y = 0;
	printf("Enter number between (0~2147483647) and digit between (0~9) you want to remove from it: ");
	while (y < 10)
	{
		scanf("%u %u", &x, &y);
		printf("%u\n", Ccalculator(x, y));
	}

}