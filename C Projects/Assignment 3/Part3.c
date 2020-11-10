#define _CRT_SECURE_NO_WARNINGS
#define ABC_LENGTH 26 //number of letters.
#define SIZE 80//Size of the strings.

#include <string.h>
#include <stdio.h>

//Function to call.
void histogram();
void reverse_char();
void reverse_word();
void ascii_renovate();
void encrypting_string();

//Main Function.
void main()
{
	char line[SIZE];
	printf("Enter a line:\n");
	scanf("%s", line);//User write here string
	histogram(line); //Calling histogram function.
	encrypting_string(line);//Encrypting the string.
	printf("\n%s\n", line);
}

//histogram Function.
void histogram(char* line)
{
	int letter_counter[ABC_LENGTH] = { 0 };
	for (int i = 0; *(line + i) != '\0'; i++)
	{

		if (*(line + i) >= 'A' && *(line + i) <= 'Z') //capital letter check
			letter_counter[line[i] - 'A']++;

		if (*(line + i) >= 'a' && *(line + i) <= 'z') //small letter check
			letter_counter[line[i] - 'a']++;

	}

	printf("\n\nLetter      |     Histogram");
	for (int i = 0; i < 26; i++)
		if (letter_counter[i] > 0)
		{
			printf("\n%c           |     ", 'a' + i);
			for (int j = 0; j < letter_counter[i]; j++)
				printf("*");
		}
}

//reverse_char Function.
void reverse_char(char* line)
{
	int j = 0;
	char result[SIZE];

	for (int i = strlen(line) - 1; i >= 0; i--)
	{
		result[j] = line[i];
		j++;
	}

	result[j] = '\0';
	strcpy(line, result);
}

//ascii_renovate Function.
void ascii_renovate(char* line)
{
	int line_index = 0;
	int word_index = 1;

	while (line[line_index] != '\0')
	{
		if (line[line_index] == ' ')
			word_index = 0;
		else
			line[line_index] += (word_index); 

		word_index++;
		line_index++;
	}
}

//reverse_word Function.
void reverse_word(char* line)
{
	char result[SIZE];
	char temp[SIZE];

	int temp_index = 0, index = strlen(line), result_index = 0;
	while (index >= 0)
	{
		while (index >= 0 && line[index] != ' ')
		{
			temp[temp_index] = line[index];
			index--;
			temp_index++;
		}
		while (temp_index > 0)
		{
			result[result_index] = temp[temp_index - 1];
			result_index++;
			temp_index--;
		}

		result[result_index] = ' ';
		result_index++;

		for (int j = 0; j < SIZE - 1; j++)
			temp[j] = '\0';

		index--;
	}

	result[result_index] = '\0';
	strcpy(line, result);
}

//encrypting_string Function.
void encrypting_string(char* line)
{
	reverse_char(line);
	ascii_renovate(line);
	reverse_word(line);
}