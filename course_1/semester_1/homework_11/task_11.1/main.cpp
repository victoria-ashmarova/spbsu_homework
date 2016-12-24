#include <iostream>

using namespace std;

enum States {start, firstSigh, intPart, point, fraction, exp, sighExp, digitExp, complete, error};

char getSymbol()
{
    char symbol = '\0';
    scanf("%c", &symbol);
    return symbol;
}

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

bool isSigh(char symbol)
{
    return symbol == '+'  || symbol == '-';
}

States transition(char symbol, States currentState)
{
    if (currentState == start && isSigh(symbol))
        return firstSigh;

    if ((currentState >= start && currentState <= intPart) && isDigit(symbol))
        return intPart;

    if (currentState == intPart && symbol == '.')
        return point;

    if ((currentState == point || currentState == fraction) && isDigit(symbol))
        return fraction;

    if (currentState == fraction && symbol == 'E')
        return exp;

    if (currentState == exp && isSigh(symbol))
        return sighExp;

    if ((currentState == exp || currentState == sighExp || currentState == digitExp) && isDigit(symbol))
        return digitExp;

    if ((currentState == intPart || currentState == fraction || currentState == digitExp) && symbol == '\n')
        return complete;

    return error;
}

void completeState()
{
    cout << "This line is digit." << endl;
}

void errorState()
{
    cout << "This line is not digit." << endl;
}

int main()
{
    cout << "This program checks if line of symbols is digit." << endl;
    States currentState = start;
    char currentSymbol = '\0';
    while (currentState != error && currentState != complete)
    {
        currentSymbol = getSymbol();
        currentState = transition(currentSymbol, currentState);
    }

    if (currentState == complete)
        completeState();
    if (currentState == error)
        errorState();
    return 0;
}
