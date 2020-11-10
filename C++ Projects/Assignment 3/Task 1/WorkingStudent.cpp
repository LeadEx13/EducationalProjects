#include "WorkingStudent.h"

WorkingStudent::WorkingStudent(long id, char* name, float average, char* institute, int salary, bool same_institute)
    : Person(id, name), Student(id, name, average, institute), Employee(id, name, salary)
{
    this->same_institute = same_institute;
}

WorkingStudent::WorkingStudent(const WorkingStudent& w)
    : Person(w), Student(w), Employee(w)
{
    this->same_institute = w.same_institute;
}

void WorkingStudent::print() const
{
    cout << "Name of Working Student is: ";
    cout << name << endl;
    cout << "ID of Working Student is: ";
    cout << id << endl;
    cout << "Avarage of Working Student is: ";
    cout << average << endl;
    cout << "Institute of Working Student is: ";
    cout << institute << endl;
    cout << "Salary of Working Student is: ";
    cout << salary << endl;
    if (same_institute)
        cout << "The student does work in same place where he studies" << endl;
    else
        cout << "The student does't work in same place where he studies" << endl;

}


