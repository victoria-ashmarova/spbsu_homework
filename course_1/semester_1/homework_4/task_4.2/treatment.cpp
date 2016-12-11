#include <stdio.h>
#include "treatment.h"

struct QueueElement
{
    char symbol;
    QueueElement *next;
};

struct Queue
{
    int size;
    QueueElement *first;
    QueueElement *last;
};

Queue *createQueue()
{
    Queue *word = new Queue;
    word->size = 0;
    word->first = nullptr;
    word->last = nullptr;
    return word;
}

void clearQueue(Queue *word)
{
    while (!isEmpty(word))
    {
        deleteElement(word);
    }
}

void deleteQueue(Queue *word)
{
    clearQueue(word);
    word->first = nullptr;
    word->last = nullptr;
    delete word;
}

int sizeQueue(Queue *word)
{
    return word->size;
}

bool isEmpty(Queue *word)
{
    return sizeQueue(word) == 0;
}

QueueElement *createElement(char symbol)
{
    QueueElement *newSymbol = new QueueElement;
    newSymbol->symbol = symbol;
    newSymbol->next = nullptr;
    return newSymbol;
}

void add(Queue *word, char symbol)
{
    if (isEmpty(word))
    {
        word->first = createElement(symbol);
        word->last = word->first;
        word->size++;
        return;
    }

    word->last->next = createElement(symbol);
    word->last = word->last->next;
    word->size++;
}

void deleteElement(Queue *word)
{
    QueueElement *toDelete = word->first;
    word->first = word->first->next;
    toDelete->next = nullptr;
    delete toDelete;
    word->size--;
    if (isEmpty(word))
    {
        word->last = nullptr;
    }
}

bool isInQueue(Queue *word, char symbol)
{
    if (isEmpty(word))
        return false;

    QueueElement *temp = word->first;
    char current = temp->symbol;
    while (temp != nullptr && current != symbol)
    {
        current = temp->symbol;
        temp = temp->next;
    }
    return current == symbol;
}

void printQueue(Queue *word)
{
    QueueElement *temp = word->first;
    while (temp != nullptr)
    {
        char toPrint = temp->symbol;
        printf("%c", toPrint);
        temp = temp->next;
    }
}

int lenghtLine(char buffer[])
{
    int i = 0;
    while (buffer[i] != '\0')
    {
        i++;
    }
    return i;
}

void treatment(char buffer[])
{
    Queue *word = createQueue();
    for (int i = 0; i < lenghtLine(buffer); i++)
    {
        if (!isInQueue(word, buffer[i]))
        {
            add(word, buffer[i]);
        }
        if (buffer[i] == ' ' || i == lenghtLine(buffer) - 1)
        {
            printQueue(word);
            clearQueue(word);
        }
    }
    deleteQueue(word);
}

