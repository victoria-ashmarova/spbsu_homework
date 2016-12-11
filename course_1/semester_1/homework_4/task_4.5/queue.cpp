#include <stdio.h>
#include "queue.h"

struct QueueElement
{
    char value;
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
    Queue *queue = new Queue;
    queue->size = 0;
    queue->first = nullptr;
    queue->last = nullptr;
    return queue;
}

void clearQueue(Queue *queue)
{
    while (!isEmptyQueue(queue))
    {
        deleteElement(queue);
    }
}

void deleteQueue(Queue *queue)
{
    clearQueue(queue);
    queue->first = nullptr;
    queue->last = nullptr;
    delete queue;
}

int sizeQueue(Queue *queue)
{
    return queue->size;
}

bool isEmptyQueue(Queue *queue)
{
    return sizeQueue(queue) == 0;
}

QueueElement *createElement(char value)
{
    QueueElement *newSymbol = new QueueElement;
    newSymbol->value = value;
    newSymbol->next = nullptr;
    return newSymbol;
}

void addElement(Queue *queue, char value)
{
    if (isEmptyQueue(queue))
    {
        queue->first = createElement(value);
        queue->last = queue->first;
        queue->size++;
        return;
    }

    queue->last->next = createElement(value);
    queue->last = queue->last->next;
    queue->size++;
}

void deleteElement(Queue *queue)
{
    QueueElement *toDelete = queue->first;
    queue->first = queue->first->next;
    toDelete->next = nullptr;
    delete toDelete;
    queue->size--;
    if (isEmptyQueue(queue))
    {
        queue->last = nullptr;
    }
}

void printQueue(Queue *queue)
{
    QueueElement *temp = queue->first;
    while (temp != nullptr)
    {
        char toPrint = temp->value;
        printf("%c", toPrint);
        temp = temp->next;
    }
}

char firstValue(Queue *queue)
{
    int toReturn = queue->first->value;
    deleteElement(queue);
    return toReturn;
}
