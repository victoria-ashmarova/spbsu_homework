#include <stdio.h>

int main()
{
	printf("This program writes all primes, that are exseed some natural value.\n Please, enter the natural value.\n");
	int number =  0;
	scanf("%d", &number);
	if (number <= 1)
		printf("Error.\n There are not primes, that are exseed this value.\n");
	else
	{
		bool *prime = new bool[number];
		for (int i = 2; i <= number; i++)
		{
			prime[i] = 1;
		} 
		printf("There are primes, that are exseed than %d:\n", number);
		for (int i = 2; i <= number; i++)
		{
			if (prime[i])
				{
					for (int j = (i * 2); j <= number; j = j + i)
					{
						prime[j] = false;
					}
					printf("%d ", i);
				}	
		} 
		delete[] prime;
	}
	return 0;
} 
