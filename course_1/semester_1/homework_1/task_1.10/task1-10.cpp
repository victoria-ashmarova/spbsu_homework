
#include <stdio.h>
#include <string.h>

int main()
{
	char word[256] = {'\0'};
	printf("This program determines whether as the string as a palindrome.\nPlease, write the string, which s shorter than 255 liters\n");
	gets(word);
	int size = strlen(word);
	bool palindrome = true;
	for (int i = 0; i < size / 2; i++)
	{
		if (word[i] != word[size - 1 - i])
			palindrome = false;
	}
	if (palindrome)
		printf("This string is palindrome.\n");
	else
		printf("This string is not a palindrome.\n");
	return 0;		 
}
