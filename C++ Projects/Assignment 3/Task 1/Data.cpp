#include "Data.h"
#define SIZE 30

void Data::operator+=(Person* p)
{
    if (size == 0)
    {
        arr = new Person * [1];
        arr[0] = p;
    }
    if (size > 0)
    {
        Person** temp;
        temp = new Person * [size];
        for (int i = 0; i < size; i++)
        {
            temp[i] = arr[i];
        }
        delete[]arr;
        arr = new Person * [size+1];
        for (int i = 0; i < size; i++)
        {
            arr[i] = temp[i];
        }
        arr[size] = p;
        delete[]temp;
    }
    size++;
}

bool Data::find(long id)
{
    for (int i = 0; i < size; i++)
        if (arr[i]->getid() == id)return true;
    return false;
}

void Data::init()
{
    int salary;
    long id;
    float average;
    char name[SIZE], institute[SIZE];
    bool same_institute;
    while (1)
    {
        int choice;
        Person* chosen;
        cin.clear();
        cout <<"-----------------------------"<<endl<<"User: "<<size+1<<endl<< "Please choice from the menu:" << endl << "1.Person" << endl << "2.Student" << endl << "3.Employee" << endl << "4.Working Student" << endl << "5.Exit" << endl;
        cin >> choice;
        switch (choice)
        {
        case 1:
            cout << "Enter person ID: ";
            cin >> id;
            cout << "Enter person name: ";
            cin >> name;
            if (find(id))
            {
                cout << "ID is already exist, try again."<<endl;
                break;
            }
            chosen = new Person(id, name);
            operator+=(chosen);
            break;
        case 2:
            cout << "Enter student ID: ";
            cin >> id;
            cout << "Enter student name: ";
            cin >> name;
            if (find(id))
            {
                cout << "ID is already exist, try again." << endl;
                break;
            }
            cout << "Enter student average: ";
            cin >> average;
            cout << "Enter student institute: ";
            cin >> institute;
            chosen = new Student(id, name,average,institute);
            operator+=(chosen);
            break;
        case 3:
            cout << "Enter employee ID: ";
            cin >> id;
            cout << "Enter employee name: ";
            cin >> name;
            if (find(id))
            {
                cout << "ID is already exist, try again." << endl;
                break;
            }
            cout << "Enter employee salary: ";
            cin >> salary;
            chosen = new Employee(id, name, salary);
            operator+=(chosen);
            break;
        case 4:
            cout << "Enter working student ID: ";
            cin >> id;
            cout << "Enter working student name: ";
            cin >> name;
            if (find(id))
            {
                cout << "ID is already exist, try again." << endl;
                break;
            }
            cout << "Enter working student average: ";
            cin >> average;
            cout << "Enter working student institute: ";
            cin >> institute;
            cout << "Enter working student salary: ";
            cin >> salary;
            cout << "Are the student work in the same institute?"<<endl<<"1=Yes"<<endl<<"0=No"<<endl;
            cin >> same_institute;
            if (same_institute != 0) same_institute = true;
            chosen = new WorkingStudent(id, name, average, institute,salary, same_institute);
            operator+=(chosen);
            break;
        case 5:
            return;
        default:
            cout << "Wrong choice, try again." << endl;
        }
    }
}

void Data::print_all()const
{
    cout << "----------------------------" << endl;
    for (int i = 0; i < size; i++)
    {
        arr[i]->print();
        cout << "----------------------------" << endl;
    }
}

void Data::print_excellent() const
{
    for (int i = 0; i < size; i++)
        if (dynamic_cast<Student*>(arr[i]))
            if (dynamic_cast<Student*>(arr[i])->getaverage() > 80)
            {
                cout << "Student " << (arr[i])->getname() << " with average above 80" << endl;
                arr[i]->print();
                cout << "----------------------------" << endl;
            }
}

void Data::print_rich() const
{
    int max_salary = 0;

    for (int i = 0; i < size; i++)
        if (dynamic_cast<Employee*>(arr[i]))
            if (dynamic_cast<Employee*>(arr[i])->getsalary() > max_salary)
                max_salary = (dynamic_cast<Employee*>(arr[i])->getsalary());

    cout << "----------------------------" << endl;
    cout << "Max salary is : " << endl << max_salary << endl;
    for (int i = 0; i < size; i++)
        if (dynamic_cast<Employee*>(arr[i]))
            if (dynamic_cast<Employee*>(arr[i])->getsalary() == max_salary)
            {
                cout << "Name of worker with this salary is : " << endl;
                (dynamic_cast<Employee*>(arr[i])->getname());
                cout << endl;
            }
}