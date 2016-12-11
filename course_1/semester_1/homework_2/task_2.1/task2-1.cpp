#include <stdio.h>

int recursionFibonachy(int number)
{
	if ((number == 0) or (number == 1))
		return 1;
	else
	{
		printf("%d = %d + %d\n", number, number - 1, number - 2);
		return recursionFibonachy(number - 1) + recursionFibonachy(number - 2);
	}	
}

int iterationFibonachy(int number)
{
	int seniorTerm = 1;
	int juniorTerm = 0;
	for (int i = 0; i < number; i++)
	{
		printf("%d + %d = %d\n", juniorTerm, seniorTerm , juniorTerm + seniorTerm);
		seniorTerm = seniorTerm + juniorTerm;
		juniorTerm = seniorTerm - juniorTerm;
	}
	return seniorTerm;
}

int main()
{
	printf("Write number of the member of Fibonachy secuence, which we are going to compute.\n");
	int number = 0;
	scanf("%d", &number);
	int numberFibonachy = 0;
	printf("Implement count of this number Fibonachy with recurcion.\n");
	numberFibonachy = recursionFibonachy(number);
	printf("%d is %d member of Fibonachy sequence.\n", numberFibonachy, number);
	printf("Implement count of this number Fibonachy with iteration.\n");
	numberFibonachy = iterationFibonachy(number);
	printf("%d is %d member of Fibonachy sequence.\n", numberFibonachy, number);
	return 0;
}
