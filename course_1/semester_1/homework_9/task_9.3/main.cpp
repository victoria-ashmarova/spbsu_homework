#include <iostream>
#include "line.h"

using namespace std;

Line *getLineFromConsole()
{
    SymbolChane *chane = createSymbolChane();
    fullChane(chane);
    return makeLine(chane);
}

int hashFunction(char *toSearch, int lenght, int const number, int const mod)
{
    int hashToReturn = 0;
    for (int i = 0; i < lenght; i++)
    {
        hashToReturn = (hashToReturn * number + toSearch[i]) % mod;
    }
    return (hashToReturn >= 0 ? hashToReturn : hashToReturn + mod);
}

bool checkIndex(char *needle, char *haystack, int index, int lenghtNeedle)
{
    int i = 0;
    while (i < lenghtNeedle && needle[i] == haystack[index + i])
    {
        i++;
    }
    if (i == lenghtNeedle)
    {
        cout << index + 1 << " ";
    }
    return i == lenghtNeedle;
}

int awayAmount(char away, int const number, int const mod, int power)
{
    int toReturn = 1;
    for (int i = 1; i < power; i++)
    {
        toReturn = (toReturn * number) % mod;
    }
    toReturn = (toReturn * away) % mod;
    return (toReturn >= 0 ? toReturn : toReturn + mod);
}

int main()
{
    cout << "Enter haystack." << endl;
    Line *haystackLine = getLineFromConsole();
    cout << "Enter needle" << endl;
    Line *needleLine = getLineFromConsole();
    if (lenght(needleLine) > lenght(haystackLine))
    {
        cout << "Incorrect data. Lenght of needle is more than needle of haystack." << endl;
        clearAndDeleteLine(needleLine);
        clearAndDeleteLine(haystackLine);
        return 0;
    }
    int const number = 7;
    int const mod = 47;
    char *needle = getChar(needleLine);
    char *haystack = getChar(haystackLine);
    if (needle == nullptr || haystack == nullptr)
    {
        cout << "Couldn't search entries : one of lines is empty." << endl;
        needle = nullptr;
        haystack = nullptr;
        clearAndDeleteLine(needleLine);
        clearAndDeleteLine(haystackLine);
        return 0;
    }
    int searchHash = hashFunction(needle, lenght(needleLine), number, mod);
    int currentHash = hashFunction(haystack, lenght(needleLine), number, mod);
    bool isInHaystack = 0;
    if (currentHash == searchHash)
    {
        bool current = checkIndex(needle, haystack, 0, lenght(needleLine));
        isInHaystack = (current ? true : isInHaystack);
    }
    int i = 0;
    while (i <= lenght(haystackLine) - lenght(needleLine))
    {
        currentHash = ((currentHash - awayAmount(haystack[i], number, mod, lenght(needleLine))) * number + haystack[i + lenght(needleLine)]) % mod;
        if (currentHash < 0)
            currentHash = currentHash + mod;
        i++;
        if (currentHash == searchHash)
        {
            bool current = checkIndex(needle, haystack, i, lenght(needleLine));
            isInHaystack = (current ? true : isInHaystack);
        }
    }
    cout << (isInHaystack ? " : indexses of entry" : "there is no indexses of entry") << endl;
    needle = nullptr;
    haystack = nullptr;
    clearAndDeleteLine(needleLine);
    clearAndDeleteLine(haystackLine);
    return 0;
}
