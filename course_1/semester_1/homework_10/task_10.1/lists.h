#pragma once

using namespace std;

struct List;

List *createList();
void clearAndDeleteList(List *list);
int sizeList(List *list);
void addElement(List *list, char symbol, int placeToAdd);
void printListToFile(List *list, ofstream &file);
char getSymbolFromTree(List *list, int number);


