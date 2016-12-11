#pragma once

struct Stack;

Stack* createStack();
void push(Stack *s, int value);
int pop(Stack *s);
bool isEmpty(Stack *s);
int sizeStack(Stack *s);
void deleteStack(Stack *s);
