#include <iostream>
#include <limits.h>
#include "queue.h"

struct QueueElement
{
    int value;
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

QueueElement *createElement(int value)
{
    QueueElement *newSymbol = new QueueElement;
    newSymbol->value = value;
    newSymbol->next = nullptr;
    return newSymbol;
}

void addElement(Queue *queue, int value)
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

int getFirst(Queue *queue)
{
    if (isEmptyQueue(queue))
        return INT_MIN;

    int toReturn = queue->first->value;
    deleteElement(queue);
    return toReturn;
}
