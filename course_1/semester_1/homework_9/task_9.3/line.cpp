#include <iostream>
#include "line.h"
#include "list.h"

using namespace std;

int const number = 5;
int const mod = 47;

struct Element
{
    int value;
    Element *next;
};

struct List
{
    int size;
    Element *head;
};

struct Symbol
{
    char symbol;
    Symbol *next;
};

struct Line
{
    int size;
    Symbol *first;
};

Symbol *createSymbol(char symbol)
{
    Symbol *newSymbol = new Symbol;
    newSymbol->next = nullptr;
    newSymbol->symbol = symbol;
    return newSymbol;
}

bool isStopSymbol(char symbol)
{
    return symbol == '\n' || symbol == '\0';
}

void fullLine(Line *line)
{
    char current = {'\0'};
    scanf("%c", &current);

    if (isStopSymbol(current))
        scanf("%c", &current);
    if (isStopSymbol(current))
        return;

    if (lenght(line) == 0)
    {
        line->first = createSymbol(current);
        line->size++;
    }

    scanf("%c", &current);
    Symbol *temp = line->first;
    while (!isStopSymbol(current))
    {
        temp->next = createSymbol(current);
        temp = temp->next;
        line->size++;
        scanf("%c", &current);
    }
}

Line *createLine()
{
    Line *line = new Line;
    line->size = 0;
    line->first = nullptr;
    fullLine(line);
    return line;
}

int lenght(Line *line)
{
    return line->size;
}

void clearAndDeleteLine(Line *line)
{
    if (lenght(line) == 0)
    {
        delete line;
        line = nullptr;
        return;
    }
    while (lenght(line) > 0)
    {
        Symbol *toDelete = line->first;
        line->first = line->first->next;
        delete toDelete;
        toDelete = nullptr;
        line->size--;
    }
    delete line;
    line = nullptr;
}

int summand(int const number, int const mod, char value, int size)
{
    int power = 1;
    for (int i = 1; i < size; i++)
    {
        power = (power * number) % mod;
    }
    return (power * value) % mod;
}

int hashFunction(Line *line)
{
    int hash = 0;
    Symbol *temp = line->first;
    while (temp != nullptr)
    {
        char current = temp->symbol;
        hash = (hash * number + current) % mod;
        temp = temp->next;
    }
    return (hash > 0 ? hash : hash + mod);
}
//распутать индексы
void hashes(Line *line, int size, int searchHash, List *list)
{
    int index = 1;
    Line *start = getSubLine(line, 1, size);
    int currentHash = hashFunction(start);
    clearAndDeleteLine(start);
    Symbol *begin = line->first;
    Symbol *end = line->first;
    int i = 1;
    while (i <= size && end != nullptr)
    {
        end = end->next;
        i++;
    }
    if (currentHash == searchHash)
        addElement(list, index);
    while (index < lenght(line) + 1 - size)
    {
        index++;
        char removed = begin->symbol;
        char added = end->symbol;
        currentHash = ((currentHash - summand(number, mod, removed, size)) * number + added) % mod;
        currentHash = (currentHash > 0 ? currentHash : currentHash + mod);
        if (currentHash == searchHash)
            addElement(list, index);
        begin = begin->next;
        end = end->next;
       /* Line *current = getSubLine(line, index, size);
        int currentHash = hashFunction(current);
        clearAndDeleteLine(current);
        if (currentHash == searchHash)
            addElement(list, index);*/
    }
}

Line *getSubLine(Line *line, int begin, int size)
{
    if (line == nullptr)
        return nullptr;

    if (begin < 0 || begin > lenght(line) || begin + size - 1 > lenght(line) || size < 0)
        return nullptr;

    Line *subLine = new Line;
    subLine->size = 0;
    subLine->first = nullptr;
    Symbol *temp = line->first;
    if (begin == 1)
    {
        char current = line->first->symbol;
        subLine->first = createSymbol(current);
        subLine->size++;
    }
    else
    {
        int i = 1;
        while (i < begin && temp->next != nullptr)
        {
            i++;
            temp = temp->next;
        }
        char current = temp->symbol;
        subLine->first = createSymbol(current);
        subLine->size++;
    }
    int i = 1;
    Symbol *endLine = subLine->first;
    temp = temp->next;
    while (i < size && temp != nullptr)
    {
        char current = temp->symbol;
        endLine->next = createSymbol(current);
        subLine->size++;
        temp = temp->next;
        endLine = endLine->next;
        i++;
    }
    return subLine;
}

bool linesAreEqual(Line *firstLine, Line *secondLine)
{
    if (lenght(firstLine) != lenght(secondLine))
        return false;

    Symbol *first = firstLine->first;
    Symbol *second = secondLine->first;
    while (first->next != nullptr && second->next != nullptr && first->symbol == second->symbol)
    {
        first = first->next;
        second = second->next;
    }
    return first->symbol == second->symbol;
}

void check(Line *haystack, Line *needle, List *list)
{
    int i = sizeList(list);
    while (i > 0)
    {
        int index = getValue(list, i);
        Line *toCheck = getSubLine(haystack, index, lenght(needle));
        if (!linesAreEqual(needle, toCheck))
            removeElement(list, index);
        i--;
    }
}

List *createList()
{
    List *list = new List;
    list->head = nullptr;
    list->size = 0;
    return list;
}

int sizeList(List *list)
{
    return list->size;
}

Element *createElement(int value, Element *next)
{
    Element *element = new Element;
    element->next = next;
    element->value = value;
    return element;
}

void addElement(List *list, int value)
{
    if (sizeList(list) == 0)
    {
        list->head = createElement(value, list->head);
        list->size++;
        return;
    }
    Element *temp = list->head;
    while (temp->next != nullptr)
        temp = temp->next;

    temp->next = createElement(value, nullptr);
    list->size++;
}

void removeElement(List *list, int value)
{
    if (sizeList(list) == 0)
        return;

    if (value == list->head->value)
    {
        Element *toDelete = list->head;
        list->head = list->head->next;
        delete toDelete;
        toDelete = nullptr;
        list->size--;
        return;
    }

    if (sizeList(list) == 2 && list->head->next->value == value)
    {
        Element *toDelete = list->head->next;
        list->head->next = nullptr;
        delete toDelete;
        toDelete = nullptr;
        list->size--;
        return;
    }

    Element *temp = list->head;
    while (temp->next->next != nullptr && temp->next->value != value)
    {
        temp = temp->next;
    }
    if (temp->next->value == value)
    {
        Element *toDelete = temp->next;
        temp->next = temp->next->next;
        delete toDelete;
        toDelete = nullptr;
        list->size--;
        return;
    }

    if (temp->next->next != nullptr)
    {
        if (temp->next->next->value == value)
        {
            Element *toDelete = temp->next->next;
            temp->next->next = temp->next->next->next;
            delete toDelete;
            toDelete = nullptr;
        }
    }
}

int getValue(List *list, int index)
{
    if (index < 0 || index > sizeList(list))
        return -1;

    Element *toReturn = list->head;
    int i = 1;
    while (i < index && toReturn != nullptr)
    {
        i++;
        toReturn = toReturn->next;
    }
    return (toReturn == nullptr ? -1 : toReturn->value);
}

void printList(List *list)
{
    if (sizeList(list) == 0)
    {
        cout << "There is no indexes." << endl;
        return;
    }

    cout << "indexes : ";
    Element *temp = list->head;
    while (temp != nullptr)
    {
        cout << temp->value << " ";
        temp = temp->next;
    }
}

void clearAndDeleteList(List *list)
{
    while (sizeList(list) > 0)
    {
        Element *toDelete = list->head;
        list->head = list->head->next;
        delete toDelete;
        toDelete = nullptr;
        list->size--;
    }
    delete list;
    list = nullptr;
}
