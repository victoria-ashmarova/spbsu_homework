#pragma once

struct List;

List *createList();
int sizeList(List *list);
void addElement(List *list, int value);
void removeElement(List *list, int value);
int getValue(List *list, int index);
void printList(List *list);
