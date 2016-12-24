#pragma once

struct Vertex;
struct List;

List *createList();
int sizeList(List *list);
void clearAndDeleteList(List *list);
void addVertex(List *list, int x, int y, int distance, int evr, Vertex *parent);
void removeVertex(List *list, int x, int y);
bool vertexIsInList(List *list, int x, int y);

int evristic(int x, int y, int finishX, int finishY);

void searchWay(char **table, int sizeX, int sizeY, int startX, int startY, int finishX, int finishY);

