#include <iostream>
#include <fstream>
#include "list.h"

using namespace std;

struct Element
{
    char litera;
    int number;
    Element *next;
};

struct List
{
    int size;
    Element *first;
};

List* createList()
{
    List *list = new List;
    list->size = 0;
    list->first = nullptr;
    return list;
}

int sizeList(List *list)
{
    return list->size;
}

bool isEmpty(List *list)
{
    return sizeList(list) == 0;
}

void clearAndDeleteList(List *list)
{
    while (list->first != nullptr)
    {
        Element *toDelete = list->first;
        list->first = list->first->next;
        toDelete->next = nullptr;
        delete toDelete;
        toDelete = nullptr;
        list->size--;
    }
    delete list;
    list = nullptr;
}

bool isInList(List *list, char litera)
{
    if (isEmpty(list))
        return false;

    Element *temp = list->first;
    char current = temp->litera;
    while (temp->next != nullptr && current != litera)
    {
        temp = temp->next;
        current = temp->litera;
    }
    return current == litera;
}

Element *createElement(char litera, Element *next)
{
    Element *element = new Element;
    element->litera = litera;
    element->number = 1;
    element->next = next;
    return element;
}

Element *getPrev(List *list, char litera)
{
    Element *temp = list->first;
    while (temp->next->next != nullptr && temp->next->litera < litera)
    {
        temp = temp->next;
    }

    if (temp->next->litera >= litera)
    {
        return temp;
    }
    else
    {
        return temp->next;
    }
}

void addElement(List *list, char litera)
{
    if (isInList(list, litera))
    {
        repeat(list, litera);
        return;
    }

    if (isEmpty(list) || litera < list->first->litera)
        {
            list->first = createElement(litera, list->first);
            list->size++;
            return;
        }

    if (sizeList(list) == 1 && litera > list->first->litera)
    {
        list->first->next = createElement(litera, nullptr);
        list->size++;
        return;
    }

    Element *prev = getPrev(list, litera);
    Element *newElement = createElement(litera, prev->next);
    prev->next = newElement;
    list->size++;
}

void repeat(List *list, char litera)
{
    if (!isInList(list, litera) || isEmpty(list))
        return;

    Element *toLitera = list->first;
    while (toLitera->next != nullptr && toLitera->litera != litera)
    {
        toLitera = toLitera->next;
    }
    toLitera->number++;
}

void printToFile(List *list, ofstream &file)
{
    if (isEmpty(list))
    {
        file << "No liters in input file." << endl;
        return;
    }

    Element *temp = list->first;
    while (temp != nullptr)
    {
        file << temp->litera << "--" << temp->number << endl;
        temp = temp->next;
    }
}

void minusLastLitera(List *list, char lastLitera)
{
    if (!isInList(list, lastLitera) || isEmpty(list))
        return;

    Element *toLitera = list->first;
    while (toLitera->next != nullptr && toLitera->litera != lastLitera)
    {
        toLitera = toLitera->next;
    }
    toLitera->number--;
}
