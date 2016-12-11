#include <stdio.h>

int main()
{
	int x = 0;
	printf("to calculate the value of an expression\n x^4+x^3+x^2+x+1\n enter the value of the variable x, please\n");
	scanf("%d", &x);
	int sqrX = x * x;
	int finalValue = (sqrX + x) * (sqrX + 1) + 1;
	printf("%d^4 + %d^3 + %d^2 + %d + 1 = %d\n", x, x, x, x, finalValue);
	return 0;	
}
