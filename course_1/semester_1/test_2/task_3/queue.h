#pragma once

struct Queue;

Queue *createQueue();
void clearQueue(Queue *queue);
void deleteQueue(Queue *queue);

int sizeQueue(Queue *queue);
bool isEmptyQueue(Queue *queue);

void addElement(Queue *queue, int value);
void deleteElement(Queue *queue);

int getFirst(Queue *queue);
