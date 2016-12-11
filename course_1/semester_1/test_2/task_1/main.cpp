#include <iostream>

using namespace std;

int main()
{
    cout << "Enter the number of Fibonaccy number" << endl;
    int i = 0;
    cin >> i;

    while (i <= 0)
    {
        cout << "Number must be positive, enter number again." << endl;
        cin >> i;
    }

    int prev = 0;
    int current = 1;

    if (i == 1)
    {
        cout << "Value of " << i << " is " << prev << "." <<endl;
        return 0;
    }

    if (i == 2)
    {
        cout << "Value of " << i << " is " << current << "." <<endl;
        return 0;
    }

    for (int count = 3; count <= i; count++)
    {
        int newNumber = prev + current;
        prev = current;
        current = newNumber;
    }

    cout << "Value of " << i << " Fibonaccy number is " << current << "." <<endl;

    return 0;
}
