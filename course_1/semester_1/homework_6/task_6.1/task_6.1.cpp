#include <iostream>
#include <inttypes.h>

using namespace std;

uint64_t getBitOnPosition(uint64_t value, int position)
{
    return (value >> position) & 1;
}

void printBinary(uint64_t value)
{
    for (int i = 63; i >= 0; i--)
    {
        cout << (getBitOnPosition(value, i) == 0 ? '0' : '1');
    }
    cout << endl;
}

uint64_t extract(uint64_t source, int begin, int end)
{
    uint64_t result = source >> end;

    uint64_t mask = 0;
    for (int i = 0; i <= begin - end; i++)
    {
        mask |= ((uint64_t) 1) << i;
    }
    result &= mask;
    return result;
}

double makeMantissa(uint64_t value)
{
    double result = 0;
    double summand = 0.5;
    for (int i = 51; i >= 0; i--)
    {
        if ((getBitOnPosition(value, i) & 1) == 1)
        {
            result = result + summand;
        }
        summand = summand / 2;
    }
    return result + 1;
}

int main()
{
    cout.precision(16);

    double enteredValue = 0;
    cout << "Enter value in double type." << endl;
    cin >> enteredValue;
    double *doublePoint = &enteredValue;
    uint64_t *integerPoint = reinterpret_cast<uint64_t*>(doublePoint);
    uint64_t integerValue = *integerPoint;

    uint64_t sigh = extract(integerValue, 63, 63);
    uint64_t power = extract(integerValue, 62, 52);
    uint64_t mantissa = extract(integerValue, 51, 0);
    double doubleMantissa = makeMantissa(mantissa);
    int64_t intPower = (int64_t) power - 1023;

    cout << "Result: " << (sigh == 0 ? '+' : '-') << doubleMantissa << "*2^" << intPower << endl;
    return 0;
}
