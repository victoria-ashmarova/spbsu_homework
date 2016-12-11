#include <stdio.h>

int numeralConstructor(int numerals[])
{
	int newNumeral = 0;
	int i = 1;
	while (numerals[i] == 0)
		i++;
	newNumeral = i;
	numerals[i]--;
	for (int i = 0; i < 10; i++)
	{
		while (numerals[i] > 0)
		{
			newNumeral = newNumeral * 10 + i;
			numerals[i]--;
		}
	}
	return newNumeral;	
}

int main()
{
	printf("Enter the positive number.\n");
	int beginNumber = 0;
	scanf("%d", &beginNumber);
	int numerals[10] = {0};
	while (beginNumber > 0)
	{
		int numeral = beginNumber % 10;
		numerals[numeral]++;
		beginNumber = beginNumber / 10;
	}
	printf("the lowest number recorded by the same numerals is %d.\n", numeralConstructor(numerals));
}
