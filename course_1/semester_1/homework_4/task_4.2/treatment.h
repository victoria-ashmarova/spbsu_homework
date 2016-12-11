#pragma once

struct Queue;

Queue *createQueue();
void clearQueue(Queue *word);
void deleteQueue(Queue *word);

int sizeQueue(Queue *word);
bool isEmpty(Queue *word);

void add(Queue *word, char symbol);
void deleteElement(Queue *word);

bool isInQueue(Queue *word, char symbol);

void printQueue(Queue *word);

int lenghtLine(char buffer[]);
void treatment(char buffer[]);
