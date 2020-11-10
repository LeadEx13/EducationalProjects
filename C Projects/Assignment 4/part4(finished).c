#define _CRT_SECURE_NO_WARNINGS
#define SIZE 20

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>

typedef enum { FALSE, TRUE }boolean;

void Init(char***, int**, int*);
void AllocateNames(char*** , int );
void AllocateGrades(int**, int);
boolean LegalNames(char*);
boolean LowercaseCheck(char*);
boolean NotOnTheList(char **,char*,int);
int Find(char**, int*, int, char*);
void FreeAll(char***, int**, int);

void main()
{
	int size;
	char** names;
	int* grades;
	char name[SIZE];
	printf("Enter the number of students:\n");
	scanf("%d", &size);
	Init(&names,&grades,&size); //makes a list of students and grades
	printf("Enter student name:\n");
	scanf("%s", name);
	printf("%s's grade is: %d\n",name,Find(names,grades,size,name));
	FreeAll(&names,&grades,size);//frees all of the allocated memory.

}

boolean LegalNames(char* name)    //checks if the name is legal
{
	if ((name[0] > 'Z') || (name[0] < 'A'))return FALSE;
	return LowercaseCheck(&name[1]);
}      
boolean LowercaseCheck(char* name)//Recursion -if the letter im looking at go to the next one and check if its the end of the name return TRUE
{                                 //else return false
	if (!strlen(name))return TRUE;
	else if (name[0] <= 'z' && name[0] >= 'a') return LowercaseCheck(&name[1]);
	return FALSE;
}
boolean NotOnTheList(char** list, char* name,int size) //checks if the name is already on the list
{
	for (int i = 0; i < size; i++)
	{
		if (!strcmp((name), (list)[i]))
			return FALSE;
	}
	return TRUE;
}
void AllocateNames(char*** names,int size) //allocating names array
{
	(*names) = NULL;
	(*names) = (char**)malloc(sizeof(char*) * size);
	if (!names) 
	{
		printf("Couldn't allocate memory.");
		return;
	}
	for (int i = 0; i < size; i++)
	{
		(*names)[i] = (char*)malloc(sizeof(char) * SIZE);
		if (!names[i])
		{
			printf("Couldn't allocate memory.");
			return ;
		}
	}
}
void AllocateGrades(int** grades,int size)//allocating grades array
{
	(*grades) = NULL;
	(*grades) = (int*)malloc(sizeof(int) * size);
	if (!(*grades))
	{
		printf("Couldn't allocate memory.");
		return;
	}
}
void Init(char*** names, int** grades, int* size)
{
	AllocateGrades(grades,(*size));
	AllocateNames(names,(*size));
	
	boolean flag = TRUE;
	for (int i = 0; i < (*size); i++)
	{
		flag = TRUE;
		while (flag)
		{
			printf("Enter name{%d}:\n",i);
			scanf("%s",(*names)[i]);
			if (LegalNames((*names)[i]) && NotOnTheList( (*names), (*names)[i], i))
			{
				flag = FALSE;
			}
			else
				printf("illegal name. try again.\n");
		}
		flag = TRUE;
		while (flag)
		{
			printf("Enter Grade{%d}:\n", i);
			scanf("%d", (*grades)+i);
			if (((*grades)[i])>=0&&((*grades)[i])<=100)
			{
				flag = FALSE;
			}
			else
			{
				printf("illegal number. try again.\n");
			}
		}


	}
}
int Find(char** names, int* grades, int size, char* name)
{
	for (int i = 0; i < size; i++)
	{
		if ( !strcmp((name),names[i]) ) 
		{
			return grades[i];
		}
	}
	return -1;
}
void FreeAll(char*** names, int** grades, int size)
{
	for (int i = 0; i <= size; i++)
	{
		free(names[i]);
	}
	free(names);
	free(grades);
}