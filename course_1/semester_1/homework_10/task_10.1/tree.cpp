#include <iostream>
#include <fstream>
#include "tree.h"
#include "lists.h"

using namespace std;

struct Node
{
    char litera;
    int variable;
    Node *left;
    Node *right;
};

struct ElementOfQueue
{
    int amount;
    Node *node;
    ElementOfQueue *next;
};

struct Queue
{
    int size;
    ElementOfQueue *first;
    ElementOfQueue *last;
};

struct Buffer
{
    List *list;
    int size;
};

Buffer *createBuffer()
{
    Buffer *buffer = new Buffer;
    buffer->list = createList();
    buffer->size = 0;
    return buffer;
}

char getSymbol(Buffer *buffer, int number)
{
    return getSymbolFromTree(buffer->list, number);
}

void deleteBuffer(Buffer *buffer)
{
    clearAndDeleteList(buffer->list);
    buffer->list = nullptr;
    buffer->size = 0;
    delete buffer;
    buffer = nullptr;
}

void addElementToBuffer(Buffer *buffer, char symbol, int placeToAdd)
{
    addElement(buffer->list, symbol, placeToAdd);
    buffer->size++;
}

int sizeBuffer(Buffer *buffer)
{
    return buffer->size;
}

Queue *createQueue()
{
    Queue *queue = new Queue;
    queue->size = 0;
    queue->first = nullptr;
    queue->last = nullptr;
    return queue;
}

int sizeQueue(Queue *queue)
{
    return queue->size;
}

Node *createNode(char litera, Node *left, Node *right)
{
    Node *node = new Node;
    node->litera = litera;
    node->left = left;
    node->right = right;
    int leftVariable = (left == nullptr ? 0 : left->variable);
    int rightVariable = (right == nullptr ? 0 : right->variable);
    node->variable = (leftVariable + rightVariable == 0 ? 1 : leftVariable + rightVariable);
    return node;
}

ElementOfQueue *createElement(Node *node)
{
    ElementOfQueue *element = new ElementOfQueue;
    element->amount = node->variable;
    element->node = node;
    element->next = nullptr;
    return element;
}

bool isInQueue(Queue *queue, char litera)
{
    if (sizeQueue(queue) == 0 || litera == '\0')
        return false;

    bool inQueue = false;
    ElementOfQueue *temp = queue->first;
    while (temp != nullptr)
    {
        char current = temp->node->litera;
        if (current == litera)
            inQueue = true;
        temp = temp->next;
    }
    return inQueue;
}

void countVariable(Queue *queue, char litera)
{
    if (!isInQueue(queue, litera) || litera == '\0')
        return;

    ElementOfQueue *temp = queue->first;
    char current = temp->node->litera;
    while (temp->next != nullptr && current != litera)
    {
        temp = temp->next;
        current = temp->node->litera;
    }
    if (current == litera)
    {
        temp->node->variable++;
        temp->amount++;
    }
}

ElementOfQueue *searchPlace(Queue *queue, int variable)
{
    if (sizeQueue(queue) == 0)
        return nullptr;

    if (sizeQueue(queue) == 1)
    {
        int current = queue->first->amount;
        return (variable > current ? queue->first : nullptr);
    }

    if (queue->first->amount > variable)
        return nullptr;

    ElementOfQueue *temp = queue->first;
    int currentAmount = temp->next->amount;
    while (temp->next->next != nullptr && currentAmount < variable)
    {
        temp = temp->next;
        currentAmount = temp->next->amount;
    }
    return (currentAmount > variable ? temp : temp->next);
}

ElementOfQueue *getPrev(Queue *queue, char litera)
{
    if (sizeQueue(queue) == 0)
        return nullptr;

    ElementOfQueue *temp = queue->first;
    while (temp->next != nullptr && temp->next->node->litera != litera)
    {
        temp = temp->next;
    }
    if (litera == queue->first->node->litera || temp->next == nullptr)
        return nullptr;
    return temp;
}

ElementOfQueue *getElement(Queue *queue, char litera)
{
    if (sizeQueue(queue) == 0)
        return nullptr;

    ElementOfQueue *temp = queue->first;
    while (temp->node->litera != litera && temp != nullptr)
        temp = temp->next;
    return temp;
}

void afterCount(Queue *queue, char litera)
{
    if (sizeQueue(queue) == 0)
        return;

    ElementOfQueue *element = getElement(queue, litera);
    ElementOfQueue *prev = getPrev(queue, litera);

    if (element->next == nullptr)
        return;

    if (element == queue->first && element->amount > element->next->amount)
    {
        queue->first = element->next;
        element->next = queue->first->next;
        queue->first->next = element;
        prev = queue->first;
    }

    if (element->next == nullptr)
    {
        queue->last = element;
        return;
    }

    while (element->amount > element->next->amount && element->next->next != nullptr)
    {
        prev->next = element->next;
        element->next = element->next->next;
        prev->next->next = element;
        prev = prev->next;
    }
    if (element->amount > element->next->amount)
    {
        prev->next = element->next;
        element->next = element->next->next;
        prev->next->next = element;
    }
    if (element->next == nullptr)
        queue->last = element;
}

void addElementToQueue(Queue *queue, char litera, Node *left, Node *right)
{
    if (isInQueue(queue, litera))
    {
        countVariable(queue, litera);
        afterCount(queue, litera);
        return;
    }

    Node *node = createNode(litera, left, right);

    if (sizeQueue(queue) == 0)
    {
        queue->first = createElement(node);
        queue->last = queue->first;
        queue->size++;
        return;
    }

    ElementOfQueue *place = searchPlace(queue, node->variable);
    if (place == nullptr)
    {
        ElementOfQueue *toAdd = createElement(node);
        toAdd->next = queue->first;
        queue->first = toAdd;
        queue->size++;
        return;
    }

    ElementOfQueue *toAdd = createElement(node);
    toAdd->next = place->next;
    place->next = toAdd;
    if (toAdd->next == nullptr)
        queue->last = toAdd;
    queue->size++;
}

Node *getFirst(Queue *queue)
{
    if (sizeQueue(queue) == 0)
        return nullptr;

    ElementOfQueue *toDelete = queue->first;
    queue->first = queue->first->next;
    if (sizeQueue(queue) == 1)
        queue->last = nullptr;
    queue->size--;
    Node *toReturn = toDelete->node;
    toDelete->next = nullptr;
    delete toDelete;
    toDelete = nullptr;
    return toReturn;
}

void makeTreeElement(Queue *queue)
{
    if (sizeQueue(queue) < 2)
        return;

    Node *left = getFirst(queue);
    Node *right = getFirst(queue);
    addElementToQueue(queue, '\0', left, right);
}

void orderWithVariables(Node *node)
{
    if (node == nullptr)
        return;

    orderWithVariables(node->left);
    orderWithVariables(node->right);
    if (node->left == nullptr && node->right == nullptr)
    {
        cout << node->litera << " - " << node->variable << endl;
    }
}

void preorder(Node *node, ofstream &outFile)
{
    if (node == nullptr)
        return;

    if (node->left == nullptr)
    {
        outFile << node->litera << " ";
    }
    if (node->left != nullptr)
        outFile << "(";
    preorder(node->left, outFile);
    preorder(node->right, outFile);
    if (node->right != nullptr)
        outFile << ")";
}

void preorder(Queue *queue, ofstream &outFile)
{
    preorder(queue->first->node, outFile);
}

void orderWithVariables(Queue *queue)
{
    cout << "Table of variables." << endl;
    return orderWithVariables(queue->first->node);
}

void clearNode(Node *node)
{
    if (node == nullptr)
        return;

    if (node->right == nullptr && node->left == nullptr)
    {
        delete node;
        node = nullptr;
        return;
    }
    if (node->right != nullptr)
    {
        clearNode(node->right);
        node->right = nullptr;
    }
    if (node->left != nullptr)
    {
        clearNode(node->left);
        node->left = nullptr;
    }
}

void clearAndDeleteElement(ElementOfQueue *element)
{
    if (element == nullptr)
        return;

    clearNode(element->node);
    element->node = nullptr;
    element->next = nullptr;
    delete element;
    element = nullptr;
}

void clearQueue(Queue *queue)
{
    if (sizeQueue(queue) == 0)
        return;

    ElementOfQueue *toDelete = queue->first;
    queue->first = queue->first->next;
    clearAndDeleteElement(toDelete);
    toDelete = nullptr;
    if (sizeQueue(queue) == 1)
        queue->last = nullptr;
    queue->size--;
}

void deleteQueue(Queue *queue)
{
    clearQueue(queue);
    queue->first = nullptr;
    queue->last = nullptr;
    delete queue;
    queue = nullptr;
}

bool isThisSymbol(char symbol, Node *node)
{
    if (node == nullptr)
        return false;

    return node->litera == symbol;
}

bool isNotThisSymbol(char symbol, Node *node)
{
    if (node == nullptr)
        return false;

    return node->litera != symbol && node->litera != '\0';
}

Node *getSymbolFromTree(char symbol, Node *node)
{
    if (isNotThisSymbol(symbol, node))
        return nullptr;

    if (isThisSymbol(symbol, node))
        return node;

    Node *toReturn = getSymbolFromTree(symbol, node->left);
    if (toReturn == nullptr)
        toReturn = getSymbolFromTree(symbol, node->right);
    return toReturn;
}

Node *getSymbolFromTree(char symbol, Queue *queue)
{
    return getSymbolFromTree(symbol, queue->first->node);
}

Node *getParent(Node *node, Node *current)
{
    if (current->left == node || current->right == node)
        return current;

    if (current->left == nullptr && current->right == nullptr)
        return nullptr;

    Node *toReturn = nullptr;

    if (current->left != nullptr)
        toReturn = getParent(node, current->left);

    if (toReturn == nullptr && current->right != nullptr)
        toReturn = getParent(node, current->right);

    return toReturn;
}

Node *getParent(Node *node, Queue *queue)
{
    return getParent(node, queue->first->node);
}

void addition(List *codeNumber, Node *symbol, Node *parent)
{
    if (parent->left == symbol)
    {
        addElement(codeNumber, '0', 1);
    }
    if (parent->right == symbol)
    {
        addElement(codeNumber, '1', 1);
    }
}

void code(Queue *queue, Buffer *buffer, ofstream &out)
{
    out << endl;
    int i = 1;
    while (i <= sizeBuffer(buffer))
    {
        char current = getSymbol(buffer, i);
        Node *symbol = getSymbolFromTree(current, queue);
        Node *parent = getParent(symbol, queue);
        List *codeNumber = createList();
        while (parent != queue->first->node)
        {
            addition(codeNumber, symbol, parent);
            symbol = parent;
            parent = getParent(symbol, queue);
        }
        addition(codeNumber, symbol, parent);
        printListToFile(codeNumber, out);
        clearAndDeleteList(codeNumber);
        i++;
    }
}
