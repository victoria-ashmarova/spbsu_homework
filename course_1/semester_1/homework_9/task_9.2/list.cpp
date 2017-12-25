#include <iostream>
#include "list.h"
#include "matrix.h"

using namespace std;

struct Vertex
{
    int x;
    int y;
    int distance;
    int evr;
    Vertex *parent;
    Vertex  *next;
};

struct List
{
    int size;
    Vertex *first;
};

List *createList()
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

void deleteVertex(Vertex *toDelete)
{
    toDelete->next = nullptr;
    toDelete->parent = nullptr;
    delete toDelete;
    toDelete = nullptr;
}

void clearAndDeleteList(List *list)
{
    while (sizeList(list) > 0)
    {
        Vertex *toDelete = list->first;
        list->first = list->first->next;
        deleteVertex(toDelete);
        list->size--;
    }
    delete list;
    list = nullptr;
}

Vertex *createVertex(int x, int y, int distance, int evr, Vertex *next, Vertex *parent)
{
    Vertex *vertex = new Vertex;
    vertex->next = next;
    vertex->x = x;
    vertex->y = y;
    vertex->distance = distance;
    vertex->evr = evr;
    vertex->parent = parent;
    return vertex;
}

Vertex *getVertex(List *list, int x, int y)
{
    if (sizeList(list) == 0)
        return nullptr;

    Vertex *temp = list->first;
    while (temp != nullptr && (x != temp->x || y != temp->y))
    {
        temp = temp->next;
    }
    return temp;
}

void addVertex(List *list, int x, int y, int distance, int evr, Vertex *parent)
{
    if (sizeList(list) == 0)
    {
        list->first = createVertex(x, y, distance, evr, nullptr, parent);
        list->size++;
        return;
    }

    if (vertexIsInList(list, x, y))
    {
        return;
    }

    if (list->first->evr >= evr)
    {
        list->first = createVertex(x, y, distance, evr, list->first, parent);
        list->size++;
        return;
    }

    if (sizeList(list) == 1 && list->first->evr < evr)
    {
        list->first->next = createVertex(x, y, distance, evr, nullptr, parent);
        list->size++;
        return;
    }

    Vertex *temp = list->first;
    while (evr < temp->next->evr && temp->next->next != nullptr)
        temp = temp->next;

    if (temp->next->evr < evr)
        temp->next->next = createVertex(x, y, distance, evr, temp->next->next, parent);
    else
        temp->next = createVertex(x, y, distance, evr, temp->next, parent);

    list->size++;
}

void replaceParent(Vertex *vertex, List *close, List *open)
{
   Vertex *newParent = getVertex(close, vertex->x, vertex->y);
   if (newParent == nullptr)
       return;

   Vertex *temp = open->first;
   while (temp != nullptr)
   {
       if (temp->parent == vertex)
       {
           temp->parent = newParent;
       }
       temp = temp->next;
   }
}

void removeVertex(List *list, int x, int y)
{
    if (!vertexIsInList(list, x, y) || sizeList(list) == 0)
        return;

    if (list->first == getVertex(list, x, y))
    {
        Vertex *toDelete = list->first;
        list->first = list->first->next;
        list->size--;
        deleteVertex(toDelete);
        return;
    }

    Vertex *toDelete = getVertex(list, x, y);
    Vertex *prev = list->first;
    while (prev->next != toDelete && prev->next != nullptr)
        prev = prev->next;

    if (prev == nullptr)
        return;

    prev->next = prev->next->next;
    list->size--;
    deleteVertex(toDelete);
}

bool vertexIsInList(List *list, int x, int y)
{
    return getVertex(list, x, y) != nullptr;
}

int getDistance(Vertex *vertex)
{
    return vertex->distance;
}

int getX(Vertex *vertex)
{
    return vertex->x;
}

int getY(Vertex *vertex)
{
    return vertex->y;
}

bool vertexIsWay(List *ways, int x, int y)
{
    Vertex *temp = ways->first;
    if (temp == nullptr)
        return false;

    while (temp != nullptr && (temp->x != x || temp->y != y))
    {
        temp = temp->parent;
    }

    return temp != nullptr;
}

Vertex *getFirst(List *list)
{
    return list->first;
}

int getEvr(Vertex *vertex)
{
    return vertex->evr;
}

Vertex *getParent(Vertex *vertex)
{
    return vertex->parent;
}

void makeWay(List *ways, Vertex *begin)
{
    ways->size = 1;
    ways->first = begin;
}

void clearWay(List *ways)
{
    ways->first = nullptr;
    delete ways;
    ways = nullptr;
}
