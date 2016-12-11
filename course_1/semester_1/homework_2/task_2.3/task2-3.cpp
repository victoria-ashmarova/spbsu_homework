#include <stdio.h>

void printTerms (int terms[], int size, int number)
{
		printf("%d = %d", number, terms[0]);
		for (int i = 1; i < size; i++)
			printf(" + %d", terms[i]);
		printf("\n");
}

void split(int remain, int terms[], int quantityOfReadyTerms, int number)
{
	if (remain <= 0)
	{
		printTerms(terms, quantityOfReadyTerms, number);
 		return;	
	}
	int start = 1;
	if (quantityOfReadyTerms > 0)
		start = terms[quantityOfReadyTerms - 1];
	for (int i = start; i <= remain; i++)
	{
		terms[quantityOfReadyTerms] = i;
		split(remain - i, terms, quantityOfReadyTerms + 1, number);
	}
}

int main()
{
	printf("Write the povitive number which will be respensered as sum.\n");
	int number = 0;
	scanf("%d", &number);
	int *terms = new int[number];
	split(number, terms, 0, number);
	delete []terms;
	return 0;
}
