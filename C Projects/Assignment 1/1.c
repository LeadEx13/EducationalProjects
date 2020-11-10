#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define Ride 10.20


int main()
{
	float km;
	int suitcase;
	printf("How long is your ride in Km?\n");
	scanf("%f", &km);
	printf("How many luggage do you take?\n");
	scanf("%d", &suitcase);
	float Total = ((km*1.30) + (float)(suitcase*2) + Ride);
	printf("In total you have to pay: %.2f", Total);
	return 0;
}