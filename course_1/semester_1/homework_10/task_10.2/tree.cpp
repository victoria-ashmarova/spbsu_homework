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

char getCurrent(char *line, int &index)
{
    if (line[index] == ' ')
        return '\0';

    if (line[index] != '\'')
        return line[index];

    if (line[index + 1] == '\'')
    {
        index++;
        return ' ';
    }

    if (line[index + 1] == '(')
    {
        index = index + 2;
        return '(';
    }

    if (line[index + 1] == ')')
    {
        index = index + 2;
        return ')';
    }

    if (line[index + 1] == ' ')
    {
        return '\'';
    }

    char element = line[index + 2];
    index = index + 3;

    if (element == 't')
        return '\t';

    if (element == 'n')
        return '\n';

    return '\0';
}

void fullTree(ifstream &file, Tree *tree)
{
    int const sizeLine = 1024;
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
            char lineElement = lineTree[i];
            char current = getCurrent(lineTree, i);
            if (lineElement == '(')
            {
                addNewNode(place, '\0');
            }
            else
            {
                if (current != '\0' && lineElement != ')')
                {
                    addNewNode(place, current);
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
    char current = '\0';
    while (!in.eof())
    {
        Node *temp = tree->root;
        while (!nodeIsLitera(temp) && temp != nullptr && !in.eof())
        {
            in >> current;
            switch (current)
            {
                case '0':
                    temp = temp->left;
                break;

                case '1':
                    temp = temp->right;
                break;
            }
        }
        if (temp != nullptr)
        {
            out << temp->symbol;
        }
    }
}
