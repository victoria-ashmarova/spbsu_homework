#include <stdio.h>
#include <stdlib.h>

void swap(int &a, int &b)
{
    int temp = a;
    a = b;
    b = temp;
}

void sort(int numbers[], int size)
{
    for (int i = 3; i < size; i = i + 2)
    {
        int j = i;
        while (j > 1 && numbers[j - 2] > numbers[j])
        {
            swap(numbers[j], numbers[j - 2]);
            j = j - 2;
        }
    }
}

int generation()
{
    return rand() % 32 + 11;
}

void printArray(int numbers[], int size)
{
    for (int i = 0; i < size; i++)
        printf("%d\n", numbers[i]);
}

int main()
{
    printf("Please, write the size of array to sort even elements of it.\n The size of array must be more that 3.\n");
    int size = 0;
    scanf("%d", &size);
    if (size <= 3)
    {
        printf("Error.\n There is no reason to sort even elements of such array.\n");
        return 0;
    }
    srand(size);
    int *numbers = new int[size];
    printf ("enter the values of elements\n");
    for (int i = 0; i < size; i++)
    {
        scanf("%d", &numbers[i]);
    }
    sort(numbers, size);
    printf("Even elements of array are sorted.\n");
    printArray(numbers, size);
    for (int i = 0; i < size; i++)
    {
        numbers[i] = generation();
    }
    printf("Elements of array are generated.\n");
    printArray(numbers, size);
    sort(numbers, size);
    printf("Even elements of array are sorted.\n");
    printArray(numbers, size);
    delete []numbers;
    return 0;
}
