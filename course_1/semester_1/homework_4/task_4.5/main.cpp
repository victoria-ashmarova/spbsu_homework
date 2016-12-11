#include <stdio.h>
#include <limits.h>
#include <string.h>
#include "stack.h"
#include "queue.h"
#include "convertorToPostfix.h"
#include "convertorToValue.h"

int main()
{
    printf("Please, write an expression in infix form.\n");
    int const maxSize = 256;
    char infixExpression[maxSize] = {'\0'};
    fgets(infixExpression, maxSize, stdin);
    Stack *s = createStack();
    Queue *postfixExpression = createQueue();
    distribution(infixExpression, postfixExpression, s);
    printf("Expression in the postfix form:\n");
    printQueue(postfixExpression);
    printf("\n");
    deleteStack(s);

    int result = calculation(postfixExpression);
    deleteQueue(postfixExpression);
    printf("The value of this expression is %d.\n", result);
    return 0;
}
