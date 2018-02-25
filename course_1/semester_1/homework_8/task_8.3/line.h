#pragma once

using namespace std;
/*
struct Line;
struct List;

List *createList();
void clearAndDeleteList(List *list);

void addLineToList(List *list, Line *line);
void removeLineFromList(List *list, Line *line);
void printAllLines(List *list);
Line *getLineFromNumber(List *list, int number);


Line *createLine();
void clearAndDeleteLine(Line *line);
Line *clone(Line *line, List *list);
void concatenation(Line *first, Line *second);
bool areEqual(Line *first, Line *second);
int  lenght(Line *line);
bool isEmpty(Line *line);
Line *getSubLine(Line *line, int begin, int size);
void printLine(Line *line);

Line *fromCharToLine(char *element, int size);*/

struct Line;
struct List;

List *createList();
int sizeList(List *list);
void clearAndDeleteList(List *list);
void clearAndDeleteList(List *list);
void addLineToList(List *list, Line *line);
void removeLineFromList(List *list, Line *line);
void printAllLines(List *list);

bool isStopSymbol(char symbol);
Line *createLine();
int lenght(Line *line);
void addLineFromConsole(List *list);
Line *getLineFromNumber(List *list, int number);
Line *clone(Line *line);
bool areEqual(Line *first, Line *second);
bool isEmpty(Line *line);
char *getChar(Line *line);
void clearAndDeleteLine(Line *line);
int hashFunction(Line *line, int mod);
Line *fromChar(char *current);
