#include <stdio.h>
#include "circularlist.h"

int main()
{
    printf("There are n warrior in circle, every warrior with number m will be killed.\n This program searches the number of surviwor warrior.\nEnter the value of n.\n");
    int n = 0;
    scanf("%d", &n);
    CircularList *list = createList();
    for (int i = 1; i <= n; i++)
    {
        addElement(list, i);
    }
    printf("Enter the value of m.\n");
    int m = 0;
    scanf("%d", &m);
    while (sizeOfList(list) > 1)
    {
        bool doNotChangeHead = isHead(list, m) || isHead(list, m + 1);
        removeElement(list, m);
        if (!doNotChangeHead)
            changeHead(list, m % (sizeOfList(list) + 1));
    }
    int surviver = getValue(list, 1);
    printf("The number of survivor warrior is %d.\n", surviver);
    deleteCircularList(list);
    return 0;
}
