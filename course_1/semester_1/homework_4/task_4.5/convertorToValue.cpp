#include <stdio.h>
#include <limits.h>
#include "stack.h"
#include "queue.h"
#include "convertorToPostfix.h"
#include "convertorToValue.h"

int calculation(Queue *postfixExpression)
{
    Stack *s = createStack();
    while (!isEmptyQueue(postfixExpression))
    {
        char current = firstValue(postfixExpression);
        if (isNumber(current))
        {
            int lastNumbers = 0;
            while (current != ' ')
            {
                int newNumber = toNumber(current);
                lastNumbers = makeNumber(newNumber, lastNumbers);
                current = firstValue(postfixExpression);
            }
            push(s, lastNumbers);
        }

        if (isOperation(current) && sizeStack(s) > 1)
        {
            int second = pop(s);
            int first = pop(s);
            int resultOfAction = action(first, second, current);
            push(s, resultOfAction);
        }
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
