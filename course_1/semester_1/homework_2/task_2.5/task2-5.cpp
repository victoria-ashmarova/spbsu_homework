#include <stdio.h>
#include <stdlib.h>

void siftDown(int arrayTree[], int size)
{
	int parent = (size - 1) / 2;
	int child = size;
	int t = 0;
	for (int i = size; i > 0; i--)
	{
		child = i;
	 	parent = (child - 1) / 2;
		if ((arrayTree[child] > arrayTree[parent]))
		{
			t = arrayTree[child];
			arrayTree[child] = arrayTree[parent];
			arrayTree[parent] = t;
			child = parent;
		}
	}
} 

void heapSort(int array[], int size) 
{
	int t = 0;
	siftDown(array, size - 1);
	for (int i = size - 1; i >= 1; i--)
	{
  		t = array[0];
 	   	array[0] = array[i];
   		array[i] = t;
   		siftDown(array, i - 1);
	}
}

int main() 
{
	printf("This program implements a pyramid sort of array elements.\n Please enter the number of elements.\n");
	int size = 0;
	scanf("%d", &size);
	int *array = new int[size];
	printf("Write the valurs of elements.\n");
	for(int i = 0; i < size; i++)
		scanf("%d", &array[i]);
	heapSort(array, size);
	printf("Ready! Elements are sorted!\n");
	for(int i = 0; i < size; i++)
		printf("%d\n", array[i]);
	delete[] array;	
	return 0;
} 
