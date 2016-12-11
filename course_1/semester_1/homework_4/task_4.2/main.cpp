#include <stdio.h>
#include <string.h>
#include "treatment.h"

int main()
{
    printf("There will be printed the words from the file.\n In this words there will be only the first letters.\n \n");
    int const maxSize = 256;
    char buffer[maxSize] = {'\0'};
    FILE *file = fopen("file.txt", "r");
    if (file == nullptr)
    {
        printf("Error.\n There is no file.\n");
        return 1;
    }
    while (NULL != fgets(buffer, maxSize, file))
    {
        treatment(buffer);
    }
    fclose(file);
    file = nullptr;
    return 0;
}
