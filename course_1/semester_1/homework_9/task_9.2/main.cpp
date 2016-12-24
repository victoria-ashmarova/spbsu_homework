#include <iostream>
#include <fstream>
#include "list.h"

using namespace std;

int getNumber(ifstream &file)
{
    int number = 0;
    file >> number;
    return number;
}

char **createTable(int sizeX, int sizeY)
{
    char **table = new char*[sizeX];
    for (int i = 0; i < sizeX; i++)
    {
        table[i] = new char[sizeY];
    }
    for (int i = 0; i < sizeX; i++)
    {
        for (int j = 0; j < sizeY; j++)
        {
            table[i][j] = '\0';
        }
    }
    return table;
}

void fulltable(char **table, int sizeX, int sizeY, ifstream &file)
{
    for (int i = 0; i < sizeX; i++)
    {
        for (int j = 0; j < sizeY; j++)
        {
            file >> table[i][j];
        }
    }
}

void deleteTable(char **table, int line)
{
    for (int i = 0; i < line; i++)
    {
        delete []table[i];
        table[i] = nullptr;
    }
    delete []table;
    table = nullptr;
}

bool isIncorrect(int value, int maxValue)
{
    return value < 0 || value >= maxValue;
}

int main()
{
    cout << "Realisation A* on map." << endl;
    cout << "Enter the name of file with map." << endl;
    int const sizeName = 32;
    char nameFile[sizeName] = {'\0'};
    cin >> nameFile;
    ifstream file(nameFile);
    if (!file.is_open())
    {
        cout << "Error. Couldn't open the file." << endl;
        return 1;
    }
    int startX = getNumber(file);
    int startY = getNumber(file);
    int finishX = getNumber(file);
    int finishY = getNumber(file);
    int sizeX = getNumber(file);
    int sizeY = getNumber(file);
    if (isIncorrect(startX, sizeX) || isIncorrect(finishX, sizeX) || isIncorrect(startY, sizeY) || isIncorrect(finishY, sizeY))
    {
        cout << "Incorrect data. There are not this points on map." << endl;
        file.close();
        return 1;
    }
    char **table = createTable(sizeX, sizeY);
    fulltable(table, sizeX, sizeY, file);
    if (table[startX][startY] == '1' || table[finishX][finishY] == '1')
    {
        cout << "Incorrect data. These points on map are in the wall." << endl;
        deleteTable(table, sizeX);
        file.close();
        return 1;
    }
    searchWay(table, sizeX, sizeY, startX, startY, finishX, finishY);
    deleteTable(table, sizeX);
    file.close();
    return 0;
}
