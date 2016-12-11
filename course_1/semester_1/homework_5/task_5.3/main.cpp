#include <stdio.h>
#include "queue.h"

void treatment(char buffer[], Queue *comments, bool &noBigComment, bool &noStringComments)
{
    int i = 0;
    bool commentHasBegan = false;
    bool quotesAreClosed = true;

    while (buffer[i] != '\0')
    {
        if (buffer[i] == '"')
            quotesAreClosed = !quotesAreClosed;

        if (quotesAreClosed && !commentHasBegan)
        {
            if (buffer[i] == '\\' && buffer[i + 1] == '*')
                noBigComment = false;

            if (buffer[i] == '*' && buffer[i + 1] == '\\' && !noBigComment)
                noBigComment = true;

            if (buffer[i] == '\\' && buffer[i + 1] == '\\' && buffer[i + 2] != '\n' && noBigComment)
                commentHasBegan = true;
        }

        if(commentHasBegan)
            addElement(comments, buffer[i]);

        i++;
    }

    if (!isEmpty(comments))
    {
        noStringComments = false;
    }

    printQueue(comments);
    clearQueue(comments);
}

int main()
{
    printf("This program prints on console single-line comments from file.\n");
    int const maxSize = 256;
    char buffer[maxSize] = {'\0'};
    FILE *file = fopen("file.txt", "r");
    if (file == nullptr)
    {
        printf("Error.\n There is no file.\n");
        return 1;
    }

    Queue *comments = createQueue();
    bool noBigComment = true;
    bool noStringComments = true;
    while (NULL != fgets(buffer, maxSize, file))
    {
        treatment(buffer, comments, noBigComment, noStringComments);
    }

    if (noStringComments)
        printf("There is single-line comments in file.\n");

    fclose(file);
    file = nullptr;
    deleteQueue(comments);
    return 0;
}
