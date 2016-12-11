#include <stdio.h>
#include <string.h>
#include <limits.h>
#include "stack.h"
#include "convertorToValue.h"

int main()
{
    printf("Enter the expression in postfix form.\n");
    int const maxSize = 256;
    char expression[maxSize] = {'\0'};
    fgets(expression, maxSize, stdin);
    int result = calculation(expression);
    printf("The value of this expression is %d.\n", result);
    return 0;
}
