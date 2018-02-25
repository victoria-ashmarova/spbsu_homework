#pragma once

struct Line;
struct ListOfCreatedLines;

ListOfCreatedLines *createList();
void clearAndDeleteList(ListOfCreatedLines *list);
void clearAndDeleteList(ListOfCreatedLines *list);
void addLineToList(ListOfCreatedLines *list, Line *line);
void removeLineFromList(ListOfCreatedLines *list, Line *line);
void printAllLines(ListOfCreatedLines *list);

int lenght(Line *line);
void addLineFromConsole(ListOfCreatedLines *list);
Line *getLineFromNumber(ListOfCreatedLines *list, int number);
Line *clone(Line *line, ListOfCreatedLines *list);
Line *getSubLine(Line *line, int begin, int size, ListOfCreatedLines *list);
void concatenation(Line *firstLine, Line *secondLine);
bool areEqual(Line *first, Line *second);
bool isEmpty(Line *line);
char *getChar(Line *line);
void clearAndDeleteLine(Line *line);
