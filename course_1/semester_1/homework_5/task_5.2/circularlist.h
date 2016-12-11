#pragma once

struct CircularList;

CircularList *createList();

int sizeOfList(CircularList *list);
bool isEmpty(CircularList *list);

void addElement(CircularList *list, int value);
void removeElement(CircularList *list, int numberOfElement);

void clearCircularList(CircularList *list);
void deleteCircularList(CircularList *list);

void printCircularList(CircularList *list);

void changeHead(CircularList *list, int numberOfNewHead);

int getValue(CircularList *list, int numberOfElement);

bool isHead(CircularList *list, int numberOfElement);

