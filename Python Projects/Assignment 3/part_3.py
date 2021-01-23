class node:
    """
    a value and 'pointer' to the next node , used in LinkedList
    """
    def __init__(self, value=0, next=None):
        self.value = value
        self.next = next

    def set_next(self, next):
        """
        sets next value
        para:next - the next node
        """
        self.next = next

    def set_value(self, value):
        """
        sets new value
        para:value - the new value
        """
        self.value = value

    def get_value(self):
        """
        gets the value
        """
        return self.value

    def get_next(self):
        """
        gets the next node
        """
        return self.next


class LinkedList:
    """
    a list of values, implemented with nodes
    """
    def __init__(self):
        self.head = None
        self.tail = None

    # def insert(self, value):  # # Failed attempt
    #     new_node = node(value)
    #
    #     if self.head == None:
    #         self.head = new_node
    #
    #     if self.tail != None:
    #         self.tail.next = new_node
    #
    #     self.tail = new_node

    def append(self,value):
        """
        takes a value and puts it at the end of the list
        para:value - the inserted value
        """
        self.insert(value)

    def insert(self,value,index=-1):
        """
        inserting a new value into the linked list if an index was not given it will be placed at the end of the list
        para:value - the inserted value
        para:index - the index which you want to push the value
        """
        temp = node(value)
        current_node = self.head
        prev = None
        count = 0
        flag = False
        # if index > self.length-1:
        #     raise print('List Index is Out Of Range')
        while current_node is not None and not flag:
            if count == index:
                flag = True
            else:
                prev = current_node
                current_node = current_node.get_next()
                count += 1
        if prev is None:
            temp.set_next(self.head)
            self.head = temp
        else:
            temp.set_next(current_node)
            prev.set_next(temp)

    def __str__(self):
        """
        a string operator (not required)
        (in order to check if the functions worked properly)
        """
        current = self.head
        string = '['
        while current is not None:
            string += str(current.get_value())
            if current.get_next() is not None:
                string += ', '
            current = current.get_next()
        string += ']'
        return string

    def remove_by_value(self,value):
        """
        removes a node from the list by value
        para:value - the value of the node you want to remove
        """
        current = self.head
        previous = None
        found = False
        while current is not None and not found:
            if current.get_value() == value:
                found = True
            else:
                previous = current
                current = current.get_next()
        if previous == None:
            # The item is the 1st value
            self.head = current.get_next()
        else:
            if current.get_next() is None:
                self.tail = previous  # in case the current tail is removed
            previous.set_next(current.get_next())

    def search(self,value):
        """
        finds and returns a node from the list by value
        para:value - the value of the node you want to find
        """
        current = self.head
        found = False
        while current is not None and not found:
            if current.get_value() == value:
                found = True
            else:
                current = current.get_next()
        if found == True :
            return current
        else:
            print("The value dosent exist in the list")
            return None

    def remove_by_index(self,i):
        """
        removes a node from the list by index
        para:i - the index of the node you want to remove
        """
        current = self.head
        index=0
        previous = None
        found = False
        while current.get_next() is not None and not found:
            if i == index:
                found = True
            else:
                previous = current
                current = current.get_next()
                index += 1
        if previous == None:
            # The value is the 1st value
            self.head = current.get_next()
        else:
            if current.get_next() is None:
                self.tail = previous  # in case the current tail is removed
            previous.set_next(current.get_next())
    def reverse(self):
        """
        Changes the list so the nodes will be sorted in reverse
        """
        new_list=LinkedList()
        current=self.head
        if self.head is self.tail:
            return
        while current is not self.tail:
            new_list.insert(current.get_value(),0)
            current=current.next
        self.head=new_list.head
        self.tail=new_list.tail




L=LinkedList()
for i in range(10):
    L.insert(i,i)
L.insert(10,3)
print(L)
L.remove_by_value(4)
print(L)
print(L.search(10).get_next().get_value())
print(L)
L.remove_by_index(3)
print(L)
L.reverse()
print(L)