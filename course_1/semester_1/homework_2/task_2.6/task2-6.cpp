#include <stdio.h>
#include <stdlib.h>

int const size = 4;

int cows(int yourArray[], int myArray[])
{
	int count = 0;
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if ((i != j) && (yourArray[i] == myArray[j]))
				count++;
		}
	}
	return count;
}

int bulls(int yourArray[], int myArray[])
{
	int count = 0;
	for (int i = 0; i < size; i++)
	{
		if (yourArray[i] == myArray[i])
				count++;
	}
	return count;
}

void numberToArray(int number, int array[])
{
	for (int i = size - 1; i >= 0 ; i--)
	{
		array[i] = number % 10;
		number = number / 10;
	}
}

void myRandomNumber(int array[])
{
	bool illegalDigits[10] = {false};
	for (int i = 0; i < size; i++)
	{
		int newDigit = rand() % 10;
		while (illegalDigits[newDigit])
		{
			newDigit = rand() % 10;
		}
		array[i] = newDigit;
		illegalDigits[newDigit] = true;
	}
}

int main()
{
	printf("This program guesses a four-digit number with recurring digits.\n Your task is to guess it. You enter a four-digit number.\n The program reports the number of cows and bulls.\n To begin, enter the number.\n");
	int number = 0;
	scanf("%d", &number);
	srand(number);  //используется, чтобы при разных числах, введенных игроком, программа не загадывала одно и то же число
	
	int myArray[size] = {0};
	myRandomNumber(myArray); 
	int yourArray[size] = {0};
	numberToArray(number, yourArray);
	int attemptsCounter = 0;
	while (bulls(yourArray, myArray) != 4)
	{
		printf("cows %d bulls %d\n try again\n", cows(yourArray, myArray), bulls(yourArray, myArray));
		scanf("%d", &number);
		numberToArray(number, yourArray);
		attemptsCounter++;
	}
	printf("bulls %d\n You have won!\n Number were guessed by you at %d attemps.\n", bulls(yourArray, myArray), attemptsCounter + 1);
	return 0;
}
