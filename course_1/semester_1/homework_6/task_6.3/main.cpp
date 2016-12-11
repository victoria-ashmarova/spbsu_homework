#include <iostream>
#include "polinomial.h"

using namespace std;

int main()
{
    cout << "Enter coefficients of polinomial." << endl;;
    int const maxSize = 256;
    char coefficients[maxSize] = {'\0'};
    cin.getline(coefficients, maxSize);
    Expression *polinomial = createExpression();
    makeExpression(polinomial, coefficients);
    deleteAndClear(polinomial);
    return 0;
}
