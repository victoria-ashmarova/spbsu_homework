#include <iostream>
#include "list.h"

using namespace std;

struct Point
{
    int x;
    int y;
};

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

bool isNotFree(char **table, Point size, int x, int y)
{
    return (x < 0 || x > size.x - 1) || (y < 0 || y > size.y - 1) || table[x][y] == '1';
}

bool pointsAreEqual(int firstX, int firstY, int secondX, int secondY)
{
    return firstX == secondX && firstY == secondY;
}

int evristic(int x, int y, int finishX, int finishY)
{
    int deltaX = (x > finishX ? x - finishX : finishX - x);
    int deltaY = (y > finishY ? y - finishY : finishY - y);
    return deltaX + deltaY;
}

void checkVertex(char **table, Point size, int x, int y, Point finish, List *close, List *open, Vertex *parent)
{
    if (vertexIsInList(close, x, y) || isNotFree(table, size, x, y))
        return;

    int distance = parent->distance + 1;
    int evr = evristic(x, y, finish.x, finish.y);

    if (!vertexIsInList(open, x, y))
    {
        addVertex(open, x, y, distance, evr, parent);
        return;
    }
}

void checkNeigbours(char **table, Point size, Point finish, Vertex *currentVertex, List *close, List *open)
{
    int parentX = currentVertex->x;
    int parentY = currentVertex->y;
    checkVertex(table, size, parentX - 1, parentY , finish, close, open, currentVertex);
    checkVertex(table, size, parentX + 1, parentY , finish, close, open, currentVertex);
    checkVertex(table, size, parentX, parentY - 1, finish, close, open, currentVertex);
    checkVertex(table, size, parentX, parentY + 1, finish, close, open, currentVertex);
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

void printVertex(int x, int y, char **table, List *open, List *close, List *ways)
{
    if (vertexIsWay(ways, x, y))
        cout << '*';
    else
    {
        if (vertexIsInList(open, x, y))
            cout << 'O';
        if (vertexIsInList(close, x, y))
            cout << 'C';
        if(!vertexIsInList(open, x, y) && !vertexIsInList(close, x, y))
            cout << table[x][y];
    }
}

void printWay(char **table, Point size, List *open, List *close, Point start, Point finish)
{
    List *ways = new List;
    ways->size = 1;
    ways->first = getVertex(close, finish.x, finish.y);

    cout << "S - start point." << endl;
    cout << "F - finish point" << endl;
    cout << "* - points of way." << endl;
    cout << "O - points of open list." << endl;
    cout << "C - points of close list." << endl;

    for (int i = 0; i < size.x; i++)
    {
        for (int j = 0; j < size.y; j++)
        {
            if (pointsAreEqual(i, j, start.x, start.y))
                cout << 'S';
            if (pointsAreEqual(i, j, finish.x, finish.y))
                cout << 'F';
            if (!pointsAreEqual(i, j, start.x, start.y) && !pointsAreEqual(i, j, finish.x, finish.y))
                printVertex(i, j, table, open, close, ways);
        }
        cout << endl;
    }
    ways->first = nullptr;
    delete ways;
    ways = nullptr;
}

void searchWay(char **table, int sizeX, int sizeY, int startX, int startY, int finishX, int finishY)
{
    List *open = createList();
    List *close = createList();

    Point size = {sizeX, sizeY};
    Point start = {startX, startY};
    Point finish = {finishX, finishY};

    int evr = evristic(startX, startY, finishX, finishY);
    addVertex(open, startX, startY, 0, evr, nullptr);
    Vertex *currentVertex = open->first;
    Point current {-1, -1};
    if (currentVertex != nullptr)
    {
        current.x = currentVertex->x;
        current.y = currentVertex->y;
    }
    while (sizeList(open) > 0 && !pointsAreEqual(current.x, current.y, finish.x, finish.y))
    {
        checkNeigbours(table, size, finish, currentVertex, close, open);
        addVertex(close, current.x, current.y, currentVertex->distance, currentVertex->evr, currentVertex->parent);
        replaceParent(currentVertex, close, open);
        removeVertex(open, current.x, current.y);
        currentVertex = open->first;
        if (currentVertex != nullptr)
        {
            current.x = currentVertex->x;
            current.y = currentVertex->y;
        }
    }
    if (currentVertex != nullptr)
    {
        addVertex(close, current.x, current.y, currentVertex->distance, currentVertex->evr, currentVertex->parent);
        replaceParent(currentVertex, close, open);
        removeVertex(open, current.x, current.y);
    }
    printWay(table, size, open, close, start, finish);
    clearAndDeleteList(open);
    clearAndDeleteList(close);
}
