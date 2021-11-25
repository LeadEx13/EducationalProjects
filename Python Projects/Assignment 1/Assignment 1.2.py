'''
        Created by Vlad "LeadEx" Kelar
        Assignment 1
        23/11/2021
'''
from sys import exit
import numpy as np
from math import sqrt

#Question 1
def factorSum(num):
    '''
        recieve a number and break him to mult of prime numbers and make a sum from them.(remove and duplicate)
    :param num:number
    :return:sum of the prime number that crate num if you mult all of them
    '''
    if num == 1: return 1
    if num < 1: return 'Negative number'
    total, limit, check = [], int(sqrt(num)) + 1, 2
    for check in range(2, limit):
        while num % check == 0:
            if check in total:
                num /= check
            else:
                total.append(check)
                num /= check
    if num > 1:
        total.append(num)
    return np.sum(total)

#Question 2
def onlyPositive(func):
    '''
        changing parameter in function to positive
    :param func: recieve a function
    :return: absolute of the parameter from the function he recieve
    '''
    def inner1(c):
        print(func(abs(c)))
    return inner1

def f1(x): return x+1 #test func

#Question 3
def interceptPoint(line1, line2):
    '''
        find a meeting point between 2 lines
    :param line1: x,y for straight formula of line 1
    :param line2: x,y for straight formula of line 2
    :return: A meeting point between the straight
    '''
    if (line2[0]-line1[0]) == 0:
        print("None")
        exit()
    a = (line1[1]-line2[1])/(line2[0]-line1[0])
    b = a*line1[0]+line1[1]
    c = a*line2[0]+line2[1]
    if b!=c: print("None")
    return print(a, b)

#Question 4
def printNumbers(first,last,num):
    '''
        print sequence of number from first to last with a skip on number that you choose. work in any direction
    :param first: starting point
    :param last: end point
    :param num: number that have to skip
    :return: print all numbers by order except "num"
    '''
    if last < 0:
        if first < last:
            exit()
        elif first != num:
            print(first)
        printNumbers(first - 1, last, num)
    else:
        if first > last:
            exit()
        elif first != num:
            print(first)
        printNumbers(first+1,last,num)

#Question 5
def arrProduct(arr1,arr2):
    '''
        make arry with numbers, from the first arry we receive numbers we want to duplicate and from the second how many times we want them to be duplicated
    :param arr1: arry of positive numbers
    :param arr2: arry of positive numbers
    :return: new arry with duplicated numbers
    '''
    a = []
    for i in range(len(arr1)):
        for j in range(arr2[i]):
            a.append(arr1[i])
    print(a)

#Question 6
def analyze(list1):
    '''
        recieve str with numbers, convert to float and remove any number under 75
    :param list1: str list of numbers
    :return: float list with numbers higher then 75
    '''
    list1 = list(list1.split(","))
    list2 = [float(i) for i in list1]
    list2.sort()
    for i in range(len(list2)):
        if list2[0]<=75.0:
            list2.pop(0)
    print(len(list2))


#Question 1 test
#print(int(factorSum(60)))

#Question 2 test
#g = onlyPositive(f1)
#g(-5)

#Question 3 test
#interceptPoint((5,4),(5,9))
#interceptPoint((5,4),(5,4))
#interceptPoint((2,4),(5,-2))

#Question 4 test
#printNumbers(1,5,3)
#printNumbers(2,-6,-2)

#Question 5 test
#arrProduct([6 , 7, 8], [3, 2, 5])

#Question 6 test
#analyze ("45 , 65, 70.4 , 82.6 , 20.1 , 90.8 , 76.1 , 67.1 , 79.9, 85, 75")