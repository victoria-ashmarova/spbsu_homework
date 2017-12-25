#include <iostream>
#include <fstream>
#include "lists.h"

using namespace std;

struct Element
{
    char symbol;
    Element *next;
};

struct List
{
    int size;
    Element *first;
};

List *createList()
{
    List *list = new List;
    list->size = 0;
    list->first = nullptr;
    return list;
}

void clearAndDeleteList(List *list)
{
    while (sizeList(list) > 0)
    {
        Element *toDelete = list->first;
        list->first = list->first->next;
        list->size--;
        toDelete->next = nullptr;
        delete toDelete;
        toDelete = nullptr;
    }
    delete list;
    list = nullptr;
}

int sizeList(List *list)
{
    return list->size;
}

Element *createElement(Element *next, char symbol)
{
    Element *element = new Element;
    element->next = next;
    element->symbol = symbol;
    return element;
}

void addElement(List *list, char symbol, int placeToBegin)
{
    if (placeToBegin < 1 || placeToBegin > sizeList(list) + 1)
        return;

    if (placeToBegin == 1)
    {
        list->first = createElement(list->first, symbol);
        list->size++;
        return;
    }

    Element *temp = list->first;
    int i = 1;
    while (i < placeToBegin && temp->next != nullptr)
    {
        i++;
        temp = temp->next;
    }
    temp->next = createElement(temp->next, symbol);
    list->size++;
}

void printListToFile(List *list, ofstream &file)
{
    if (sizeList(list) < 1)
        return;

    Element *temp = list->first;
    while (temp != nullptr)
    {
        char symbol = temp->symbol;
        switch (symbol)
        {
            case '\n':
                file << "'\\n'";
            break;

            case ' ':
                file  << "'' ";
            break;

            case '\t':
                file << "'\\t'";
            break;

            case '(':
                file << "'(' ";
            break;

            case ')':
                file << "')' ";
            break;

            default:
                file << temp->symbol;
            break;
        }
        temp = temp->next;
    }
}

char getSymbolFromTree(List *list, int number)
{
    if (list == nullptr)
        return '\0';

    if (number < 1 || number > sizeList(list))
        return '\0';

    if (number == 1)
        return list->first->symbol;

    Element *temp = list->first;
    int i = 1;
    while (i < number && temp->next != nullptr)
    {
        i++;
        temp = temp->next;
    }
    return temp->symbol;
}
