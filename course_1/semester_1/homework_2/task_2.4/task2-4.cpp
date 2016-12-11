#include <stdio.h>

int greatestCommonDivisior(int a, int b)
{
	while (a != b)
	{
		if (a > b)
			 a = a - b;
		else
			b = b - a;
	}
	return a;
}

int leastCommonMultiple(int finalNumber)
{
	int composition = 1;
	for (int i = 2; i <= finalNumber; i++)
	{
			composition = composition * i / greatestCommonDivisior(i, composition);
	}
	return composition;
}

int main()
{
	printf("Please, write max denominator.\n");
	int maxDenominator = 0;
	scanf("%d", &maxDenominator);
	printf("There is evidence irreducible fractions between 0 and 1, which denominators do not exceed %d:\n", maxDenominator);
	int vertex = leastCommonMultiple(maxDenominator);
	for (int i = 1; i < vertex; i++)
	{
		int numerator = i / greatestCommonDivisior(i, vertex);
		int denominator = vertex / greatestCommonDivisior(i, vertex);
		if (denominator <= maxDenominator)
			printf("%d/%d  ", numerator, denominator);
	}
	return 0;
}


