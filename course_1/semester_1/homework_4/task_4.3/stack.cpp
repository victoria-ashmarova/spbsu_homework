#include "stack.h"

struct StackElement
{
    char symbol;
    StackElement *next;
};

struct Stack
{
    int size;
    StackElement *top;
};

Stack* createStack()
{
    Stack *s = new Stack;
    s->top = nullptr;
    s->size = 0;
    return s;
}

int stackSize(Stack *s)
{
    return s->size;
}

bool isEmpty(Stack *s)
{
    return stackSize(s) == 0;
}

void push(Stack *s, char symbol)
{
    StackElement *newSymbol = new StackElement;
    newSymbol->symbol = symbol;
    newSymbol->next = s->top;
    s->top = newSymbol;
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

char pop(Stack *s)
{
    if (isEmpty(s))
        return '\0';

    char x = s->top->symbol;
    deleteElement(s);
    return x;
}

void deleteStack(Stack *s)
{
    while (!isEmpty(s))
    {
        deleteElement(s);
    }
    s->top = nullptr;
    delete s;
}
