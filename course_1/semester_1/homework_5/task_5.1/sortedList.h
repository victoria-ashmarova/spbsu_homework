#pragma once

struct List;

List *createList();

void addElement(List *list, int value);
void removeElement(List *list, int value);
bool isEmpty(List *list);
int size(List *list);
void clearList(List *list);
void deleteList(List *list);
void printList(List *list);
