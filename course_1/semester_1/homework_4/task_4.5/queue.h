#pragma once

struct Queue;

Queue *createQueue();
void clearQueue(Queue *queue);
void deleteQueue(Queue *queue);

int sizeQueue(Queue *queue);
bool isEmptyQueue(Queue *queue);

void addElement(Queue *queue, char symbol);
void deleteElement(Queue *queue);

void printQueue(Queue *queue);

char firstValue(Queue *queue);
