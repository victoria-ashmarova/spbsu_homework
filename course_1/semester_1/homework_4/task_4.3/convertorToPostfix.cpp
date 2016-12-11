#include <stdio.h>
#include <string.h>
#include "stack.h"
#include "queue.h"
#include "convertorToPostfix.h"

struct QueueElement
{
    char value;
    QueueElement *next;
};

struct Queue
{
    int size;
    QueueElement *first;
    QueueElement *last;
};

Queue *createQueue()
{
    Queue *queue = new Queue;
    queue->size = 0;
    queue->first = nullptr;
    queue->last = nullptr;
    return queue;
}

void clearQueue(Queue *queue)
{
    while (!isEmptyQueue(queue))
    {
        deleteElement(queue);
    }
}

void deleteQueue(Queue *queue)
{
    clearQueue(queue);
    queue->first = nullptr;
    queue->last = nullptr;
    delete queue;
}

int sizeQueue(Queue *queue)
{
    return queue->size;
}

bool isEmptyQueue(Queue *queue)
{
    return sizeQueue(queue) == 0;
}

QueueElement *createElement(char value)
{
    QueueElement *newSymbol = new QueueElement;
    newSymbol->value = value;
    newSymbol->next = nullptr;
    return newSymbol;
}

void addElement(Queue *queue, char value)
{
    if (isEmptyQueue(queue))
    {
        queue->first = createElement(value);
        queue->last = queue->first;
        queue->size++;
        return;
    }

    queue->last->next = createElement(value);
    queue->last = queue->last->next;
    queue->size++;
}

void deleteElement(Queue *queue)
{
    QueueElement *toDelete = queue->first;
    queue->first = queue->first->next;
    toDelete->next = nullptr;
    delete toDelete;
    queue->size--;
    if (isEmptyQueue(queue))
    {
        queue->last = nullptr;
    }
}

void printQueue(Queue *queue)
{
    QueueElement *temp = queue->first;
    while (temp != nullptr)
    {
        char toPrint = temp->value;
        printf("%c", toPrint);
        temp = temp->next;
    }
}

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

        if (isOperationFirst(current) || isOperationSecond(current))
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
