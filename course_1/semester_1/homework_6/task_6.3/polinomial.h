#pragma once

struct Expression;

Expression *createExpression();
int sizeOfExpression(Expression *polinomial);
void addCoefficients(Expression *polinomial, char coefficients[]);
bool normalCoefficient(Expression *polinomial);
bool onlyZeroCoefficient(Expression *polinomial);
bool onlyOneCoefficient(Expression *polinomial);

void addX(Expression *polinomial);
void addSigh(Expression *polinomial);
void addPowers(Expression *polinomial);

void treatmentOfZero(Expression *polinomial);

void treatmentOfOne(Expression *polinomial);
void treatmentOfFirstElement(Expression *polinomial);
void spaceTreatment(Expression *polinomial);

void printExpression(Expression *polinomial);

void makeExpression(Expression *polinomial, char coefficients[]);

void deleteAndClear(Expression *polinomial);
