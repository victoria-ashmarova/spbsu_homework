#include <iostream>
#include <fstream>

using namespace std;

int **createTable(int size)
{
    int **table = new int*[size];
    for (int i = 0; i < size; i++)
    {
        table[i] = new int[size];
    }
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            table[i][j] = 0;
        }
    }
    return table;
}

int *createArray(int size)
{
    int *array = new int[size];
    array[0] = 0;
    for (int i = 1; i < size; i++)
    {
        array[i] = 0;
    }
    return array;
}

void deleteTable(int **table, int size)
{
    for (int i = 0; i < size; i++)
    {
        delete table[i];
        table[i] = nullptr;
    }
    delete []table;
    table = nullptr;
}

void deleteArray(int *array)
{
    delete []array;
    array = nullptr;
}

void printWays(int *ways, int index)
{
    if (index == 0)
    {
        cout << '1';
        return;
    }

    printWays(ways, ways[index]);
    cout << " -> " << index + 1;
}

void print(int **table, int *roads, int size, int max, int *ways)
{
    int i = 1;
    while (i < size && roads[i] != 0)
    {
        int index = roads[i];
        cout << index + 1;
        cout << " lenght of road: ";
        if (table[index][0] > max)
            cout << "no road";
        else
            cout << table[index][0];
        cout << "; distance: ";
        if (table[0][index] > max)
            cout << " no way" << endl;
        else
        {
            cout << table[0][index] << endl;
            printWays(ways, index);
            cout << endl;
        }
        i++;
    }
}

int getMin(int *array, int *label, int size, int max)
{
    int min = max + 1;
    int minIndex = 0;
    for (int i = 0; i < size; i++)
    {
        if (array[i] > 0 && array[i] < min && label[i] == 0)
        {
            min = array[i];
            minIndex = i;
        }
    }
    return minIndex;
}

void fullTable(int **table, int size, int max)
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            table[i][j] = (i != j && table[i][j] == 0 ? max + 1 : table[i][j]);
        }
    }
}

void calculate(int **table, int size, int max)
{
    int *roads = createArray(size);
    int *lable = createArray(size);
    int *ways = createArray(size);
    for (int i = 1; i < size; i++)
    {
        int minIndex = getMin(table[0], lable, size, max);
        for (int j = 1; j < minIndex; j++)
        {
            int distance = table[0][minIndex] + table[j][minIndex];
            ways[j] = (table[0][j] <= distance ? ways[j] : minIndex);
            table[0][j] = (table[0][j] <= distance ? table[0][j] : distance);
        }
        for (int j = minIndex + 1; j < size; j++)
        {
            int distance = table[0][minIndex] + table[minIndex][j];
            ways[j] = (table[0][j] <= distance ? ways[j] : minIndex);
            table[0][j] = (table[0][j] <= distance ? table[0][j] : distance);       
        }
        lable[minIndex] = 1;
        roads[i] = minIndex;
    }
    print(table, roads, size, max, ways);
    deleteArray(ways);
    deleteArray(roads);
    deleteArray(lable);
}

int getNumber(ifstream &file)
{
    int number = 0;
    file >> number;
    return number;
}

void reading()
{
    cout << "Enter the name of file." << endl;
    int const sizeName = 256;
    char nameFile[sizeName] = {'\0'};
    cin >> nameFile;
    ifstream file(nameFile);
    if (!file.is_open())
    {
        cout << "Error. No file." << endl;
        return;
    }
    int sizeTable = getNumber(file);
    int numberOfRoads = getNumber(file);
    if (sizeTable <= 1 || numberOfRoads < 1)
    {
        cout << "Error. Incorrect data." << endl;
        file.close();
        return;
    }
    int **table = createTable(sizeTable);
    int max = 0;
    for (int i = 0; i < numberOfRoads; i++)
    {
        int first = getNumber(file);
        int second = getNumber(file);
        int distance = getNumber(file);
        if (first >= 1 && first <= sizeTable && second >= 1 && second <= sizeTable)
        {
            table[first - 1][second - 1] = distance;
            table[second - 1][first - 1] = distance;
        }
        max = max + distance;
    }
    fullTable(table, sizeTable, max);
    calculate(table, sizeTable, max);
    deleteTable(table, sizeTable);
    table = nullptr;
    file.close();
}

int main()
{
    cout << "This program counts distances between vertexes of towns' graph." << endl;
    reading();
    return 0;
}
