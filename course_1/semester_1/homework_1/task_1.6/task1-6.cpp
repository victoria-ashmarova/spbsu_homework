#include <stdio.h>
#include <string.h>

const int size = 256;

bool isIncluded(char needle[], char haystack[], int beginning)
{
	int i = 0;
	while ((needle[i] == haystack[beginning + i]) && (needle[i + 1] != '\0'))
	{
		i++;
	}	 
	return i == strlen(needle) - 1;	 		
}

int main()
{
	printf("Please, write the string haystack, which doesn't exseed 255 liters.\n");
	char haystack[size] = {'\0'};
	fgets(haystack, size, stdin);	
	printf("Please, write the string needle, which doesn't exseed the lenght of the string haystack.\n Now we are going to try to search the needle in the haystack.\n");
	char needle[size] = {'\0'};
	fgets(needle, size, stdin);
	int lengthDifference = (strlen(haystack) - strlen(needle));
	if (lengthDifference < 0)
	{
		printf("Error!\n The string haystack doesn't include the string needle, because it is shorter than the string needle.\n");	
	}
	else
	{
		int numberOfInclusions = 0;
		for (int i = 0; i <= lengthDifference; i++)
		{
			if (haystack[i] == needle[0])
			{
				 if (isIncluded(needle, haystack, i))
			 		numberOfInclusions++;
			}	
		}	
	printf("The string needle is in the srting haystack %d times.\n", numberOfInclusions);	
    }
	return 0;
}
