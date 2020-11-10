#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>

typedef struct
{
    char name[30];
    int amount, price;
} Product;

typedef struct
{
    int id;
    Product* pProd;
    int numProd;
} Cart;

void InputProduct(Product* pProduct);
void PrintProduct(Product prod);
void InputCart(Cart* pCart);
void PrintCart(Cart crt);
void FreeAll(Cart** ppQueue, int size);
void PrintAll(Cart* pQueue, int size);
void Payment(Cart* pQueue, int size);
int InitCashReg(Cart** ppQueue);

//Main Function.
int main()
{
    Cart* pQueue = NULL;
    int size,test1;
    size = InitCashReg(&pQueue);
    PrintAll(pQueue, size);
    Payment(pQueue, size);
    FreeAll(&pQueue, size);
    return 0;
}

//PrintAll Function.
void PrintAll(Cart* pQueue, int size)
{
    printf("\n\n");
    for (int i = 0; i < size; i++)
    {
        printf("\nCart number %d\n", i + 1);
        PrintCart(pQueue[i]);
    }
}

//PrintCart Function.
void PrintCart(Cart crt)
{
    printf("Cart owner ID: %d\n", crt.id);
    printf("Number of products in the cart: %d\n", crt.numProd);
    for (int i = 0; i < crt.numProd; i++)
    {
        printf("Product number %d :\n", i + 1);
        PrintProduct(crt.pProd[i]);
    }
}

//PrintProduct Function.
void PrintProduct(Product prod)
{
    printf("The product's name is: %s\n", prod.name);
    printf("The product's price is: %d\n", prod.price);
    printf("The amout of that product that have been sold is: %d\n", prod.amount);
}

//InitCashReg Function.
int InitCashReg(Cart** ppQueue)
{
    int cAmount;
    printf("How many carts are in queue?: ");
    scanf("%d", &cAmount);
    *ppQueue = NULL;
    *ppQueue = (Cart*)malloc(sizeof(Cart) * cAmount);
    if (!*ppQueue)
    {
        printf("Could not allocate memory.");
        return 0;
    }
    for (int i = 0; i < cAmount; i++)
    {
        printf("Cart number %d\n", i + 1);
        InputCart((*ppQueue) + i);
    }
    return cAmount;
}

//InputCart Function.
void InputCart(Cart* pCart)
{
    printf("Enter your ID: ");
    scanf("%d", &pCart->id);
    printf("Enter number of products you wanna buy: ");
    scanf("%d", &pCart->numProd);
    pCart->pProd = (Product*)malloc(sizeof(Product) * pCart->numProd);
    for (int i = 0; i < pCart->numProd; i++)
    {
        printf("Enter product number %d :\n", i + 1);
        InputProduct(pCart->pProd + i);
    }
}

//InputProduct Function.
void InputProduct(Product* pProduct)
{
    printf("Enter product name: ");
    scanf("%s", pProduct->name);
    printf("Enter price: ");
    scanf("%d", &pProduct->price);
    printf("Enter amount purchesed: ");
    scanf("%d", &pProduct->amount);
}

//FreeAll Function.
void FreeAll(Cart** ppQueue, int size)
{
    for (int i = 0; i < size; i++) free((*ppQueue)[i].pProd);
    free(*ppQueue);
}

//Payment Function.
void Payment(Cart* pQueue, int size)
{
    printf("\n\n");
    int payment = 0;
    for (int i = 0; i < size; i++)
    {
        payment = 0;
        for (int j = 0; j < pQueue[i].numProd; j++)
            payment += (pQueue[i].pProd[j].price) * (pQueue[i].pProd[j].amount);
        printf("Customer # %d, Total payment: %d\n", i + 1, payment);
    }
}