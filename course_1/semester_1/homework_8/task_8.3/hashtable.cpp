#include <iostream>
#include <fstream>
#include "hashtable.h"
#include "line.h"

using namespace std;

List **createTable(int size)
{
    List **table = new List*[size];
    for (int i = 0; i < size; i++)
    {
        table[i] = createList();
    }
    return table;
}

void clearAndDeleteTable(List **table, int size)
{
    for (int i = 0; i < size; i++)
    {
        clearAndDeleteList(table[i]);
        table[i] = nullptr;
    }
    delete []table;
    table = nullptr;
}

void countHashes(List **lastTable, int lastSize, List **newTable, int newSize)
{
    for (int i = 0; i < lastSize; i++)
    {
        while (sizeList(lastTable[i]) > 0)
        {
            Line *lastLine = getLineFromNumber(lastTable[i], 1);
            Line *newLine = clone(lastLine);
            if (lastLine != nullptr)
            {
                int newIndex = hashFunction(lastLine, newSize);
                removeLineFromList(lastTable[i], lastLine);
                addLineToList(newTable[newIndex], newLine);
            }
        }
    }
}

List **makeNewTable(List **lastTable, int lastSize)
{
    int newSize = lastSize + lastSize / 2;
    List **newTable = createTable(newSize);
    countHashes(lastTable, lastSize, newTable, newSize);
    clearAndDeleteTable(lastTable, lastSize);
    return newTable;
}

float loadFactor(List **table, int size)
{
    int ammount = 0;
    for (int i = 0; i < size; i++)
    {
        ammount = ammount + sizeList(table[i]);
    }
    float toReturn = (float) ammount / size;
    return toReturn;
}

int emptyElement(List **table, int size)
{
    int numberOfEmpty = 0;
    for (int i = 0; i < size; i++)
    {
        if (sizeList(table[i]) == 0)
            numberOfEmpty++;
    }
    return numberOfEmpty;
}

int maxSize(List **table, int size)
{
    int max = 0;
    for (int i = 0; i < size; i++)
    {
        max = (sizeList(table[i]) > max ? sizeList(table[i]) : max);
    }
    return max;
}

void printMaxLists(List **table, int size)
{
    int maxIndex = maxSize(table, size);
    for (int i = 0; i < size; i++)
    {
        if (sizeList(table[i]) == maxIndex)
        {
            cout << endl;
            cout << "List number " << i << endl;
            cout << "words and its frequenses in list : " << endl;
            printAllLines(table[i]);
        }
    }
}

int amountOfWord(List **table, int size)
{
    int amount = 0;
    for (int i = 0; i < size; i++)
    {
        amount = amount + sizeList(table[i]);
    }
    return amount;
}

int lenghOfLine(List **table, int size)
{
    int amount = amountOfWord(table, size);
    return (amount % size > size % 2 ? amount / size + 1 : amount / size);
}

void printInformation(List **table, int size)
{
    cout << "Load factor: " << loadFactor(table, size) << endl;
    cout << "Size table: " << size << endl;
    cout << "Amount of words: " << amountOfWord(table, size) << endl;
    cout << "The average lenght of list: " << lenghOfLine(table, size) << endl;
    cout << "Amount of empty lists: " << emptyElement(table, size) << endl;
    cout << "The max size of list: " << maxSize(table, size) << endl;
    printMaxLists(table, size);
    cout << endl;
    cout << "Words and its frequenses: " << endl;
    for (int i = 0; i < size; i++)
    {
        printAllLines(table[i]);
    }
}

void countFrequencies(ifstream &file)
{
    int sizeTable = 100;
    List **table = createTable(sizeTable);
    int sizeWord = 64;
    while (!file.eof())
    {
        char temp[sizeWord] = {'\0'};
        file >> temp;
        if (!isStopSymbol(temp[0]) && temp[0] != '-')
        {
            Line *current = fromChar(temp);
            int index = hashFunction(current, sizeTable);
            addLineToList(table[index], current);
        }
        if (loadFactor(table, sizeTable) > 0.8)
        {
            table = makeNewTable(table, sizeTable);
            sizeTable = sizeTable + sizeTable / 2;
        }
    }
    printInformation(table, sizeTable);
    clearAndDeleteTable(table, sizeTable);
}
