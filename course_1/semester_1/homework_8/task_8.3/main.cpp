#include <iostream>
#include <fstream>
#include "hashtable.h"

using namespace std;

int main()
{
    cout << "Enter the name of file which text." << endl;
    int const sizeName = 256;
    char nameFile[sizeName] = {'\0'};
    cin >> nameFile;
    ifstream file(nameFile);
    if (!file.is_open())
    {
        cout << "Error. Couldn't open the file." << endl;
        return 1;
    }
    countFrequencies(file);
    file.close();
    return 0;
}
