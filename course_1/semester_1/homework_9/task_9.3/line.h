#pragma once

struct Line;
struct List;

Line *createLine();
int lenght(Line *line);
void clearAndDeleteLine(Line *line);
int hashFunction(Line *line);
Line *getSubLine(Line *line, int begin, int size);
void hashes(Line *line, int size, int searchHash, List *list);

List *createList();
int sizeList(List *list);
void addElement(List *list, int value);
void removeElement(List *list, int value);
int getValue(List *list, int index);
void printList(List *list);
void check(Line *haystack, Line *needle, List *list);
void clearAndDeleteList(List *list);
