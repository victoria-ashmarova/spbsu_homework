#include <stdio.h>

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

int main()
{
	printf("This program implements the quick sort of array elements.\n Please, write quantity of elements of array.\n");
	int size = 0;
	scanf("%d", &size);
	int *array = new int[size];
	printf("Please, write values of elements of array.\n Pass enter to write a value of next element.\n The elements will be sorted in ascending order.\n");
	for (int i = 0; i < size; i++)
		scanf("%d", &array[i]);
	quickSort(array, 0, size - 1);	
	printf("Elements are sorted.\n");
	for (int i = 0; i < size; i++)
		printf("%d\n", array[i]);
	delete []array;
	return 0;	
}
