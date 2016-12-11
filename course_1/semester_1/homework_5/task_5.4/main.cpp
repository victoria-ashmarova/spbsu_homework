#include <iostream>
#include <fstream>
#include "phonebook.h"

using namespace std;

enum {exitProgram, addition, save, searchNumbers, searchNames};

int main()
{
    cout << "Phonebook" << endl;
    Book *names = createBook();
    Book *numbers = createBook();
    readFromFile(names, numbers);

    cout << "You can do this actions with phonebook:" << endl;
    cout << "0 exit, 1 add, 2 save, 3 search number, 4 search name." << endl;
    int action = 0;
    cin >> action;
    while (action != exitProgram)
    {
        switch (action)
        {
            case addition:
               addLineFromConsole(names, numbers);
            break;

            case save:
                saveToFile(names, numbers);
            break;

            case searchNames:
            {
                searchName(names, numbers);
            }
            break;

            case searchNumbers:
            {
                searchNumber(names, numbers);
            }
            break;
        }
        cout << "Enter the action again." << endl;
        cin >> action;
    }
    clearAndDeleteBook(names);
    clearAndDeleteBook(numbers);
    cout << "You have finished working witn phone book." << endl;
    return 0;
}
