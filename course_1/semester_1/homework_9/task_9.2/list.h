#pragma once

struct Vertex;
struct List;

List *createList();
int sizeList(List *list);
void clearAndDeleteList(List *list);
void addVertex(List *list, int x, int y, int distance, int evr, Vertex *parent);
void removeVertex(List *list, int x, int y);
bool vertexIsInList(List *list, int x, int y);
void replaceParent(Vertex *vertex, List *close, List *open);
Vertex *getVertex(List *list, int x, int y);
int getDistance(Vertex *vertex);
int getX(Vertex *vertex);
int getY(Vertex *vertex);
bool vertexIsWay(List *ways, int x, int y);
Vertex *getFirst(List *list);
int getEvr(Vertex *vertex);
Vertex *getParent(Vertex *vertex);
void makeWay(List *ways, Vertex *begin);
void clearWay(List *ways);
