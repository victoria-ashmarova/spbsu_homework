#include <iostream>

using namespace std;

void additionOfNumbers(int firstSummand[], int secondSummand[], int result[], int maxNumberOfDigits)
{
    int nextNumber = 0;
    for (int i = 0; i < maxNumberOfDigits; i++)
    {
        result[i] = (firstSummand[i] + secondSummand[i] + nextNumber) % 2;
        nextNumber = (firstSummand[i] + secondSummand[i] + nextNumber > 1 ? 1 : 0);
    }
}

void inversion(int inBinary[], int maxNumberOfDigits)
{
    for (int i = 0; i < maxNumberOfDigits; i++)
    {
        inBinary[i] = (inBinary[i] + 1) % 2;
    }
    int one[maxNumberOfDigits] = {0};
    one[0] = 1;
    int result[maxNumberOfDigits] = {0};
    additionOfNumbers(inBinary, one, result, maxNumberOfDigits);
    for (int i = 0; i < maxNumberOfDigits; i++)
    {
        inBinary[i] = result[i];
    }
}

void representationInBinary(int number, int inBinary[],  int maxNumberOfDigits)
{
    int i = 0;
    bool negative = number < 0;
    number = (negative ? -number : number);

    while (number > 0 && i < maxNumberOfDigits)
    {
        inBinary[i] = number % 2;
        number = number / 2;
        i++;
    }
    if (negative)
    {
        inversion(inBinary, maxNumberOfDigits);
    }
}

int numberInDemical(int numberInBinary[], int maxNumberOfDigits)
{
    int result = 0;
    int factor = 1;
    for (int i = 0; i < maxNumberOfDigits; i++)
    {
        result = result + numberInBinary[i] * factor;
        factor = factor * 2;
    }
    return result;
}

void printCouple(int demicalNumber, int binaryNumber[],  int maxNumberOfDigits)
{
    cout << demicalNumber << " (demical) = ";
    int i = maxNumberOfDigits - 1;
    bool beginOfNumber = false;
    while (i >= 0)
    {
        if (binaryNumber[i] == 1)
            beginOfNumber = true;
        if (beginOfNumber)
            cout << binaryNumber[i];
        i--;
    }
    if (!beginOfNumber)
        cout << 0;
    cout << " (binary)" << endl;
}

int main()
{
    cout << "Enter numbers in demical to get amout." << endl;
    int const maxNumberOfBinaryDigits = 32;
    int firstSummand = 0;
    int secondSummand = 0;
    cin >> firstSummand;
    cin >> secondSummand;
    int binaryFirstSummand[maxNumberOfBinaryDigits] = {0};
    int binarySecondSummand[maxNumberOfBinaryDigits] = {0};
    int resultInBinary[maxNumberOfBinaryDigits] = {0};
    representationInBinary(firstSummand, binaryFirstSummand, maxNumberOfBinaryDigits);
    representationInBinary(secondSummand, binarySecondSummand, maxNumberOfBinaryDigits);
    additionOfNumbers(binaryFirstSummand, binarySecondSummand, resultInBinary, maxNumberOfBinaryDigits);
    cout << "summands:" << endl;
    printCouple(firstSummand, binaryFirstSummand, maxNumberOfBinaryDigits);
    printCouple(secondSummand, binarySecondSummand, maxNumberOfBinaryDigits);
    cout << "result:" << endl;
    printCouple(numberInDemical(resultInBinary, maxNumberOfBinaryDigits), resultInBinary, maxNumberOfBinaryDigits);
    return 0;
}
