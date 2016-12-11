#include <iostream>

using namespace std;

bool mayAddStudent(int nonSelfStudent, int resourse, int numberOfStudents)
{
    return (nonSelfStudent > 3 && nonSelfStudent <= numberOfStudents) && (resourse >= 0 && resourse <= numberOfStudents);
}

void getCouples(int students[], int numberOfStudents)
{
    int nonSelfStudent = 0;
    int resourse = 0;
    cin >> nonSelfStudent;
    cin >> resourse;
    if (mayAddStudent(nonSelfStudent, resourse, numberOfStudents))
    {
        students[nonSelfStudent - 1] = resourse;
    }
}

int amountOfStudents()
{
    int numberOfStudents = 0;
    cin >> numberOfStudents;
    while (numberOfStudents <= 3)
    {
        cout << "Ammount of students must be more then 3. Enter the number of students again." <<endl;
        cin >> numberOfStudents;
    }
    return numberOfStudents;
}

void getInformationAboutStudents(int numberOfStudents, int students[])
{
    for (int i = 0; i < 3; i++)
    {
        students[i] = i + 1;
    }
    for (int i = 3; i < numberOfStudents; i++)
    {
        students[i] = 0;
    }
    cout << "Enter the number of non-self student and number of student who share his variant witn him." << endl;
    for (int i = 3; i < numberOfStudents; i++)
    {
        getCouples(students, numberOfStudents);
        if (i + 1 != numberOfStudents)
        cout << "Enter the information again." << endl;
    }
    cout << "Information about students is obtained." << endl;
    cout << endl;
}

bool conditionOfFirstResourse(int index, int numberOfStudents, int beginIndex)
{
    return index <= 3 || index > numberOfStudents || index == beginIndex;
}

int searchOfFirstResousre(int students[], int index, int numberOfStudents)
{
    int endIndex = students[index - 1];
    while (!conditionOfFirstResourse(endIndex, numberOfStudents, index))
    {
        endIndex = students[endIndex - 1];
    }

    if (endIndex >= 0 && endIndex <= 3)
        return endIndex;

    return 0;
}

void distributionOfStudents(int students[], int numberOfStudents)
{
    for (int i = 4; i <= numberOfStudents; i++)
    {
        students[i - 1] = searchOfFirstResousre(students, i, numberOfStudents);
    }

    for (int i = 0; i < numberOfStudents; i++)
    {
        if (students[i] == 0)
        {
            cout << "student number " << i + 1 << " has handed nothing." << endl;
        }
        else
        {
            cout << "student number " << i + 1 << " has handed " << students[i] << " variant." << endl;
        }
    }
}

int main()
{
    cout << "This program counts students, which didn't do their nomework themselves. " << endl;
    cout << "Enter the number of students. It must be more then 3." << endl;
    int numberOfStudents = amountOfStudents();
    int *students = new int[numberOfStudents];
    getInformationAboutStudents(numberOfStudents, students);
    distributionOfStudents(students, numberOfStudents);
    delete[] students;
    students = nullptr;
    return 0;
}
