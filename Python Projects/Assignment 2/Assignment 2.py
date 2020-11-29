def myFilter(L, func):
    """
    Filter 'L' list values with the boolean function 'func'
    
    Parameters: L (list) , func(reference to a function)
    
    Output:Return a list with all the values that when inserted as an input
           to the given function it returns 'True' .
    """
    return [n for n in L if func(n)]



def myFilterMulti(L,funcL):
    """
    Filter 'L' list values with all of the boolean functions in the list 'funcL'.
    Parameters:L (list), funcL (list of references to functions)
    Output:Returns a list with all the values that when inserted
           to each function in 'funcL' all of them return 'True' value.
    """
    for f in funcL:
        L= myFilter(L,f)
    return L


def myPrime(x):
    """
    Is the input a prime number.
    Parameters:x(int)
    Output:Returns boolean value 'True' if 'x' is a prime number ,otherwise returns 'False'.
    """
    if x>1:
        for n in range(2,x//2):
            if (x%n)==0:
                return False
        return True
    else:
        return False

def isPalindrome(x):
    """
    Is the input a palindrome.
    Parameters:x(int/str)
    Output:Returns boolean value 'True' if 'x' is a a palindrome ,otherwise returns 'False'.
    """
    x = str(x)
    return x == x[::-1]

def is_anagram(a,b):
    """
    Is the input 'a' is an anagram of 'b' and vice versa.
    Parameters:a(str),b(str)
    Output:Returns boolean value 'True' if all of the characters (not case sensitive)
           that are present in string 'a' are also present in string 'b' (in the same quantity) ,otherwise returns 'False'.
    """
    a=a.upper()
    b=b.upper()
    for c in a:
        if c not in b:
            return False
        b=b.replace(c,'',1)
    return b==''



print(myFilter([9,10,16,24, 29, 36,11], myPrime))
print(myFilterMulti([1,9,10,16,24,55, 131,149,181],[ myPrime,isPalindrome]))
print(is_anagram('School master', 'The clAssroom'))