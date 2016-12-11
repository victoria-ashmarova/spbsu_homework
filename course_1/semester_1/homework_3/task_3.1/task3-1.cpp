#include <stdio.h>

int notNegativePower(int a, int n)
{

	int result = 1;
	while (n > 0)
	{
		if (n % 2 == 1)
		{
			result = result * a;		
		}
		a = a * a;
		n = n / 2;
	}
	return result;
}

float negativePower(int a, int n)
{
	n = n * (-1);
	return 1.0 / (notNegativePower(a, n));
}

int main()
{

	printf("To calculate the entire power of the number, please, write the value of number...\n");
	int a = 0;
	scanf("%d", &a);
	printf("And write the value of power.\n");
	int n = 0;
	scanf("%d", &n);
	if (n >= 0)
	{
		printf("%d^%d = %d", a, n, notNegativePower(a, n));
	}
	else
	{
		printf("%d^(%d) = %g", a, n, negativePower(a, n));
	}
	return 0;
}
