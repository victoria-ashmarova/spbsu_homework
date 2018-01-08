#include <iostream>
#include "line.h"

using namespace std;

struct Line
{
    char *line;
    int size;
    Line *next;
};

struct ListOfCreatedLines
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

ListOfCreatedLines *createList()
{
    ListOfCreatedLines *list = new ListOfCreatedLines;
    list->size = 0;
    list->first = nullptr;
    return list;
}

int sizeList(ListOfCreatedLines *list)
{
    return list->size;
}

void clearAndDeleteList(ListOfCreatedLines *list)
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

void addLineToList(ListOfCreatedLines *list, Line *line)
{
    if (sizeList(list) == 0)
    {
        list->first = line;
        list->size++;
        return;
    }

    Line *tempPoint = list->first;
    while (tempPoint->next != nullptr)
        tempPoint = tempPoint->next;
    tempPoint->next = line;
    list->size++;
}

void removeLineFromList(ListOfCreatedLines *list, Line *line)
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

void printAllLines(ListOfCreatedLines *list)
{
    if (sizeList(list) == 0)
    {
        cout << "There is no created lines." << endl;
        return;
    }

    Line *tempPoint = list->first;
    while (tempPoint != nullptr)
    {
        char *toPrint = tempPoint->line;
        if (toPrint != nullptr)
            cout << toPrint << endl;
        else
            cout << endl;
        tempPoint = tempPoint->next;
        toPrint = nullptr;
    }
}

Line *getLineFromNumber(ListOfCreatedLines *list, int number)
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

Line *createLine()
{
    Line *line = new Line;
    line->size = 0;
    line->line = nullptr;
    line->next = nullptr;
    return line;
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

    Line *line = new Line;
    line->line = current;
    line->next = nullptr;
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
    while (i < lenght(line) - 1)
    {
        end->next = createSymbol(current[i]);
        end = end->next;
        chain->size++;
        i++;
    }
    return chain;
}

bool isStopSymbol(char symbol)
{
    return symbol == '\n' || symbol == '\0' || symbol == ' ';
}

void fullChain(SymbolsChain *chain)
{
    char toAdd = '\0';
    scanf("%c", &toAdd);
    if (isStopSymbol(toAdd))
        scanf("%c", &toAdd);
    if (isStopSymbol(toAdd))
        return;

    chain->first = createSymbol(toAdd);
    chain->size++;
    Symbol *end = chain->first;
    scanf("%c", &toAdd);
    while (!isStopSymbol(toAdd))
    {
        end->next = createSymbol(toAdd);
        end = end->next;
        chain->size++;
        scanf("%c", &toAdd);
    }
}

void addLineFromConsole(ListOfCreatedLines *list)
{
    SymbolsChain *chain = createChain();
    fullChain(chain);
    Line *line = makeLine(chain);
    clearAndDeleteChain(chain);
    addLineToList(list, line);
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

void concatenationChains(SymbolsChain *first, SymbolsChain *second)
{
    if (second == nullptr || sizeChain(second) == 0)
        return;

    Symbol *placeOfConcatenation = getSymbol(first, sizeChain(first));
    Symbol *toAdd = second->first;
    if (placeOfConcatenation == nullptr)
    {
        first->first = createSymbol(toAdd->symbol);
        first->size++;
        placeOfConcatenation = first->first;
        toAdd = toAdd->next;
    }
    while (toAdd != nullptr)
    {
        placeOfConcatenation->next = createSymbol(toAdd->symbol);
        first->size++;
        placeOfConcatenation = placeOfConcatenation->next;
        toAdd = toAdd->next;
    }
}

void concatenation(Line *firstLine, Line *secondLine)
{
    SymbolsChain *first = makeChain(firstLine);
    SymbolsChain *second = makeChain(secondLine);
    concatenationChains(first, second);

    clearAndDeleteChain(second);
    delete []firstLine->line;
    firstLine->line = nullptr;

    int lenght = sizeChain(first) + 1;
    char *line = new char[lenght];
    int i = 0;
    Symbol *temp = first->first;
    while (i <= lenght && temp != nullptr)
    {
        line[i] = temp->symbol;
        i++;
        temp = temp->next;
    }
    line[lenght - 1] = '\0';
    firstLine->line = line;
    clearAndDeleteChain(first);
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

SymbolsChain *getSubLine(SymbolsChain *chain, int begin, int size)
{
    if (isEmpty(chain) || begin < 1 || begin + size > sizeChain(chain) + 1)
    {
        return nullptr;
    }

    SymbolsChain *subLine = createChain();
    Symbol *beginOfSubLine = getSymbol(chain, begin);

    char firstSymbol = beginOfSubLine->symbol;
    subLine->first = createSymbol(firstSymbol);
    subLine->size++;
    Symbol *current = beginOfSubLine->next;
    Symbol *endSubLine = subLine->first;
    int i = 1;
    while (current != nullptr && i < size)
    {
        char currentSymbol = current->symbol;
        endSubLine->next = createSymbol(currentSymbol);
        current = current->next;
        endSubLine = endSubLine->next;
        subLine->size++;
        i++;
    }
    return subLine;
}

Line *clone(Line *line, ListOfCreatedLines *list)
{
    SymbolsChain *chain = makeChain(line);
    Line *clonedLine = makeLine(chain);
    addLineToList(list, clonedLine);
    clearAndDeleteChain(chain);
    return clonedLine;
}

Line *getSubLine(Line *line, int begin, int size, ListOfCreatedLines *list)
{
    SymbolsChain *lineChain = makeChain(line);
    SymbolsChain *subLineChain = getSubLine(lineChain, begin, size);
    if (subLineChain == nullptr)
        return nullptr;

    Line *subLine = makeLine(subLineChain);
    addLineToList(list, subLine);
    clearAndDeleteChain(subLineChain);
    clearAndDeleteChain(lineChain);
    return subLine;
}

char *getChar(Line *line)
{
    if (line == nullptr)
        return nullptr;

    return line->line;
}
