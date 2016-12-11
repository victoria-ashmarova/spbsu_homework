#include <iostream>
#include <fstream>

using namespace std;

bool isNotEmptyLine(char line[])
{
    bool empty = true;
    int i = 0;
    while (line[i] != '\n' && line[i] != '\0')
    {
        if (line[i] != ' ' && line[i] != '\t')
            empty = false;
        i++;
    }
    return !empty;
}

int numberOfNotEmptyLines(ifstream &file)
{
    int const maxSize = 256;
    char line[maxSize] = {'\0'};
    int countNotEmptyLines = 0;
    while (!file.eof())
    {
        file.getline(line, maxSize);
        if (isNotEmptyLine(line))
            countNotEmptyLines++;
    }
    return countNotEmptyLines;
}

int main()
{
    int const maxSize = 256;
    cout << "This program counts not empty lines from file." << endl;
    char name[maxSize] = {'\0'};
    cout << "Enter the name of file" << endl;
    cin >> name;

    ifstream file(name);

    if (!file.is_open())
    {
        cout << "Error" << endl << "Couln't open the file" << endl;
        return 1;
    }

    int notEmptyLines = numberOfNotEmptyLines(file);
    cout << "There are " << notEmptyLines << " not empty lines in " << name << "." <<endl;
    file.close();
    return 0;
}
