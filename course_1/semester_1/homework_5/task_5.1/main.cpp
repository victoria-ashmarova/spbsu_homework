#include <stdio.h>
#include "sortedList.h"

enum {exit, addition, removal, print};

int main()
{
    printf("In this program you can work with the list of integer values.\nPress 0 to exit.\nPress 1 to add a value to list.\n Press 2 to remove a value.\n Press 3 to print the list.\n");
    List *list = createList();
    int action = 0;
    scanf("%d", &action);
    while (action != exit)
    {
        if (action == addition)
        {
            int value = 0;
            printf("Enter the value to add it in the list.\n");
            scanf("%d", &value);
            addElement(list, value);
        }

        if (action == removal)
        {
            if (isEmpty(list))
            {
                printf("It is impossible to remove value. List is empty.\n");
            }
            else
            {
                int value = 0;
                printf("Enter the value to remove it in the list.\n");
                scanf("%d", &value);
                removeElement(list, value);
            }
        }

        if (action == print)
        {
            printList(list);
        }

        printf("Chose the action, which you want to do with list again.\n");
        scanf("%d", &action);

    }
    deleteList(list);
    printf("You have finished work with list.\n");
    return 0;
}
