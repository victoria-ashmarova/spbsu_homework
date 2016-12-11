#include <stdio.h>

void swap(int &a, int &b)
{
	int t = a;
	a = b;
	b = t;
}

int searchOfMax(int numbers[], int begin, int size)
{
	int indexOfMax = begin;
	for (int i = begin; i < size; i++)
	{
		if (numbers[indexOfMax] < numbers[i])
			indexOfMax = i;
	}
	return indexOfMax;	
}

void fullArray(int numbers[], int size)
{
	printf("Enter the values of array's elements.\n Press enter to write new number.\n");
	for (int i = 0; i < size; i++)
	{
		scanf("%d", &numbers[i]);
	}
}

void sortAndSearch(int numbers[], int size)
{
	swap(numbers[searchOfMax(numbers, 0, size)], numbers[0]);
	int j = 0;
	while ((numbers[j] != numbers[j - 1]) && (j < size))
	{
		j++;
		int indexOfMax = searchOfMax(numbers, j, size);
		swap(numbers[indexOfMax], numbers[j]);
	}
	if (j == size)
		printf("Error.\n There aren't elemets with equal values in this array.\n");
	else 
		printf("Max element of array which value is in array more than one time is %d\n", numbers[j - 1]);
}

int main()
{
	printf("This program searches the max array's element, which value repeats in array.\n Enter the value of array's size.\n");
	int size = 0;
	scanf("%d", &size);
	int *numbers = new int[size];
	if (size < 2)
	{
		printf("Error\n The array is too little.\n");
		return 0;
	}
	fullArray(numbers, size);
	sortAndSearch(numbers, size);
	delete []numbers;	
	return 0;
}
