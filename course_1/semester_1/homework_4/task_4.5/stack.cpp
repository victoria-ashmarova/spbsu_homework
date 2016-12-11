#include <stdio.h>
#include "stack.h"

struct StackElement
{
    int data;
    StackElement *next;
};

struct Stack
{
    StackElement *top;
    int size;
};

Stack* createStack()
{
    Stack *s = new Stack;
    s->top = nullptr;
    s->size = 0;
    return s;
}

void push(Stack *s, int value)
{
    StackElement *newElement = new StackElement;
    newElement->data = value;
    newElement->next = s->top;
    s->top = newElement;
    s->size++;
}

void deleteElement(Stack *s)
{
    StackElement *toDelete = s->top;
    s->top = s->top->next;
    toDelete->next = nullptr;
    delete toDelete;
    s->size--;
}

int pop(Stack *s)
{
    if (isEmpty(s))
            return -1;

    int x = s->top->data;
    deleteElement(s);
    return x;
}

bool isEmpty(Stack *s)
{
    return sizeStack(s) == 0;
}

int sizeStack(Stack *s)
{
    return s->size;
}

void deleteStack(Stack *s)
{
    while (!isEmpty(s))
    {
        deleteElement(s);
    }
    delete s;
}
