#include <stdio.h>
#include "tree.h"

enum {exit, addition, removal, check, inorder, preorder, postorder};

int enterValue()
{
    int value = 0;
    scanf("%d", &value);
    return value;
}

void printActions()
{
    printf("0 - to exit;\n 1 - to add new value;\n 2 - to remove value;\n 3 to check .\n 4 - to inorder\n 5 - to preorder\n 6 - to postorder\n");
}

void actionAddition(BinaryTree *tree)
{
    printf("Enter value to add.\n");
    int value = enterValue();
    addElement(tree, value);
}

void actionRemoval(BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        printf("There is no value to remove, because it is empty.\n");
        return;
    }

    printf("Enter value to remove.\n");
    int value = enterValue();
    removeElement(tree, value);
    printf("Value %d removed.\n", value);
    if (isEmpty(tree))
        printf("Now tree is empty");
}

void actionCheck(BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        printf("There is no value to check in tree, because it is empty.\n");
        return;
    }

    printf("Enter value to check.\n");
    int value = enterValue();
    if (isInTree(tree, value))
        printf("Value %d is in tree.\n", value);
    else
        printf("Value %d is not in tree.\n", value);
}

void workWithTree(BinaryTree *tree)
{
    int action = enterValue();
    while (action != exit)
    {
        switch (action)
        {
            case addition:
                actionAddition(tree);
            break;

            case removal:
                actionRemoval(tree);
            break;

            case check:
                actionCheck(tree);
            break;

            case inorder:
                inorderTree(tree);
            break;

            case preorder:
                preorderTree(tree);
            break;

            case postorder:
                postorderTree(tree);
            break;
        }
        printf("\nEnter the number of the action again.\n");
        printActions();
        scanf("%d", &action);
    }
}

int main()
{
    printf("In this program you can work with the binary search tree.\n Enter the number of action.\n");
    printActions();
    BinaryTree *tree = createTree();
    workWithTree(tree);
    deleteTree(tree);
    printf("You have finished the work with the binary search tree.\n");
    return 0;
}
