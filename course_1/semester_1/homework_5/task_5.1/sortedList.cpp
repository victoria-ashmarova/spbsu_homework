#include <stdio.h>
#include "sortedList.h"

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    int size;
    ListElement *head;
};

List *createList()
{
    List *list = new List;
    list->size = 0;
    list->head = nullptr;
    return list;
}

ListElement *getPrev(List *list, int value)
{
    ListElement *temp = list->head;
    while (temp->next->next != nullptr && temp->next->value < value)
    {
        temp = temp->next;
    }

    if (temp->next->value >= value)
    {
        return temp;
    }
    else
    {
        return temp->next;
    }
}

ListElement *createElement(int value, ListElement *next)
{
    ListElement *newElement = new ListElement;
    newElement->value = value;
    newElement->next = next;
    return newElement;
}

bool isInlist(List *list, int value)
{
    if (isEmpty(list))
        return false;

    ListElement *temp = list->head;
    while (temp->next != nullptr  && temp->value != value)
    {
         temp = temp->next;
    }
    return temp->value == value;
}

void addElement(List *list, int value)
{
    if (isInlist(list, value))
    {
        printf("The value %d is in list yet.\n", value);
        return;
    }

    if (isEmpty(list) || value < list->head->value)
    {
        list->head = createElement(value, list->head);
        if (isEmpty(list))
            printf("Now list is not empty.\n");
        //This statement seems strange, but it is ok because value have added already, but the size has not changed yet
        printf("Element with value %d is added.\n", value);
        list->size++;
        return;
    }

    //there is only one element in list so temp->next == nullptr and there is no ability to use getPrev()
    if (size(list) == 1 && value > list->head->value)
    {
        list->head->next = createElement(value, nullptr);
        list->size++;
        printf("Element with value %d is added.\n", value);
        return;
    }

    ListElement *prev = getPrev(list, value);
    ListElement *newElement = createElement(value, prev->next);
    prev->next = newElement;
    list->size++;
    printf("Element with value %d is added.\n", value);
}

void removeElement(List *list, int value)
{
    if (isEmpty(list))
    {
        return;
    }

    if (!isInlist(list, value))
    {
        printf("Error.\n It is impossible to delete value, which is not in the list.\n");
        return;
    }

    if (value == list->head->value)
    {
        ListElement *toDelete = list->head;
        list->head = list->head->next;
        toDelete->next = nullptr;
        delete toDelete;
        list->size--;
        printf("Element with the value %d is removed.\n", value);
        if (isEmpty(list))
            printf("List is empry.\n");
        return;
    }

    ListElement *prev = getPrev(list, value);
    ListElement *toDelete = prev->next;
    prev->next = prev->next->next;
    toDelete->next = nullptr;
    delete toDelete;
    list->size--;
    printf("Element with the value %d is removed.\n", value);
    if (isEmpty(list))
        printf("List is empry.\n");
}

bool isEmpty(List *list)
{
    return size(list) == 0;
}

int size(List *list)
{
    return list->size;
}

void clearList(List *list)
{
    while (!isEmpty(list))
    {
        ListElement *toDelete = list->head;
        list->head = list->head->next;
        toDelete->next = nullptr;
        delete toDelete;
        list->size--;
    }
}

void deleteList(List *list)
{
    clearList(list);
    list->head = nullptr;
    delete list;
}

void printList(List *list)
{
    if (isEmpty(list))
    {
        printf("There is no list to print. The list is empty\n");
    }

    ListElement *temp = list->head;
    int number = 1;
    while (temp != nullptr)
    {
        int toPrint = temp->value;
        printf("%d) %d\n", number, toPrint);
        temp = temp->next;
        number++;
    }
}
