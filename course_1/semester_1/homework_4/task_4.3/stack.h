#pragma once

struct Stack;

Stack* createStack();
void push(Stack *s, char symbol);
char pop(Stack *s);
bool isEmpty(Stack *s);
int stackSize();
void deleteStack(Stack *s);
