#pragma once

struct Queue;

Queue *createQueue();
void addElement(Queue *queue, char symbol);
void removeElement(Queue *queue);
int sizeOfQueue(Queue *queue);
bool isEmpty(Queue *queue);
void printQueue(Queue *queue);
void clearQueue(Queue *queue);
void deleteQueue(Queue *queue);
