#include <iostream>
#include "polinomial.h"

using namespace std;

struct PartOfExpression
{
    char power;
    char element;
    PartOfExpression *next;
};

struct Expression
{
    int size;
    PartOfExpression *begin;
};

Expression *createExpression()
{
    Expression *polinomial = new Expression;
    polinomial->begin = nullptr;
    polinomial->size = 0;
    return polinomial;
}

int sizeOfExpression(Expression *polinomial)
{
    return polinomial->size;
}

PartOfExpression *createElement(char element, PartOfExpression *next, char power)
{
    PartOfExpression *part = new PartOfExpression;
    part->element = element;
    part->power = power;
    part->next = next;
    return part;
}

void deleteElement(PartOfExpression *toDelete)
{
    toDelete->next = nullptr;
    delete toDelete;
    toDelete = nullptr;
}

void removeNextElement(PartOfExpression *prev)
{
    if (prev->next == nullptr)
        return;

    PartOfExpression *toDelete = prev->next;
    prev->next = prev->next->next;
    deleteElement(toDelete);
}

void addCoefficients(Expression *polinomial, char coefficients[])
{
    polinomial->begin = createElement(coefficients[0], nullptr, ' ');
    int i = 1;
    PartOfExpression *temp = polinomial->begin;
    while (coefficients[i] != '\0')
    {
        temp->next = createElement(coefficients[i], nullptr, ' ');
        polinomial->size++;
        i++;
        temp = temp->next;
    }
}

bool nearEnd(PartOfExpression *current, int distance)
{
    int i = 0;
    PartOfExpression *temp = current;
    while (i < distance && temp != nullptr)
    {
        i++;
        temp = temp->next;
    }
    return temp == nullptr;
}

bool isSigh(char current)
{
    return current == '+' || current == '-';
}

bool isPositiveNumber(char current)
{
    return current >= '1' && current <= '9';
}

bool normalCoefficient(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    bool onlyNumbers = true;
    while (!nearEnd(temp, 0))
    {
        char value = temp->element;
        if (!isPositiveNumber(value) && value != '0' && value != ' ' && value != '-')
            onlyNumbers = false;
        temp = temp->next;
    }
    return onlyNumbers;
}

bool onlyZeroCoefficient(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    bool onlyZero = true;
    while (!nearEnd(temp, 0))
    {
        char value = temp->element;
        if (isPositiveNumber(value))
            onlyZero = false;
        temp = temp->next;
    }
    return onlyZero;
}

bool onlyOneCoefficient(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    bool noSpace = true;
    while (!nearEnd(temp, 0))
    {
        if (temp->element == ' ')
            noSpace = false;
        temp = temp->next;
    }
    return noSpace;
}

void deleteMinusBeforeZero(Expression *polinomial)
{
    if (polinomial->begin->element == '-' && polinomial->begin->next->element == '0')
    {
        PartOfExpression *toDelete = polinomial->begin;
        polinomial->begin = polinomial->begin->next;
        deleteElement(toDelete);
    }

    PartOfExpression *temp = polinomial->begin;
    while (!nearEnd(temp, 2))
    {
        if (temp->next->element == '-' && temp->next->next->element == '0')
        {
            removeNextElement(temp);
            polinomial->size--;
        }
        temp = temp->next;
    }
}

void addX(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    while (!nearEnd(temp, 1))
    {
        if (temp->next->element == ' ')
        {
            temp->next = createElement('x', temp->next, ' ');
            polinomial->size++;
            temp = temp->next;
        }
        temp = temp->next;
    }
}

void addSigh(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;

    while (!nearEnd(temp, 1))
    {
        if (temp->element == ' ')
        {
            if (temp->next->element != '-')
            {
                temp->next = createElement('+', temp->next, ' ');
                polinomial->size++;
                temp = temp->next;
            }
        }
        temp = temp->next;
    }

    temp = polinomial->begin;
    while (!nearEnd(temp, 1))
    {
        char current = temp->element;
        if (isSigh(current))
        {
            temp->next = createElement(' ', temp->next, ' ');
            polinomial->size++;
            temp = temp->next;
        }
        temp = temp->next;
    }
}

char lastNumeralOfPower(char number)
{
    int power = number % 10;
    return power + '0';
}

void addPowers(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    int maxPower = 0;
    while (!nearEnd(temp, 1))
    {
        if (temp->element == 'x')
            maxPower++;
        temp = temp->next;
    }

    temp = polinomial->begin;
    while (!nearEnd(temp, 1) && maxPower > 1)
    {
        if (temp->element == 'x')
        {
            int currentPower = maxPower;
            while (currentPower != 0)
            {
                temp->next = createElement(' ', temp->next, lastNumeralOfPower(currentPower));
                polinomial->size++;
                currentPower = currentPower / 10;
            }
            temp = temp->next;
            maxPower--;
        }
        temp = temp->next;
    }
}

void treatmentOfFirstZero(Expression *polinomial)
{
    if (sizeOfExpression(polinomial) == 1)
        return;

    if (polinomial->begin->element == '0' && polinomial->begin->next->element == 'x')
    {
        PartOfExpression *zeroPower = polinomial->begin;
        PartOfExpression *newBegin = polinomial->begin;
        char current = newBegin->next->element;
        while (!isSigh(current) && !nearEnd(newBegin, 2))
        {
            newBegin = newBegin->next;
            current = newBegin->next->element;
        }
        polinomial->begin = newBegin->next;
        newBegin->next = nullptr;

        while (!nearEnd(zeroPower, 0))
        {
            PartOfExpression *toDelete = zeroPower;
            zeroPower = zeroPower->next;
            deleteElement(toDelete);
            polinomial->size--;
        }

        if (polinomial->begin->element == '+')
        {
            PartOfExpression *toDelete = polinomial->begin;
            polinomial->begin = polinomial->begin->next->next;
            toDelete->next = nullptr;
            removeNextElement(toDelete);
            polinomial->size--;
            deleteElement(toDelete);
            polinomial->size--;
        }
    }
}

bool zeroSummand(PartOfExpression *begin)
{
    PartOfExpression *temp = begin;
    if (nearEnd(begin, 3))
        return false;

    char sigh = temp->next->element;
    char number = temp->next->next->next->element;
    return isSigh(sigh) && number == '0';
}

bool notZeroSummand(PartOfExpression *begin)
{
    PartOfExpression *temp = begin;
    if (nearEnd(temp, 3))
        return false;

    char sigh = temp->next->element;
    char number = temp->next->next->next->element;
    return isSigh(sigh) && isPositiveNumber(number);
}

void treatmentOfZero(Expression *polinomial)
{
    while (polinomial->begin->element == '0')
    {
        treatmentOfFirstZero(polinomial);
    }

    if (sizeOfExpression(polinomial) == 1)
        return;

    PartOfExpression *temp = polinomial->begin;
    while (!nearEnd(temp, 0))
    {
        if (zeroSummand(temp))
        {
            while (!nearEnd(temp, 1) && !notZeroSummand(temp->next))
            {
                removeNextElement(temp);
                polinomial->size--;
            }
        }
        temp = temp->next;
    }
}

void treatmentOfOne(Expression *polinomial)
{
    if (sizeOfExpression(polinomial) <= 1)
        return;

    PartOfExpression *temp = polinomial->begin;
    while (!nearEnd(temp, 3))
    {
        if (temp->element == ' ' && temp->next->element == '1' && temp->next->next->element == 'x')
        {
            removeNextElement(temp);
            polinomial->size--;
        }
        temp = temp->next;
    }
}

void treatmentOfFirstElement(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;

    if (sizeOfExpression(polinomial) <= 1)
        return;

    if (temp->element == '-')
    {
        removeNextElement(temp);
        polinomial->size--;
    }

    if (temp->element == '1' && temp->next->element == 'x')
    {
        PartOfExpression *toDelete = temp;
        polinomial->begin = polinomial->begin->next;
        temp = polinomial->begin;
        deleteElement(toDelete);
        polinomial->size--;
    }
}

bool isSpace(PartOfExpression *current)
{
    if (current == nullptr)
        return false;

    return current->power == ' ' && current->element == ' ';
}

void spaceTreatment(Expression *polinomial)
{
    if (onlyOneCoefficient(polinomial))
        return;

    PartOfExpression *temp = polinomial->begin;
    while (!nearEnd(temp, 1))
    {
        char currentPower = temp->power;
        while (isSpace(temp->next) && currentPower != ' ')
        {
            removeNextElement(temp);
            polinomial->size--;
        }
        temp = temp->next;
    }
}

bool notEmptyPowers(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;
    bool numeralPower = false;
    while (!nearEnd(temp, 0))
    {
        int current = temp->power;
        if (isPositiveNumber(current))
            numeralPower = true;
        temp = temp->next;
    }
    return numeralPower;
}

void printExpression(Expression *polinomial)
{
    PartOfExpression *temp = polinomial->begin;

    if (temp == nullptr)
    {
        cout << "Error. There is no polinimial." <<endl;
    }

    if (notEmptyPowers(polinomial))
    {
        while (!nearEnd(temp, 0))
        {
            char toPrint = temp->power;
            cout << toPrint;
            temp = temp->next;
        }
        cout << endl;
    }

    temp = polinomial->begin;
    while (!nearEnd(temp, 0))
    {
        char toPrint = temp->element;
        cout << toPrint;
        temp = temp->next;
    }
    cout << endl;
    cout << "This is polinomial with entered coefficients." << endl;;
}

void makeExpression(Expression *polinomial, char coefficients[])
{
    addCoefficients(polinomial, coefficients);
    if (!normalCoefficient(polinomial))
    {
        cout << "Error.\n There is no ablility to make polinomial from entered data." << endl;
        return;
    }
    if (onlyZeroCoefficient(polinomial))
    {
        cout << "Value of polinomial is 0, because all coefficients are 0." << endl;
        return;
    }
    if (onlyOneCoefficient(polinomial))
    {
        printExpression(polinomial);
        return;
    }
    deleteMinusBeforeZero(polinomial);
    addX(polinomial);
    addSigh(polinomial);
    addPowers(polinomial);
    treatmentOfZero(polinomial);
    treatmentOfOne(polinomial);
    treatmentOfFirstElement(polinomial);
    spaceTreatment(polinomial);
    printExpression(polinomial);
}

void deleteAndClear(Expression *polinomial)
{
    while (sizeOfExpression(polinomial) > 0)
    {
        PartOfExpression *toDelete = polinomial->begin;
        polinomial->begin = polinomial->begin->next;
        deleteElement(toDelete);
        polinomial->size--;
    }
    deleteElement(polinomial->begin);
    polinomial->begin = nullptr;
    delete polinomial;
    polinomial = nullptr;
}
