#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
typedef enum {FALSE,TRUE}boolean;
void two_prime();
#define last 999 
#define first 100

void main()
{
    two_prime();
}

void two_prime()
{
    int n=0, result,check1=1,check2;

    printf("Enter a number: \n");
    scanf("%d", &n);
    if (n<=last && n>=first)
    {
        for (int i = 2; n % i != 0; i++)
            check1 = i++;
        check2 = n / check1;
        if (check_prime(check1) && check1 != 1)
        {
            if (check_prime(check2) && check2 != 1)
                printf("True");
            else
                printf("False, The number is non prime or is 1");
        }
        else
            printf("False, The number is non prime or is 1");
    }
    else
        printf("number need to be between 100~999");


}

boolean check_prime(int a)
{
    int c;

    for (c = 2; c <= a - 1; c++)
    {
        if (a % c == 0)
            return FALSE;
    }
    if (c == a)
        return TRUE;
}
