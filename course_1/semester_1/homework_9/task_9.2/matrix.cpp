#include <iostream>
#include "list.h"
#include "matrix.h"

using namespace std;

struct Point
{
    int x;
    int y;
};

bool isNotFree(char **table, Point size, Point point)
{
    return (point.x < 0 || point.x > size.x - 1) || (point.y < 0 || point.y > size.y - 1) || table[point.x][point.y] == '1';
}

bool pointsAreEqual(Point first, Point second)
{
    return first.x == second.x && first.y == second.y;
}

int evristic(Point point, Point finish)
{
    int deltaX = (point.x > finish.x ? point.x - finish.x : finish.x - point.x);
    int deltaY = (point.y > finish.y ? point.y - finish.y : finish.y - point.y);
    return deltaX + deltaY;
}

void checkVertex(char **table, Point size, int x, int y, Point finish, List *close, List *open, Vertex *parent)
{
    Point point = {x, y};
    if (vertexIsInList(close, x, y) || isNotFree(table, size, point))
        return;

    int distance = getDistance(parent) + 1;
    int evr = evristic(point, finish);

    if (!vertexIsInList(open, x, y))
    {
        addVertex(open, x, y, distance, evr, parent);
        return;
    }
}

void checkNeigbours(char **table, Point size, Point finish, Vertex *currentVertex, List *close, List *open)
{
    int parentX = getX(currentVertex);
    int parentY = getY(currentVertex);
    checkVertex(table, size, parentX - 1, parentY , finish, close, open, currentVertex);
    checkVertex(table, size, parentX + 1, parentY , finish, close, open, currentVertex);
    checkVertex(table, size, parentX, parentY - 1, finish, close, open, currentVertex);
    checkVertex(table, size, parentX, parentY + 1, finish, close, open, currentVertex);
}

void printVertex(Point point, char **table, List *open, List *close, List *ways)
{
    if (vertexIsWay(ways, point.x, point.y))
        cout << '*';
    else
    {
        if (vertexIsInList(open, point.x, point.y))
            cout << 'O';
        if (vertexIsInList(close, point.x, point.y))
            cout << 'C';
        if(!vertexIsInList(open, point.x, point.y) && !vertexIsInList(close, point.x, point.y))
            cout << table[point.x][point.y];
    }
}

void printWay(char **table, Point size, List *open, List *close, Point start, Point finish)
{
    List *ways = createList();
    makeWay(ways, getVertex(close, finish.x, finish.y));

    cout << "S - start point." << endl;
    cout << "F - finish point" << endl;
    cout << "* - points of way." << endl;
    cout << "O - points of open list." << endl;
    cout << "C - points of close list." << endl;

    for (int i = 0; i < size.x; i++)
    {
        for (int j = 0; j < size.y; j++)
        {
            Point point = {i, j};
            if (pointsAreEqual(point, start))
                cout << 'S';
            if (pointsAreEqual(point, finish))
                cout << 'F';
            if (!pointsAreEqual(point, start) && !pointsAreEqual(point, finish))
                printVertex(point, table, open, close, ways);
        }
        cout << endl;
    }
    clearWay(ways);
}

void searchWay(char **table, int sizeX, int sizeY, int startX, int startY, int finishX, int finishY)
{
    List *open = createList();
    List *close = createList();

    Point size = {sizeX, sizeY};
    Point start = {startX, startY};
    Point finish = {finishX, finishY};

    int evr = evristic(start, finish);
    addVertex(open, startX, startY, 0, evr, nullptr);
    Vertex *currentVertex = getFirst(open);
    Point current {-1, -1};
    if (currentVertex != nullptr)
    {
        current.x = getX(currentVertex);
        current.y = getY(currentVertex);
    }
    while (sizeList(open) > 0 && !pointsAreEqual(current, finish))
    {
        checkNeigbours(table, size, finish, currentVertex, close, open);
        addVertex(close, current.x, current.y, getDistance(currentVertex), getEvr(currentVertex), getParent(currentVertex));
        replaceParent(currentVertex, close, open);
        removeVertex(open, current.x, current.y);
        currentVertex = getFirst(open);
        if (currentVertex != nullptr)
        {
            current.x = getX(currentVertex);
            current.y = getY(currentVertex);
        }
    }
    if (currentVertex != nullptr)
    {
        addVertex(close, current.x, current.y, getDistance(currentVertex), getEvr(currentVertex), getParent(currentVertex));
        replaceParent(currentVertex, close, open);
        removeVertex(open, current.x, current.y);
    }
    printWay(table, size, open, close, start, finish);
    clearAndDeleteList(open);
    clearAndDeleteList(close);
}
