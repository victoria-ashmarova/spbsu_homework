#include <iostream>
#include "line.h"

using namespace std;

struct Line
{
    char *line;
    int size;
    int frequency;
    Line *next;
};

struct List
{
    Line *first;
    int size;
};

struct Symbol
{
    char symbol = '\0';
    Symbol *next;
};

struct SymbolsChain
{
    int size;
    Symbol *first;
};

List *createList()
{
    List *list = new List;
    list->size = 0;
    list->first = nullptr;
    return list;
}

int sizeList(List *list)
{
    return list->size;
}

void clearAndDeleteList(List *list)
{
    while (sizeList(list) != 0)
    {
        Line *toDelete = list->first;
        list->first = list->first->next;
        clearAndDeleteLine(toDelete);
        toDelete = nullptr;
        list->size--;
    }
    list->first = nullptr;
    delete list;
    list = nullptr;
}

void addLineToList(List *list, Line *line)
{
    if (sizeList(list) == 0)
    {
        list->first = line;
        list->size++;
        return;
    }

    Line *tempPoint = list->first;
    while (tempPoint->next != nullptr && !areEqual(tempPoint, line))
        tempPoint = tempPoint->next;

    if (areEqual(tempPoint, line))
    {
        tempPoint->frequency++;
        clearAndDeleteLine(line);
        return;
    }

    tempPoint->next = line;
    list->size++;
}

void removeLineFromList(List *list, Line *line)
{
    if (sizeList(list) == 0 && line == nullptr)
        return;

    if (list->first == line)
    {
        Line *toDelete = list->first;
        list->first = list->first->next;
        clearAndDeleteLine(toDelete);
        toDelete = nullptr;
        list->size--;
        return;
    }

    Line *tempPoint = list->first;
    while (tempPoint->next != line && tempPoint->next != nullptr)
        tempPoint = tempPoint->next;

    if (tempPoint->next == nullptr)
        return;

    Line *toDelete = tempPoint->next;
    tempPoint->next = tempPoint->next->next;
    clearAndDeleteLine(toDelete);
    toDelete = nullptr;
    list->size--;
}

void printAllLines(List *list)
{
    if (sizeList(list) == 0)
    {
        return;
    }

    Line *tempPoint = list->first;
    while (tempPoint != nullptr)
    {
        char *toPrint = getChar(tempPoint);
        if (toPrint != nullptr)
            cout << toPrint << " " << tempPoint->frequency << endl;
        else
            cout << endl;
        tempPoint = tempPoint->next;
        toPrint = nullptr;
    }
}

Line *getLineFromNumber(List *list, int number)
{
    if (number < 1 || number > sizeList(list) || sizeList(list) < 1)
        return nullptr;

    int i = 1;
    Line *temp = list->first;
    while (i < number && temp != nullptr)
    {
        i++;
        temp = temp->next;
    }
    return temp;
}

bool isStopSymbol(char symbol)
{
    return !((symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z') || (symbol >= '0' && symbol <= '9') || symbol == '-' || symbol == '\'');
}

Line *createLine()
{
    Line *newLine = new Line;
    newLine->size = 0;
    newLine->line = nullptr;
    newLine->next = nullptr;
    newLine->frequency = 1;
    return newLine;
}

void clearAndDeleteLine(Line *line)
{
    delete []line->line;
    line->line = nullptr;
    line->next = nullptr;
    delete line;
    line = nullptr;
}

int lenght(Line *line)
{
    if (line == nullptr)
        return 0;

    return line->size;
}

Symbol *createSymbol(char symbol)
{
    Symbol *newSymbol = new Symbol;
    newSymbol->next = nullptr;
    newSymbol->symbol = symbol;
    return newSymbol;
}

void deleteSymbol(Symbol *toDelete)
{
    toDelete->next = nullptr;
    delete toDelete;
    toDelete = nullptr;
}

SymbolsChain *createChain()
{
    SymbolsChain *chain = new SymbolsChain;
    chain->first = nullptr;
    chain->size = 0;
    return chain;
}

int sizeChain(SymbolsChain *chain)
{
    return chain->size;
}

void clearAndDeleteChain(SymbolsChain *chain)
{
    while (sizeChain(chain) > 0)
    {
        Symbol *toDelete = chain->first;
        chain->first = chain->first->next;
        chain->size--;
        deleteSymbol(toDelete);
    }
    delete chain;
    chain = nullptr;
}

Line *makeLine(SymbolsChain *chain)
{
    if (sizeChain(chain) < 1)
    {
        Line *line = createLine();
        return line;
    }

    int lenght = chain->size + 1;
    char *current = new char[lenght];
    int i = 0;
    Symbol *temp = chain->first;
    while (i < lenght && temp != nullptr)
    {
        current[i] = temp->symbol;
        i++;
        temp = temp->next;
    }
    current[lenght - 1] = {'\0'};

    Line *line = createLine();
    line->line = current;
    line->size = lenght;
    return line;
}

SymbolsChain *makeChain(Line *line)
{
    if (lenght(line) < 1)
    {
        return createChain();
    }

    SymbolsChain *chain = createChain();
    char *current = line->line;
    chain->first = createSymbol(current[0]);
    chain->size = 1;
    Symbol *end = chain->first;
    int i = 1;
    while (i < lenght(line) && !isStopSymbol(current[i]))
    {
        end->next = createSymbol(current[i]);
        end = end->next;
        chain->size++;
        i++;
    }
    return chain;
}

Symbol *getSymbol(SymbolsChain *chain, int number)
{
    if (sizeChain(chain) == 0)
        return nullptr;

    Symbol *temp = chain->first;
    int i = 1;
    while (temp->next != nullptr && i < number)
    {
        temp = temp->next;
        i++;
    }
    return temp;
}

bool onlyOneLineIsEmpty(Line *first, Line *second)
{
    return (lenght(first) == 0 && lenght(second) != 0) || (lenght(first) != 0 && lenght(second) == 0);
}

bool areEqual(Line *first, Line *second)
{
    if (lenght(first) != lenght(second) || onlyOneLineIsEmpty(first, second))
        return false;

    if (first->line == nullptr && second->line == nullptr)
        return true;

    int lenghtLine = lenght(first);
    int i = 0;
    while (i < lenghtLine && first->line[i] == second->line[i])
    {
        i++;
    }
    return first->line[i] == second->line[i];
}

int size(SymbolsChain *chain)
{
    if (chain == nullptr)
        return 0;

    return chain->size;
}

bool isEmpty(SymbolsChain *chain)
{
    return size(chain) == 0;
}

bool isEmpty(Line *line)
{
    return lenght(line) == 0;
}

Line *clone(Line *line)
{
    SymbolsChain *chain = makeChain(line);
    Line *clonedLine = makeLine(chain);
    clonedLine->frequency = line->frequency;
    clearAndDeleteChain(chain);
    return clonedLine;
}

char *getChar(Line *line)
{
    if (line == nullptr)
        return nullptr;

    return line->line;
}

int hashFunction(Line *line, int mod)
{
    if (lenght(line) == 0)
        return -1;

    SymbolsChain *chain = makeChain(line);
    int hashToReturn = 0;
    Symbol *temp = chain->first;
    while (temp != nullptr)
    {
        char current = temp->symbol;
        hashToReturn = (hashToReturn * 47 + current) % mod;
        temp = temp->next;
    }
    clearAndDeleteChain(chain);
    return (hashToReturn >= 0 ? hashToReturn : hashToReturn + mod);
}

Line *fromChar(char *current)
{
    SymbolsChain *chain = createChain();
    chain->first = createSymbol(current[0]);
    chain->size++;
    Symbol *end = chain->first;
    int i = 1;
    while (!isStopSymbol(current[i]))
    {
        end->next = createSymbol(current[i]);
        end = end->next;
        chain->size++;
        i++;
    }
    Line *line = makeLine(chain);
    clearAndDeleteChain(chain);
    return line;
}
