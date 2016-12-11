#include <stdio.h>

int amountOfNumbers(int threeDigits)
{
	int amount = 0;
	while (threeDigits > 0)
	{
		amount = amount + (threeDigits % 10);
		threeDigits = threeDigits / 10;
	}
	return amount;
}

int main()
{
	int amounts[27] = {0};
	for (int num = 0; num < 1000; num++)
	{
		amounts[amountOfNumbers(num)]++;
	}
	int luckyTicket = 0;
	for (int amountTicket = 0; amountTicket < 28; amountTicket++)
	{
		luckyTicket = luckyTicket + (amounts[amountTicket] * amounts[amountTicket]);
	}
	printf("there are %d lucky tickets", luckyTicket);
	return 0;
}
