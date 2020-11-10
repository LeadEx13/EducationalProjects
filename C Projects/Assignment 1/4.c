#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>


int main()
{
	int Day, Month, Year;
	printf("Enter a day: ");
	scanf("%d", &Day);
	printf("Enter a month: ");
	scanf("%d", &Month);
	printf("Enter a year: ");
	scanf("%d", &Year);
	if (Year < 2099 && Year > 1900)
	{
		if (Month > 0 && Month < 13)
		{
			if (Month == 2)
			{
				if ((Year % 4 == 0 && Year % 100 != 0) || Year % 400 == 0)
				{
					if (Day < 30 && Day > 1)
					{
						printf("The date is correct: %d.%d.%d", Day, Month, Year);
					}
					else
					{
						printf("%d.%d.%d Incorrect date", Day, Month, Year);
					}
				}
				else
				{
					if (Day < 29 && Day > 1)
					{
						printf("The date is correct: %d.%d.%d", Day, Month, Year);
					}
					else
					{
						printf("%d.%d.%d Incorrect date", Day, Month, Year);
					}
				}
			}
			else
			{
				if (Day == 31)
				{
					if(Month == 1,3,5,7,8,10,12)
					{
						printf("The date is correct: %d.%d.%d", Day, Month, Year);
					}
					else
					{
						printf("%d.%d.%d Incorrect date", Day, Month, Year);
					}
				}
				else
				{
					if (Day <= 30 && Day > 0)
					{
						printf("The date is correct: %d.%d.%d", Day, Month, Year);
					}
					else
					{
						printf("%d.%d.%d Incorrect date", Day, Month, Year);
					}
				}

			}

		}
	}
	else
	{
		printf("%d.%d.%d Incorrect date", Day, Month, Year);
	}
	return 0;
}