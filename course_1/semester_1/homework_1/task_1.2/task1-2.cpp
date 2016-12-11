#include <stdio.h>

int quotionPositivePositive(int a, int b)
{
	int partialQuotion = 0;
	while (a >= b)
		{
			a = a - b;
			partialQuotion++;
		}
		return partialQuotion;
}

int quotionNegativePositive(int a, int b)
{
	int partialQuotion = 0;
	while (a < 0)
		{
			a = a + b;
			partialQuotion--;
		}
		return partialQuotion;
}

int quotionPositiveNegative(int a, int b)
{
	int partialQuotion = 0;
	while (a >= (b * (-1)))
		{
			a = a + b;
			partialQuotion--;
		}
		return partialQuotion;
}

int quotionNegativeNegative(int a, int b)
{
	int partialQuotion = 0;
	while (a  <  0)
		{
			a = a - b;
			partialQuotion++;
		}
		return partialQuotion;
}

int main()
{
	int a = 0;
	int b = 0;
	int resault = 0;
	printf("to calculate partial quotion of values a and b\n write variable of a, pass enter and write variable b, please\n");
	scanf("%d%d", &a, &b);
	if (b == 0)
		printf("Error.\n Sorry, it's impossible to find partial quotion obtained by dividing number by zero\n");
	else
	{	
		if (a == 0) 
			resault = 0;
		if ((a > 0) && (b > 0))
			resault = quotionPositivePositive(a, b);
		if ((a > 0) && (b < 0))
			resault = quotionPositiveNegative(a, b);
		if ((a < 0) && (b > 0))
			resault = quotionNegativePositive(a, b);
		if ((a < 0) && (b < 0))
			resault = quotionNegativeNegative(a, b);
		printf("partial quotiot of %d and %d is %d", a, b, resault);	
	}
	return 0;
}
