#pragma once

int calculation(Queue *postfixExpression);
int toNumber(char value);
int makeNumber(int newNumber, int lastNumbers);
int action(int first, int second, char operation);
bool isOperation(char current);
