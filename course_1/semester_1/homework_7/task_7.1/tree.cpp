#include <stdio.h>
#include "tree.h"

struct Node
{
    int value;
    Node *rightChild;
    Node *leftChild;
};

struct BinaryTree
{
    Node *root;
    int size;
};

BinaryTree *createTree()
{
    BinaryTree *newTree = new BinaryTree;
    newTree->root = nullptr;
    newTree->size = 0;
    return newTree;
}

void clearTree(BinaryTree *tree)
{
    if (isEmpty(tree))
        return;

    while (!isEmpty(tree))
    {
        int deletedValue = tree->root->value;
        removeElement(tree, deletedValue);
    }
}

void deleteTree(BinaryTree *tree)
{
    clearTree(tree);
    tree->root = nullptr;
    delete tree;
    tree = nullptr;
}

int sizeTree(BinaryTree *tree)
{
    return tree->size;
}

bool isEmpty(BinaryTree *tree)
{
    return sizeTree(tree) == 0;
}

Node *createNode(int value)
{
    Node *newNode = new Node;
    newNode->leftChild = nullptr;
    newNode->rightChild = nullptr;
    newNode->value = value;
    return newNode;
}

Node *getParent(Node *current, int value)
{
    if (current->value > value)
    {
        if (current->leftChild == nullptr || current->leftChild->value == value)
            return current;

        return getParent(current->leftChild, value);
    }

    if (current->value <= value)
    {
        if(current->rightChild == nullptr || current->rightChild->value == value)
            return current;

        return getParent(current->rightChild, value);
    }
}

void addElement(BinaryTree *tree, int value)
{
    if (isEmpty(tree))
    {
        tree->root = createNode(value);
        tree->size++;
        return;
    }

    if (isInTree(tree, value))
    {
        printf("Couldn't add the value %d.\n value is in tree.\n", value);
        return;
    }

    Node *temp = getParent(tree->root, value);

    if (temp->value > value)
        temp->leftChild = createNode(value);

    if(temp->value < value)
        temp->rightChild = createNode(value);

    tree->size++;

    printf("Value %d added.\n", value);
}

Node *getNodeToDelete(Node *current, int value)
{
    if (current == nullptr)
        return current;

    if (current->value == value)
        return current;

    if (current->value > value)
    {
        return current->leftChild;
    }

    if (current->value < value)
    {
        return current->rightChild;
    }
}

void deleteNode(Node *toDelete)
{
    toDelete->leftChild = nullptr;
    toDelete->rightChild = nullptr;
    delete toDelete;
    toDelete = nullptr;
}

bool childFree(Node *node)
{
    return node->leftChild == nullptr && node->rightChild == nullptr;
}

bool onlyLeft(Node *node)
{
    return node->leftChild != nullptr &&  node->rightChild == nullptr;
}

bool onlyRight(Node *node)
{
    return node->leftChild == nullptr &&  node->rightChild != nullptr;
}

bool twoChildren(Node *node)
{
    return node->leftChild != nullptr && node->rightChild != nullptr;
}

void removeRoot(BinaryTree *tree)
{
    Node *toDelete = tree->root;

    if (childFree(tree->root))
    {
        deleteNode(toDelete);
        tree->root = nullptr;
        tree->size--;
        return;
    }

    if (onlyLeft(tree->root))
    {
        tree->root = tree->root->leftChild;
        deleteNode(toDelete);
        tree->size--;
        return;
    }

    if (onlyRight(tree->root))
    {
        tree->root = tree->root->rightChild;
        deleteNode(toDelete);
        tree->size--;
        return;
    }

    if (twoChildren(tree->root))
    {
        Node *temp = tree->root->rightChild;
        tree->root = tree->root->leftChild;
        int withoutParent = temp->value;
        Node *newParent = getParent(tree->root, withoutParent);
        newParent->rightChild = temp;
        deleteNode(toDelete);
        tree->size--;
        return;
    }
}

void removeChildFree(Node *toDelete, Node *parent)
{
    if (onlyLeft(parent))
    {
        parent->leftChild = nullptr;
    }
    if (onlyRight(parent))
    {
        parent->rightChild = nullptr;
    }
    if (twoChildren(parent))
    {
        if (toDelete->value == parent->leftChild->value)
            parent->leftChild = nullptr;

        if (toDelete->value == parent->rightChild->value)
            parent->rightChild = nullptr;
    }

    deleteNode(toDelete);
}

void removeWithOnlyLeftChild(Node *toDelete, Node *parent)
{
    if (parent->value > toDelete->value)
    {
        parent->leftChild = toDelete->leftChild;
    }
    if (parent->value < toDelete->value)
    {
        parent->rightChild = toDelete->leftChild;
    }
    deleteNode(toDelete);
}

void removeWithOnlyRightChild(Node *toDelete, Node *parent)
{
    if (parent->value > toDelete->value)
    {
        parent->leftChild = toDelete->rightChild;
    }
    if (parent->value < toDelete->value)
    {
        parent->rightChild = toDelete->rightChild;
    }
    deleteNode(toDelete);
}

void removeWihtTwoChildren(Node *toDelete, Node *parent, int value)
{
    Node *temp = toDelete->rightChild;
    if (parent->value > value)
    {
        parent->leftChild = toDelete->leftChild;
    }
    if (parent->value < value)
    {
        parent->rightChild = toDelete->leftChild;
    }
    int withoutParent = temp->value;
    Node *newParent = getParent(parent, withoutParent);
    newParent->rightChild = temp;
    deleteNode(toDelete);
}

void removeElement(BinaryTree *tree, int value)
{
    if (isEmpty(tree))
        return;

    if (!isInTree(tree, value))
    {
        printf("Couldn't remove the value %d. There is not in tree\n", value);
        return;
    }

    if (value == tree->root->value)
    {
        removeRoot(tree);
        return;
    }

    Node *toDelete = nullptr;
    Node *parent = getParent(tree->root, value);

    if (parent->value > value)
    {
        toDelete = parent->leftChild;
    }
    if (parent->value < value)
    {
        toDelete = parent->rightChild;
    }

    if (childFree(toDelete))
    {
        removeChildFree(toDelete, parent);
        tree->size--;
        return;
    }

    if (onlyLeft(toDelete))
    {
        removeWithOnlyLeftChild(toDelete, parent);
        tree->size--;
        return;
    }

    if (onlyRight(toDelete))
    {
        removeWithOnlyRightChild(toDelete, parent);
        tree->size--;
        return;
    }

    if (twoChildren(toDelete))
    {
        removeWihtTwoChildren(toDelete, parent, value);
        tree->size--;
        return;
    }
}

bool isEqual(Node *current, int value)
{
    if (current == nullptr)
        return false;

    if (current->value == value)
        return true;

    if (current->value > value)
        return isEqual(current->leftChild, value);

    if (current->value < value)
        return isEqual(current->rightChild, value);
}

bool isInTree(BinaryTree *tree, int value)
{
    if (isEmpty(tree))
        return false;

    return isEqual(tree->root, value);
}

void printValueOfNode(Node *current)
{
    int toPrint = current->value;
    printf("%d ", toPrint);
}

void partOfInorder(Node *current)
{
    if (current == nullptr)
        return;

    partOfInorder(current->leftChild);
    printValueOfNode(current);
    partOfInorder(current->rightChild);
}

void inorderTree(BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        printf("Couldn't order tree.\n tree is empty.\n");
        return;
    }

    printf("Inorder: \n");
    partOfInorder(tree->root);
}

void partOfPreorder(Node *current)
{
    if (current == nullptr)
    {
        printf("null ");
        return;
    }

    printf("(");
    printValueOfNode(current);
    partOfPreorder(current->leftChild);
    partOfPreorder(current->rightChild);
    printf(")");
}

void preorderTree(BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        printf("Couldn't order tree.\n tree is empty.\n");
        return;
    }

    printf("Preorder:\n");
    partOfPreorder(tree->root);
}

void partOfPostorder(Node *current)
{
    if (current == nullptr)
        return;

    partOfPostorder(current->rightChild);
    printValueOfNode(current);
    partOfPostorder(current->leftChild);
}

void postorderTree(BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        printf("Couldn't order tree.\n tree is empty.\n");
        return;
    }

    printf("Postorder:\n");
    partOfPostorder(tree->root);
}
