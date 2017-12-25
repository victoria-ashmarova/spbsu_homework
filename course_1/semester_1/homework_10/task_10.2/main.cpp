#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

int main()
{
    cout << "This program decodes line." << endl;
    cout << "Enter the name of input file." << endl;
    int const sizeName = 32;
    char nameFile[sizeName] = {'\0'};
    cin >> nameFile;
    ifstream in(nameFile);
    if (!in.is_open())
    {
        cout << "Error. Couldn't open the file." << endl;
        return 1;
    }
    cout << "Enter the name of output file." << endl;
    cin >> nameFile;
    ofstream out(nameFile);
    Tree *tree = createTree();
    fullTree(in, tree);
    decode(tree, in, out);
    cout << "Line is decoded." << endl;
    deleteTree(tree);
    in.close();
    out.close();
    return 0;
}
