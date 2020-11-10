#define _CRT_SECURE_NO_WARNINGS
#define SIZE 80

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>

typedef enum { FALSE, TRUE }boolean;

char* CreateString();
char* Palindroms();
boolean aPalindrom();

void main()
{
	char str1[SIZE];
	char str2[SIZE];
	printf("Enter the first string:\n");
	gets(str1, SIZE, stdin);//???????? ????? ???? ?????
	printf("Enter the second string:\n");
	gets(str2, SIZE, stdin);
	printf("%s", CreateString(str1, str2));
	char str3[SIZE];//???? ?????? ????? ??? ??? ??? 
	printf("\nEnter the third string:\n");
	gets(str3, SIZE, stdin);
	printf("%s\n", (Palindroms(str3)));
}

char* CreateString(char* string1, char* string2)
{
	char* result = NULL;
	result = (char*)malloc(sizeof(char) * (strlen(string1) + strlen(string2)) + 1);
	int result_index = 0;
	if (!result)
	{
		printf("Error! Can't allocate memory.");
		return NULL;
	}

	for (int string1_index = 0; string1_index < (int)strlen(string1); string1_index++)
	{
		result[result_index] = string1[string1_index];
		result_index++;
		result[result_index] = '\0';
		strcat(result, string2);
		result_index += strlen(string2);
	}
	result[strlen(result)] = '\0';
	return result;
}

boolean aPalindrom(char* str)
{
	int flag = TRUE;
	for (int i = 0; i < (int)strlen(str) / 2; i++)
	{
		if (str[i] != str[strlen(str) - i - 1])
		{
			flag = FALSE;
			break;
		}
	}
	return flag;
}

char* Palindroms(char* str)
{

	boolean flag = TRUE;
	char* temp = NULL;
	char* result = NULL;
	int index = 0, temp_index = 0;

	temp = (char*) malloc (sizeof(char) * strlen(str));
	if (!temp)
	{
		printf("Error! Can't allocate memory.");
		return NULL;
	}

	result = (char*) malloc (sizeof(char)* strlen(str));
	if (!result)
	{
		printf("Error! Can't allocate memory.");
		return NULL;
	}
	result[0] = '\0';
	
	while (str[index] != '\0')
	{
		do
		{
			if (str[index] == '\0')
				break;
			temp[temp_index] = str[index];
			index++;
			temp_index++;
		} while (str[index] != ' ');
		temp[temp_index] = '\0';
		if (aPalindrom(temp))
		{
			temp[temp_index] = str[index];//Add the space
			index++;
			temp_index++;
			temp[temp_index] = '\0';
			strcat(result, temp);
		}
		else
			index++;
		if (str[index - 1] == '\0') break;
		temp_index = 0;
	}
	return result;
}
