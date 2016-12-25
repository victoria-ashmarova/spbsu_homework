#include <iostream>
#include "line.h"

using namespace std;

struct Symbol
{
    char symbol;
    Symbol *next;
};

struct SymbolChane
{
    Symbol *first;
    int size;
};

struct Line
{
    char *line;
    int lenght;
};

SymbolChane *createSymbolChane()
{
    SymbolChane *chane = new SymbolChane;
    chane->first = nullptr;
    chane->size = 0;
    return chane;
}

Symbol *createSymbol(char element)
{
    Symbol *symbol = new Symbol;
    symbol->next = nullptr;
    symbol->symbol = element;
    return symbol;
}

bool isStopSymbol(char symbol)
{
    return symbol == ' ' || symbol == '\n' || symbol == '\0';
}

void fullChane(SymbolChane *chane)
{
    char toAdd = '\0';
    scanf("%c", &toAdd);
    if (isStopSymbol(toAdd))
        scanf("%c", &toAdd);
    if (isStopSymbol(toAdd))
        return;

    chane->first = createSymbol(toAdd);
    chane->size++;
    Symbol *end = chane->first;
    scanf("%c", &toAdd);
    while (!isStopSymbol(toAdd))
    {
        end->next = createSymbol(toAdd);
        end = end->next;
        chane->size++;
        scanf("%c", &toAdd);
    }
}

void clearAndDeleteChane(SymbolChane *chane)
{
    while (sizeChane(chane) > 0)
    {
        chane->size--;
        Symbol *toDelete = chane->first;
        chane->first = chane->first->next;
        delete toDelete;
        toDelete = nullptr;
    }
    delete chane;
    chane = nullptr;
}

int sizeChane(SymbolChane *chane)
{
    return chane->size;
}

Line *makeLine(SymbolChane *chane)
{
    int lenght = chane->size + 1;
    char *charElement = new char[lenght];
    int i = 0;
    Symbol *current = chane->first;
    if (current == nullptr)
    {
        Line *line = new Line;
        line->lenght = 0;
        line->line = nullptr;
        clearAndDeleteChane(chane);
        return line;
    }
    charElement[i] = current->symbol;
    while (current->next != nullptr && i < lenght - 1)
    {
        i++;
        current = current->next;
        charElement[i] = current->symbol;
    }
    charElement[lenght - 1] = '\0';

    Line *line = new Line;
    line->lenght = lenght;
    line->line = charElement;
    clearAndDeleteChane(chane);
    return line;
}

int lenght(Line *line)
{
    return line->lenght - 1;
}

void clearAndDeleteLine(Line *line)
{
    delete []line->line;
    line->line = nullptr;
    delete line;
    line = nullptr;
}

char *getChar(Line *line)
{
    return line->line;
}
