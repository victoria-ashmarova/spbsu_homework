#include <stdio.h>
#include <limits.h>
#include "circularlist.h"


struct CirlucarListElement
{
    int value;
    CirlucarListElement *next;
};

struct CircularList
{
    int size;
    CirlucarListElement *head;
};

CircularList *createList()
{
    CircularList *list = new CircularList;
    list->head = nullptr;
    list->size = 0;
    return list;
}

int sizeOfList(CircularList *list)
{
    return list->size;
}

bool isEmpty(CircularList *list)
{
    return sizeOfList(list) == 0;
}

CirlucarListElement *createElement(int value, CirlucarListElement *next)
{
    CirlucarListElement *newElement = new CirlucarListElement;
    newElement->value = value;
    newElement->next = next;
    return newElement;
}

CirlucarListElement *getElement(CircularList *list, int number)
{
    if (isEmpty(list) || number <= 0)
        return nullptr;

    CirlucarListElement *temp = list->head;
    int i = 1;
    while (i < number)
    {
        temp = temp->next;
        i++;
    }
    return temp;
}

void addElement(CircularList *list, int value)
{
     if (isEmpty(list))
     {
         list->head = createElement(value, nullptr);
         list->head->next = list->head;
         list->size++;
         return;
     }

     CirlucarListElement *prev = getElement(list, sizeOfList(list));
     prev->next = createElement(value, prev->next);
     list->size++;

}

void removeElement(CircularList *list, int numberOfElement)
{
    if (numberOfElement <= 0 || isEmpty(list))
        return;



    if (getElement(list, numberOfElement) == list->head)
    {
        CirlucarListElement *temp = getElement(list, sizeOfList(list));
        CirlucarListElement *toDelete = temp->next;
        list->head = list->head->next;
        temp->next = temp->next->next;
        toDelete->next = nullptr;
        delete toDelete;
        list->size--;
        return;
    }

    CirlucarListElement *prev = getElement(list, numberOfElement - 1);
    CirlucarListElement *toDelete = prev->next;
    prev->next = prev->next->next;
    toDelete->next = nullptr;
    delete toDelete;
    list->size--;
}

void clearCircularList(CircularList *list)
{
    while (!isEmpty(list))
    {
        CirlucarListElement *toDelete = list->head;
        list->head = list->head->next;
        list->size--;
        toDelete->next = toDelete;
        delete toDelete;
    }
    list->head = nullptr;
}

void deleteCircularList(CircularList *list)
{
    clearCircularList(list);
    delete list;
}

void printCircularList(CircularList *list)
{
    if (isEmpty(list))
        return;

    CirlucarListElement *temp = list->head;
    int i = 1;
    while (i <= sizeOfList(list))
    {
        int toPrint = temp->value;
        printf("%d) %d\n", i, toPrint);
        temp = temp->next;
        i++;
    }
}

void changeHead(CircularList *list, int numberOfNewHead)
{
    if (numberOfNewHead < 0)
        return;

    if (numberOfNewHead == 0)
    {
        list->head = getElement(list, sizeOfList(list));
        return;
    }

    list->head = getElement(list, numberOfNewHead);
}

int getValue(CircularList *list, int numberOfElement)
{
    CirlucarListElement *temp = getElement(list, numberOfElement);
    if (temp == nullptr)
        return INT_MIN;

    return temp->value;
}

bool isHead(CircularList *list, int numberOfElement)
{
    return numberOfElement % sizeOfList(list) == 1;
}
/*
bool thisElementIsHead(CircularList *list, int numberOfElement)
{
    if (numberOfElement % sizeOfList(list) == 1)
        return true;
    CirlucarListElement *temp = getElement(list, numberOfElement);
    return temp == list->head;
}
*/
