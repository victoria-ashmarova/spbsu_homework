#include <stdio.h>
#include "queue.h"

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
    Queue *queue = new Queue;
    queue->first = nullptr;
    queue->last = nullptr;
    queue->size = 0;
    return queue;
}

QueueElement *createElement(char symbol)
{
    QueueElement *newElement = new QueueElement;
    newElement->symbol = symbol;
    newElement->next = nullptr;
    return newElement;
}

void addElement(Queue *queue, char symbol)
{
    if (isEmpty(queue))
    {
        queue->first = createElement(symbol);
        queue->last = queue->first;
        queue->size++;
        return;
    }

    queue->last->next = createElement(symbol);
    queue->last = queue->last->next;
    queue->size++;
}

void removeElement(Queue *queue)
{
    if (isEmpty(queue))
        return;

    if (sizeOfQueue(queue) == 1)
    {
        QueueElement *toDelete = queue->first;
        queue->first = nullptr;
        queue->last = nullptr;
        queue->size--;
        toDelete->next = nullptr;
        delete toDelete;
        return;
    }

    QueueElement *toDelete = queue->first;
    queue->first = queue->first->next;
    toDelete->next = nullptr;
    delete toDelete;
    queue->size--;
}

int sizeOfQueue(Queue *queue)
{
    return queue->size;
}

bool isEmpty(Queue *queue)
{
    return sizeOfQueue(queue) == 0;
}

void printQueue(Queue *queue)
{
    if (isEmpty(queue))
        return;

    QueueElement *temp = queue->first;
    while (temp != nullptr)
    {
        char toPrint = temp->symbol;
        printf("%c", toPrint);
        temp = temp->next;
    }
}

void clearQueue(Queue *queue)
{
    while (!isEmpty(queue))
    {
        removeElement(queue);
    }
}

void deleteQueue(Queue *queue)
{
    clearQueue(queue);
    delete queue;
}
