#include <stdio.h>
#include <string.h>
#include <limits.h>
#include "stack.h"
#include "convertorToValue.h"

int calculation(char expression[])
{
    Stack *s = createStack();
    int lenght = strlen(expression);
    int i = 0;
    while (i < lenght)
    {
        if (isNumber(expression[i]))
        {
            int lastNumbers = 0;
            while (expression[i] != ' ')
            {
                int newNumber = toNumber(expression[i]);
                lastNumbers = makeNumber(newNumber, lastNumbers);
                i++;
            }
            push(s, lastNumbers);
        }
        if (isOperation(expression[i]) && sizeStack(s) > 1)
        {
            int second = pop(s);
            int first = pop(s);
            int resultOfOperation = action(first, second, expression[i]);
            push(s, resultOfOperation);
        }
        i++;
    }
    int result = pop(s);
    deleteStack(s);
    return result;
}

int toNumber(char value)
{
    return value - '0';
}

int makeNumber(int newNumber, int lastNumbers)
{
    return lastNumbers * 10 + newNumber;
}

int action(int first, int second, char operation)
{
    if (operation == '+')
        return first + second;
    if (operation == '-')
        return first - second;
    if (operation == '*')
        return first * second;
    if (operation == '/')
        return first / second;
    else
        return INT_MIN;
}

bool isOperation(char symbol)
{
    return symbol == '+' || symbol == '-' || symbol == '/' || symbol == '*';
}

bool isNumber(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}
