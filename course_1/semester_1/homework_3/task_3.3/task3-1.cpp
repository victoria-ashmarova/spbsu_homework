#include <stdio.h>
#include<string.h>

int const maxSize = 256;

void swapSymbols(char &a, char &b)
{
	char t = a;
	a = b;
	b = t;
}

void quickSortSymbols(char string[], int begin, int end) 
{
	int i = begin;
	int j = end; 		
	int pivot = string[(begin + end) / 2];
	while (i <= j)
	{
   		while (string[i] < pivot) 
			i++;
    	while (string[j] > pivot)
			j--;
    	if (i <= j)
		{
			swapSymbols(string[i], string[j]);
    		i++;
			j--;
		}
 	}
	if (j > begin)
		quickSortSymbols(string, begin, j);
	if (end > i) 
		quickSortSymbols(string, i, end);
}

int main()
{
	printf("This promram checks ability to make one string with symbols of other string.\n Write the first string.\n");
	char theFirstString[maxSize] = {'\0'};
	fgets(theFirstString, maxSize, stdin);
	printf("Write the second string.\n");
	char theSecondString[maxSize] = {'\0'};
	fgets(theSecondString, maxSize, stdin);	
	if (strlen(theFirstString) == strlen(theSecondString))
	{
		int commonLenght = strlen(theFirstString) - 1;
		quickSortSymbols(theFirstString, 0, commonLenght);
		quickSortSymbols(theSecondString, 0, commonLenght);
		int i = 0;
		while ((theFirstString[i] == theSecondString[i]) && (i < commonLenght))
		{
			i++;
		}
		if (i == commonLenght)
			printf("It's possible to make the second string from the first string.\n");
		else
			printf("It's impossible to make the second string from the first string.\n There are unegual symbols in these strings.\n");				
	}		
	else
		printf("It's impossible to make the second string from the first string.\n Strings' lenghts are not equal.\n");
	return 0;
}
