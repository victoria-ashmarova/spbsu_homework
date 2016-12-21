#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

int main()
{
    cout << "Enter the name of file." << endl;
    int sizeName = 32;
    char nameInputFile[sizeName] = {'\0'};
    cin >> nameInputFile;
    ifstream in(nameInputFile);
    if (!in.is_open())
    {
        cout << "Error. Couldn't open the file" << endl;
        return 1;
    }
    Queue *toTreatment = createQueue();
    Buffer *buffer = createBuffer();
    int const sizeLine = 512;
    while (!in.eof())
    {
        char current[sizeLine] = {'\0'};
        in.getline(current, sizeLine);
        int i = 0;
        while (i < sizeLine && current[i] != '\0')
        {
            addElementToQueue(toTreatment, current[i], nullptr, nullptr);
            addElementToBuffer(buffer, current[i], sizeBuffer(buffer) + 1);
            i++;
        }
    }
    in.close();
    while (sizeQueue(toTreatment) > 1)
    {
        makeTreeElement(toTreatment);
    }
    cout << "Enter the name of file to output." << endl;
    char nameOutPutFile[sizeName] = {'\0'};
    cin >> nameOutPutFile;
    ofstream out(nameOutPutFile);
    preorder(toTreatment, out);
    orderWithVariables(toTreatment);
    code(toTreatment, buffer, out);
    out.close();
    deleteBuffer(buffer);
    deleteQueue(toTreatment);
    return 0;
}
