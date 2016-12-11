#include <iostream>
#include <fstream>
#include "list.h"

using namespace std;

int main()
{
    cout << "Program counts repeat liters in file." << endl;
    cout << "Enter the name of input file." << endl;
    int const sizeName = 256;
    char nameInputFile[sizeName] = {'\0'};
    cin >> nameInputFile;
    ifstream infile(nameInputFile);
    if (!infile.is_open())
    {
        cout << "Error. No input file." << endl;
        return 1;
    }
    cout << "Enter the name of output file." << endl;
    char nameOutputFile[sizeName] = {'\0'};
    cin >> nameOutputFile;

    List *list = createList();
    char current = {'\0'};
    while (!infile.eof())
    {
        infile >> current;
        if (current >= 'a' && current <= 'z')
        {
            addElement(list, current);
        }
    }
    minusLastLitera(list, current);
    ofstream outfile(nameOutputFile);
    printToFile(list, outfile);
    cout << "liters are counted." << endl;
    clearAndDeleteList(list);
    infile.close();
    outfile.close();
    return 0;
}
