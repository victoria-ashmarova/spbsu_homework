#pragma once

using namespace std;

struct List;
struct Element;

List* createList();
int sizeList(List *list);
bool isEmpty(List *list);
void clearAndDeleteList(List *list);
void addElement(List *list, char litera);
bool isInList(List *list, char litera);
void repeat(List *list, char litera);
void printToFile(List *list, ofstream &file);
void minusLastLitera(List *list, char lastLitera);
