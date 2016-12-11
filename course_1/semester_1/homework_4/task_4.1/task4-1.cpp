#include <stdio.h>

void fullMatrix(int **table, int size)
{
	printf("enter the values of matrix elements.\n");	
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			scanf("%d", &table[i][j]);
		}
	}
}

void counterClockWise(int **table, int half)
{
	printf("Elements are printed in a counter clock-wise direction.\n");
	printf("%d ", table[half][half]);
	int distance = 0;
	while (distance < half)
	{
		distance++;
		for (int i = half - distance + 1; i < half + distance; i++)
			printf("%d ", table[i][half - distance]);	
		for (int i = half - distance; i < half + distance; i++)
			printf("%d ", table[half + distance][i]);	
		for (int i = half + distance; i > half - distance; i--)
			printf("%d ", table[i][half + distance]);
		for (int i = half + distance; i >= half - distance; i--)
			printf("%d ", table[half - distance][i]);			
	}
	printf("\n");
}

void clockWise(int **table, int half)
{
	printf("Elements are printed in a clock-wise direction.\n");
	printf("%d ", table[half][half]);
	int distance = 0;
	while (distance < half)
	{
		distance++;
		for (int i = half - distance + 1; i > half - distance; i--)
			printf("%d ", table[i][half - distance]);	
		for (int i = half - distance; i < half + distance; i++)
			printf("%d ", table[half - distance][i]);	
		for (int i = half - distance; i < half + distance; i++)
			printf("%d ", table[i][half + distance]);
		for (int i = half + distance; i >= half - distance; i--)
			printf("%d ", table[half + distance][i]);			
	}
	printf("\n");
}

int main()
{
	printf("This promram prints the matrix elements in a spiral.\nEnter the size of matrix - the odd number.\n");
	int size = 0;
	scanf("%d", &size);
	if (size % 2 == 0)
	{
		printf("Error.\n The even value of matrix size.\n");
		return 0;
	}
	int **table = new int *[size];
	for (int i = 0; i < size; i++)
		table[i] = new int[size];
	fullMatrix(table, size);	
	counterClockWise(table, size / 2);
	clockWise(table, size / 2);
	for (int i = 0; i < size; i++)
		delete []table[i];
	delete []table;		
	return 0;
}
