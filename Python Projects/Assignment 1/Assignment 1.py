import math as mt
# 1


def XNor(a, b):
    """
    Compares A to B and if they are equal returns true
    a simple xnor function that return true if the both true or both false
    :param a:true/false
    :param b:true/false
    :return: true/false after xnor
    """
    return a == b


# 2
def RemoveMinDigit(number):
    """
    get a number and remove the smallest digit in it and return the new number without the smallest
    :param number: digit input
    :return: number without smallest digit
    """
    number = str(number)
    temp = number
    for a in range(0, 10):
        number = number.replace(str(a), '')
        if temp != number:
            return int(number)


# same function but in a different way
'''
def RemoveMinDigit(c):
    x,temp=9,1
    while c != 0:
        if(c % 10) <= x:
            x= c%10
        temp = (temp * 10) + c % 10
        c=int((c-c%10)/10)
    while temp !=1:
        if(temp%10)!=x:
            c=c*10
            c= c+(temp%10)
        temp=int(temp-temp%10)/10

    return int(x,c)
'''


# 3
def SquareArea(d, c, b, a, h, g):
    """
    get parameters and make a square area math
    :param d: number parameter
    :param c: number parameter
    :param b: number parameter
    :param a: number parameter
    :param h: degree1
    :param g: degree2
    :return: calculation of square area
    """
    s = (a + b + c + d) / 2
    h = (mt.pi * h) / 180
    g = (mt.pi * g) / 180
    return mt.sqrt((s - a) * (s - b) * (s - c) * (s - d) - a * b * c * d * mt.cos((g + h) / 2) ** 2)


# 4
def CheckArithmeticSeries(a):
    """
    check of the number is a Invoice series
    :param a: number
    :return:true/false if it is Invoice series
    """
    list = [int(d) for d in str(a)]
    for d in range(len(list))[2:]:
        if (list[0] - list[1]) != (list[d - 1] - list[d]):
            return False
    return True


# 5
def CanBeTriangle(a, b, c):
    """
    check if the parameters can make a triangle
    :param a: number parameter
    :param b: number parameter
    :param c: number parameter
    :return: true\false if all of the parameters can make a triangle
    """
    return (a < b + c and b < a + c and c < a + b and a > 0 and b > 0 and c > 0)


# 6
def CalcUpperCalcLower(str):
    """
    calculat the number of lower cases and the upper cases
    :param str: try of word
    :return: number of the lower and upper cases
    """
    lower = 0
    upper = 0
    for char in str:
        if char.islower():
            lower += 1
        elif char.isupper():
            upper += 1
    print('Number of Upper cases: {0}. \nNumber of Lower cases: {1}.'.format(upper, lower))


# 7
def PerfectNumber(a):
    """
    check if the number is perfect by divide to the previous number
    and then sum all of them and if they have same number so its  perfect
    :param a:number
    :return:true/false if it is perfect
    """
    sum = 0
    for i in range(a - 1, 0, -1):
        if (a % i == 0):
            sum = sum + i
    return sum == a


# 8
def IsPangrams(str):
    """
    check if the line is own all of the a-z symbols and not matter if it is upper or lower
    :param str: line of words
    :return: true/false if the own all of the a-z in it
    """
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for char in alphabet:
        if char not in str.upper():
            return False
    return True


print(SquareArea(4, 5, 4, 5, 90, 90))


"""
#Tests

a = bool(int(input("Enter 0 for false and 1 for true for A...")))
b = bool(int(input("Enter 0 for false and 1 for true for B...")))
print(f'a({a}) xnor b({b}) is: {XNor(a, b)}')
print(RemoveMinDigit(55503456))
print(CheckArithmeticSeries(2468))
print(CheckArithmeticSeries(14789))
print(CheckArithmeticSeries(1234567))
print('Can be triangle ',CanBeTriangle(10,10,10))
CalcUpperCalcLower('fdfSmkSADNlnkl')
print ('The square area is:'+str(SquareArea(10,5,6,8,45, 90)))
a=int(input('Enter a number to check if its perfect...'))
print(f"The number {a} is a Perfect number" if PerfectNumber(a) else f"The number {a} is not a Perfect number")
a=input('Enter text to check if the string is pangram ...')
print(f"The string {a} {'is ' if IsPangrams(a) else 'is not '}a pangram")
"""
