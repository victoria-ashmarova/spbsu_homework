#include <iostream>
#include "line.h"

using namespace std;

int main()
{
    cout << "This program searches a string needle in a string haystack." << endl;
    cout << "Enter haystack." << endl;
    Line *haystack = createLine();
    cout << "Enter needle." << endl;
    Line *needle = createLine();
    if (lenght(needle) > lenght(haystack))
    {
        cout << "Error. Incorrect data." << endl;
        clearAndDeleteLine(needle);
        clearAndDeleteLine(haystack);
        return 1;
    }
    List *indexes = createList();
    int searchHash = hashFunction(needle);
    hashes(haystack, lenght(needle), searchHash, indexes);
    check(haystack, needle, indexes);
    printList(indexes);
    clearAndDeleteList(indexes);
    clearAndDeleteLine(needle);
    clearAndDeleteLine(haystack);
    return 0;
}
