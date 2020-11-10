#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <malloc.h>

typedef enum {FALSE,TRUE}boolean;

void BuildGroup();
int* Union();
int* Intersection();
int* Minus();
void PrintGroup();

void main()
{//--------------------------------------------//
	int* group1;
	int* group2;
	int* group3;
	int* group4;
	int* group5;
	int count1,count2,count3,count4,count5;
//---------------------------------------------//
	printf("Enter Group1 size..\n");
	scanf("%d", &count1);
	printf("Enter Group1 values..\n");
	BuildGroup(&group1, &count1);

	printf("Enter Group2 size..\n");
	scanf("%d", &count2);
	printf("Enter Group2 values..\n");
	BuildGroup(&group2, &count2);
	
	printf("\nGroup1: ");
	PrintGroup(group1, count1);
	printf("\nGroup2: ");
	PrintGroup(group2, count2);

	group3=Union(group1, count1, group2, count2, &count3);
	printf("\nGroup3(1u2): ");
	PrintGroup(group3,count3);
	
	free(group3);

	group4 = Intersection(group1, count1, group2, count2, &count4);
	printf("\nGroup4(1n2): ");
	PrintGroup(group4, count4);
	
	free(group4);

	group5 = Minus(group1, count1, group2, count2, &count5);
	printf("\nGroup(1-2): ");
	PrintGroup(group5, count5);
	free(group5);
	return;
}

void BuildGroup(int** group, int* count)
{//-----------------------------------------------//
	boolean flag;
	int index = 0,temp;
	*group = NULL;
	*group =(int*)malloc(sizeof(int)*(*count));
//------------------------------------------------//	
	if (!*group) 
	{ printf("Error,cannot allocate memory.");
	return; } 

	while (index < (*count))
	{
		flag = TRUE;
		printf("Number {%d}:\n",index+1);
		scanf("%d", &temp);
		for (int i = 0; i < index; i++)
		{
			if ((*group)[i] == temp)
			{
				flag = FALSE;
			}
		}
		if (flag == TRUE)
		{
			(*group)[index] = temp;
			index++;
		}
	}
}

int* Union(int* group1, int size1, int* group2, int size2, int* sizeUnion)
{ //-----------------------------------------------------//
	boolean flag;
	int groups_index = 0,result_index=0;
	int* result = NULL;
	result= (int*)malloc(sizeof(int) * (size1 + size2));
 //------------------------------------------------------//
	if (!result)
	{ 
		return NULL; 
		printf("could not allocate memory");
	}

	while (groups_index < (size1 + size2))
	{
		flag = TRUE;
		if(groups_index<size1)
		{
			for (int i = 0; i <= groups_index; i++)
			{
				if (result[i] == group1[groups_index])
				{
					flag = FALSE;
				}
			}
			if (flag)
			{
				result[result_index] = group1[groups_index];
				result_index++;
			}
		}
		else
		{
			for (int i = 0; i <= (groups_index); i++)
			{
				if (result[i] == group2[groups_index-size1])
				{
					flag = FALSE;
				}
			}
			if (flag)
			{
				result[result_index] = group2[groups_index-size1];
				result_index++;
			}
		}

		groups_index++;

	}
	*sizeUnion = result_index;
	result = (int*)realloc(result, sizeof(int) * result_index);
	return result;
}


int* Intersection(int* group1, int size1, int* group2, int size2, int* sizeInter)
{ //-----------------------------------------------------//
	boolean flag;
	int groups_index = 0, result_index = 0;
	int* result = NULL;
	result = (int*)malloc(sizeof(int) * (size1 + size2));
	//------------------------------------------------------//
	if (!result)
	{
		return NULL;
		printf("could not allocate memory");
	}

	while (groups_index < (size1))
	{
		flag = FALSE;

			for (int i = 0; i <=size2; i++)
			{
				if (group2[i] == group1[groups_index])
				{
					flag = TRUE;
				}
			}
			if (flag)
			{
				result[result_index] = group1[groups_index];
				result_index++;
			}
	
		groups_index++;

	}
	*sizeInter = result_index;
	result = (int*)realloc(result, sizeof(int) * result_index);
	return result;
}

int* Minus(int* group1, int size1, int* group2, int size2, int* sizeMinus)
{ //-----------------------------------------------------//
	boolean flag;
	int groups_index = 0, result_index = 0;
	int* result = NULL;
	result = (int*)malloc(sizeof(int) * (size1 + size2));
	//------------------------------------------------------//
	if (!result)
	{
		return NULL;
		printf("could not allocate memory");
	}

	while (groups_index < (size1))
	{
		flag = TRUE;

		for (int i = 0; i <= size2; i++)
		{
			if (group2[i] == group1[groups_index])
			{
				flag = FALSE;
			}
		}
		if (flag)
		{
			result[result_index] = group1[groups_index];
			result_index++;
		}

		groups_index++;

	}
	*sizeMinus = result_index;
	result = (int*)realloc(result, sizeof(int) * result_index);
	return result;
}

void PrintGroup(int* group, int size)
{
	printf("{ ");
	for (int i = 0; i < (size-1); i++)printf(" %d ,", group[i]);
	printf(" %d }", group[size-1]);
	return;
}


