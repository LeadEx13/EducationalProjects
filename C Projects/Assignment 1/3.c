#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>


int main()
{
	int choice;
	printf("Choose shape:\n1 triangle\n2 rectangle\n3 square\n4 circle\n");
	scanf("%d", &choice);
	if (choice > 0 && choice < 5)
	{
		if (choice == 1)
		{
			float base, height;
			printf("Enter Base: \n");
			scanf("%f",&base);
			printf("Enter height: \n");
			scanf("%f", &height);
			printf("%f", height * base / 2);
		}
		if (choice == 2)
		{
			float base, height;
			printf("Enter Base: \n");
			scanf("%f", &base);
			printf("Enter height: \n");
			scanf("%f", &height);
			printf("%f", height * base);
		}
		if (choice == 3)
		{
			float base;
			printf("Enter Base: \n");
			scanf("%f", &base);
			printf("%f", base * base);
		}
		if (choice == 4)
		{
			float radius;
			printf("Enter radius: \n");
			scanf("%f", &radius);
			printf("%f", radius*radius*3.141592);
		}
	}
	else
	{
		printf("Wrong Choice!");
	}
	return 0;
}