#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

void NumericRectangle()
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
}

void main()  
{
	NumericRectangle(); 
}

