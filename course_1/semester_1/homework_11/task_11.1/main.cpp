#include <iostream>
#include "states.h"

using namespace std;

char getState()
{
    char state = '\0';
    scanf("%c", &state);
    return (state >= '0' && state <= '9' ? '1' : state);
}

void startState()
{
    char nextState = getState();
    switch (nextState)
    {
        case '+':
            stateOne();
        break;

        case '-':
            stateTwo();
        break;

        case '1':
            stateThree();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateOne()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateThree();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateTwo()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateThree();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateThree()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateThree();
        break;

        case '.':
            stateFour();
        break;

        case '\n':
            stateTen();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateFour()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateFive();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateFive()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateFive();
        break;

        case 'E':
            stateSix();
        break;

        case '\n':
            stateTen();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateSix()
{
    char nextState = getState();
    switch (nextState)
    {
        case '+':
            stateSeven();
        break;

        case '-':
            stateEight();
        break;

        case '1':
            stateNine();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateSeven()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateNine();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateEight()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateNine();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateNine()
{
    char nextState = getState();
    switch (nextState)
    {
        case '1':
            stateNine();
        break;

        case '\n':
            stateTen();
        break;

        default:
            stateEleven();
        break;
    }
}

void stateTen()
{
    cout << "Symbols are number." << endl;
}

void stateEleven()
{
    cout << "Symbols are not number." << endl;
}

int main()
{
    cout << "Enter symbols." << endl;
    startState();
    return 0;
}
