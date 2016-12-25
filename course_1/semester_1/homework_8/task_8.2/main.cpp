#include <iostream>
#include "line.h"

using namespace std;

enum {exitProgram, printLines, addition, removal, checkLenght, cloneLine, contactenationLines, equality, checkEmpty, makeSubString};

void printAction()
{
    cout << "0 - exit." << endl;
    cout << "1 - print all lines which you can work with." << endl;
    cout << "2 - add new line" << endl;
    cout << "3 - remove line from list" << endl;
    cout << "4 - to check lenght of line." << endl;
    cout << "5 - to clone line." << endl;
    cout << "6 - contact 2 lines." << endl;
    cout << "7 - check eguality of two lines." << endl;
    cout << "8 - check empty of line." << endl;
    cout << "9 - make subline." << endl;
}

Line *getLineFromList(ListOfCreatedLines *list)
{
    int number = 0;
    scanf("%d", &number);
    return getLineFromNumber(list, number);
}

void actionAddition(ListOfCreatedLines *list)
{
    cout << "Enter line." << endl;
    addLineFromConsole(list);
    cout << "Line is added" << endl;
}

void actionRemoval(ListOfCreatedLines *list)
{
    cout << "Enter the number of line, which you want to remove from list." << endl;
    Line *toRemove = getLineFromList(list);
    if (toRemove == nullptr)
    {
        cout << "Error. Nothing to remove." << endl;
        return;
    }
    char *line = getChar(toRemove);
    if (line != nullptr)
    {
        cout << "Line " << line << " was removed." << endl;
    }
    else
    {
        cout << "This empty line was removed." << endl;
    }
    delete []line;
    line = nullptr;
    removeLineFromList(list, toRemove);
}

void actionLenght(ListOfCreatedLines *list)
{
    cout << "Enter the number of line, which you want to check lenght." << endl;
    Line *toCheck = getLineFromList(list);
    if (toCheck == nullptr)
    {
        cout << "Error. Nothing to check lenght." << endl;
        return;
    }
    char *line = getChar(toCheck);
    if (line != nullptr)
    {
        cout << "Lenght of line " << line << " : " << lenght(toCheck) - 1 << endl;
    }
    else
    {
        cout << "Line is empty." << endl;
    }
    line = nullptr;
}

void actionCloneLine(ListOfCreatedLines *list)
{
    cout << "Enter the number of line to clone." << endl;
    Line *toClone = getLineFromList(list);
    if (toClone == nullptr)
    {
        cout << "Error. Nothing to clone." << endl;
        return;
    }
    Line *newLine = clone(toClone, list);
    char *current = getChar(newLine);
    cout << "Line " << current << " was cloned." << endl;
}

void actionConcatenation(ListOfCreatedLines *list)
{
    cout << "Enter the numbers  of lines to concatenation." << endl;
    Line *firstLine = getLineFromList(list);
    Line *secondLine = getLineFromList(list);
    if (firstLine == nullptr || secondLine == nullptr)
    {
        cout << "Error. Nothing to concat." << endl;
        return;
    }
    concatenation(firstLine, secondLine);
}

void actionEqual(ListOfCreatedLines *list)
{
    cout << "Enter number of lines to check equality." << endl;
    Line *firstLine = getLineFromList(list);
    Line *secondLine = getLineFromList(list);
    if (firstLine == nullptr || secondLine == nullptr)
    {
        cout << "Error. Nothing to search equality." << endl;
        return;
    }
    char *first = getChar(firstLine);
    char *second = getChar(secondLine);
    if (first != nullptr && second != nullptr)
    {
        cout << "Lines " << first << " and " << second;
        cout << (areEqual(firstLine, secondLine) ? " are equal" : " are not equal") << endl;
    }
    else
    {
        if (areEqual(firstLine, secondLine))
            cout << "These empty lines are equal." << endl;
        else
            cout << "Line " << (first == nullptr ? second : first) << " and empty line are not equal" << endl;
    }
    first = nullptr;
    second = nullptr;
}

void actionEmpty(ListOfCreatedLines *list)
{
    cout << "Enter the number of line to check its empty." << endl;
    Line *toCheck = getLineFromList(list);
    char *current = getChar(toCheck);
    if (current != nullptr)
    {
        cout << "Line " << current << "is not empty" << endl;
    }
    else
    {
        cout << "Line is empty." << endl;
    }
    current = nullptr;
}

void actionSubLine(ListOfCreatedLines *list)
{
    cout << "Enter tne number of line to get subline." << endl;
    Line *line = getLineFromList(list);
    if (line == nullptr)
    {
        cout << "Error. No line to get subline." << endl;
        return;
    }
    cout << "Enter the number of begin subline and lenght of subline." << endl;
    int begin = 0;
    cin >> begin;
    int lenght = 0;
    cin >> lenght;
    Line *subLine = getSubLine(line, begin, lenght, list);
    if (subLine == nullptr)
    {
        cout << "Incorrect data to make subline." << endl;
        return;
    }
    char *currentLine = getChar(line);
    char *currentSubLine = getChar(subLine);
    cout << "Subline " << currentSubLine << " was got from line " << currentLine << endl;
    currentLine = nullptr;
    currentSubLine = nullptr;
}

int main()
{
    ListOfCreatedLines *listLines = createList();

    cout << "In this program you can work with ADT line." << endl;
    cout << "You can choose an action, which you would like to do." << endl;

    printAction();
    int action = exitProgram;
    cin >> action;

    while (action != exitProgram)
    {
        switch (action)
        {
            case printLines:
                printAllLines(listLines);
            break;

            case addition:
                actionAddition(listLines);
            break;

            case removal:
                actionRemoval(listLines);
            break;

            case checkLenght:
                actionLenght(listLines);
            break;

            case cloneLine:
                actionCloneLine(listLines);
            break;

            case contactenationLines:
                actionConcatenation(listLines);
            break;

            case equality:
                actionEqual(listLines);
            break;

            case checkEmpty:
                actionEmpty(listLines);
            break;

            case makeSubString:
                actionSubLine(listLines);
            break;
        }
        cout << "Enter the action again." << endl;
        cin >> action;
    }
    cout << "You have finished working with lines." << endl;
    clearAndDeleteList(listLines);
    return 0;
}
