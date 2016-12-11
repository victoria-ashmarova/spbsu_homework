#pragma once

void distribution(char infixExpression[], Queue *postfixExpression, Stack *s);
bool isNumber(char current);
bool isOperationFirst(char current);
bool isOperationSecond(char current);
