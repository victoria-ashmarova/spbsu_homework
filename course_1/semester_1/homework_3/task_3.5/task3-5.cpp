#include <stdio.h>
#include <stdlib.h>

void swap(int &a, int &b)
{
	int t = a;
	a = b;
	b = t;
}

void quickSort(int array[], int begin, int end) 
{
	int i = begin;
	int j = end; 		
	int pivot = array[(begin + end) / 2];
	while (i <= j)
	{
   		while (array[i] < pivot) 
			i++;
	   	while (array[j] > pivot)
			j--;
	 	if (i <= j)
		{
			swap(array[i], array[j]);
			i++;
			j--;
   		}
 	}
	if (j > begin)
		quickSort(array, begin, j);
	if (end > i) 
		quickSort(array, i, end);
}

int threeStepsGeneration()
{
	int const steps = 3;
	int value = 0;
	for (int i = 0; i < steps; i++)
	{
		value = value * 1000 + rand() % 1000;
	}
	return value;
}

bool binarySearch(int array[], int begin, int end, int valueToSearch)
{
	int centre = (begin + end) / 2;
	if (array[centre] == valueToSearch)
	{
		return true;
	}
	else
	{
		if (begin >= end)
			return false;
		if (valueToSearch < array[centre])
			binarySearch(array, begin, centre - 1, valueToSearch);
		else
			binarySearch(array, centre + 1, end, valueToSearch);	
	}
}

void test()
{
	int const size = 5;
	int const valueToSearch = 3;
	int array[size] = {6, 7, 1, 4, valueToSearch};
	quickSort(array, 0, size - 1);
	if (binarySearch(array, 0, size - 1, valueToSearch))
		printf("test is complited\n");
	else
		printf("test is failed\n");	
}

int main()
{
	printf("The program checks whether there are generated numbers in the generated array.\n");
	test();
	printf("Enter the size of array.\n It must be more then 0 and not more then 5000\n");
	int n = 0;
	scanf("%d", &n);
	if ((n <= 0) || (n > 5000))
	{
		printf("Error.\n Inapropriate value of array's size.\n");
		return 0;
	}
	srand(n);
	int *blockOfNumbers = new int[n];
	if (n)
	for (int i = 0; i < n; i++)
	{
		blockOfNumbers[i] = threeStepsGeneration();
	}
	quickSort(blockOfNumbers, 0, n - 1);
	printf("There are values of generated array elements\n ");
	for (int i = 0; i < n; i++)
	{
		printf("%d\n", blockOfNumbers[i]);
	}
	printf("Enter the quality of numbers.\nIt must be more then 0 and not more then 300000\n");
	int k = 0;
	scanf("%d", &k);
	if ((k <= 0) || (k > 300000))
	{
		printf("Error.\n Inapropriate value of quality of elements.\n");
		delete []blockOfNumbers;
		return 0;
	}
	for (int i = 0; i < k; i++)
	{
		int currentValue = threeStepsGeneration();
		if (binarySearch(blockOfNumbers, 0, n - 1, currentValue))
			printf("%d is in array.\n", currentValue);
		else
			printf("%d is not in array.\n", currentValue);	
	}
	delete []blockOfNumbers;
	return 0;
}
