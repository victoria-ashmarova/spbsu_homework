#include <iostream>
#include <fstream>
#include "queue.h"

using namespace std;

int main()
{
    cout << "Enter the value a" <<endl;
    int a = 0;
    cin >> a;
    cout << "Enter the value b which is more then value a" <<endl;
    int b = 0;
    cin >> b;
    while (b <= a)
    {
        cout << "Error. Value b must be more then value a. Enter value b again" <<endl;
        cin >> b;
    }

    int const maxSize = 256;
    char nameFile[maxSize] = {'\0'};
    cout << "Enter the name of file" <<endl;
    cin >> nameFile;

    ifstream inFile(nameFile);
    ofstream outFile("g.txt");

    if (!inFile.is_open())
    {
        cout << "Error. There is no file" <<endl;
        return 1;
    }

    Queue *lessA = createQueue();
    Queue *fromAtoB = createQueue();
    Queue *moreB = createQueue();

    while (!inFile.eof())
    {
        int current = 0;
        inFile >> current;
        if (current < a)
        {
            addElement(lessA, current);
        }

        if (current >= a && current <= b)
        {
            addElement(fromAtoB, current);
        }
        if (current > b)
        {
            addElement(moreB, current);
        }
    }

    while (!isEmptyQueue(lessA))
    {
        int toPrint = getFirst(lessA);
        outFile << toPrint << " ";
    }
    outFile << " " << endl;

    while (!isEmptyQueue(fromAtoB))
    {
        int toPrint = getFirst(fromAtoB);
         outFile << toPrint << " ";
    }
    outFile << endl;

    while (!isEmptyQueue(moreB))
    {
        int toPrint = getFirst(moreB);
         outFile << toPrint << " ";
    }

    deleteQueue(lessA);
    deleteQueue(fromAtoB);
    deleteQueue(moreB);
    inFile.close();
    outFile.close();
    return 0;
}
