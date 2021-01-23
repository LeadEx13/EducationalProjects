"""
 *   Authers: Maor Arnon (ID: 205974553) and Vlad Kelar (ID:320467335)
 *            Emails: maorar1@ac.sce.ac.il    angerag3@Gmail.com
 *   Department of Computer Engineering - Assignment 2 / Part 1 - Principles of software languages
"""

class Triangle(object):
    """represents a 2d hape with three edges: edge1, edge2, edge3"""

    def __init__(self, edge1=0, edge2=0, edge3=0):
        """
        initialize a triangle object
        para: edge1,edge2,edge3 - the three edges of the shape
        """
        self.edge_1 = edge1
        self.edge_2 = edge2
        self.edge_3 = edge3
        self.dispatch_list = {"perimeter": self.perimeter, "int": self.__int__, "add": self.__add__,"str": self.__str__, "repr": self.__repr__}

    def perimeter(self):
        """
        returns the perimeter of the shape
        """
        return self.edge_1 + self.edge_2 + self.edge_3

    def __int__(self):
        """"the shape in int format"""
        return self.edge_1 + self.edge_2 + self.edge_3

    def __add__(self, other):
        """"the shape's add operator"""
        return self.perimeter() + other.perimeter()

    def __str__(self):
        """"the shape in str format"""
        return f'Triangle with sides equal to {self.edge_1} {self.edge_2} and {self.edge_3}'

    def __repr__(self):
        """"the shape's represent operator"""
        return f'Triangle({self.edge_1} , {self.edge_2} , {self.edge_3})'


class Square(object):
    """represents a 2d hape with four edges: edge1, edge2, edge3 ,edge4"""

    def __init__(self, edge1=0, edge2=0, edge3=0, edge4=0):
        """
        initialize a square object
        para: edge1,edge2,edge3,edge4 - the three edges of the shape
        """
        self.edge_1 = edge1
        self.edge_2 = edge2
        self.edge_3 = edge3
        self.edge_4 = edge4
        self.dispatch_list = {"perimeter": self.perimeter, "int": self.__int__, "add": self.__add__,"str": self.__str__, "repr": self.__repr__}

    def perimeter(self):
        """
        returns the perimeter of the shape
        """
        return self.edge_1 + self.edge_2 + self.edge_3 + self.edge_4

    def __add__(self, other):
        """"the shape's add operator"""
        return self.perimeter() + other.perimeter()

    def __str__(self):
        """"the shape in str format"""
        return f'Square with sides equal to {self.edge_1} {self.edge_2} {self.edge_3} and {self.edge_4}'

    def __repr__(self):
        """"the shape's represent operator"""
        return f'Square({self.edge_1} , {self.edge_2} , {self.edge_3} , {self.edge_4})'


# def add(shape_1, shape_2):
#     return shape_1.perimeter() + shape_2.perimeter()

def type_tag(x):
    """
    returns the type of the object in string format
    para:x - the object i want to check
    """
    tags={Triangle:"Triangle",Square:"Square"}
    return type_tag.tags[type(x)]

def add(x,y):
    """
    uses the right function using a dictionary of all the options to add shapes..returns int value
    para x,y- the shapes i want to add together
    """
    implementation={("Square","Square"):add_Square,("Triangle","Triangle"):add_Triangle,("Triangle","Square")\
        :add_Triangle_Square,("Square","Triangle"):add_Square_Triangle}
    types=(type_tag(x),type_tag(y))
    return implementation[types](x,y)


def add_Triangle_Square(a,b):
    """
    adds a triangle with a square
    para:a,b-the objects i want to add together
    """
    return a.perimeter() + b.perimeter()


def add_Square_Triangle(b,a):
    """
    adds a square with a triangle.
    para:a,b-the objects i want to add together
    """
    return a.perimeter() + b.perimeter()


def add_Square(a,b):
    """
    adds a square with a square.
    para:a,b-the objects i want to add together
    """
    return a.perimeter() + b.perimeter()


def add_Triangle(a,b):
    """
    adds a triangle with a triangle.
    para:a,b-the objects i want to add together
    """
    return a.perimeter() + b.perimeter()





t = Triangle(1, 2, 3)

print(t.perimeter())

print(add(t,(Triangle(1,1,1))))

print(repr(t))  # Also t.__repr__() would work
