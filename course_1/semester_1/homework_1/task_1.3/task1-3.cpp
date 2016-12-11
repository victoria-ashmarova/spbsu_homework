#include <stdio.h>

void swapping(int array[], int low, int high)
{
	int i = 0;
	while ((low + i) <= (high - i))
	{
		int t = array[low + i];
		array[low + i] = array[high - i];
		array[high - i] = t;
		i++;
	}
}

int main()
{
	printf("This program swaps two small parts of a big array.\nPlease, write volume of the first part of array.\n");
	int m = 0;
	scanf("%d", &m);
	printf("Please, write volume of the second part of array.\n");
	int n = 0;
	scanf("%d", &n);
	int *x = new int[m + n];	
	printf("please, write values of array's elementes.\n");
	for (int i = 0; i < (m + n); i++)
		scanf("%d", &x[i]);		 		
	swapping(x, 0, m - 1);
	swapping(x, m , m + n - 1); 
	swapping(x, 0, m + n - 1);
	printf("thank you, parts of the array are swapped.\n");
	for (int i = 0; i < (m + n); i++)
		printf("%d\n", x[i]);		 		
	delete []x;
	return 0;
}
