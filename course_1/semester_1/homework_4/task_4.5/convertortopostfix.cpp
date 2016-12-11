#include <stdio.h>
#include <string.h>
#include "stack.h"
#include "queue.h"
#include "convertorToValue.h"
#include "convertorToPostfix.h"

void operation(Stack *s, Queue *postfixExpression, char current)
{
    if (! isEmpty(s))
    {
        char top = pop(s);

        if (isOperationFirst(current))
        {
            while (isOperationFirst(top))
            {
                addElement(postfixExpression, ' ');
                addElement(postfixExpression, top);
                top = pop(s);
            }
            push(s, top);
        }

        if (isOperationSecond(current))
        {
            while (top != '(' && ! isEmpty(s))
            {
                addElement(postfixExpression, ' ');
                addElement(postfixExpression, top);
                top = pop(s);
            }
            if (top == '(')
            {
                push(s, top);
            }
            else
            {
                addElement(postfixExpression, ' ');
                addElement(postfixExpression, top);
            }
        }
    }
    push(s, current);
    addElement(postfixExpression, ' ');
}

void distribution(char infixExpression[], Queue *postfixExpression, Stack *s)
{
    int lenght = strlen(infixExpression);

    for (int i = 0; i < lenght; i++)
    {
        char current = infixExpression[i];

        if (isNumber(current))
        {
            addElement(postfixExpression, current);
        }

        if (isOperation(current))
            operation(s, postfixExpression, current);

        if (current == '(')
        {
            addElement(postfixExpression, ' ');
            push(s, current);
        }

        if ((current == ')') || (i == lenght - 1))
        {
            char top = pop(s);
            while (top != '(' && ! isEmpty(s))
            {
               addElement(postfixExpression, ' ');
               addElement(postfixExpression, top);
               top = pop(s);
            }
            if (top != '(')
            {
                addElement(postfixExpression, ' ');
                addElement(postfixExpression, top);
            }
        }
    }
}

bool isNumber(char current)
{
    return (current >= '0' && current <='9');
}

bool isOperationFirst(char current)
{
    return (current == '*' || current == '/');
}

bool isOperationSecond(char current)
{
    return (current == '+' || current == '-');
}
