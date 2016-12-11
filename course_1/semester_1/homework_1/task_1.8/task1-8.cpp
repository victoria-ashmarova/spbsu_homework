#include <stdio.h>

long int recursFactorial(int number)
{
	if (number == 0)
		return 1;
	else 
		return (number * recursFactorial(number - 1));		
}

long int iterationFactorial(int number)
{
	int i = 1;
	int factorial = 1;
	for (i = 1; i <= number; i++)
	{
		factorial = factorial * i;	
	}
	return factorial;
}

int main()
{
	printf("Now we are going try two use to ways of findind factorial.\n Please, enter the positive number, that you want to find factorial.\n");
	int number = 0;
	scanf("%d",&number);
	if (number < 0)
		printf("Sorry, we can't find factorial of a negative number.");
	else
	{
		long int factorial;
		factorial = recursFactorial(number);
		printf("Thank you.\n We have known, that %d!=%d with recursive function\n", number, factorial);
		factorial = iterationFactorial(number);
		printf("And now we cheked that %d!=%d with iteration function", number, factorial);
	}
	return 0;	
}
