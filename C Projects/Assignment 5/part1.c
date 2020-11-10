#define _CRT_SECURE_NO_WARNINGS
#define ABC_DIFF ('a'-'A')
#define SIZE 80

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

//True/False function.
typedef enum { false, true }boolean;

//Function to call.
boolean CheckNumber();
boolean Equal();
int Creator(int);
void Reducing(char*);
boolean CheckString(char*, char*);
int Compare(char*, char*);


//Main Function.
int main()
{
	int sum,num;
	char str1[SIZE];
	printf("Please enter a number: ");
	scanf("%d",&num);
	printf(CheckNumber(num) ? "All of the digits are even.\n":"Not all digits are even.\n");
	printf("Please enter a number: ");
	scanf("%d", &num);
	printf("Please enter a number to check Equal:");
	scanf("%d", &sum);
	printf(Equal(num,sum) ? "The sum is equals to the number you choise.\n" : "The sum is not equals to the number you choise.\n");
	printf("Please enter a number: ");
	scanf("%d", &num);
	printf("Even numbers are: %d\n",Creator(num));
	printf("Please enter a string(with no space): ");
	getchar();
	fgets(str1, SIZE, stdin);
	printf("String backwords with only letters:");
	Reducing(str1);
	printf("\nPlese enter a string(with no space): ");
	scanf("%s", str1);
	printf("Enter the first letter of the string you want to compare(length of your string can be %d): ", strlen(str1));
	scanf("%d", &num);
	num--;
	printf(CheckString(str1, &(str1[num])) ? "These halves of the string are equal\n" : "These halves of the string are not equal\n");
	printf("There is %d similar letters.\n",Compare(str1, &(str1[num])));
	return 0;
}



//CheckNumber Function.
boolean CheckNumber(int num)
{
	if(num ==0) return true;
	if (num % 2 != 0) return false;
	else CheckNumber(num = num / 10);
}

//Equal Function.
boolean Equal(int num,int sum)
{
	if (sum > num || num % 10 > sum) return false;
	if (num % 10 == sum)return true;
	return Equal(num / 10, sum - (num % 10));
}

//Creator Function.
int Creator(int num)
{
	if (num < 10 && num > -10) return (!(num % 2) ? num : 0);
	if (!(num % 10 % 2)) return(num % 10 + Creator(num / 10) * 10);
	return Creator(num / 10);
}

//Reducing Function.
void Reducing(char* str)
{
	if (strlen(str) == 1)
		if ((str[0] < 'z' && str[0]>'a') || (str[0] < 'Z' && str[0]>'A'))
		{
			printf("%s", str);
			return;
		}
		else return;
	if ((str[0] <= 'z' && str[0] >= 'a') || (str[0] <= 'Z' && str[0] >= 'A'))
	{
		Reducing(&str[1]);
		printf("%c", str[0]);
		return;
	}
	Reducing(&str[1]);
}

//MinMaxArray Function.
void MinMaxArray(int* array, int size, int* min, int* max)
{
	if (size == 1)
	{
		if (*array > * max) *max = *array;
		if (*array < *min) *min = *array;
	}
}

//CheckString Function.
boolean CheckString(char* a, char* b)
{
	if (strlen(b) == 1) return (*a == *b);
	if (*a == *b) return CheckString(a + 1, b + 1);
	return false;
}

//Compare Function.
int Compare(char* str1, char* str2)
{
	if (strlen(str1) == 1 || strlen(str2) == 1) return (*str1 == *str2 || *str1 + ABC_DIFF == *str2 + ABC_DIFF || *str1 == *str2 + ABC_DIFF || *str1 + ABC_DIFF == *str2);
	if (*str1 == *str2 || *str1 + ABC_DIFF == *str2 + ABC_DIFF || *str1 == *str2 + ABC_DIFF || *str1 + ABC_DIFF == *str2) return 1 + Compare(str1 + 1, str2 + 1);
	return Compare(str1 + 1, str2 + 1);
}