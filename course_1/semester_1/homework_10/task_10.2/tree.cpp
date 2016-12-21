#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

struct Node
{
    char symbol;
    Node *left;
    Node *right;
};

struct Tree
{
    Node *root;
};

Tree *createTree()
{
    Tree *tree = new Tree;
    tree->root = nullptr;
    return tree;
}

Node *createNode(char symbol, Node *left, Node *right)
{
    Node *node = new Node;
    node->symbol = symbol;
    node->left = left;
    node->right = right;
    return node;
}

Node *createDoubler()
{
    return createNode('\0', nullptr, nullptr);
}

bool nodeIsFree(Node *node)
{
    if (node == nullptr)
        return false;

    if (node->symbol == '\0' && node->left == nullptr)
        return true;

    return node->symbol == '\0' && node->left->symbol != '\0' && node->right == nullptr;
}

bool nodeIsLitera(Node *node)
{
    if (node == nullptr)
        return false;

    return node->symbol != '\0';
}

bool nodeHasLeftPlace(Node *node)
{
    if (node == nullptr)
        return false;

    if (node->left == nullptr)
        return false;

    return node->symbol == '\0' && node->left->symbol == '\0';
}

bool nodeHasRightPlace(Node *node)
{
    if (node == nullptr)
        return false;

    if (node->right == nullptr)
        return false;

    return node->symbol == '\0' && node->right->symbol == '\0';
}

Node *getNeartFreeNode(Node *node)
{
    if (nodeIsFree(node))
        return node;

    if (nodeIsLitera(node))
        return nullptr;

    Node *toReturn = nullptr;
    if(nodeHasLeftPlace(node))
        toReturn = getNeartFreeNode(node->left);

    if (toReturn == nullptr)
    {
        if (node->right == nullptr && node->symbol == '\0')
            return node;

        if(nodeHasRightPlace(node))
            toReturn = getNeartFreeNode(node->right);
    }
    return toReturn;
}

Node *getNeartFreeNode(Tree *tree)
{
    if (tree->root == nullptr)
        return nullptr;

    return getNeartFreeNode(tree->root);
}

void addNewNode(Node *parent, char toAdd)
{
    if (parent == nullptr)
        return;

    if (parent->left == nullptr)
    {
        parent->left = createNode(toAdd, nullptr, nullptr);
    }
    else
    {
        parent->right = createNode(toAdd, nullptr, nullptr);
    }
}

bool isSpace(char line[], int index)
{
    if (index == 0)
        return false;
    bool spaceSymbol = (line[index] == ' ' && line[index + 1] == ' '  && (line[index - 1] == '(' || line[index - 1] == ' '));
    return line[index] == ' ' && !spaceSymbol;
}

void fullTree(ifstream &file, Tree *tree)
{
    int const sizeLine = 512;
    char lineTree[sizeLine] = {'\0'};
    file.getline(lineTree, sizeLine);
    int i = 0;
    while (i < sizeLine &&  lineTree[i] != '\0')
    {
        Node *place = getNeartFreeNode(tree);
        if (place == nullptr)
        {
            if (tree->root == nullptr)
            {
               tree->root = createDoubler();
            }
        }
        else
        {
            if (lineTree[i] == '(')
            {
                addNewNode(place, '\0');
            }
            else
            {
                if (!isSpace(lineTree, i) && lineTree[i] != ')')
                {
                    addNewNode(place, lineTree[i]);
                }
            }
        }
        i++;
    }
}

void clearNode(Node *node)
{
    if (node == nullptr)
        return;

    if (node->left == nullptr && node->right == nullptr)
    {
        delete node;
        node = nullptr;
        return;
    }

    if (node->left != nullptr)
        clearNode(node->left);

    if (node->right != nullptr)
        clearNode(node->right);
}


void deleteTree(Tree *tree)
{
    clearNode(tree->root);
    delete tree;
    tree = nullptr;
}

void decode(Tree *tree, ifstream &in, ofstream &out)
{
    int const sizeLine = 512;
    char lineCode[sizeLine] = {'\0'};
    in.getline(lineCode, sizeLine);
    int i = 0;
    {
        while (i < sizeLine && lineCode[i] != '\0')
        {
            Node *temp = tree->root;
            while (!nodeIsLitera(temp) && i < sizeLine && lineCode[i] != '\0')
            {
                switch (lineCode[i])
                {
                    case '0':
                        temp = temp->left;
                    break;

                    case '1':
                        temp = temp->right;
                    break;
                }
                i++;
            }
            if (temp != nullptr)
            {
                out << temp->symbol;
            }
        }
    }
}
