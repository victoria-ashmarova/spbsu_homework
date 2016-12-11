#include <stdio.h>
#include <string.h>

const int size = 256;

int main()
{
	char line[size] = {'\0'};
	printf("To check the rule Nesting, please, write the string, which is shorter 255 liters.\n");
	fgets(line, size, stdin);
	int lenghtLine = strlen(line);
	int countBrackets = 0;
	bool negativeCountBrackets = false;
	for (int i = 0; i < lenghtLine; i++)
	{
		if (line[i] == '(')
			countBrackets++;
		if  (line[i] == ')')
			countBrackets--;
		if (countBrackets < 0)		
			negativeCountBrackets = true; 	
	}         
	if (countBrackets == 0 && ! negativeCountBrackets)
		printf("Rule Nesting is reformed in this string.\n");		
	if ((countBrackets != 0) || negativeCountBrackets)
		printf("Rule Nesting is violated in this string.\n");	
	return 0;		
}
